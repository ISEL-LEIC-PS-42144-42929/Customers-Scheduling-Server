package com.customersscheduling.DTO;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class StorePK implements Serializable {

    @ManyToOne
    @JoinColumn(name="business_nif", referencedColumnName = "nif", nullable=false)
    private Business business;

    @Column(name="store_name")
    private String storeName;

    public StorePK(){}

    public StorePK(Business business, String storeName) {
        this.business = business;
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
