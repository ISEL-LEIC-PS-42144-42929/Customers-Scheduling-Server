package com.customersscheduling.DTO;

import com.customersscheduling.HALObjects.ClientResource;

import javax.persistence.*;

@Entity
@Table( name = "Client")
@PrimaryKeyJoinColumn(name="user_person_email")
public class Client extends User {

    public Client(){
    }

    public Client(String email, String name) {
        super(email, name);
    }

    public ClientResource toResource() {
        return new ClientResource(this);
    }
}
