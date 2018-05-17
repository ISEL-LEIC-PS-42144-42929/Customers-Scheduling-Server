package com.customersscheduling.DTO;

import javax.persistence.*;

@Entity(name = "Store")
@Table( name = "Store")
public class Store {

    @EmbeddedId
    private StorePK pk;

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



    public Store(){}

    public Store(Address addr, Category category, StorePK pk, String contact) {
        this.address = addr;
        this.category=category;
        this.pk = pk;
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public StorePK getPk() {
        return pk;
    }

    public void setPk(StorePK pk) {
        this.pk = pk;
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
}
