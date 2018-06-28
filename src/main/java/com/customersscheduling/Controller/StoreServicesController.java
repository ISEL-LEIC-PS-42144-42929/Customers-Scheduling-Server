package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.ServiceInputModel;
import com.customersscheduling.Domain.Booking;
import com.customersscheduling.Domain.Store;
import com.customersscheduling.Domain.StoreServices;
import com.customersscheduling.Domain.StoreServicesPK;
import com.customersscheduling.HALObjects.BookingResource;
import com.customersscheduling.HALObjects.ServiceResource;
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
public class StoreServicesController {

    private final IStoreService storeService;

    public StoreServicesController(IStoreService businessService) {
        this.storeService = businessService;
    }

    @PostMapping(value = "/{nif}/service")
    public Resource<ServiceResource> insertServiceForStore(@RequestBody ServiceInputModel service, @PathVariable String nif) {
        Store store = new Store(); store.setNif(nif);
        StoreServices ss = new StoreServices(new StoreServicesPK(store,service.toDto()));
        ServiceResource serviceResource = storeService.insertServiceForStore(ss);
        Link link = linkTo(methodOn(StoreServicesController.class).insertServiceForStore(service, nif)).withSelfRel();
        Resource<ServiceResource> re = new Resource<>(serviceResource, link);
        return re;
    }


    @GetMapping(value = "/{nif}/services")
    public Resources<ServiceResource> getServicesOfStore(@PathVariable String nif) {
        List<ServiceResource> sr = storeService.getServicesOfStore(nif);
        Link link = linkTo(methodOn(StoreServicesController.class).getServicesOfStore(nif)).withSelfRel();
        Resources<ServiceResource> resr = new Resources<>(sr, link);
        return resr;
    }


    @GetMapping(value = "/{nif}/services/staff")
    public ResponseEntity<Resources<ServiceResource>> getStaffOfService(@PathVariable String name, @PathVariable String nif) {
        return null;
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
