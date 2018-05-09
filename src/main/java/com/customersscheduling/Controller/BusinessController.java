package com.customersscheduling.Controller;

import com.customersscheduling.DTO.BusinessDto;
import com.customersscheduling.HALObjects.BusinessHAL;
import com.customersscheduling.Service.IBusinessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/business")
public class BusinessController {

    private final IBusinessService businessService;

    public BusinessController(IBusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping(value = "", produces = "application/son")
    public BusinessHAL insertBusiness(HttpServletRequest request) {
        return businessService.insertBusiness(new BusinessDto("Inout",123456789, "Tecnology"));
    }

}
