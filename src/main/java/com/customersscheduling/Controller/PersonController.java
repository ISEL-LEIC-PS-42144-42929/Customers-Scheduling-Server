package com.customersscheduling.Controller;

import com.customersscheduling.DTO.ClientDto;
import com.customersscheduling.DTO.OwnerDto;
import com.customersscheduling.DTO.StaffDto;
import com.customersscheduling.DTO.TimetableDto;
import com.customersscheduling.Service.IPersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        personService.insertClient(new ClientDto("bito_user@gmail.com","bito_user"));
    }

    @PostMapping(value = "/owner", produces = "application/json")
    public void insertOwner(HttpServletRequest request) {
        personService.insertOwner(new OwnerDto("bito_owner@gmail.com","bito_owner",1234));
    }

    @PostMapping(value = "/staff", produces = "application/json")
    public void insertStaff(HttpServletRequest request) {
        personService.insertStaff(new StaffDto("bito_staff@gmail.com","bito_staff"));
    }

    @PostMapping(value = "/staff/timetable", produces = "application/json")
    public void insertStaffTimetable(HttpServletRequest request) {
        TimetableDto t=new TimetableDto(8.0, 16.0, 12.0, 15.0);
        StaffDto staff = new StaffDto("bito_staff2@gmail.com","bito_staff2");
        staff.getTimetable().add(t);
        t.getStaff().add(staff);
        personService.insertStaffTimetable(t);
    }
}
