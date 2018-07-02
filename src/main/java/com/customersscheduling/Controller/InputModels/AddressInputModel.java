package com.customersscheduling.Controller.InputModels;

import com.customersscheduling.Domain.Address;

public class AddressInputModel {
    public String zip_code;
    public String lot;
    public String city;
    public String country;
    public String street;

    public Address toDto(){
        return new Address(zip_code,street, lot, city, country);
    }
}
