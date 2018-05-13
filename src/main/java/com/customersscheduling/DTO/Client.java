package com.customersscheduling.DTO;

import javax.persistence.*;

@Entity
@Table( name = "Client")
@PrimaryKeyJoinColumn(name="user_person_email")
public class Client extends User {

    public Client(){
        super("defaultemail", "default_name");
    }

    public Client(String email, String name) {
        super(email, name);
    }

}
