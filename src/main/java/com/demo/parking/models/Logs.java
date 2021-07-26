package com.demo.parking.models;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_logs")
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Long parking_slot_id;
    private Long entry_point_id;
    private Long ticket_id;
    private Integer vehicle_type;
    private String vehicle_plate_number;
    private String status;
    private LocalDateTime timestamp;

    public Logs() {
    }

    public Logs(Long id, Long parking_slot_id, Long entry_point_id, Long ticket_id, Integer vehicle_type, String vehicle_plate_number, String status, LocalDateTime timestamp) {
        Id = id;
        this.parking_slot_id = parking_slot_id;
        this.entry_point_id = entry_point_id;
        this.ticket_id = ticket_id;
        this.vehicle_type = vehicle_type;
        this.vehicle_plate_number = vehicle_plate_number;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Logs(Long parking_slot_id, Long entry_point_id, Long ticket_id, Integer vehicle_type, String vehicle_plate_number, String status, LocalDateTime timestamp) {
        this.parking_slot_id = parking_slot_id;
        this.entry_point_id = entry_point_id;
        this.ticket_id = ticket_id;
        this.vehicle_type = vehicle_type;
        this.vehicle_plate_number = vehicle_plate_number;
        this.status = status;
        this.timestamp = timestamp;
    }



    public Long getParking_slot_id() {
        return parking_slot_id;
    }

    public void setParking_slot_id(Long parking_slot_id) {
        this.parking_slot_id = parking_slot_id;
    }

    public Long getEntry_point_id() {
        return entry_point_id;
    }

    public void setEntry_point_id(Long entry_point_id) {
        this.entry_point_id = entry_point_id;
    }

    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Integer getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(Integer vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getVehicle_plate_number() {
        return vehicle_plate_number;
    }

    public void setVehicle_plate_number(String vehicle_plate_number) {
        this.vehicle_plate_number = vehicle_plate_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
