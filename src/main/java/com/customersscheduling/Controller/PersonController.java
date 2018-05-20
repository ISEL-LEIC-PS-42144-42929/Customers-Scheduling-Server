package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.OwnerInputModel;
import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Controller.InputModels.TimetableInputModel;
import com.customersscheduling.DTO.*;
import com.customersscheduling.HALObjects.*;
import com.customersscheduling.Service.IPersonService;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/person", produces = "application/hal+json")
public class PersonController {

    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/client")
    public ClientResource insertClient(@RequestBody PersonInputModel person) {
        return personService.insertClient(person.toClientDto());
    }

    @PostMapping(value = "/owner")
    public OwnerResource insertOwner(@RequestBody OwnerInputModel owner) {
        return personService.insertOwner(owner.toOwnerDto());
    }

    @PostMapping(value = "/staff", produces = "application/json")
    public StaffResource insertStaff(@RequestBody PersonInputModel person) {
        return personService.insertStaff(person.toStaffDto());
    }

    @PostMapping(value = "/{email}/timetable")
    public StaffResource insertStaffTimetable(@PathVariable String email, @RequestBody TimetableInputModel timetable) {
        Staff staff = new Staff(); staff.setEmail(email);
        Timetable tt = timetable.toDto();
        return personService.insertStaffTimetable(new StaffTimetable(new StaffTimetablePK(tt, staff)));
    }

    @GetMapping(value = "/{email}/owner")
    public Resources<OwnerResource> getOwner(@PathVariable String email) {
        return null;
    }

    @GetMapping(value = "/{email}/client")
    public Resources<ClientResource> getClient(@PathVariable String email) {
        return null;
    }

    @GetMapping(value = "/{email}/staff")
    public Resources<StaffResource> getStaff(@PathVariable String email) {
        return null;
    }

    @GetMapping(value = "/{email}/pendentrequests")
    public ResponseEntity<Resources<StoreResource>> getPendentRequestOfClient(@PathVariable String email) {
        return null;
    }

    @GetMapping(value = "/{email}/books")
    public ResponseEntity<Resources<BookingResource>> getBooksOfClient(@PathVariable String email) {
        return null;
    }
}
