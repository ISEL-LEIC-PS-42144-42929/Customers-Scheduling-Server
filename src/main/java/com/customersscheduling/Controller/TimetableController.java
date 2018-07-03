package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.TimetableInputModel;
import com.customersscheduling.Domain.*;
import com.customersscheduling.ExceptionHandling.CustomExceptions.InvalidBodyException;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.HALObjects.*;
import com.customersscheduling.Service.IStaffService;
import com.customersscheduling.Service.IStoreService;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/timetable", produces = "application/hal+json")
public class TimetableController {

    @Autowired
    private  IStoreService storeService;

    @Autowired
    private IStaffService personService;

    @PutMapping(value = "/staff/{email}/{weekDay}")
    public Resource<StaffResource> updateStaffTimetable(@PathVariable String email, @PathVariable int weekDay,  @RequestBody TimetableInputModel timetable) {
        if(timetable.week_day!=weekDay) throw new InvalidBodyException("Weekday specified on query isn't coerent with the body's one ( "+weekDay+"!="+timetable.week_day+").");
        Staff staff = new Staff(); staff.setEmail(email);
        Timetable tt = timetable.toDto();
        StaffResource staffResource = personService.updateStaffTimetable(new StaffTimetable(new StaffTimetablePK(tt, staff))).toResource();
        Link link = linkTo(methodOn(TimetableController.class).updateStaffTimetable(email, weekDay, timetable)).withSelfRel();
        Resource<StaffResource> staffRe = new Resource<>(staffResource, staffResource.getLinks(link));
        return staffRe;
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
        StoreResource storeResource = storeService.insertStoreTimetable(new StoreTimetable(new StoreTimetablePK(tt, s))).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(TimetableController.class).insertStoreTimetable(nif,timetable)).withSelfRel();
        return new Resource<StoreResource>(storeResource, storeResource.getLinks(link));
    }

    @PutMapping(value = "/store/{nif}/{weekDay}")
    public Resource<StoreResource> updateStoreTimetable(@PathVariable String nif, @PathVariable int weekDay,  @RequestBody TimetableInputModel timetable) {
        if(timetable.week_day!=weekDay) throw new InvalidBodyException("Weekday specified on query isn't coerent with the body's one ( "+weekDay+"!="+timetable.week_day+").");
        Store s = new Store();
        s.setNif(nif);
        Timetable tt = timetable.toDto();
        StoreResource storeResource = storeService.updateStoreTimetable(new StoreTimetable(new StoreTimetablePK(tt, s))).toResource(storeService.getScore(nif));
        Link link = linkTo(methodOn(TimetableController.class).insertStoreTimetable(nif,timetable)).withSelfRel();
        return new Resource<>(storeResource, storeResource.getLinks(link));
    }

    @GetMapping(value = "/store/{nif}")
    public Resource<StoreTimetableResource> getStoreTimetable(@PathVariable String nif) {
        List<StoreTimetable> st = storeService.getStoreTimetable(nif);
        Iterator<StoreTimetable> iterator = st.iterator();
        Store s = null;
        if(iterator.hasNext())
            s = iterator.next().getPk().getStore();
        else
            throw new ResourceNotFoundException("Store with NIF - "+nif+" - doesn't have any timetable specified");
        List<Timetable> listtt = st.stream()
                                     .map( stt -> stt.getPk().getTimetable())
                                    .collect(Collectors.toList());

        StoreTimetableResource stResource = new StoreTimetableResource(listtt, s.toResource(storeService.getScore(nif)));
        Link link = linkTo(methodOn(TimetableController.class).getStoreTimetable(nif)).withSelfRel();
        return new Resource<StoreTimetableResource>(stResource, stResource.getLinks(link));
    }
}
