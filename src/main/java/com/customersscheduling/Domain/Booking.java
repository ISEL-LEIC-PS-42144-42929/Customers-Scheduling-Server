package com.customersscheduling.Domain;

import com.customersscheduling.HALObjects.BookingResource;

import javax.persistence.*;

@Entity
@Table( name = "booking")
public class Booking {

    @Id
    @Column(name="idbooking")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="store_nif",referencedColumnName="nif",nullable=false)
    private Store store;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="staff_person_email",referencedColumnName="person_email",nullable=false)
    private Staff staff;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="client_user_person_email",referencedColumnName="user_person_email",nullable=false)
    private Client client;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="services_idservices",referencedColumnName="idservices",nullable=false)
    private Service service;

    public Booking(){}

    public Booking(Store store, Staff staff, Client client, Service service) {
        this.store = store;
        this.staff = staff;
        this.client = client;
        this.service = service;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public BookingResource toResource() {
        return new BookingResource(this);
    }
}
