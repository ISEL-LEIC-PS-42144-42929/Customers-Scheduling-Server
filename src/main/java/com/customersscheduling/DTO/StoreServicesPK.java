package com.customersscheduling.DTO;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
public class StoreServicesPK implements Serializable {

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinColumns( {
            @JoinColumn(name="store_store_name",referencedColumnName="store_name",nullable=false),
            @JoinColumn(name="store_business_nif",referencedColumnName="business_nif",nullable=false),
    })
    private Store store;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinColumn(name="staff_person_email",referencedColumnName="person_email",nullable=false)
    private Staff staff;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinColumn(name="services_idservices",referencedColumnName="idservices",nullable=false)
    private Service service;

    public StoreServicesPK() {
    }

    public StoreServicesPK(Store store, Staff staff, Service service) {
        this.store = store;
        this.staff = staff;
        this.service = service;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
