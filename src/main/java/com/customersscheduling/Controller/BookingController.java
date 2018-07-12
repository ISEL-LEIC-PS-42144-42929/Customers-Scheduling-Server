package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.BookInputModel;
import com.customersscheduling.OutputResources.BookingResource;
import com.customersscheduling.Service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping(value="/store", produces = "application/hal+json")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @PostMapping(value = "/{nif}/book/{id}")
    public Resource<BookingResource> insertBook(@PathVariable String nif, @PathVariable int id, @RequestBody BookInputModel input, HttpServletResponse resp) {
        BookingResource booking = bookingService.insertBook(id, input.client_email).toResource();
        Link link = linkTo(methodOn(BookingController.class).insertBook(nif, id, input, null)).withSelfRel();
        booking.add(link);
        resp.setStatus(HttpStatus.CREATED.value());
        return new Resource<>(booking);
    }
}
