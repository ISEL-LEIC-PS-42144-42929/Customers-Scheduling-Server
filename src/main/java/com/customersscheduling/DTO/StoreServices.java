package com.customersscheduling.DTO;

import javax.persistence.*;

@Entity
@Table( name = "services_has_staff")
public class StoreServices {

    @Id
    @Column(name="idstoreservices")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="store_nif",referencedColumnName="nif",nullable=false)
    private Store store;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="staff_person_email",referencedColumnName="person_email",nullable=false)
    private Staff staff;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="services_idservices",referencedColumnName="idservices",nullable=false)
    private Service service;

    public StoreServices(Store store, Staff staff, Service service){
        this.store = store;
        this.staff = staff;
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
