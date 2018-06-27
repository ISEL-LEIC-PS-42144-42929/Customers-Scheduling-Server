package com.customersscheduling.HALObjects;

import com.customersscheduling.Controller.PersonController;
import com.customersscheduling.Domain.Owner;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class OwnerResource extends ResourceSupport {
    private Owner person;

    public OwnerResource(Owner person) {
        this.person = person;

        add(linkTo(methodOn(PersonController.class).getOwner(person.getEmail())).withRel("get"));
        add(linkTo(methodOn(PersonController.class).insertOwner(null)).withRel("insert"));
        add(linkTo(methodOn(PersonController.class).getStoresOfOwner(person.getEmail())).withRel("stores"));
    }

    public Owner getPerson() {
        return person;
    }

    public void setPerson(Owner person) {
        this.person = person;
    }
}
