package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.Domain.Owner;
import com.customersscheduling.Domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Client> {

    @Override
    Owner save(Owner entity);

    Owner findByClient_Email(String email);
}
