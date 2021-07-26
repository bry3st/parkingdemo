package com.demo.parking.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Parking {
    private Integer vehicle_type;
    private String vehicle_plate_number;
    private Integer park_type;
    private Integer entry_x;
    private Integer entry_y;
    private String transaction_id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datetime;

    public Parking() {
    }

    public Parking(Integer vehicle_type, String vehicle_plate_number, Integer park_type, Integer entry_x, Integer entry_y) {
        this.vehicle_type = vehicle_type;
        this.vehicle_plate_number = vehicle_plate_number;
        this.park_type = park_type;
        this.entry_x = entry_x;
        this.entry_y = entry_y;
    }

    public Parking(String transaction_id, LocalDateTime datetime) {
        this.transaction_id = transaction_id;
        this.datetime = datetime;
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

    public Integer getPark_type() {
        return park_type;
    }

    public void setPark_type(Integer park_type) {
        this.park_type = park_type;
    }

    public Integer getEntry_x() {
        return entry_x;
    }

    public void setEntry_x(Integer entry_x) {
        this.entry_x = entry_x;
    }

    public Integer getEntry_y() {
        return entry_y;
    }

    public void setEntry_y(Integer entry_y) {
        this.entry_y = entry_y;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public LocalDateTime getDatetime() {

        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}
