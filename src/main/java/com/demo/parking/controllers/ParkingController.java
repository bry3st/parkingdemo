package com.demo.parking.controllers;

import com.demo.parking.models.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

//Models

//Services
import com.demo.parking.services.ParkingService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/parking")
public class ParkingController {

    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService){
        this.parkingService = parkingService;
        //this.parkingService = new ParkingService();
    }

    @GetMapping("getParkingSlots")
    public List<ParkingSlot> getParkingSlots(){
        return parkingService.getParkingSlots();
    }

    @GetMapping("getEntryPoints")
    public List<EntryPoint> getEntryPoints(){
        return parkingService.getEntryPoints();
    }

    @PostMapping("registerEntryPoint")
    public void registerEntryPoint(@RequestBody @Valid EntryPoint entrypoint){
        parkingService.addEntryPoint(entrypoint);
    }

    @PostMapping("park")
    public Ticket park(@RequestBody @Valid Parking parking ){
        LocalDateTime timestamp = LocalDateTime.now();
        Long parkingSlotId = parkingService.checkAvailableParkingSlot(parking.getVehicle_type(), parking.getEntry_x(), parking.getEntry_y());
        ParkingSlot parkingSlot = parkingService.findParkingSlot(parkingSlotId);
        Long entryPointId = parkingService.getEntryPoint(parking.getEntry_x(), parking.getEntry_y());
        Ticket ticket = new Ticket(parkingSlot.getSize_type(), parking.getVehicle_plate_number(), parking.getVehicle_type(), timestamp);
        Ticket savedTicket = parkingService.addTicket(ticket);
        Logs log = new Logs(parkingSlotId, entryPointId, savedTicket.getId(), parking.getVehicle_type(), parking.getVehicle_plate_number(), "Park", timestamp);
        parkingService.addParkingLog(log);
        parkingService.updateParkingSlot(parkingSlotId, Boolean.TRUE);
        return savedTicket;
    }

    @PostMapping("unpark")
    public Ticket unpark(@RequestBody @Valid Parking parking){
//        LocalDateTime timestamp = LocalDateTime.now();
        Ticket savedTicket = parkingService.findTicket(parking.getTransaction_id());
        Logs parkingLog = parkingService.findParkingLog(savedTicket.getId());
        Logs log = new Logs(parkingLog.getParking_slot_id(), parkingLog.getEntry_point_id(), savedTicket.getId(), parkingLog.getVehicle_type(), parkingLog.getVehicle_plate_number(), "Unpark", parking.getDatetime());
        parkingService.addParkingLog(log);
        parkingService.updateParkingSlot(parkingLog.getParking_slot_id(), Boolean.FALSE);
        return parkingService.updateTicket(savedTicket.getId(), savedTicket.getPark_type() ,savedTicket.getId(), parkingLog, parking.getDatetime());
    }
}
