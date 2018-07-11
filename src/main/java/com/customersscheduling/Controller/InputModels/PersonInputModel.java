package com.customersscheduling.Controller.InputModels;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.Domain.Staff;

public class PersonInputModel {
    public String email;
    public String name;
    public String contact;
    public int gender;

    public Client toClientDto() {
        Client client = new Client(email, name);
        client.setContact(contact);
        client.setGender(gender);
        return client;
    }

}
