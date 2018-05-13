package com.customersscheduling.DTO;

import javax.persistence.*;

@Entity(name = "Store")
@Table( name = "Store")
public class Store {

    @EmbeddedId
    private StorePK pk;

    @Column(name="address")
    private String address;

    @OneToOne
    @JoinColumn(name="owner_user_person_email",referencedColumnName="user_person_email",nullable=false)
    private Owner owner;

    @OneToOne
    @JoinColumn(name="category_name",referencedColumnName="name",nullable=false)
    private Category category;

    public Store(){}

    public Store(String addr, Owner ownerId, Category category, StorePK pk) {
        this.address = addr;
        this.owner = ownerId;
        this.category=category;
        this.pk = pk;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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
}
