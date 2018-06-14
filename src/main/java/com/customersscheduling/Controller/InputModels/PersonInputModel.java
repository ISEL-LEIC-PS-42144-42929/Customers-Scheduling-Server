package com.customersscheduling.Controller.InputModels;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.Domain.Staff;

public class PersonInputModel {
    public String email;
    public String name;
    public String contact;
    public boolean gender;

    public Client toClientDto() {
        Client client = new Client(email, name);
        client.setContact(contact);
        client.setGender(gender == true ? 1 : 0);
        return client;
    }

    public Staff toStaffDto() {
        Staff staff = new Staff(email, name);
        staff.setContact(contact);
        staff.setGender(gender == true ? 1 : 0);
        return staff;
    }
}
