package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.BookInputModel;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.BookingResource;
import com.customersscheduling.Service.IStoreService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping(value="/store", produces = "application/hal+json")
public class BookingController {


    private final IStoreService storeService;

    public BookingController(IStoreService businessService) {
        this.storeService = businessService;
    }

    @PostMapping(value = "/{nif}/book/{id}")
    public Resource<BookingResource> insertBook(@PathVariable String nif, @PathVariable int id, @RequestBody BookInputModel input) {
        BookingResource booking = storeService.insertBook(id, input.client_email).toResource();
        Link link = linkTo(methodOn(BookingController.class).insertBook(nif, id, input)).withSelfRel();
        return new Resource<>(booking, booking.getLinks(link));
    }
}
