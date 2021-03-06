package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.OwnerInputModel;
import com.customersscheduling.Controller.Util.ResourcesUtil;
import com.customersscheduling.OutputResources.OwnerResource;
import com.customersscheduling.OutputResources.StoreResource;
import com.customersscheduling.Service.IOwnerService;
import com.customersscheduling.Service.IStoreService;
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
public class OwnerController {

    @Autowired
    private IOwnerService personService;

    @Autowired
    private IStoreService storeService;


    @PostMapping(value = "/owner")
    public Resource<OwnerResource> insertOwner(@RequestBody OwnerInputModel owner, HttpServletResponse resp) {
        OwnerResource personResource = personService.insertOwner(owner.toOwnerDto()).toOwnerResource();
        Link link = linkTo(methodOn(OwnerController.class).insertOwner(owner, null)).withSelfRel();
        personResource.add(link);
        resp.setStatus(HttpStatus.CREATED.value());
        return new Resource<>(personResource);
    }

    @GetMapping(value = "/owner/{email}")
    public Resource<OwnerResource> getOwner(@PathVariable String email) {
        OwnerResource personResource = personService.getOwner(email).toOwnerResource();
        Link link = linkTo(methodOn(OwnerController.class).getOwner(email)).withSelfRel();
        personResource.add(link);
        return new Resource<>(personResource);
    }


    @DeleteMapping(value = "/owner/{email}")
    public Resource<OwnerResource> deleteOwner(@PathVariable String email) {
        OwnerResource personResource = personService.deleteOwner(email).toOwnerResource();
        Link link = linkTo(methodOn(OwnerController.class).deleteOwner(email)).withSelfRel();
        Resource<OwnerResource> rp = new Resource<>(personResource, personResource.getLinks(link));
        return rp;
    }


    @GetMapping(value = "/owner/{email}/stores")
    public Resources<StoreResource> getStoreOfOwner(@PathVariable String email) {
        List<StoreResource> storesOfUser = storeService.getStoresOfUser(email)
                .stream()
                .map( i -> i.toResource(storeService.getScore(i.getNif())))
                .collect(Collectors.toList());
        Link link = linkTo(methodOn(OwnerController.class).getStoreOfOwner(email)).withSelfRel();
        return ResourcesUtil.getResources(StoreResource.class, storesOfUser, link);
    }
}

