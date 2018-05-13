package com.customersscheduling.DTO;

import javax.persistence.*;

@Entity
@Table( name = "User")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="person_email")
public class UserDto extends PersonDto {


    public UserDto(){
        super("defaultemail", "default_name");
    }


    public UserDto(String email, String name) {
        super(email, name);
    }

}
