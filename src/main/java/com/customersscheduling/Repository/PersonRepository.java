package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Override
    Person save(Person entity);

    @Query("select s from Person s where s.email = :email")
    Person findOne(@Param("email") String email);
}
