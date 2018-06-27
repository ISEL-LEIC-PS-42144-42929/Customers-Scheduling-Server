package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.OwnerInputModel;
import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Controller.InputModels.TimetableInputModel;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.*;
import com.customersscheduling.Service.IPersonService;
import com.customersscheduling.Service.IStoreService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/person", produces = "application/hal+json")
public class PersonController {

    private final IPersonService personService;

    private final IStoreService storeService;

    public PersonController(IPersonService personService,IStoreService storeService) {
        this.personService = personService;
        this.storeService = storeService;
    }

    @PostMapping(value = "/client")
    public Resource<ClientResource> insertClient(@RequestBody PersonInputModel person) {
        ClientResource personResource = personService.insertClient(person.toClientDto());
        Resource<ClientResource> rp = new Resource<>(personResource);
        return rp;
    }

    @PostMapping(value = "/owner")
    public Resource<OwnerResource>  insertOwner(@RequestBody OwnerInputModel owner) {
        OwnerResource personResource = personService.insertOwner(owner.toOwnerDto());
        Resource<OwnerResource> rp = new Resource<>(personResource);
        return rp;
    }

    @PostMapping(value = "/staff")
    public Resource<StaffResource>  insertStaff(@RequestBody PersonInputModel person) {
        StaffResource personResource = personService.insertStaff(person.toStaffDto());
        Resource<StaffResource> rp = new Resource<>(personResource);
        return rp;
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


    @GetMapping(value = "/client/{email}")
    public ResponseEntity<Resource<StoreResource>> getStoresOfClient(@PathVariable String email) {
        return null;
    }

    @GetMapping(value = "/owner/{email}")
    public ResponseEntity<Resources<StoreResource>> getStoresOfOwner(@PathVariable("email") String email) {
        final List<Store> stores = storeService.getStoresOfUser(email);
        final List<StoreResource> mappedStores = new ArrayList<>();
        stores.iterator().forEachRemaining( st ->
                mappedStores.add(new StoreResource(st))
        );
        final Resources<StoreResource> resources = new Resources<StoreResource>(mappedStores);
        return ResponseEntity.ok(resources);
    }
}
