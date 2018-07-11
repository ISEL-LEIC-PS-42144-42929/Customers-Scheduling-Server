package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.ServiceInputModel;
import com.customersscheduling.Controller.Util.ResourcesUtil;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.BookingResource;
import com.customersscheduling.HALObjects.ServiceResource;
import com.customersscheduling.HALObjects.StaffResource;
import com.customersscheduling.Service.IServicesOfStoreService;
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
public class StoreServicesController {

    @Autowired
    private IServicesOfStoreService servicesOfStoreService;

    @Autowired
    private IStoreService storeService;

    @PostMapping(value = "/{nif}/services")
    public Resource<ServiceResource> insertServiceForStore(@RequestBody ServiceInputModel service, @PathVariable String nif, HttpServletResponse resp) {
        Store store = new Store(); store.setNif(nif);
        StoreServices ss = new StoreServices(new StoreServicesPK(store,service.toDto()));
        StoreServices storeServices = servicesOfStoreService.insertServiceForStore(ss);
        ServiceResource serviceResource = new ServiceResource(storeServices.getPk().getService(), storeServices.getPk().getStore().toResource(storeService.getScore(nif)));
        Link link = linkTo(methodOn(StoreServicesController.class).insertServiceForStore(service, nif, null)).withSelfRel();
        serviceResource.add(link);
        resp.setStatus(HttpStatus.CREATED.value());
        return new Resource<>(serviceResource);
    }

    @PutMapping(value = "/{nif}/services/{id}")
    public Resource<ServiceResource> updateService(@RequestBody ServiceInputModel service, @PathVariable String nif, @PathVariable int id) {
        Store store = new Store(); store.setNif(nif);
        StoreServices ss = new StoreServices(new StoreServicesPK(store,service.toDto()));
        StoreServices storeServices = servicesOfStoreService.updateService(ss, id);
        ServiceResource serviceResource = new ServiceResource(storeServices.getPk().getService(), storeServices.getPk().getStore().toResource(storeService.getScore(nif)));
        Link link = linkTo(methodOn(StoreServicesController.class).updateService(service, nif, id)).withSelfRel();
        serviceResource.add(link);
        return new Resource<>(serviceResource);
    }



    @GetMapping(value = "/{nif}/services")
    public Resources<ServiceResource> getServicesOfStore(@PathVariable String nif) {
        List<ServiceResource> sr = storeService.getServicesOfStore(nif)
                                                .stream()
                                                .map( i -> new ServiceResource(i.getPk().getService(), i.getPk().getStore().toResource(storeService.getScore(nif))))
                                                .collect(Collectors.toList());
        Link link = linkTo(methodOn(StoreServicesController.class).getServicesOfStore(nif)).withSelfRel();
        return ResourcesUtil.getResources(ServiceResource.class, sr, link);
    }

    @GetMapping(value = "/{nif}/services/{id}")
    public Resource<ServiceResource> getService(@PathVariable int id) {
        ServiceResource sr = servicesOfStoreService.getService(id).toResource(null);
        Link link = linkTo(methodOn(StoreServicesController.class).getService(id)).withSelfRel();
        sr.add(link);
        return new Resource<>(sr);
    }

    @GetMapping(value = "/{nif}/services/{id}/staff")
    public Resources<StaffResource> getStaffOfService(@PathVariable int id, @PathVariable String nif) {
        List<StaffResource> staffs = servicesOfStoreService.getStaffOfService(id, nif)
                                                    .stream()
                                                    .map( i -> i.toResource())
                                                    .collect(Collectors.toList());
        Link link = linkTo(methodOn(StoreServicesController.class).getStaffOfService(id, nif)).withSelfRel();
        return ResourcesUtil.getResources(StaffResource.class, staffs, link);
    }


    @GetMapping(value = "/{nif}/services/{id}/disp")
    public Resources<BookingResource> getDispOfService(@PathVariable String nif, @PathVariable int id) {
        final List<BookingResource> books = servicesOfStoreService.getServiceDisp(id)
                .stream()
                .map( i -> i.toResource())
                .collect(Collectors.toList());
        Link link = linkTo(methodOn(StoreServicesController.class).getDispOfService(nif, id)).withSelfRel();
        return ResourcesUtil.getResources(BookingResource.class, books, link);
    }

    @DeleteMapping(value = "/{nif}/service/{id}")
    public Resource<ServiceResource> deleteService(@PathVariable String nif, @PathVariable int id) {
        ServiceResource service = servicesOfStoreService.deleteService(nif, id).toResource(null);
        Link link = linkTo(methodOn(StoreServicesController.class).deleteService(nif, id)).withSelfRel();
        service.add(link);
        return new Resource<>(service);
    }
}
