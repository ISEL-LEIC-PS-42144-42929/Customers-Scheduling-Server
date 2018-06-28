package com.customersscheduling.HALObjects;

import com.customersscheduling.Controller.StaffController;
import com.customersscheduling.Controller.TimetableController;
import com.customersscheduling.Domain.Staff;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class StaffResource extends ResourceSupport {
    private Staff person;

    public StaffResource(Staff person) {
        this.person = person;
        add(linkTo(methodOn(StaffController.class).getStaff(person.getEmail())).withRel("get"));
        add(linkTo(methodOn(StaffController.class).insertStaff(null)).withRel("insert"));
        add(linkTo(methodOn(TimetableController.class).insertStaffTimetable(person.getEmail(), null)).withRel("insert_timetable"));
    }

    public List<Link> getLinks(Link l) {
        add(l);
        return super.getLinks();
    }

    public Staff getPerson() {
        return person;
    }

    public void setPerson(Staff person) {
        this.person = person;
    }
}
