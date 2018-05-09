package com.customersscheduling.DTO;

import javax.persistence.*;

@Entity
@Table( name = "Staff")
@PrimaryKeyJoinColumn(name="Person_email")
public class StaffDto extends PersonDto {

    public StaffDto(){
        super("defaultemail", "default_name");
    }

    public StaffDto(String email, String name) {
        super(email, name);
    }
}
