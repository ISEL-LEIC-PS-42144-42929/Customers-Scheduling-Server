package com.customersscheduling.Controller.InputModels;

import com.customersscheduling.DTO.Service;

public class ServiceInputModel {
    public String description;
    public double price;
    public String title;
    public int duration;

    public Service toDto() {
        return new Service(description, title, price, duration);
    }
}
