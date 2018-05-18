package com.customersscheduling.HALObjects;

import com.customersscheduling.DTO.Client;
import org.springframework.hateoas.ResourceSupport;

public class BookingResource extends ResourceSupport {
    private final Client person;

    public BookingResource(Client person) {
        this.person = person;
    }
}
