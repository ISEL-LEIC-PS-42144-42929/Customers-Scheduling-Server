package com.customersscheduling.Domain;

import com.customersscheduling.OutputResources.StaffResource;

import javax.persistence.*;

@Entity
@Table( name = "staff")
@PrimaryKeyJoinColumn(name="person_email")
public class Staff extends Person {

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="store_nif",referencedColumnName="nif", nullable=false)
    private Store store;;

    public Staff(){
    }

    public Staff(String email, String name, Store s) {
        super(email, name);
        store = s;
    }

    public StaffResource toResource() {
        return new StaffResource(this);
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
