package com.customersscheduling.HALObjects;

import com.customersscheduling.Controller.StoreController;
import com.customersscheduling.Domain.Store;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class StoreResource extends ResourceSupport {

    private Store store;

    public StoreResource(Store store) {
        this.store = store;
        final String nif = store.getNif();
        final String name = store.getStoreName();
        add(linkTo(methodOn(StoreController.class).getStore(nif)).withRel("get"));
        add(linkTo(methodOn(StoreController.class).getServicesOfStore(nif)).withRel("services"));
        add(linkTo(methodOn(StoreController.class).getPortfolioOfStore(nif)).withRel("portfolio"));

    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
