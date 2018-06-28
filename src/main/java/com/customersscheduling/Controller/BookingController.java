package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.BookInputModel;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.BookingResource;
import com.customersscheduling.Service.IStoreService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/store", produces = "application/hal+json")
public class BookingController {


    private final IStoreService storeService;

    public BookingController(IStoreService businessService) {
        this.storeService = businessService;
    }

    @PostMapping(value = "/{nif}/service/{id}/staff/{email}/book")
    public BookingResource insertBook(@RequestBody BookInputModel book, @PathVariable String nif, @PathVariable int id, @PathVariable String email) {
        Store store = new Store(); store.setNif(nif);
        Staff staff = new Staff(); staff.setEmail(email);
        Service service = new Service(); service.setId(id);
        Client client = new Client(); client.setEmail(book.client_email);
        Booking booking = new Booking(store, staff, client, service);
        return storeService.insertBook(booking);
    }
}
