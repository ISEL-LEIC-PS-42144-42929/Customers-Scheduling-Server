package com.customersscheduling.DTO;

import javax.persistence.*;

@Entity
@Table( name = "Address")
public class Address {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="zip_code", length = 10)
    private String zip_code;

    @Column(name="street", length = 50)
    private String street;

    @Column(name="lot", length = 10)
    private String lot;

    @Column(name="city", length = 45)
    private String city;

    @Column(name="country", length = 45)
    private String country;

    public Address(){}

    public Address(String zip_code, String street, String lot, String city, String country) {
        this.zip_code = zip_code;
        this.street = street;
        this.lot = lot;
        this.city = city;
        this.country = country;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
