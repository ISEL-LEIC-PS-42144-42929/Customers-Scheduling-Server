package com.customersscheduling.Repository;

import com.customersscheduling.DTO.BusinessDto;
import com.customersscheduling.DTO.ClientDto;
import com.customersscheduling.DTO.PersonDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonDto, Integer> {

    @Override
    PersonDto save(PersonDto entity);
}
