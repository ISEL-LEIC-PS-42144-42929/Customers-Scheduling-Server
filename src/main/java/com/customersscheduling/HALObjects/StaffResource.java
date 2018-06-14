package com.customersscheduling.HALObjects;

import com.customersscheduling.Domain.Staff;
import org.springframework.hateoas.ResourceSupport;

public class StaffResource extends ResourceSupport {
    private final Staff person;

    public StaffResource(Staff person) {
        this.person = person;
    }
}
