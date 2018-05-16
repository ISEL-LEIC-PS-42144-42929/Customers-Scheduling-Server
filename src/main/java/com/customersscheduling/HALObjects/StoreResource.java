package com.customersscheduling.HALObjects;

import com.customersscheduling.Controller.BusinessController;
import com.customersscheduling.DTO.Store;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class StoreResource extends ResourceSupport {
 /*   private final Store store;

    public StoreResource(Store store) {
        this.store = store;
        final int business_nif = store.getPk().getBusiness().getNIF();
        final String name = store.getPk().getStoreName();
        add(linkTo(methodOn(BusinessController.class).getStore(name, business_nif)).withSelfRel());
        //add link to Business
        //add link to Services
        //add link to Portfolio
    }*/
}
