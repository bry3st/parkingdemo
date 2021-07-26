package com.demo.parking.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "entry_points")
public class EntryPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String entryPointName;

    @NotNull
    private Integer coordinate_x;

    @NotNull
    private Integer coordinate_y;

    public EntryPoint() {
    }

    public EntryPoint(Long id, String entryPointName, Integer coordinate_x, Integer coordinate_y) {
        this.id = id;
        this.entryPointName = entryPointName;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
    }

    public EntryPoint(String entryPointName, Integer coordinate_x, Integer coordinate_y) {
        this.entryPointName = entryPointName;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
    }

    @Column(name = "entry_point_name", nullable = false)
    public String getEntryPointName() {
        return entryPointName;
    }

    public void setEntryPointName(String entryPointName) {
        this.entryPointName = entryPointName;
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
}
