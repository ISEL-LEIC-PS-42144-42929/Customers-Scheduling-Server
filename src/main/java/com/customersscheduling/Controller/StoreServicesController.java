package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.ServiceInputModel;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.BookingResource;
import com.customersscheduling.HALObjects.ServiceResource;
import com.customersscheduling.HALObjects.StaffResource;
import com.customersscheduling.Service.IStoreService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/store", produces = "application/hal+json")
public class StoreServicesController {

    private final IStoreService storeService;

    public StoreServicesController(IStoreService businessService) {
        this.storeService = businessService;
    }

    @PostMapping(value = "/{nif}/service")
    public Resource<ServiceResource> insertServiceForStore(@RequestBody ServiceInputModel service, @PathVariable String nif) {
        Store store = new Store(); store.setNif(nif);
        StoreServices ss = new StoreServices(new StoreServicesPK(store,service.toDto()));
        StoreServices storeServices = storeService.insertServiceForStore(ss);
        ServiceResource serviceResource = new ServiceResource(storeServices.getPk().getService(), storeServices.getPk().getStore().toResource());
        Link link = linkTo(methodOn(StoreServicesController.class).insertServiceForStore(service, nif)).withSelfRel();
        return new Resource<>(serviceResource, link);
    }


    @GetMapping(value = "/{nif}/services")
    public Resources<ServiceResource> getServicesOfStore(@PathVariable String nif) {
        List<ServiceResource> sr = storeService.getServicesOfStore(nif)
                                                .stream()
                                                .map( i -> new ServiceResource(i.getPk().getService(), i.getPk().getStore().toResource()))
                                                .collect(Collectors.toList());
        Link link = linkTo(methodOn(StoreServicesController.class).getServicesOfStore(nif)).withSelfRel();
        Resources<ServiceResource> resr = new Resources<>(sr, link);
        return resr;
    }


    @GetMapping(value = "/{nif}/services/{id}/staff")
    public Resources<StaffResource> getStaffOfService(@PathVariable int id, @PathVariable String nif) {
        List<StaffResource> staffs = storeService.getStaffOfService(id, nif)
                                                    .stream()
                                                    .map( i -> i.toResource())
                                                    .collect(Collectors.toList());
        Link link = linkTo(methodOn(StoreServicesController.class).getStaffOfService(id, nif)).withSelfRel();
        return new Resources<>(staffs, link);
    }

    @GetMapping(value = "/{nif}/services/{id}/disp")
    public ResponseEntity<Resources<BookingResource>> getDispOfService(@PathVariable String nif, @PathVariable int id) {
        final List<Booking> books = storeService.getServiceDisp(id);
        final List<BookingResource> mappedBooking = new ArrayList<>();
        books.iterator().forEachRemaining( st ->
                mappedBooking.add(new BookingResource(st))
        );
        Link link = linkTo(methodOn(StoreServicesController.class).getDispOfService(nif, id)).withSelfRel();
        final Resources<BookingResource> resources = new Resources<BookingResource>(mappedBooking, link);
        return ResponseEntity.ok(resources);
    }
}
