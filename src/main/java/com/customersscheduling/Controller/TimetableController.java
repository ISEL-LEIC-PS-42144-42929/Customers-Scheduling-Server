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
        StaffResource staffResource = personService.insertStaffTimetable(new StaffTimetable(new StaffTimetablePK(tt, staff)));
        Resource<StaffResource> staffRe = new Resource<>(staffResource);
        return staffRe;
    }

    @GetMapping(value = "/staff/{email}")
    public Resource<StaffTimetableResource> getStaffTimetable(@PathVariable String email) {
        StaffTimetableResource ttResource = personService.getStaffTimetable(email);
        Resource<StaffTimetableResource> staffRe = new Resource<>(ttResource);
        return staffRe;
    }


    @PostMapping(value = "/store/{nif}")
    public Resource<StoreResource> insertStoreTimetable(@PathVariable String nif, @RequestBody TimetableInputModel timetable) {
        Store s = new Store();
        s.setNif(nif);
        Timetable tt = timetable.toDto();
        StoreResource storeResource = storeService.insertStoreTimetable(new StoreTimetable(new StoreTimetablePK(tt, s)));
        Resource<StoreResource> resource = new Resource<StoreResource>(storeResource);
        return resource;
    }


    @GetMapping(value = "/store/{nif}")
    public Resource<TimetableResource> getStoreTimetable(@PathVariable String nif) {
        return null;
    }
}
