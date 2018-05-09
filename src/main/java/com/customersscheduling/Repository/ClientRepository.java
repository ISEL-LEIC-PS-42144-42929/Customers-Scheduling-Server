package com.customersscheduling.Repository;

import com.customersscheduling.DTO.BusinessDto;
import com.customersscheduling.DTO.ClientDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientDto, Integer> {

    @Override
    ClientDto save(ClientDto entity);
}
