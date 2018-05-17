package com.customersscheduling.Controller;

import com.customersscheduling.DTO.*;
import com.customersscheduling.HALObjects.StoreResource;
import com.customersscheduling.Service.IBusinessService;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/business")
public class BusinessController {

    private final IBusinessService businessService;

    public BusinessController(IBusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping(value = "", produces = "application/son")
    public void insertBusiness(HttpServletRequest request) {
        businessService.insertBusiness(new Business("inout", 111, new Owner("bitoowner@gmail.com", "bitoowner", 999)));
    }

    @PostMapping(value = "/store", produces = "application/son")
    public void insertStore(HttpServletRequest request) {
        for (int i = 0; i < 10; i++) {
            Owner o = new Owner("bitoowner@gmail.com", "bitoowner", 999);
            Store s =new Store(
                    new Address("2690-321", "Rua do Bito", "221", "Lisbon", "Portugal"),
                    new Category("tech", "tech"),
                    new StorePK(new Business("inout", 111, o),"Inout_Store2"+i), "969413432");
            businessService.insertStore(s);
        }
    }

    @PostMapping(value = "/store/service", produces = "application/son")
    public void insertServiceForStore(HttpServletRequest request) {
        Owner o = new Owner("bitoowner@gmail.com", "bitoowner", 999);
        Store store = new Store(
                new Address("2690-321", "Rua do Bito", "221", "Lisbon", "Portugal"),
                new Category("tech", "tech"),
                new StorePK(new Business("inout", 111, o),"Inout_Store2"), "969413432");
        Service s = new Service("abc","dbc",7.5, 15);

        StoreServices ss = new StoreServices(new StoreServicesPK(
                store,
                new Staff("bito_staff@gmail.com","bito_staff"),
                s
        ));
        businessService.insertServiceForStore(ss);
    }

    @PostMapping(value = "/store/book", produces = "application/son")
    public void insertBook(HttpServletRequest request) {
        Owner o = new Owner("bitoowner@gmail.com", "bitoowner", 999);
        Store store = new Store(
                new Address("2690-321", "Rua do Bito", "221", "Lisbon", "Portugal"),
                new Category("tech", "tech"),
                new StorePK(new Business("inout", 111, o),"Inout_Store2"), "969413432");
        Service s = new Service("abc","dbc",7.5, 15);
        s.setId(7);
        Staff staff = new Staff("bito_staff@gmail.com","bito_staff");
        Client c = new Client("bito_user@gmail.com","bito_user");
        businessService.insertBook(new Booking(0 ,store,staff, null, s));
    }

    @PostMapping(value = "/store/book/client", produces = "application/son")
    public void setClientOnBook(HttpServletRequest request) {
        Client c = new Client("bito_user@gmail.com","bito_user");
        Booking book = businessService.getBookingById(9);
        book.setClient(c);
        businessService.insertBook(book);
    }

    @GetMapping(value = "/{nif}/store/{name}")
    public Resources<StoreResource> getStore(@PathVariable String name, @PathVariable int nif) {
        return null;
    }


    @GetMapping(value = "/{nif}/stores")
    public ResponseEntity<Resources<StoreResource>> getBusinessStores(HttpServletRequest request, @PathVariable int nif) {
        final List<Store> stores = businessService.getStoresByNif(nif);
        final List<StoreResource> mappedStores = new ArrayList<>();
        stores.iterator().forEachRemaining( st ->
                mappedStores.add(new StoreResource(st))
        );
        final Resources<StoreResource> resources = new Resources<StoreResource>(mappedStores);
        return ResponseEntity.ok(resources);
    }
}
