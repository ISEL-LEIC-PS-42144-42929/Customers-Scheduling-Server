package com.customersscheduling.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class StaffServicesPK implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="staff_person_email",referencedColumnName="person_email",nullable=false)
    private Staff staff;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns( {
            @JoinColumn(name = "storeservices_services_idservices", referencedColumnName = "services_idservices"),
            @JoinColumn(name = "storeservices_store_nif", referencedColumnName = "store_nif")
    })
    private StoreServices storesServices;

    public StaffServicesPK(){}

    public StaffServicesPK(Staff staff, StoreServices storesServices) {
        this.staff = staff;
        this.storesServices = storesServices;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public StoreServices getStoresServices() {
        return storesServices;
    }

    public void setStoresServices(StoreServices storesServices) {
        this.storesServices = storesServices;
    }
}
