package com.customersscheduling.Domain;

import com.customersscheduling.HALObjects.OwnerResource;
import com.customersscheduling.HALObjects.PersonResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table( name = "Owner")
@PrimaryKeyJoinColumn(name="user_person_email")
public class Owner extends User {

    @Column(name="NIF")
    private String nif;

    public Owner() {
    }

    public Owner(String email, String name, String nif) {
        super(email, name);
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public OwnerResource toResource() {
        return new OwnerResource(this);
    }
}
