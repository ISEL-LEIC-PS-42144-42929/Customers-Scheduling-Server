package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Owner;
import com.customersscheduling.Domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, String> {

    @Override
    Person save(Person entity);

    Person findByEmail(String email);

}
