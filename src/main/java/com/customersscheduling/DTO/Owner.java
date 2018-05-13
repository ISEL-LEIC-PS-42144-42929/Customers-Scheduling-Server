package com.customersscheduling.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table( name = "Owner")
@PrimaryKeyJoinColumn(name="user_person_email")
public class Owner extends User {

    @Column(name="NIF")
    private int nif;

    public Owner() {
    }

    public Owner(String email, String name, int nif) {
        super(email, name);
        this.nif = nif;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }
}
