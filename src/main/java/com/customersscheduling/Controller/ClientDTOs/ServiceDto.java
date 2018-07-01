package com.customersscheduling.Controller.ClientDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceDto {

    public int id;
    public String description;
    public String title;
    public double price;
    public int duration;

    public ServiceDto(){}
}
