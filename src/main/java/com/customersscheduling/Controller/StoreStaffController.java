package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.ServiceResource;
import com.customersscheduling.Service.IStoreService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/store", produces = "application/hal+json")
public class StoreStaffController {


    private final IStoreService storeService;

    public StoreStaffController(IStoreService businessService) {
        this.storeService = businessService;
    }


    @PostMapping(value = "/{nif}/service/{id}/staff")
    public Resource<ServiceResource> insertStaffForService(@RequestBody PersonInputModel person, @PathVariable String nif, @PathVariable int id) {
        Store store = new Store(); store.setNif(nif);
        Staff staff = person.toStaffDto();
        Service service = new Service(); service.setId(id);
        StaffServices ss = new StaffServices(new StaffServicesPK(staff, new StoreServices(new StoreServicesPK(store, service))));
        StaffServices staffServices = storeService.insertStafForService(ss);
        StoreServicesPK pk = staffServices.getPk().getStoresServices().getPk();
        ServiceResource sres = new ServiceResource(pk.getService(), pk.getStore().toResource());
        Link link = linkTo(methodOn(StoreStaffController.class).insertStaffForService(person, nif, id)).withSelfRel();
        return new Resource<>(sres, sres.getLinks(link));
    }
}
