package com.customersscheduling.Controller.ClientDTOs;

import com.customersscheduling.Domain.Service;
import com.customersscheduling.Domain.Store;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicesOfStore {

    public EmbeddedServicesOfStore _embedded;

    public ServicesOfStore(){}

}
