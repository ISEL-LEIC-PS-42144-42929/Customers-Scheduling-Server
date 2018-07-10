package com.customersscheduling.HALObjects;

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
        add(linkTo(methodOn(StoreController.class).updateStoreAddress(nif, null)).withRel("update_addr"));
        add(linkTo(methodOn(StoreController.class).updateInfoStore(nif, null)).withRel("update_info"));
        add(linkTo(methodOn(StoreController.class).getClientsOfStore(nif)).withRel("clients"));
        add(linkTo(methodOn(StoreController.class).getPendentRequestsOfStore(nif)).withRel("pendent_requests"));
        add(linkTo(methodOn(StoreStaffController.class).getStaffOfStore(nif)).withRel("staff"));
        add(linkTo(methodOn(StoreTimetableController.class).insertStoreTimetable(nif, null, null)).withRel("timetable"));
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
