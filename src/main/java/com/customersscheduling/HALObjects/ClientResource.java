package com.customersscheduling.HALObjects;

import com.customersscheduling.Controller.PersonController;
import com.customersscheduling.Domain.Client;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ClientResource extends ResourceSupport {
    private Client person;

    public ClientResource(Client person) {
        this.person = person;
        add(linkTo(methodOn(PersonController.class).getClient(person.getEmail())).withSelfRel());
        add(linkTo(methodOn(PersonController.class).insertClient(null)).withRel("insert"));

        add(linkTo(methodOn(PersonController.class).getPendentRequestOfClient(person.getEmail())).withRel("pendent requests"));
        add(linkTo(methodOn(PersonController.class).getStoresOfClient(person.getEmail())).withRel("stores"));
    }

    public Client getPerson() {
        return person;
    }

    public void setPerson(Client person) {
        this.person = person;
    }
}
