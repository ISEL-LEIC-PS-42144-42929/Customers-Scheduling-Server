package com.customersscheduling.DTO;

import javax.persistence.*;

@Entity
@Table( name = "storeservices")
public class StoreServices {

    @EmbeddedId
    private StoreServicesPK pk;

    public StoreServices(){}

    public StoreServices(StoreServicesPK pk){

        this.pk = pk;
    }

    public StoreServicesPK getPk() {
        return pk;
    }

    public void setPk(StoreServicesPK pk) {
        this.pk = pk;
    }
}
