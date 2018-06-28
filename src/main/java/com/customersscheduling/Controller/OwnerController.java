package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.OwnerInputModel;
import com.customersscheduling.HALObjects.OwnerResource;
import com.customersscheduling.Service.IPersonService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/person", produces = "application/hal+json")
public class OwnerController {

    private final IPersonService personService;

    public OwnerController(IPersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/owner")
    public Resource<OwnerResource> insertOwner(@RequestBody OwnerInputModel owner) {
        OwnerResource personResource = personService.insertOwner(owner.toOwnerDto()).toResource();
        Resource<OwnerResource> rp = new Resource<>(personResource);
        return rp;
    }

    @GetMapping(value = "/{email}/owner")
    public Resources<OwnerResource> getOwner(@PathVariable String email) {
        return null;
    }
}

