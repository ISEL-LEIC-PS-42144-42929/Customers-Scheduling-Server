package com.customersscheduling.HALObjects;

import com.customersscheduling.DTO.Client;
import org.springframework.hateoas.ResourceSupport;

public class ClientResource extends ResourceSupport {
    private final Client person;

    public ClientResource(Client person) {
        this.person = person;
    }
}
