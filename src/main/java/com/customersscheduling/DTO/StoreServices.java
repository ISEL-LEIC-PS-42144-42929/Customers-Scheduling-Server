package com.customersscheduling.DTO;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "services_has_staff")
public class StoreServices {

    @EmbeddedId
    private StoreServicesPK pk;

    public StoreServices(){}

    public StoreServices(StoreServicesPK pk){
        this.pk=pk;
    }
}
