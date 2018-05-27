package com.customersscheduling.Controller.ClientDTOs;

public class ClientStores {
    private Link[] links;
    private StoreDto[] content;

    public Link[] getLinks() {
        return links;
    }

    public void setLinks(Link[] links) {
        this.links = links;
    }

    public StoreDto[] getContent() {
        return content;
    }

    public void setContent(StoreDto[] content) {
        this.content = content;
    }
}
