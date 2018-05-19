package com.customersscheduling.Repository;

import com.customersscheduling.DTO.Client;
import com.customersscheduling.DTO.Person;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Override
    Person save(Person entity);

    @Query("select s from Person s where s.email = :email")
    Client findOne(@Param("email") String email);
}
