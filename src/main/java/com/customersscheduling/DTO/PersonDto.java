package com.customersscheduling.DTO;

public class PersonDto {

    private String email;
    private String name;

    public PersonDto(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
