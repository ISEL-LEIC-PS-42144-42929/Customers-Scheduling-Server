package com.customersscheduling.Controller;

import com.customersscheduling.Service.IClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {


    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }
}
