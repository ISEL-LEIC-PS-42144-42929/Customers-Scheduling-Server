package com.customersscheduling.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinColumn(name="owner_user_person_email",referencedColumnName="user_person_email",nullable=false)
    private Owner owner;

    @JsonIgnore
    @OneToMany(mappedBy = "pk.business", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    private Set<Store> stores;

    public Business(){ }

    public Business(String companyName, int nif, Owner owner) {
        this.name = companyName;
        NIF = nif;
        this.owner = owner;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }
}
