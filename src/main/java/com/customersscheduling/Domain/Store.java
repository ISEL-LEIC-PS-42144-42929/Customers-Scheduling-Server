package com.customersscheduling.Domain;

import com.customersscheduling.HALObjects.StoreResource;

import javax.persistence.*;

@Entity(name = "Store")
@Table( name = "Store")
public class Store {

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinColumn(name="address_id",referencedColumnName="id",nullable=false)
    private Address address;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinColumn(name="category_name",referencedColumnName="name",nullable=false)
    private Category category;

    @Column(name="contact")
    private String contact;

    @Column(name="store_name")
    private String storeName;

    @Id
    @Column(name="nif", length=9)
    private String nif;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="owner_user_person_email",referencedColumnName="user_person_email",nullable=false)
    private Owner owner;

    public Store(){}

    public Store(Address addr, Category category, String name, String contact, String nif, Owner owner) {
        this.address = addr;
        this.category=category;
        this.storeName = name;
        this.contact = contact;
        this.nif = nif;
        this.owner = owner;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public StoreResource toResource() {
        return new StoreResource(this);
    }
}
