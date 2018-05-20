package com.customersscheduling.HALObjects;

import com.customersscheduling.DTO.Service;
import org.springframework.hateoas.ResourceSupport;

public class ServiceResource extends ResourceSupport {

    private final Service service;

    public ServiceResource(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }
}
