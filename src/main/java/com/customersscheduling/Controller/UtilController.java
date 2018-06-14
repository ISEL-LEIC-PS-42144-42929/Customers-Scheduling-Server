package com.customersscheduling.Controller;

import com.customersscheduling.Domain.Service;
import com.customersscheduling.HALObjects.ServiceResource;
import com.customersscheduling.Service.IUtilService;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/config")
public class UtilController {

    private final IUtilService utilService;

    public UtilController(IUtilService utilService) {
        this.utilService = utilService;
    }

    @PostMapping(value = "/service", produces = "application/son")
    public void insertService(HttpServletRequest request) {
        Service s = new Service("abc","dbc",7.5, 15);
        utilService.insertService(s);
    }

    @GetMapping(value = "/service/{id}")
    public ResponseEntity<Resources<ServiceResource>> getService(@PathVariable int id) {
        return null;
    }
}
