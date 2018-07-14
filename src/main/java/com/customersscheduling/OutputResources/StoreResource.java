package com.customersscheduling.OutputResources;

import com.customersscheduling.Controller.*;
import com.customersscheduling.Domain.Store;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class StoreResource extends ResourceSupport {

    private Store store;
    private double score;

    public StoreResource(Store store, double score) {
        this.store = store;
        this.score = score;
        String nif = store.getNif();
        add(linkTo(methodOn(StoreController.class).getStore(nif)).withRel("get"));
        add(linkTo(methodOn(StoreController.class).insertStore(null,null, null)).withRel("insert"));
        add(linkTo(methodOn(StoreServicesController.class).getServicesOfStore(nif)).withRel("services"));
        add(linkTo(methodOn(StorePortfolioController.class).getPortfolioOfStore(nif)).withRel("portfolio"));
        add(linkTo(methodOn(StoreController.class).updateStore(nif, null)).withRel("update"));
        add(linkTo(methodOn(StoreController.class).getClientsOfStore(nif)).withRel("clients"));
        add(linkTo(methodOn(StoreController.class).getPendentRequestsOfStore(nif)).withRel("pendent_requests"));
        add(linkTo(methodOn(StoreStaffController.class).getStaffOfStore(nif)).withRel("get_staff"));
        add(linkTo(methodOn(StoreStaffController.class).insertStaffForService(null,nif,-1, null)).withRel("add_service_staff"));
        add(linkTo(methodOn(StaffController.class).insertStaff(null,null)).withRel("insert_staff"));
        add(linkTo(methodOn(StoreTimetableController.class).insertStoreTimetable(nif, null, null)).withRel("timetable"));
        add(linkTo(methodOn(StoreTimetableController.class).updateStoreTimetable(nif, null)).withRel("update_timetable"));
        add(linkTo(methodOn(StoreController.class).setClientForStore(nif, null, null)).withRel("set_client"));
        add(linkTo(methodOn(StoreController.class).deleteClientOfStore(nif, null)).withRel("delete_client"));
        add(linkTo(methodOn(StoreController.class).setClientForStore(nif, null, null)).withRel("set_store"));
        add(linkTo(methodOn(StoreController.class).updateClientScore(nif, null, null)).withRel("score"));
    }

    public List<Link> getLinks(Link l) {
        add(l);
        return super.getLinks();
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
