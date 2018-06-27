package com.customersscheduling.Domain;


import com.customersscheduling.HALObjects.ServiceResource;
import com.customersscheduling.HALObjects.StoreResource;

import javax.persistence.*;

@Entity
@Table( name = "services")
public class Service {

    @Id
    @Column(name="idservices")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="description")
    private String description;

    @Column(name="title")
    private String title;

    @Column(name="price")
    private double price;

    @Column(name="duration")
    private int duration;

    public Service(){}

    public Service(String description, String title, double price, int duration) {
        this.description = description;
        this.title = title;
        this.price = price;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ServiceResource toResource(StoreResource s) {
        return new ServiceResource(this, s);
    }
}
