package com.customersscheduling.HALObjects;

import com.customersscheduling.Controller.*;
import com.customersscheduling.Domain.Booking;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class BookingResource extends ResourceSupport {
    private Booking book;

    public BookingResource(Booking book) {
        this.book = book;
        add(linkTo(methodOn(StoreServicesController.class).getDispOfService(book.getStore().getNif(), book.getService().getId())).withRel("disponibility"));
        add(linkTo(methodOn(ClientController.class).getClient(book.getClient().getEmail())).withRel("client"));
        add(linkTo(methodOn(StaffController.class).getStaff(book.getClient().getEmail())).withRel("staff"));
        add(linkTo(methodOn(UtilController.class).getService(book.getService().getId())).withRel("service"));
        add(linkTo(methodOn(StoreController.class).getStore(book.getStore().getNif())).withRel("store"));
    }

    public List<Link> getLinks(Link l) {
        add(l);
        return super.getLinks();
    }

    public Booking getBook() {
        return book;
    }
}
