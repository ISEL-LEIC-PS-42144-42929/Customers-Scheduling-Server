package com.customersscheduling.Controller;

import com.customersscheduling.DTO.*;
import com.customersscheduling.HALObjects.BusinessHAL;
import com.customersscheduling.Service.IBusinessService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;


@RestController
@RequestMapping("/business")
public class BusinessController {

    private final IBusinessService businessService;

    public BusinessController(IBusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping(value = "", produces = "application/son")
    public BusinessHAL insertBusiness(HttpServletRequest request) {
        return businessService.insertBusiness(new Business("inout", 111));
    }

    @PostMapping(value = "/store", produces = "application/son")
    public BusinessHAL insertStore(HttpServletRequest request) {
        return businessService.insertStore(new Store(
                "Rua do Bito 2",
                new Owner("bitoowner@gmail.com", "bitoowner", 999),
                new Category("tech", "tech"),
                new StorePK(new Business("inout", 111),"Inout_Store2")));
    }

    @PostMapping(value = "/store/service", produces = "application/son")
    public void insertServiceForStore(HttpServletRequest request) {
        Store store = new Store(
                "Rua do Bito 2",
                new Owner("bitoowner@gmail.com", "bitoowner", 999),
                new Category("tech", "tech"),
                new StorePK(new Business("inout", 111),"Inout_Store2"));
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
        Store store = new Store(
                "Rua do Bito 2",
                new Owner("bitoowner@gmail.com", "bitoowner", 999),
                new Category("tech", "tech"),
                new StorePK(new Business("inout", 111),"Inout_Store2"));
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
}
