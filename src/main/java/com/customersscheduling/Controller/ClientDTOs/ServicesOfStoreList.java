package com.customersscheduling.Controller.ClientDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicesOfStoreList {

    public ServiceDto service;
    public StoreInListDto store;

    public ServicesOfStoreList(){}

}
