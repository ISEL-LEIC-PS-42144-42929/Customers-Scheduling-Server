package com.customersscheduling.DTO;

import java.sql.Blob;

public class StorePortfolio {

    private Blob image;
    private String description;
    private int storeId;

    public StorePortfolio(Blob image, String description, int storeId) {
        this.image = image;
        this.description = description;
        this.storeId = storeId;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
