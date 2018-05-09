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

    @Column(name="Category_name")
    private String cat_name;

    @Id
    @Column(name="NIF")
    private int NIF;

    public BusinessDto(){
        name="default";
        NIF=-1;
        cat_name="default_cat_name";
    }

    public BusinessDto(String companyName, int nif, String cat_name) {
        this.name = companyName;
        NIF = nif;
        this.cat_name=cat_name;
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

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }
}
