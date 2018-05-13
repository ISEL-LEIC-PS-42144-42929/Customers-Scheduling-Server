package com.customersscheduling.DTO;

public class Service {
    private String description;
    private int price;
    private double duration;

    public Service(String description, int price, double duration) {
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
