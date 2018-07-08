package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.HALObjects.OwnerResource;
import com.customersscheduling.HALObjects.StaffResource;
import com.customersscheduling.Service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/person", produces = "application/hal+json")
public class StaffController {

    @Autowired
    private IStaffService personService;

    @PostMapping(value = "/staff")
    public Resource<StaffResource> insertStaff(@RequestBody PersonInputModel person) {
        StaffResource personResource = personService.insertStaff(person.toStaffDto()).toResource();
        Resource<StaffResource> rp = new Resource<>(personResource);
        return rp;
    }

    @GetMapping(value = "/staff/{email}")
    public Resource<StaffResource> getStaff(@PathVariable String email) {
        StaffResource personResource = personService.getStaff(email).toResource();
        Link link = linkTo(methodOn(StaffController.class).getStaff(email)).withSelfRel();
        Resource<StaffResource> rp = new Resource<>(personResource, personResource.getLinks(link));
        return rp;
    }

    @PutMapping(value = "/staff/{email}")
    public Resource<StaffResource> updateStaff(@PathVariable String email, @RequestBody PersonInputModel person) {
        StaffResource personResource = personService.updateStaff(email, person.toStaffDto()).toResource();
        Link link = linkTo(methodOn(StaffController.class).getStaff(email)).withSelfRel();
        Resource<StaffResource> rp = new Resource<>(personResource, personResource.getLinks(link));
        return rp;
    }

    @DeleteMapping(value = "/staff/{email}")
    public Resource<StaffResource> deleteStaff(@PathVariable String email) {
        StaffResource personResource = personService.deleteStaff(email).toResource();
        Link link = linkTo(methodOn(StaffController.class).deleteStaff(email)).withSelfRel();
        Resource<StaffResource> rp = new Resource<>(personResource, personResource.getLinks(link));
        return rp;
    }
}
