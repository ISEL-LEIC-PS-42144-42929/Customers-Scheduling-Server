package com.customersscheduling.Controller.InputModels;

import com.customersscheduling.DTO.Owner;

public class OwnerInputModel extends PersonInputModel{
    public String nif;

    public Owner toOwnerDto(){
        Owner o = new Owner(email, name, nif);
        o.setContact(contact);
        o.setGender(gender == true ? 1 : 0);
        return o;
    }
}
