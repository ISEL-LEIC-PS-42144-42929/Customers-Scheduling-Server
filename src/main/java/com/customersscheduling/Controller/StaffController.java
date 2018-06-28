package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.HALObjects.StaffResource;
import com.customersscheduling.Service.IPersonService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/person", produces = "application/hal+json")
public class StaffController {

    private final IPersonService personService;

    public StaffController(IPersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/staff")
    public Resource<StaffResource> insertStaff(@RequestBody PersonInputModel person) {
        StaffResource personResource = personService.insertStaff(person.toStaffDto()).toResource();
        Resource<StaffResource> rp = new Resource<>(personResource);
        return rp;
    }

    @GetMapping(value = "/{email}/staff")
    public Resources<StaffResource> getStaff(@PathVariable String email) {
        return null;
    }

}