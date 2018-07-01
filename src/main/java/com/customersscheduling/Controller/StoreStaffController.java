package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.ServiceResource;
import com.customersscheduling.Service.IBookingService;
import com.customersscheduling.Service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/store", produces = "application/hal+json")
public class StoreStaffController {

    @Autowired
    private IStoreService storeService;

    @Autowired
    private IBookingService bookingService;

    @PostMapping(value = "/{nif}/service/{id}/staff/{email}")
    public Resource<ServiceResource> insertStaffForService(@PathVariable String email, @PathVariable String nif, @PathVariable int id) {
        Store store = new Store(); store.setNif(nif);
        Staff staff = new Staff(); staff.setEmail(email);
        Service service = new Service(); service.setId(id);
        StaffServices ss = new StaffServices(new StaffServicesPK(staff, new StoreServices(new StoreServicesPK(store, service))));
        StaffServices staffServices = storeService.insertStafForService(ss);
        bookingService.updateBookingOfStore(staffServices);
        StoreServicesPK pk = staffServices.getPk().getStoresServices().getPk();
        ServiceResource sres = new ServiceResource(pk.getService(), pk.getStore().toResource());
        Link link = linkTo(methodOn(StoreStaffController.class).insertStaffForService(email, nif, id)).withSelfRel();
        return new Resource<>(sres, sres.getLinks(link));
    }
}
