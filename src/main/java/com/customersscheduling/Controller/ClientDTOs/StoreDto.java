package com.customersscheduling.Controller.ClientDTOs;

public class StoreDto {
    private AddressDto address;
    private CategoryDto category;
    private String storeName;
    private String nif;
    private Link[] links;

    public StoreDto(AddressDto address, CategoryDto category, String storeName, String nif, Link[] links) {
        this.address = address;
        this.category = category;
        this.storeName = storeName;
        this.nif = nif;
        this.links = links;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Link[] getLinks() {
        return links;
    }

    public void setLinks(Link[] links) {
        this.links = links;
    }
}
