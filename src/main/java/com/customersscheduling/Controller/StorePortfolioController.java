package com.customersscheduling.Controller;

import com.customersscheduling.HALObjects.PortfolioResource;
import com.customersscheduling.Service.IStoreService;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/store", produces = "application/hal+json")
public class StorePortfolioController {

    private final IStoreService storeService;

    public StorePortfolioController(IStoreService businessService) {
        this.storeService = businessService;
    }

    @GetMapping(value = "/{nif}/portfolio")
    public ResponseEntity<Resources<PortfolioResource>> getPortfolioOfStore(@PathVariable String nif) {
        return null;
    }
}
