package com.customersscheduling.Controller;

import com.customersscheduling.DTO.*;
import com.customersscheduling.HALObjects.*;
import com.customersscheduling.Service.IPersonService;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/person", produces = "application/hal+json")
public class PersonController {

    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/client", produces = "application/json")
    public void insertClient(HttpServletRequest request) {
        personService.insertClient(new Client("bito_user@gmail.com","bito_user"));
    }

    @PostMapping(value = "/owner", produces = "application/json")
    public void insertOwner(HttpServletRequest request) {
        personService.insertOwner(new Owner("bitoowner@gmail.com","bitoowner",999));
    }

    @PostMapping(value = "/staff", produces = "application/json")
    public void insertStaff(HttpServletRequest request) {
        personService.insertStaff(new Staff("bito_staff@gmail.com","bito_staff"));
    }

    @PostMapping(value = "/staff/timetable", produces = "application/json")
    public void insertStaffTimetable(HttpServletRequest request) {
        Timetable t1=new Timetable(12.0, 12.0, 18.0, 18.0, "monday");
        Timetable t2=new Timetable(0, 0, 0, 0, "thursday");
        Staff staff = new Staff("bito_staff3@gmail.com","bito_staff3");
        staff.getTimetable().add(t1);
        staff.getTimetable().add(t2);
        personService.insertStaffTimetable(staff);
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
