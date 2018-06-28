package com.customersscheduling.HALObjects;

import com.customersscheduling.Controller.StoreController;
import com.customersscheduling.Controller.TimetableController;
import com.customersscheduling.Domain.Store;
import com.customersscheduling.Domain.Timetable;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class StoreTimetableResource extends TimetableResource  {

    private StoreResource store;

    public StoreTimetableResource(List<Timetable> t, StoreResource s) {
        super(t);
        this.store = s;
        add(linkTo(methodOn(TimetableController.class).getStoreTimetable(s.getStore().getNif())).withRel("get"));
        add(linkTo(methodOn(TimetableController.class).insertStoreTimetable(s.getStore().getNif(), null)).withRel("insert"));
    }

    public List<Link> getLinks(Link l) {
        add(l);
        return super.getLinks();
    }

    public StoreResource getStore() {
        return store;
    }

    public void setStore(StoreResource store) {
        this.store = store;
    }
}
