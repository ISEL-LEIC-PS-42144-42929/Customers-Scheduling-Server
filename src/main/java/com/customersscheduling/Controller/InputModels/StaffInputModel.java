package com.customersscheduling.Controller.InputModels;

import com.customersscheduling.Domain.Staff;
import com.customersscheduling.Domain.Store;

public class StaffInputModel extends PersonInputModel {
    public String nif;


    public Staff toDto() {
        Store s = new Store();
        s.setNif(nif);
        Staff staff = new Staff(email, name, s);
        if(contact!=null)
            staff.setContact(contact);
        staff.setGender(gender);
        return staff;
    }

}
