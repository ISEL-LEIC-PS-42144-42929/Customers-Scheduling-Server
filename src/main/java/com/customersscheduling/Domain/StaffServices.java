package com.customersscheduling.Domain;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "staff_has_storeservices")
public class StaffServices {

    @EmbeddedId
    private StaffServicesPK pk;

    public StaffServices(StaffServicesPK pk) {
        this.pk = pk;
    }

    public StaffServicesPK getPk() {
        return pk;
    }

    public void setPk(StaffServicesPK pk) {
        this.pk = pk;
    }
}
