package com.customersscheduling.Controller.InputModels;

import com.customersscheduling.Domain.Address;
import com.customersscheduling.Domain.Category;
import com.customersscheduling.Domain.Owner;
import com.customersscheduling.Domain.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class StoreInputModel {

    public String name;
    public String contact;
    public String category;
    public String nif;
    public String street;
    public String zip_code;
    public String lot;
    public String city;
    public String country;

    public Store toDto(Owner o) {
        Address address= new Address(zip_code, street, lot, city, country);
        Category cat = new Category();
        cat.setName(category);
        return new Store(address, cat, name, contact, nif, o);
    }
}
