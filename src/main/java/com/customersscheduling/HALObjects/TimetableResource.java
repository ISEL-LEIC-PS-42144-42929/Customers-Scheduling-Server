package com.customersscheduling.HALObjects;

import com.customersscheduling.Controller.PersonController;
import com.customersscheduling.Controller.StoreController;
import com.customersscheduling.Controller.TimetableController;
import com.customersscheduling.Domain.Staff;
import com.customersscheduling.Domain.Store;
import com.customersscheduling.Domain.Timetable;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class TimetableResource extends ResourceSupport {

    private List<Timetable> timetable;

    public TimetableResource(List<Timetable> t){
        this.timetable = t;
    }

    public List<Timetable> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<Timetable> timetable) {
        this.timetable = timetable;
    }

}
