package com.customersscheduling.DTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "timetable")
public class TimetableDto {

    @Id
    @Column(name="idtimetable")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="openhour")
    private double openHour;
    @Column(name="closehour")
    private double closeHour;
    @Column(name="initbreak")
    private double initBreak;
    @Column(name="finishbreak")
    private double finishBreak;

    //Staff N-N
    @ManyToMany(mappedBy = "timetable", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<StaffDto> staff = new HashSet<>();


    public TimetableDto(double openHour, double closeHour, double initBreak, double finishBreak) {
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.initBreak = initBreak;
        this.finishBreak = finishBreak;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getOpenHour() {
        return openHour;
    }

    public void setOpenHour(double openHour) {
        this.openHour = openHour;
    }

    public double getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(double closeHour) {
        this.closeHour = closeHour;
    }

    public double getInitBreak() {
        return initBreak;
    }

    public void setInitBreak(double initBreak) {
        this.initBreak = initBreak;
    }

    public double getFinishBreak() {
        return finishBreak;
    }

    public void setFinishBreak(double finishBreak) {
        this.finishBreak = finishBreak;
    }


    public Set<StaffDto> getStaff() {
        return staff;
    }

    public void setStaff(Set<StaffDto> staff) {
        this.staff = staff;
    }
}
