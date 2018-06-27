package com.customersscheduling.HALObjects;

import com.customersscheduling.Controller.PersonController;
import com.customersscheduling.Controller.TimetableController;
import com.customersscheduling.Domain.Staff;
import com.customersscheduling.Domain.Timetable;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class StaffTimetableResource extends TimetableResource {

    private StaffResource staff;

    public StaffTimetableResource(List<Timetable> t, StaffResource s) {
        super(t);
        this.staff = s;
        add(linkTo(methodOn(TimetableController.class).getStaffTimetable(s.getPerson().getEmail())).withSelfRel());
        add(linkTo(methodOn(TimetableController.class).insertStaffTimetable(s.getPerson().getEmail(), null)).withRel("insert"));
    }

    public StaffResource getStaff() {
        return staff;
    }

    public void setStaff(StaffResource staff) {
        this.staff = staff;
    }
}
