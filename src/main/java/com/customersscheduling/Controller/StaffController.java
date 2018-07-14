package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.StaffInputModel;
import com.customersscheduling.Controller.Util.ResourcesUtil;
import com.customersscheduling.Domain.Service;
import com.customersscheduling.OutputResources.ServiceResource;
import com.customersscheduling.OutputResources.StaffResource;
import com.customersscheduling.Service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/person", produces = "application/hal+json")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    @PostMapping(value = "/staff")
    public Resource<StaffResource> insertStaff(@RequestBody StaffInputModel staff, HttpServletResponse resp) {
        StaffResource personResource = staffService.insertStaff(staff.toDto()).toResource();
        Link link = linkTo(methodOn(StaffController.class).insertStaff(staff, null)).withSelfRel();
        personResource.add(link);
        resp.setStatus(HttpStatus.CREATED.value());
        return new Resource<>(personResource);
    }

    @GetMapping(value = "/staff/{email}")
    public Resource<StaffResource> getStaff(@PathVariable String email) {
        StaffResource personResource = staffService.getStaff(email).toResource();
        Link link = linkTo(methodOn(StaffController.class).getStaff(email)).withSelfRel();
        personResource.add(link);
        return new Resource<>(personResource);
    }

    @PutMapping(value = "/staff/{email}")
    public Resource<StaffResource> updateStaff(@PathVariable String email, @RequestBody StaffInputModel person) {
        StaffResource personResource = staffService.updateStaff(email, person.toDto()).toResource();
        Link link = linkTo(methodOn(StaffController.class).getStaff(email)).withSelfRel();
        personResource.add(link);
        return new Resource<>(personResource);
    }

    @DeleteMapping(value = "/staff/{email}")
    public Resource<StaffResource> deleteStaff(@PathVariable String email) {
        StaffResource personResource = staffService.deleteStaff(email).toResource();
        Link link = linkTo(methodOn(StaffController.class).deleteStaff(email)).withSelfRel();
        personResource.add(link);
        return new Resource<>(personResource, personResource.getLinks(link));
    }

    @GetMapping(value = "/staff/{email}/services")
    public Resources<ServiceResource> getServicesOfStaff(@PathVariable String email) {
        List<ServiceResource> services = staffService.getServices(email)
                                                        .stream()
                                                        .map( i -> i.toResource(null))
                                                        .collect(Collectors.toList());
        Link link = linkTo(methodOn(StaffController.class).getServicesOfStaff(email)).withSelfRel();
        return ResourcesUtil.getResources(ServiceResource.class, services, link);
    }
}
