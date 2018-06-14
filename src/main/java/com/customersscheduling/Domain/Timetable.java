package com.customersscheduling.Domain;

import javax.persistence.*;

@Entity
@Table( name = "timetable")
public class Timetable {

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
    @Column(name="weekday")
    private String weekDay;

    public Timetable() {
    }

    public Timetable(double openHour, double closeHour, double initBreak, double finishBreak, String weekDay) {
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.initBreak = initBreak;
        this.finishBreak = finishBreak;
        this.weekDay = weekDay;
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

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }
}
