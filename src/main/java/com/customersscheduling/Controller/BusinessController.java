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
                "Rua do Bito",
                new Owner("bitoowner@gmail.com", "bitoowner", 999),
                new Category("tech", "tech"),
                new StorePK(new Business("inout", 111),"Inout_Store")));
    }
}
