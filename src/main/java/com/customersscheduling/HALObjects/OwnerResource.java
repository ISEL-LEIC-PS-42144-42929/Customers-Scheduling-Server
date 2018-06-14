package com.customersscheduling.HALObjects;

import com.customersscheduling.Domain.Owner;
import org.springframework.hateoas.ResourceSupport;

public class OwnerResource extends ResourceSupport {
    private final Owner person;

    public OwnerResource(Owner person) {
        this.person = person;
    }
}
