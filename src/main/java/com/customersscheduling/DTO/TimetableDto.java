package com.customersscheduling.DTO;

import javax.persistence.*;

@Entity
@Table( name = "TimeTable")
public class TimetableDto {

    @Id
    @Column(name="IdTimeTable")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="openHour")
    private double openHour;
    @Column(name="closeHour")
    private double closeHour;
    @Column(name="initBreak")
    private double initBreak;
    @Column(name="finishBreak")
    private double finishBreak;


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



}
