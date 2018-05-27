package com.customersscheduling.Controller.InputModels;

import com.customersscheduling.DTO.Address;
import com.customersscheduling.DTO.Category;
import com.customersscheduling.DTO.Owner;
import com.customersscheduling.DTO.Store;

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
