package com.customersscheduling.OutputResources;

import com.customersscheduling.Controller.OwnerController;
import com.customersscheduling.Domain.Owner;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class OwnerResource extends ResourceSupport {
    private Owner person;

    public OwnerResource(Owner person) {
        this.person = person;
        add(linkTo(methodOn(OwnerController.class).getOwner(person.getClient().getEmail())).withRel("get"));
        add(linkTo(methodOn(OwnerController.class).insertOwner(null, null)).withRel("insert"));
        add(linkTo(methodOn(OwnerController.class).getStoreOfOwner(person.getClient().getEmail())).withRel("stores"));
    }

    public List<Link> getLinks(Link l) {
        add(l);
        return super.getLinks();
    }

    public Owner getPerson() {
        return person;
    }

    public void setPerson(Owner person) {
        this.person = person;
    }
}
