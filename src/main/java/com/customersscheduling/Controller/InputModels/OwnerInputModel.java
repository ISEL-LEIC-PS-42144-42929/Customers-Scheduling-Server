package com.customersscheduling.Controller.InputModels;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.Domain.Owner;

public class OwnerInputModel extends PersonInputModel{
    public String nif;

    public Owner toOwnerDto(){
        Client c= new Client();
        c.setEmail(email);
        Owner o = new Owner(nif, c);
        return o;
    }
}
