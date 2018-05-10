package com.customersscheduling.DTO;

import javax.persistence.*;

@Entity
@Table( name = "Client")
@PrimaryKeyJoinColumn(name="user_person_email")
public class ClientDto extends UserDto {

    public ClientDto(){
        super("defaultemail", "default_name");
    }

    public ClientDto(String email, String name) {
        super(email, name);
    }

}
