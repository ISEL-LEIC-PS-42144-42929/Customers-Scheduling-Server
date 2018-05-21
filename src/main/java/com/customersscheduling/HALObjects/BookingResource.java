package com.customersscheduling.HALObjects;

import com.customersscheduling.Controller.PersonController;
import com.customersscheduling.Controller.StoreController;
import com.customersscheduling.Controller.UtilController;
import com.customersscheduling.DTO.Booking;
import com.customersscheduling.DTO.Client;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class BookingResource extends ResourceSupport {
    private final Booking book;

    public BookingResource(Booking book) {
        this.book = book;
        add(linkTo(methodOn(StoreController.class).getDispOfService(book.getStore().getNif(), book.getService().getId())).withSelfRel());
        add(linkTo(methodOn(PersonController.class).getClient(book.getClient().getEmail())).withRel("client"));
        add(linkTo(methodOn(PersonController.class).getStaff(book.getClient().getEmail())).withRel("staff"));
        add(linkTo(methodOn(UtilController.class).getService(book.getService().getId())).withRel("service"));
        add(linkTo(methodOn(StoreController.class).getStore(book.getStore().getNif())).withRel("store"));
    }
}
