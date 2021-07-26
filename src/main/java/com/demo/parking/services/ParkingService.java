package com.demo.parking.services;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//models
import com.demo.parking.jpaRepositories.*;
import com.demo.parking.models.EntryPoint;
import com.demo.parking.models.Logs;
import com.demo.parking.models.ParkingSlot;
import com.demo.parking.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ParkingService {

    private final ParkingSlotRepository parkingSlotRepository;
    private final EntryPointRepository entryPointRepository;
    private final ParkingLogRepository parkingLogRepository;
    private final TicketRepository ticketRepository;
    private String vehicle_types;

    @Autowired
    public ParkingService(ParkingSlotRepository parkingSlotRepository, EntryPointRepository entryPointRepository, ParkingLogRepository parkingLogRepository, TicketRepository ticketRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
        this.entryPointRepository = entryPointRepository;
        this.parkingLogRepository = parkingLogRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<ParkingSlot> getParkingSlots(){

        return parkingSlotRepository.findAll();
    }

    public List<EntryPoint> getEntryPoints(){
        return entryPointRepository.findAll();
    }

    public void addEntryPoint(EntryPoint entryPoint){
        Optional<EntryPoint> EntryPointFindName = entryPointRepository.findExistingName(entryPoint.getEntryPointName());
        if(EntryPointFindName.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry name already exists.");
        }else{
            entryPointRepository.save(entryPoint);
        }
    }

    public Long getEntryPoint(int entry_x, int entry_y){
        return entryPointRepository.findByCoordinate_xAndCoordinate_y(entry_x,entry_y);
    }

    public Long checkAvailableParkingSlot(int vehicle_type, int entry_x, int entry_y){
        List<ParkingSlot> parking_slots =  parkingSlotRepository.findByParkedFalse();
        if(parking_slots.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No available parking slot.");
        }else{
            Integer closestDistance = null;
            Long parkingSlotId = null;
            for (int i = 0; i < parking_slots.size(); i++) {
                if(parking_slots.get(i).getSize_type() >= vehicle_type){
                    if(closestDistance == null){
                        closestDistance = parking_slots.get(i).computeDistance(entry_x,entry_y);
                        parkingSlotId = parking_slots.get(i).getId();
                    }else if(parking_slots.get(i).computeDistance(entry_x,entry_y) < closestDistance){
                        closestDistance = parking_slots.get(i).computeDistance(entry_x,entry_y);
                        parkingSlotId = parking_slots.get(i).getId();
                    }
                }
            }
            if(parkingSlotId == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No available parking slot.");
            }else{
                return parkingSlotId;
            }

        }
    }

    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public ParkingSlot findParkingSlot(Long parkingSlotId) {
        return parkingSlotRepository.findById(parkingSlotId).get();
    }

    public void addParkingLog(Logs log) {
        parkingLogRepository.save(log);
    }

    public void updateParkingSlot(Long parkingSlotId, Boolean parked) {
        Optional<ParkingSlot> parkingSlot = parkingSlotRepository.findById(parkingSlotId);
        ParkingSlot updateParkingSlot = parkingSlot.get();
        updateParkingSlot.setId(parkingSlotId);
        updateParkingSlot.setParked(parked);
        parkingSlotRepository.save(updateParkingSlot);
    }

    public Ticket findTicket(String transaction_id) {
        return ticketRepository.findByTransaction_id(transaction_id);
    }

    public Logs findParkingLog(Long ticket_id) {
        return parkingLogRepository.findByTicket_id(ticket_id);
    }

    public Ticket updateTicket(Long id, Integer park_type, Long ticketId, Logs parkingLog, LocalDateTime datetime) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        Ticket updateTicket = ticket.get();

        Duration duration = Duration.between(updateTicket.getTimestamp(), datetime);
        double seconds = duration.getSeconds();
        double hours = seconds / (60 * 60);
        double totalHours = Math.ceil(hours);

        int[] vehiclePricing = {20, 60, 100};
        double totalAmount = 0;
        if((totalHours/24) > 1){
            int intPart = (int) (totalHours/24);
            totalAmount += ((double)intPart)*5000;
            double remaining = totalHours%24;
            totalAmount += (((remaining-3)*vehiclePricing[(park_type-1)]) + 40);
        }else{
            if(totalHours < 3){
                totalAmount += 40;
            }else{
                totalAmount += 40 + ((totalHours-3)*vehiclePricing[(park_type-1)]);
            }
        }
        updateTicket.setId(id);
        updateTicket.setCharge_amount(totalAmount);
        updateTicket.setCharge_hours(totalHours);
        updateTicket.setTimestamp(datetime);
        return ticketRepository.save(updateTicket);
    }
}
