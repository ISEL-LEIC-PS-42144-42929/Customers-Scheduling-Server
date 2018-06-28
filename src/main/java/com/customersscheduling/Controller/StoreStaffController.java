package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.ServiceResource;
import com.customersscheduling.Service.IStoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/store", produces = "application/hal+json")
public class StoreStaffController {


    private final IStoreService storeService;

    public StoreStaffController(IStoreService businessService) {
        this.storeService = businessService;
    }


    @PostMapping(value = "/{nif}/service/{id}/staff")
    public ServiceResource insertStaffForService(@RequestBody PersonInputModel person, @PathVariable String nif, @PathVariable int id) {
        Store store = new Store(); store.setNif(nif);
        Staff staff = person.toStaffDto();
        Service service = new Service(); service.setId(id);
        StaffServices ss = new StaffServices(new StaffServicesPK(staff, new StoreServices(new StoreServicesPK(store, service))));
        return storeService.insertStafForService(ss);
    }
}
