package com.customersscheduling.HALObjects;

import com.customersscheduling.DTO.Client;
import org.springframework.hateoas.ResourceSupport;

public class StaffResource extends ResourceSupport {
    private final Client person;

    public StaffResource(Client person) {
        this.person = person;
    }
}
