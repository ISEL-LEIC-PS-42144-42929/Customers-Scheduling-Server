package com.customersscheduling.Domain;

import javax.persistence.*;

@Entity
@Table( name = "User")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="person_email")
public class User extends Person {


    public User(){

    }


    public User(String email, String name) {
        super(email, name);
    }

}
