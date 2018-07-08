package com.customersscheduling.HALObjects;

import com.customersscheduling.Domain.Timetable;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class StaffTimetableResource extends TimetableResource {

    private StaffResource staff;

    public StaffTimetableResource(List<Timetable> t, StaffResource s) {
        super(t);
        this.staff = s;
        add(linkTo(methodOn(TimetableController.class).getStaffTimetable(s.getPerson().getEmail())).withRel("get"));
        add(linkTo(methodOn(TimetableController.class).insertStaffTimetable(s.getPerson().getEmail(), null)).withRel("insert"));
    }

    public List<Link> getLinks(Link l) {
        add(l);
        return super.getLinks();
    }

    public StaffResource getStaff() {
        return staff;
    }

    public void setStaff(StaffResource staff) {
        this.staff = staff;
    }
}
