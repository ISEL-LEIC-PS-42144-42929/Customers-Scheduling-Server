package com.customersscheduling.OutputResources;

import com.customersscheduling.Controller.StoreTimetableController;
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
        add(linkTo(methodOn(StoreTimetableController.class).getStoreTimetable(s.getStore().getNif())).withRel("get"));
        add(linkTo(methodOn(StoreTimetableController.class).insertStoreTimetable(s.getStore().getNif(), null, null)).withRel("insert"));
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
