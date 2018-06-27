package com.customersscheduling.Controller;

import com.customersscheduling.Controller.InputModels.BookInputModel;
import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Controller.InputModels.ServiceInputModel;
import com.customersscheduling.Controller.InputModels.StoreInputModel;
import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.*;
import com.customersscheduling.Service.IStoreService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping(value="/store", produces = "application/hal+json")
public class StoreController {

    private final IStoreService storeService;

    public StoreController(IStoreService businessService) {
        this.storeService = businessService;
    }

    @PostMapping(value = "/{email}")
    public StoreResource insertStore(@PathVariable String email, @RequestBody StoreInputModel store) {
        Owner o = new Owner();
        o.setEmail(email);
        return storeService.insertStore(store.toDto(o));
    }

    @PostMapping(value = "/{nif}/service")
    public ServiceResource insertServiceForStore(@RequestBody ServiceInputModel service, @PathVariable String nif) {
        Store store = new Store(); store.setNif(nif);
        StoreServices ss = new StoreServices(new StoreServicesPK(store,service.toDto()));
        return storeService.insertServiceForStore(ss);
    }

    @PostMapping(value = "/{nif}/service/{id}/staff")
    public ServiceResource insertStaffForService(@RequestBody PersonInputModel person, @PathVariable String nif, @PathVariable int id) {
        Store store = new Store(); store.setNif(nif);
        Staff staff = person.toStaffDto();
        Service service = new Service(); service.setId(id);
        StaffServices ss = new StaffServices(new StaffServicesPK(staff, new StoreServices(new StoreServicesPK(store, service))));
        return storeService.insertStafForService(ss);
    }

    @PostMapping(value = "/{nif}/service/{id}/staff/{email}/book")
    public BookingResource insertBook(@RequestBody BookInputModel book, @PathVariable String nif, @PathVariable int id, @PathVariable String email) {
        Store store = new Store(); store.setNif(nif);
        Staff staff = new Staff(); staff.setEmail(email);
        Service service = new Service(); service.setId(id);
        Client client = new Client(); client.setEmail(book.client_email);
        Booking booking = new Booking(store, staff, client, service);
        return storeService.insertBook(booking);
    }

    @PostMapping(value = "/{nif}/client/{email}")
    public StoreResource setClientForStore(@PathVariable String nif, @PathVariable String email) {
        Client c = new Client();
        Store s = new Store();
        ClientStores cs = new ClientStores(new ClientStoresPK(s,c),false, 0);
        return storeService.insertClientForStore(cs);
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

    @GetMapping(value = "/{nif}/services/{id}/disp")
    public ResponseEntity<Resources<BookingResource>> getDispOfService(@PathVariable String nif, @PathVariable int id) {
        final List<Booking> books = storeService.getServiceDisp(id);
        final List<BookingResource> mappedBooking = new ArrayList<>();
        books.iterator().forEachRemaining( st ->
                mappedBooking.add(new BookingResource(st))
        );
        Link link = linkTo(methodOn(StoreController.class).getDispOfService(nif, id)).withSelfRel();
        final Resources<BookingResource> resources = new Resources<BookingResource>(mappedBooking, link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping(value = "/{nif}/pendentrequests")
    public ResponseEntity<Resources<ClientResource>> getPendentRequestsOfStore(@PathVariable String nif) {
        final List<Client> clients = storeService.getPendentRequests(nif);
        final List<ClientResource> mappedClients = new ArrayList<>();
        clients.iterator().forEachRemaining( client ->
                mappedClients.add(new ClientResource(client))
        );
        Link link = linkTo(methodOn(StoreController.class)
                .getPendentRequestsOfStore(nif)).withSelfRel();
        final Resources<ClientResource> resources = new Resources<ClientResource>(mappedClients, link);
        return ResponseEntity.ok(resources);
    }
}
