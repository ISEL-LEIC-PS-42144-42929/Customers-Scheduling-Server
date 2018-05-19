package com.customersscheduling.Controller;

import com.customersscheduling.DTO.*;
import com.customersscheduling.HALObjects.ClientResource;
import com.customersscheduling.HALObjects.PortfolioResource;
import com.customersscheduling.HALObjects.ServiceResource;
import com.customersscheduling.HALObjects.StoreResource;
import com.customersscheduling.Service.IStoreService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping("/store")
public class StoreController {

    private final IStoreService storeService;

    public StoreController(IStoreService businessService) {
        this.storeService = businessService;
    }

    @PostMapping(value = "", produces = "application/son")
    public void insertStore(HttpServletRequest request) {
        for (int i = 0; i < 10; i++) {
            Owner o = new Owner("bitoowner@gmail.com", "bitoowner", 999);
            Store s =new Store(
                    new Address("2690-321", "Rua do Bito", "221", "Lisbon", "Portugal"),
                    new Category("tech", "tech"),
                    "Bito_Store",
                    "969413432",
                    "123456789", o);
            storeService.insertStore(s);
        }
    }

    @PostMapping(value = "/{nif}/service", produces = "application/son")
    public void insertServiceForStore(HttpServletRequest request, @PathVariable String nif) {
        Owner o = new Owner("bitoowner@gmail.com", "bitoowner", 999);
        Store store = new Store(
                new Address("2690-321", "Rua do Bito", "221", "Lisbon", "Portugal"),
                new Category("tech", "tech"),
                "Bito_Store",
                "969413432",
                nif, o);
        Service s = new Service("abc","dbc",7.5, 15);

        StoreServices ss = new StoreServices(new StoreServicesPK(
                store,
                new Staff("bito_staff@gmail.com","bito_staff"),
                s
        ));
        storeService.insertServiceForStore(ss);
    }

    @PostMapping(value = "/book", produces = "application/son")
    public void insertBook(HttpServletRequest request) {
        Owner o = new Owner("bitoowner@gmail.com", "bitoowner", 999);
        Store store = new Store(
                new Address("2690-321", "Rua do Bito", "221", "Lisbon", "Portugal"),
                new Category("tech", "tech"),
                "Bito_Store",
                "969413432",
                "123456789", o);
        Service s = new Service("abc","dbc",7.5, 15);
        s.setId(7);
        Staff staff = new Staff("bito_staff@gmail.com","bito_staff");
        Client c = new Client("bito_user@gmail.com","bito_user");
        storeService.insertBook(new Booking(0 ,store,staff, null, s));
    }

    @PostMapping(value = "/store/book/client", produces = "application/son")
    public void setClientOnBook(HttpServletRequest request) {
        Client c = new Client("bito_user@gmail.com","bito_user");
        Booking book = storeService.getBookingById(9);
        book.setClient(c);
        storeService.insertBook(book);
    }

    @GetMapping(value = "/{nif}")
    public Resources<StoreResource> getStore(@PathVariable String nif) {
        return null;
    }

    @GetMapping(value = "/{nif}/services")
    public ResponseEntity<Resources<ServiceResource>> getServicesOfStore(@PathVariable String nif) {
        return null;
    }

    @GetMapping(value = "/{nif}/portfolio")
    public ResponseEntity<Resources<PortfolioResource>> getPortfolioOfStore(@PathVariable String nif) {
        return null;
    }

    @GetMapping(value = "/{nif}/services/staff")
    public ResponseEntity<Resources<ServiceResource>> getStaffOfService(@PathVariable String name, @PathVariable String nif) {
        return null;
    }

    @GetMapping(value = "/{nif}/services/disp")
    public ResponseEntity<Resources<ServiceResource>> getDispOfService(@PathVariable String nif) {
        return null;
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<Resources<StoreResource>> getStoresOfUser(@PathVariable String email) {
        final List<Store> stores = storeService.getStoresOfUser(email);
        final List<StoreResource> mappedStores = new ArrayList<>();
        stores.iterator().forEachRemaining( st ->
                mappedStores.add(new StoreResource(st))
        );
        Link link = linkTo(methodOn(StoreController.class)
                .getStoresOfUser(email)).withSelfRel();
        final Resources<StoreResource> resources = new Resources<StoreResource>(mappedStores, link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping(value = "/{nif}/pendentrequests")
    public ResponseEntity<Resources<ClientResource>> getPendentRequestsOfStore(@PathVariable String nif, @PathVariable String name) {
        return null;
    }
}
