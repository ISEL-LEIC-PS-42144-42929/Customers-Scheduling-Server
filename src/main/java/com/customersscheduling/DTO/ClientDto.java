package com.customersscheduling.DTO;

import javax.persistence.*;

@Entity
@Table( name = "Client")
@PrimaryKeyJoinColumn(name="User_Person_email")
public class ClientDto extends UserDto {

    public ClientDto(){
        super("defaultemail", "default_name");
    }

    public ClientDto(String email, String name) {
        super(email, name);
    }

}
