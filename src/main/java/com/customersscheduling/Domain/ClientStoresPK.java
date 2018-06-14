package com.customersscheduling.Domain;


import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ClientStoresPK implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="store_nif",referencedColumnName="nif",nullable=false)
    private Store store;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="client_user_person_email",referencedColumnName="user_person_email",nullable=false)
    private Client client;

    public ClientStoresPK(){}

    public ClientStoresPK(Store st, Client cl){
        this.store=st;
        this.client=cl;
    }


    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
