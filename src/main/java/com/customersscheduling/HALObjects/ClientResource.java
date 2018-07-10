package com.customersscheduling.HALObjects;

import com.customersscheduling.Controller.ClientController;
import com.customersscheduling.Domain.Client;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ClientResource extends ResourceSupport {
    private Client person;

    public ClientResource(Client person) {
        this.person = person;
        add(linkTo(methodOn(ClientController.class).getClient(person.getEmail())).withRel("get"));
        add(linkTo(methodOn(ClientController.class).insertClient(null, null)).withRel("insert"));
        add(linkTo(methodOn(ClientController.class).getPendentRequestOfClient(person.getEmail())).withRel("pendent_requests"));
        add(linkTo(methodOn(ClientController.class).getStoresOfClient(person.getEmail())).withRel("stores"));
    }

    public List<Link> getLinks(Link l) {
        add(l);
        return super.getLinks();
    }

    public Client getPerson() {
        return person;
    }

    public void setPerson(Client person) {
        this.person = person;
    }
}
