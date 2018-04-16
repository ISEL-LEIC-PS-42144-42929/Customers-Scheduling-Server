package com.customersscheduling.DTO;

public class TimetableDto {

    private double openHour;
    private double closeHour;
    private double breakInit;
    private double breakFinish;
    private boolean breakDay;
    private int id;

    public TimetableDto(double openHour, double closeHour, double breakInit, double breakFinish, boolean breakDay, int id) {
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.breakInit = breakInit;
        this.breakFinish = breakFinish;
        this.breakDay = breakDay;
        this.id = id;
    }

    public void setOpenHour(double openHour) {
        this.openHour = openHour;
    }

    public void setCloseHour(double closeHour) {
        this.closeHour = closeHour;
    }

    public void setBreakInit(double breakInit) {
        this.breakInit = breakInit;
    }

    public void setBreakFinish(double breakFinish) {
        this.breakFinish = breakFinish;
    }

    public boolean isBreakDay() {
        return breakDay;
    }

    public void setBreakDay(boolean breakDay) {
        this.breakDay = breakDay;
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

    public double getCloseHour() {
        return closeHour;
    }

    public double getBreakInit() {
        return breakInit;
    }

    public double getBreakFinish() {
        return breakFinish;
    }

}
