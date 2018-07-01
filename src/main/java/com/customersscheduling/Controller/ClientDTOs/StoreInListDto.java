package com.customersscheduling.Controller.ClientDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.Link;
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreInListDto {

    public StoreDto store;

    public StoreInListDto(){}
}
