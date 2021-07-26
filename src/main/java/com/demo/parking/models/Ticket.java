package com.demo.parking.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.bytebuddy.utility.RandomString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String transaction_id;
    private Integer vehicle_type;
    private String vehicle_plate_number;
    private Integer park_type;
    private Double charge_hours;
    private Double charge_amount;
    private LocalDateTime timestamp;

    public Ticket() {
    }

    public Ticket(Long id, String transaction_id, Integer vehicle_type, String vehicle_plate_number, Integer park_type, Double charge_hours, Double charge_amount, LocalDateTime timestamp) {
        this.id = id;
        this.transaction_id = transaction_id;
        this.vehicle_type = vehicle_type;
        this.vehicle_plate_number = vehicle_plate_number;
        this.park_type = park_type;
        this.charge_hours = charge_hours;
        this.charge_amount = charge_amount;
        this.timestamp = timestamp;
    }

    public Ticket(String transaction_id, Integer vehicle_type, String vehicle_plate_number, Integer park_type, Double charge_hours, Double charge_amount, LocalDateTime timestamp) {
        this.transaction_id = transaction_id;
        this.vehicle_type = vehicle_type;
        this.vehicle_plate_number = vehicle_plate_number;
        this.park_type = park_type;
        this.charge_hours = charge_hours;
        this.charge_amount = charge_amount;
        this.timestamp = timestamp;
    }

    public Ticket(Integer vehicle_type, String vehicle_plate_number, Integer park_type, LocalDateTime timestamp) {
        this.transaction_id = RandomString.make(10);
        this.vehicle_type = vehicle_type;
        this.vehicle_plate_number = vehicle_plate_number;
        this.park_type = park_type;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
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

    public Double getCharge_hours() {
        return charge_hours;
    }

    public void setCharge_hours(Double charge_hours) {
        this.charge_hours = charge_hours;
    }

    public Double getCharge_amount() {
        return charge_amount;
    }

    public void setCharge_amount(Double charge_amount) {
        this.charge_amount = charge_amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
