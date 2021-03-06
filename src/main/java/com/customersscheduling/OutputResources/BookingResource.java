package com.customersscheduling.OutputResources;

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
        String email = book.getClient()==null ? "{email" : book.getClient().getEmail();
        add(linkTo(methodOn(ClientController.class).getClient(email)).withRel("client"));
        add(linkTo(methodOn(StaffController.class).getStaff(book.getStaff().getEmail())).withRel("staff"));
        add(linkTo(methodOn(StoreServicesController.class).getServicesOfStore(book.getStore().getNif())).withRel("store_services"));
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
