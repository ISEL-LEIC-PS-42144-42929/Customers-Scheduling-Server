package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Controller.Util.ResourcesUtil;
import com.customersscheduling.OutputResources.BookingResource;
import com.customersscheduling.OutputResources.ClientResource;
import com.customersscheduling.OutputResources.StoreResource;
import com.customersscheduling.Service.IClientService;
import com.customersscheduling.Service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/person", produces = "application/hal+json")
public class ClientController {

    @Autowired
    private IClientService personService;

    @Autowired
    private IStoreService storeService;

    @PostMapping(value = "/client")
    public Resource<ClientResource> insertClient(@RequestBody PersonInputModel person, HttpServletResponse resp) {
        ClientResource personResource = personService.insertClient(person.toClientDto()).toResource();
        Link link = linkTo(methodOn(ClientController.class).insertClient(person, null)).withSelfRel();
        personResource.add(link);
        resp.setStatus(HttpStatus.CREATED.value());
        return new Resource<>(personResource);
    }

    @GetMapping(value = "/client/{email}")
    public Resource<ClientResource> getClient(@PathVariable String email) {
        ClientResource c = personService.getClient(email).toResource();
        Link link = linkTo(methodOn(ClientController.class).getClient(email)).withSelfRel();
        c.add(link);
        return new Resource<>(c);
    }


    @GetMapping(value = "/client/{email}/pendentrequests")
    public Resources<StoreResource> getPendentRequestOfClient(@PathVariable String email) {
        List<StoreResource> stores = personService.getPendentRequests(email)
                                                    .stream()
                                                    .map( c -> c.toResource(storeService.getScore(c.getNif())))
                                                    .collect(Collectors.toList());
        Link link = linkTo(methodOn(ClientController.class)
                .getPendentRequestOfClient(email)).withSelfRel();
        return ResourcesUtil.getResources(StoreResource.class, stores, link);
    }

    @GetMapping(value = "/client/{email}/books")
    public Resources<BookingResource> getBooksOfClient(@PathVariable String email) {
        List<BookingResource> books = personService.getBooks(email)
                                                    .stream()
                                                    .map( i -> i.toResource())
                                                    .collect(Collectors.toList());
        Link link = linkTo(methodOn(ClientController.class)
                .getBooksOfClient(email)).withSelfRel();
        return ResourcesUtil.getResources(BookingResource.class, books, link);
    }

    @GetMapping(value = "/client/{email}/stores")
    public Resources<StoreResource> getStoresOfClient(@PathVariable String email) {
        List<StoreResource> stores = personService.getStoresByEmail(email)
                                                    .stream()
                                                    .map( c -> c.toResource(storeService.getScore(c.getNif())))
                                                    .collect(Collectors.toList());
        Link link = linkTo(methodOn(ClientController.class).getStoresOfClient(email)).withSelfRel();
        return ResourcesUtil.getResources(StoreResource.class, stores, link);
    }

    @DeleteMapping(value = "/client/{email}")
    public Resource<ClientResource> deleteClient(@PathVariable String email) {
        ClientResource cs = personService.deleteClient(email).toResource();
        Link link = linkTo(methodOn(ClientController.class).getStoresOfClient(email)).withSelfRel();
        cs.add(link);
        return new Resource<>(cs);
    }
}
