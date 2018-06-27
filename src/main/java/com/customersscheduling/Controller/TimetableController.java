package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.StoreInputModel;
import com.customersscheduling.Controller.InputModels.TimetableInputModel;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.StaffResource;
import com.customersscheduling.HALObjects.StoreResource;
import com.customersscheduling.Service.IPersonService;
import com.customersscheduling.Service.IStoreService;
import org.springframework.web.bind.annotation.*;

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
    public StaffResource insertStaffTimetable(@PathVariable String email, @RequestBody TimetableInputModel timetable) {
        Staff staff = new Staff(); staff.setEmail(email);
        Timetable tt = timetable.toDto();
        return personService.insertStaffTimetable(new StaffTimetable(new StaffTimetablePK(tt, staff)));
    }
}
