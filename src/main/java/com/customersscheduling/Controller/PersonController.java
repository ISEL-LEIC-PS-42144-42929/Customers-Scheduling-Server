package com.customersscheduling.Controller;

import com.customersscheduling.DTO.ClientDto;
import com.customersscheduling.Service.IPersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/client", produces = "application/son")
    public void insertClient(HttpServletRequest request) {
        personService.insertClient(new ClientDto("bito@gmail.com","bito"));
    }
}
