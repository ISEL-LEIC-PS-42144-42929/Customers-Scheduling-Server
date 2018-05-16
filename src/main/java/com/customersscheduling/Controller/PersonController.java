package com.customersscheduling.Controller;

import com.customersscheduling.DTO.Client;
import com.customersscheduling.DTO.Owner;
import com.customersscheduling.DTO.Staff;
import com.customersscheduling.DTO.Timetable;
import com.customersscheduling.Service.IPersonService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/person")
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

    @GetMapping(value = "/{email}/stores", produces = "application/hal+json")
    public void getUserStores(HttpServletRequest request, @PathVariable String email) {

        return;
    }
}
