package com.customersscheduling.Controller;

import com.customersscheduling.DTO.Service;
import com.customersscheduling.Service.IUtilService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
