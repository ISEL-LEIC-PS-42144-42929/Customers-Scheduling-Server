package com.customersscheduling.DTO;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name = "Business")
public class Business {

    @Column(name="name")
    private String name;

    @Id
    @Column(name="NIF")
    private int NIF;

    public Business(){ }

    public Business(String companyName, int nif) {
        this.name = companyName;
        NIF = nif;
    }

    public String getCompanyName() {
        return name;
    }

    public void setCompanyName(String companyName) {
        this.name = companyName;
    }

    public int getNIF() {
        return NIF;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }
}
