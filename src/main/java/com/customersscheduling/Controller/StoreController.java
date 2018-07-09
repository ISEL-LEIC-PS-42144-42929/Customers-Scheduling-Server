package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.*;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.*;
import com.customersscheduling.Service.IStoreService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping(value="/store", produces = "application/hal+json")
public class StoreController {

    private final IStoreService storeService;

    public StoreController(IStoreService businessService) {
        this.storeService = businessService;
    }

    @GetMapping(value = "/")
    public Resources<StoreResource> getStoreByName(@RequestParam("name") String name) {
        List<StoreResource> res = storeService.getStoresByName(name)
                                            .stream()
                                            .map(s -> s.toResource(storeService.getScore(s.getNif())))
                                            .collect(Collectors.toList());
        Link link = linkTo(methodOn(StoreController.class).getStoreByName(name)).withSelfRel();
        return new Resources<>(res, link);
    }

    @GetMapping(value = "/{nif}")
    public Resource<StoreResource> getStore(@PathVariable String nif) {
        StoreResource res = storeService.getStore(nif).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(StoreController.class).getStore(nif)).withSelfRel();
        return new Resource<>(res, res.getLinks(link));
    }

    @PostMapping(value = "/owner/{email}")
    public Resource<StoreResource> insertStore(@PathVariable String email, @RequestBody StoreInputModel store) {
        Owner o = new Owner();
        o.setEmail(email);
        StoreResource storeResource = storeService.insertStore(store.toDto(o)).toResource(0);
        Link link = linkTo(methodOn(StoreController.class).insertStore(email, store)).withSelfRel();
        return new Resource<>(storeResource, storeResource.getLinks(link));
    }

    @PutMapping(value = "/{nif}/address/{id}")
    public Resource<StoreResource> updateStoreAddress(@PathVariable String nif, @RequestBody AddressInputModel addr) {
        Address address = addr.toDto();
        Store st = new Store();
        st.setNif(nif);
        StoreResource storeResource = storeService.updateStoreAddress(nif, address).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(StoreController.class).updateStoreAddress(nif, addr)).withSelfRel();
        return new Resource<>(storeResource, storeResource.getLinks(link));
    }

    @PutMapping(value = "/{nif}/info")
    public Resource<StoreResource> updateInfoStore(@PathVariable String nif, @RequestBody StoreInputModel store) {
        StoreResource storeResource = storeService.updateStore(nif, store.toDto(new Owner())).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(StoreController.class).updateInfoStore(nif, store)).withSelfRel();
        return new Resource<>(storeResource, storeResource.getLinks(link));
    }


    @PostMapping(value = "/{nif}/client/{email}")
    public Resource<StoreResource> setClientForStore(@PathVariable String nif, @PathVariable String email, @RequestBody ClientStoreInputModel csim) {
        Client c = new Client();
        c.setEmail(email);
        Store s = new Store();
        s.setNif(nif);
        ClientStores cs = new ClientStores(new ClientStoresPK(s,c),csim.accepted, csim.score);
        StoreResource storeResource = storeService.insertClientForStore(cs).toResource(storeService.getScore(nif));
        Link self = linkTo(methodOn(StoreController.class).setClientForStore(nif, email, csim)).withSelfRel();
        return new Resource<>(storeResource, storeResource.getLinks(self));
    }


    @GetMapping(value = "/{nif}/pendentrequests")
    public Resources<ClientResource> getPendentRequestsOfStore(@PathVariable String nif) {
        List<ClientResource> clients = storeService.getPendentRequests(nif)
                                                    .stream()
                                                    .map( i -> i.toResource())
                                                    .collect(Collectors.toList());
        Link link = linkTo(methodOn(StoreController.class)
                .getPendentRequestsOfStore(nif)).withSelfRel();
        return new Resources<>(clients, link);
    }

    @DeleteMapping(value = "/{nif}")
    public Resource<StoreResource> deleteStore(@PathVariable String nif) {
        StoreResource storeResource = storeService.deleteStore(nif).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(StoreController.class).deleteStore(nif)).withSelfRel();
        Resource<StoreResource> rp = new Resource<>(storeResource, storeResource.getLinks(link));
        return rp;
    }

}
