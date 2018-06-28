package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.StoreInputModel;
import com.customersscheduling.Controller.InputModels.TimetableInputModel;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.*;
import com.customersscheduling.Service.IPersonService;
import com.customersscheduling.Service.IStoreService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/timetable", produces = "application/hal+json")
public class TimetableController {

    private final IStoreService storeService;
    private final IPersonService personService;


    public TimetableController(IStoreService businessService,IPersonService personService) {
        this.personService = personService;
        this.storeService = businessService;
    }


    @PostMapping(value = "/staff/{email}")
    public Resource<StaffResource> insertStaffTimetable(@PathVariable String email, @RequestBody TimetableInputModel timetable) {
        Staff staff = new Staff(); staff.setEmail(email);
        Timetable tt = timetable.toDto();
        StaffResource staffResource = personService.insertStaffTimetable(new StaffTimetable(new StaffTimetablePK(tt, staff))).toResource();
        Link link = linkTo(methodOn(TimetableController.class).insertStaffTimetable(email, timetable)).withSelfRel();
        Resource<StaffResource> staffRe = new Resource<>(staffResource, staffResource.getLinks(link));
        return staffRe;
    }

    @GetMapping(value = "/staff/{email}")
    public Resource<StaffTimetableResource> getStaffTimetable(@PathVariable String email) {
        List<StaffTimetable> tt = personService.getStaffTimetable(email);
        Iterator<StaffTimetable> iterator = tt.iterator();
        Staff s = null;
        if(iterator.hasNext())
            s = iterator.next().getPk().getStaff();
        List<Timetable> listtt = tt.stream()
                                    .map( stt -> stt.getPk().getTimetable())
                                    .collect(Collectors.toList());

        StaffTimetableResource ttResource = new StaffTimetableResource(listtt, s.toResource());
        Link link = linkTo(methodOn(TimetableController.class).getStaffTimetable(email)).withSelfRel();
        return new Resource<>(ttResource, link);
    }


    @PostMapping(value = "/store/{nif}")
    public Resource<StoreResource> insertStoreTimetable(@PathVariable String nif, @RequestBody TimetableInputModel timetable) {
        Store s = new Store();
        s.setNif(nif);
        Timetable tt = timetable.toDto();
        StoreResource storeResource = storeService.insertStoreTimetable(new StoreTimetable(new StoreTimetablePK(tt, s))).toResource();
        Link link = linkTo(methodOn(TimetableController.class).insertStoreTimetable(nif,timetable)).withSelfRel();
        return new Resource<StoreResource>(storeResource, storeResource.getLinks(link));
    }


    @GetMapping(value = "/store/{nif}")
    public Resource<StoreTimetableResource> getStoreTimetable(@PathVariable String nif) {
        List<StoreTimetable> st = storeService.getStoreTimetable(nif);
        Iterator<StoreTimetable> iterator = st.iterator();
        Store s = null;
        if(iterator.hasNext())
            s = iterator.next().getPk().getStore();
        List<Timetable> listtt = st.stream()
                                     .map( stt -> stt.getPk().getTimetable())
                                    .collect(Collectors.toList());

        StoreTimetableResource stResource = new StoreTimetableResource(listtt, s.toResource());
        Link link = linkTo(methodOn(TimetableController.class).getStoreTimetable(nif)).withSelfRel();
        return new Resource<StoreTimetableResource>(stResource, stResource.getLinks(link));
    }
}
