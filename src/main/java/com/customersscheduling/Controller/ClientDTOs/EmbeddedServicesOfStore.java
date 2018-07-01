package com.customersscheduling.Controller.ClientDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmbeddedServicesOfStore {
    public ServicesOfStoreList [] serviceResourceList;
    public EmbeddedServicesOfStore(){}
}
