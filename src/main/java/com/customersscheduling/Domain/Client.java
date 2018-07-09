package com.customersscheduling.Domain;

import com.customersscheduling.HALObjects.ClientResource;

import javax.persistence.*;

@Entity
@Table( name = "Client")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="person_email")
public class Client extends Person {



    public Client(){
    }

    public Client(String email){
        super(email);
    }

    public Client(String email, String name) {
        super(email, name);
    }

    public ClientResource toResource() {
        return new ClientResource(this);
    }

}
