package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.*;
import com.customersscheduling.Controller.Util.ResourcesUtil;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.*;
import com.customersscheduling.Service.IOwnerService;
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
@RequestMapping(value="/store", produces = "application/hal+json")
public class StoreController {

    @Autowired
    private IStoreService storeService;

    @Autowired
    private IOwnerService ownerService;

    public StoreController() {

    }

    @GetMapping(value = "/{nif}/clients")
    public Resources<ClientResource> getClientsOfStore(@PathVariable String nif) {
        List<ClientResource> res = storeService.getClientsOfStore(nif)
                                        .stream()
                                        .map(i->i.toResource())
                                        .collect(Collectors.toList());
        Link link = linkTo(methodOn(StoreController.class).getStoresByName(nif)).withSelfRel();
        return ResourcesUtil.getResources(ClientResource.class, res, link);
    }

    @GetMapping(value = "", params = "name")
    public Resources<StoreResource> getStoresByName(@RequestParam("name") String name) {
        List<StoreResource> res = storeService.getStoresByName(name)
                                            .stream()
                                            .map(s -> s.toResource(storeService.getScore(s.getNif())))
                                            .collect(Collectors.toList());
        Link link = linkTo(methodOn(StoreController.class).getStoresByName(name)).withSelfRel();
        return ResourcesUtil.getResources(StoreResource.class, res, link);
    }

    @GetMapping(value = "", params = {"category","location"})
    public Resources<StoreResource> getStoresByCategoryAndLocation(@RequestParam("category") String category, @RequestParam("location") String location) {
        List<StoreResource> res = storeService.getStoresByLocationAndCategory(location, category)
                                                .stream()
                                                .map(s -> s.toResource(storeService.getScore(s.getNif())))
                                                .collect(Collectors.toList());
        Link link = linkTo(methodOn(StoreController.class).getStoresByCategoryAndLocation(category, location)).withSelfRel();
        return ResourcesUtil.getResources(StoreResource.class, res, link);
    }

    @GetMapping(value = "/{nif}")
    public Resource<StoreResource> getStore(@PathVariable String nif) {
        StoreResource res = storeService.getStore(nif).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(StoreController.class).getStore(nif)).withSelfRel();
        res.add(link);
        return new Resource<>(res);
    }

    @PostMapping(value = "/owner/{email}")
    public Resource<StoreResource> insertStore(@PathVariable String email, @RequestBody StoreInputModel store, HttpServletResponse resp) {
        Owner o = ownerService.getOwner(email);
        StoreResource storeResource = storeService.insertStore(store.toDto(o)).toResource(0);
        Link link = linkTo(methodOn(StoreController.class).insertStore(email, store, null)).withSelfRel();
        storeResource.add(link);
        resp.setStatus(HttpStatus.CREATED.value());
        return new Resource<>(storeResource);
    }

    @PutMapping(value = "/{nif}/address/{id}")
    public Resource<StoreResource> updateStoreAddress(@PathVariable String nif, @RequestBody AddressInputModel addr) {
        Address address = addr.toDto();
        Store st = new Store();
        st.setNif(nif);
        StoreResource storeResource = storeService.updateStoreAddress(nif, address).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(StoreController.class).updateStoreAddress(nif, addr)).withSelfRel();
        storeResource.add(link);
        return new Resource<>(storeResource);
    }

    @PutMapping(value = "/{nif}/info")
    public Resource<StoreResource> updateInfoStore(@PathVariable String nif, @RequestBody StoreInputModel store) {
        StoreResource storeResource = storeService.updateStore(nif, store.toDto(new Owner())).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(StoreController.class).updateInfoStore(nif, store)).withSelfRel();
        storeResource.add(link);
        return new Resource<>(storeResource);
    }


    @PostMapping(value = "/{nif}/client/{email}")
    public Resource<StoreResource> setClientForStore(@PathVariable String nif, @PathVariable String email, @RequestBody ClientStoreInputModel csim, HttpServletResponse resp) {
        Client c = new Client();
        c.setEmail(email);
        Store s = new Store();
        s.setNif(nif);
        ClientStores cs = new ClientStores(new ClientStoresPK(s,c),csim.accepted, csim.score);
        StoreResource storeResource = storeService.insertClientForStore(cs).toResource(storeService.getScore(nif));
        Link self = linkTo(methodOn(StoreController.class).setClientForStore(nif, email, csim, null)).withSelfRel();
        storeResource.add(self);
        resp.setStatus(HttpStatus.CREATED.value());
        return new Resource<>(storeResource);
    }

    @DeleteMapping(value = "/{nif}/client/{email}")
    public Resource<StoreResource> deleteClientOfStore(@PathVariable String nif, @PathVariable String email) {
        StoreResource s = storeService.deleteClient(email, nif).toResource(storeService.getScore(nif));
        Link self = linkTo(methodOn(StoreController.class).deleteClientOfStore(nif, email)).withSelfRel();
        s.add(self);
        return new Resource<>(s);
    }


    @GetMapping(value = "/{nif}/pendentrequests")
    public Resources<ClientResource> getPendentRequestsOfStore(@PathVariable String nif) {
        List<ClientResource> clients = storeService.getPendentRequests(nif)
                                                    .stream()
                                                    .map( i -> i.toResource())
                                                    .collect(Collectors.toList());
        Link link = linkTo(methodOn(StoreController.class)
                .getPendentRequestsOfStore(nif)).withSelfRel();
        return ResourcesUtil.getResources(ClientResource.class, clients, link);
    }

    @DeleteMapping(value = "/{nif}")
    public Resource<StoreResource> deleteStore(@PathVariable String nif) {
        StoreResource storeResource = storeService.deleteStore(nif).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(StoreController.class).deleteStore(nif)).withSelfRel();
        storeResource.add(link);
        return new Resource<>(storeResource);
    }

}
