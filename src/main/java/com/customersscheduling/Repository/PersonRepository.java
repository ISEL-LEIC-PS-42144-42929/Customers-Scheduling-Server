package com.customersscheduling.Repository;

import com.customersscheduling.DTO.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Override
    Person save(Person entity);
}
