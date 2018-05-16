package com.customersscheduling.DTO;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "client_has_store")
public class ClientStores {

    @EmbeddedId
    private ClientStoresPK pk;

    @Column(name="accepted")
    private boolean accepted;

    @Column(name="score")
    private int score;

    public ClientStores(){}

    public ClientStores(ClientStoresPK pk, boolean accepted, int score) {
        this.pk = pk;
        this.accepted = accepted;
        this.score = score;
    }

    public ClientStoresPK getPk() {
        return pk;
    }

    public void setPk(ClientStoresPK pk) {
        this.pk = pk;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
