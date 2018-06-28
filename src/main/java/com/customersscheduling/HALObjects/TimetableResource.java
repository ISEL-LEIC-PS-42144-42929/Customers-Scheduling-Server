package com.customersscheduling.HALObjects;

import com.customersscheduling.Domain.Timetable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class TimetableResource extends ResourceSupport {

    private List<Timetable> timetable;

    public TimetableResource(List<Timetable> t){
        this.timetable = t;
    }

    public List<Link> getLinks(Link l) {
        add(l);
        return super.getLinks();
    }

    public List<Timetable> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<Timetable> timetable) {
        this.timetable = timetable;
    }

}
