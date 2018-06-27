package com.customersscheduling.HALObjects;

import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Controller.InputModels.TimetableInputModel;
import com.customersscheduling.Controller.PersonController;
import com.customersscheduling.Controller.StoreController;
import com.customersscheduling.Controller.TimetableController;
import com.customersscheduling.Domain.Staff;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class StaffResource extends ResourceSupport {
    private Staff person;

    public StaffResource(Staff person) {
        this.person = person;
        add(linkTo(methodOn(PersonController.class).getStaff(person.getEmail())).withSelfRel());
        add(linkTo(methodOn(PersonController.class).insertStaff(null)).withRel("insert"));
        add(linkTo(methodOn(TimetableController.class).insertStaffTimetable(person.getEmail(), null)).withRel("insert timetable"));
    }

    public Staff getPerson() {
        return person;
    }

    public void setPerson(Staff person) {
        this.person = person;
    }
}
