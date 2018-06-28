package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Domain.Store;
import com.customersscheduling.HALObjects.BookingResource;
import com.customersscheduling.HALObjects.ClientResource;
import com.customersscheduling.HALObjects.StoreResource;
import com.customersscheduling.Service.IPersonService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/person", produces = "application/hal+json")
public class ClientController {
    private final IPersonService personService;

    public ClientController(IPersonService personService) {
        this.personService = personService;
    }


    @PostMapping(value = "/client")
    public Resource<ClientResource> insertClient(@RequestBody PersonInputModel person) {
        ClientResource personResource = personService.insertClient(person.toClientDto()).toResource();
        Link link = linkTo(methodOn(ClientController.class).insertClient(person)).withSelfRel();
        return new Resource<>(personResource, personResource.getLinks(link));
    }

    @GetMapping(value = "/{email}/client")
    public Resources<ClientResource> getClient(@PathVariable String email) {
        return null;
    }


    @GetMapping(value = "/{email}/pendentrequests")
    public Resources<StoreResource> getPendentRequestOfClient(@PathVariable String email) {
        List<StoreResource> stores = personService.getPendentRequests(email)
                                                    .stream()
                                                    .map( c -> c.toResource())
                                                    .collect(Collectors.toList());
        Link link = linkTo(methodOn(ClientController.class)
                .getPendentRequestOfClient(email)).withSelfRel();
        return new Resources<>(stores, link);
    }

    @GetMapping(value = "/{email}/books")
    public ResponseEntity<Resources<BookingResource>> getBooksOfClient(@PathVariable String email) {
        return null;
    }

    @GetMapping(value = "/{email}/client/stores")
    public Resources<StoreResource> getStoresOfClient(@PathVariable String email) {
        List<StoreResource> stores = personService.getStoresByEmail(email)
                                                    .stream()
                                                    .map( c -> c.toResource())
                                                    .collect(Collectors.toList());
        Link link = linkTo(methodOn(ClientController.class).getStoresOfClient(email)).withSelfRel();
        return new Resources<>(stores, link);
    }

}