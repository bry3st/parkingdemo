package com.demo.parking.models;

import javax.persistence.*;

@Entity
@Table(name="parking_slots")
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String parkName;
    private Integer coordinate_x;
    private Integer coordinate_y;
    private Integer size_type;
    private Boolean parked;

    public ParkingSlot() {
    }

    public ParkingSlot(Long id, String parkName, Integer coordinate_x, Integer coordinate_y, Integer size_type, Boolean parked) {

        this.id = id;
        this.parkName = parkName;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
        this.size_type = size_type;
        this.parked = parked;
    }

    public ParkingSlot(String parkName, Integer coordinate_x, Integer coordinate_y, Integer size_type, Boolean parked) {
        this.parkName = parkName;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
        this.size_type = size_type;
        this.parked = parked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "park_name", nullable = false)
    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {

        this.parkName = parkName;
    }

    @Column(name = "coordinate_x", nullable = false)
    public Integer getCoordinate_x() {

        return coordinate_x;
    }

    public void setCoordinate_x(Integer coordinate_x) {

        this.coordinate_x = coordinate_x;
    }

    @Column(name = "coordinate_y", nullable = false)
    public Integer getCoordinate_y() {

        return coordinate_y;
    }

    public void setCoordinate_y(Integer coordinate_y) {

        this.coordinate_y = coordinate_y;
    }

    @Column(name = "size_type", nullable = false)
    public Integer getSize_type() {

        return size_type;
    }

    public void setSize_type(Integer size_type) {
        this.size_type = size_type;
    }

    @Column(name = "parked", nullable = false)
    public Boolean getParked() {

        return parked;
    }

    public void setParked(Boolean parked) {

        this.parked = parked;
    }

    public int computeDistance(int entry_x, int entry_y) {
        int difference_x = entry_x - this.coordinate_x;
        int difference_y = entry_y - this.coordinate_y;

        return  difference_x*difference_x+difference_y*difference_y;
    }
}
