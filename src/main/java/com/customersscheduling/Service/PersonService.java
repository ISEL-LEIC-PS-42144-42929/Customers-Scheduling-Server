package com.customersscheduling.Service;

import com.customersscheduling.DTO.ClientDto;
import com.customersscheduling.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {

    @Autowired
    ClientRepository repo;

    @Override
    public void insertClient(ClientDto client) {
        repo.save(client);
    }
}
