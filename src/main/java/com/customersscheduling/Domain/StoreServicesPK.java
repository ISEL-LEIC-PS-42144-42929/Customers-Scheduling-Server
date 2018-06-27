package com.customersscheduling.Domain;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
public class StoreServicesPK implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="store_nif",referencedColumnName="nif",nullable=false)
    private Store store;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="services_idservices",referencedColumnName="idservices",nullable=false)
    private Service service;

    public StoreServicesPK() {
    }

    public StoreServicesPK(Store store, Service service) {
        this.store = store;
        this.service = service;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
