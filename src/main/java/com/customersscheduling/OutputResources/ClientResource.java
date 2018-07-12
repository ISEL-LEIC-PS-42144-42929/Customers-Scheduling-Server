package com.customersscheduling.OutputResources;

import com.customersscheduling.Controller.ClientController;
import com.customersscheduling.Controller.StoreController;
import com.customersscheduling.Domain.Client;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ClientResource extends ResourceSupport {
    private Client person;
    private Boolean accepted;

    public ClientResource(Client person) {
        this.person = person;
        links();
    }

    public ClientResource(Client person, boolean accepted){
        this.person= person;
        this.accepted= new Boolean(accepted);
        links();
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

    private void links(){
        add(linkTo(methodOn(ClientController.class).getClient(person.getEmail())).withRel("get"));
        add(linkTo(methodOn(ClientController.class).insertClient(null, null)).withRel("insert"));
        add(linkTo(methodOn(ClientController.class).getPendentRequestOfClient(person.getEmail())).withRel("pendent_requests"));
        add(linkTo(methodOn(ClientController.class).getStoresOfClient(person.getEmail())).withRel("stores"));
        add(linkTo(methodOn(StoreController.class).setClientForStore(null, person.getEmail(), null)).withRel("set_store"));
        add(linkTo(methodOn(StoreController.class).updateClientScore(null, person.getEmail(), null)).withRel("score"));
        add(linkTo(methodOn(StoreController.class).updateClientAccepted(null, person.getEmail(), null)).withRel("accept"));
        add(linkTo(methodOn(StoreController.class).deleteClientOfStore(null, person.getEmail())).withRel("decline_client"));
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
