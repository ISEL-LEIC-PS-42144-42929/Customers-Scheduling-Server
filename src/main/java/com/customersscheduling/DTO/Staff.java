package com.customersscheduling.DTO;

import com.customersscheduling.HALObjects.StaffResource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "staff")
@PrimaryKeyJoinColumn(name="person_email")
public class Staff extends Person {


    public Staff(){
    }

    public Staff(String email, String name) {
        super(email, name);
    }

    public StaffResource toResource() {
        return new StaffResource(this);
    }
}
