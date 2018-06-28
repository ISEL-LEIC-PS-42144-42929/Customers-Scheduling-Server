package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.*;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.*;
import com.customersscheduling.Service.IStoreService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping(value="/store", produces = "application/hal+json")
public class StoreController {

    private final IStoreService storeService;

    public StoreController(IStoreService businessService) {
        this.storeService = businessService;
    }


    @GetMapping(value = "/{nif}")
    public Resource<StoreResource> getStore(@PathVariable String nif) {
        StoreResource res = storeService.getStoreByNif(nif);
        Link link = linkTo(methodOn(StoreController.class).getStore(nif)).withSelfRel();
        List<Link> links = res.getLinks();
        links.add(link);
        return new Resource<>(res, links);
    }

    @PostMapping(value = "/{email}")
    public Resource<StoreResource> insertStore(@PathVariable String email, @RequestBody StoreInputModel store) {
        Owner o = new Owner();
        o.setEmail(email);
        StoreResource storeResource = storeService.insertStore(store.toDto(o));
        Link link = linkTo(methodOn(StoreController.class).insertStore(email, store)).withSelfRel();
        List<Link> links = storeResource.getLinks();
        links.add(link);
        return new Resource<>(storeResource, links);
    }

    @GetMapping(value = "/owner/{email}")
    public Resources<StoreResource> getStoreOfOwner(@PathVariable String email) {
        List<StoreResource> storesOfUser = storeService.getStoresOfUser(email);
        Link link = linkTo(methodOn(StoreController.class).getStoreOfOwner(email)).withSelfRel();
        Resources<StoreResource> storeRe = new Resources<>(storesOfUser, link);
        return storeRe;
    }

    @PostMapping(value = "/{nif}/client/{email}")
    public Resource<StoreResource> setClientForStore(@PathVariable String nif, @PathVariable String email, @RequestBody ClientStoreInputModel csim) {
        Client c = new Client();
        c.setEmail(email);
        Store s = new Store();
        s.setNif(nif);
        ClientStores cs = new ClientStores(new ClientStoresPK(s,c),csim.accepted, csim.score);
        StoreResource storeResource = storeService.insertClientForStore(cs);
        Link self = linkTo(methodOn(StoreController.class).setClientForStore(nif, email, csim)).withSelfRel();
        return new Resource<>(storeResource, storeResource.getLinks(self));
    }


    @GetMapping(value = "/{nif}/pendentrequests")
    public Resources<ClientResource> getPendentRequestsOfStore(@PathVariable String nif) {
        List<ClientResource> clients = storeService.getPendentRequests(nif);
        Link link = linkTo(methodOn(StoreController.class)
                .getPendentRequestsOfStore(nif)).withSelfRel();
        final Resources<ClientResource> resources = new Resources<>(clients, link);
        return resources;
    }
}
