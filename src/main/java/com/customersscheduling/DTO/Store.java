package com.customersscheduling.DTO;

public class StoreDto {
    private String storeName;
    private String streetName;
    private String postalCode;
    private String lot;
    private int id;
    private String locality;
    private String ownerId;

    public StoreDto(String storeName, String streetName, String postalCode, String lot, int id, String locality, String ownerId) {
        this.storeName = storeName;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.lot = lot;
        this.id = id;
        this.locality = locality;
        this.ownerId = ownerId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
