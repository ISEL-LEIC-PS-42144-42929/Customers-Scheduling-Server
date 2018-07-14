package com.customersscheduling.OutputResources;

import com.customersscheduling.Controller.StaffController;
import com.customersscheduling.Controller.StoreServicesController;
import com.customersscheduling.Controller.StoreStaffController;
import com.customersscheduling.Domain.Service;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ServiceResource extends ResourceSupport {

    private Service service;
    private StoreResource store;

    public ServiceResource(Service service, StoreResource store) {
        this.service = service;
        this.store = store;
        String nif = store == null ? "{nif}" : store.getStore().getNif();
        add(linkTo(methodOn(StoreServicesController.class).getServicesOfStore(nif)).withRel("get_store_services"));
        add(linkTo(methodOn(StoreServicesController.class).insertServiceForStore(null, nif, null)).withRel("insert_store_service"));
        add(linkTo(methodOn(StoreServicesController.class).updateService(null, nif, service.getId())).withRel("update"));
        add(linkTo(methodOn(StoreServicesController.class).getStaffOfService(service.getId(), nif)).withRel("get_staff"));
        add(linkTo(methodOn(StoreServicesController.class).getDispOfService(nif, service.getId())).withRel("disp"));
        add(linkTo(methodOn(StaffController.class).getServicesOfStaff(null)).withRel("get_staff_services"));
        add(linkTo(methodOn(StoreStaffController.class).insertStaffForService(null, nif, -1,null)).withRel("post_staff_service"));
        add(linkTo(methodOn(StoreStaffController.class).removeStaffOfService(null, nif, -1)).withRel("delete_staff_service"));
    }

    public List<Link> getLinks(Link l) {
        add(l);
        return super.getLinks();
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public StoreResource getStore() {
        return store;
    }

    public void setStore(StoreResource store) {
        this.store = store;
    }
}
