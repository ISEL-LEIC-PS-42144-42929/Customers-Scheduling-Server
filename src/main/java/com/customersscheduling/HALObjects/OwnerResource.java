package com.customersscheduling.HALObjects;

import com.customersscheduling.DTO.Client;
import org.springframework.hateoas.ResourceSupport;

public class OwnerResource extends ResourceSupport {
    private final Client person;

    public OwnerResource(Client person) {
        this.person = person;
    }
}
