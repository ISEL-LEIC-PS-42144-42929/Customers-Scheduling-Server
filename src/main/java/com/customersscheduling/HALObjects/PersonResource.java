package com.customersscheduling.HALObjects;

import com.customersscheduling.Domain.Person;
import org.springframework.hateoas.ResourceSupport;

public class PersonResource extends ResourceSupport {

    private Person person;

    public PersonResource(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}