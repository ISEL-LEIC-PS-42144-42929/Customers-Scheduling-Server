package com.customersscheduling.Domain;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table( name = "Portfolio")
public class Portfolio {

    @Column(name="image")
    private Blob image;

    @Id
    @Column(name="idportfolio")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})

    @JoinColumn(name="store_nif",referencedColumnName="nif",nullable=false)
    private Store store;

    public Portfolio(){}

    public Portfolio(Blob image, String description, int storeId, Store store) {
        this.image = image;
        this.id = storeId;
        this.store = store;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
