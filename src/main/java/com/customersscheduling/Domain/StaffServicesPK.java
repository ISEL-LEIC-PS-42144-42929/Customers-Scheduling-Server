package com.customersscheduling.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class StaffServicesPK implements Serializable {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="staff_person_email",referencedColumnName="person_email",nullable=false, insertable = false, updatable = false)
    private Staff staff;

    @ManyToOne
    @JoinColumns( {
            @JoinColumn(name = "storeservices_services_idservices", referencedColumnName = "services_idservices", insertable = false, updatable = false),
            @JoinColumn(name = "storeservices_store_nif", referencedColumnName = "store_nif", insertable = false, updatable = false)
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
