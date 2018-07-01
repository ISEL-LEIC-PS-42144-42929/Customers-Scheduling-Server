package com.customersscheduling.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "Person")
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements Serializable{

    @Id
    @Column(name="email")
    private String email;

    @Column(name="name")
    private String name;

    @Column(name="contact")
    private String contact;

    @Column(name="gender")
    private int gender;

    public Person(){
        this.name="default_name";
        this.email="defaul_email";
    }


    public Person(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Person(String email, String name, String contact, int gender) {
        this.email = email;
        this.name = name;
        this.contact=contact;
        this.gender=gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

}
