package com.customersscheduling.HALObjects;

import com.customersscheduling.Controller.StoreController;
import com.customersscheduling.Domain.Service;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ServiceResource extends ResourceSupport {

    private Service service;
    private StoreResource store;

    public ServiceResource(Service service, StoreResource store) {
        this.service = service;
        this.store = store;

        add(linkTo(methodOn(StoreController.class).getServicesOfStore(store.getStore().getNif())).withRel("get_store_services"));
        add(linkTo(methodOn(StoreController.class).insertServiceForStore(null, store.getStore().getNif())).withRel("insert_store_service"));
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public StoreResource getStore() {
        return store;
    }

    public void setStore(StoreResource store) {
        this.store = store;
    }
}
