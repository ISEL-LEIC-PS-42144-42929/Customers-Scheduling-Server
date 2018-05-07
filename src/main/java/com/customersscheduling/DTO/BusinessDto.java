package com.customersscheduling.DTO;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "Business")
public class BusinessDto {

    @Column(name="name")
    private String name;
    @Id
    @Column(name="NIF")
    private int NIF;

    public BusinessDto(String companyName, int nif) {
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
