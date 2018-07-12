package com.customersscheduling.Domain;

import com.customersscheduling.OutputResources.OwnerResource;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Owner")
public class Owner implements Serializable {

    @Id
    @Column(name="nif")
    private String nif;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="client_person_email",referencedColumnName="person_email", nullable = false)
    private Client client;;


    public Owner() {
    }

    public Owner(String nif, Client client) {
        this.client=client;
        this.nif=nif;
    }

    public OwnerResource toOwnerResource() {
        return new OwnerResource(this);
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
