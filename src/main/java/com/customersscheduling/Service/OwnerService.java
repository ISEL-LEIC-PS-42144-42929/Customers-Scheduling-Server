package com.customersscheduling.Service;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.Domain.Owner;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = ResourceNotFoundException.class)
public class OwnerService implements IOwnerService {

    @Autowired
    OwnerRepository ownerRepo;

    @Autowired
    PersonRepository personRepo;

    @Autowired
    StoreRepository storesRepo;

    @Override
    public Owner insertOwner(Owner owner) {
        Client c = (Client) personRepo.findByEmail(owner.getClient().getEmail()).orElseThrow(()->new ResourceNotFoundException("Owner "+owner.getClient().getEmail()+" doesn't exists."));;
        owner.setClient(c);
        return ownerRepo.save(owner);
    }

    @Override
    @Cacheable(value = "owner")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Owner getOwner(String email) {
        return ownerRepo.findByClient_Email(email).orElseThrow(()->
                new ResourceNotFoundException("Owner "+email+" doesn't exists.")
        );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public Owner deleteOwner(String email) {
        Owner o = getOwner(email);
        storesRepo.deleteByOwner_Client_Email(email);
        ownerRepo.delete(o);
        return o;
    }

}
