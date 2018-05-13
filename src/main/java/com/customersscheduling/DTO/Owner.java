package com.customersscheduling.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table( name = "Owner")
@PrimaryKeyJoinColumn(name="user_person_email")
public class OwnerDto extends UserDto {

    @Column(name="NIF")
    private int nif;

    public OwnerDto() {
        super("default_email_owner", "default_name_owner");
        this.nif = -1;
    }

    public OwnerDto(String email, String name, int nif) {
        super(email, name);
        this.nif = nif;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }
}
