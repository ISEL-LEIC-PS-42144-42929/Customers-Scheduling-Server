package com.customersscheduling.Service;

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
    OwnerRepository personRepo;

    @Override
    public Owner insertOwner(Owner owner) {
        return (Owner)personRepo.save(owner);
    }

    @Override
    @Cacheable(value = "owner")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Owner getOwner(String email) {
        Owner o=  (Owner)personRepo.findByClient_Email(email);//.orElseThrow(()->new ResourceNotFoundException("Owner "+email+" doesn't exists."));
        if(o == null) throw new ResourceNotFoundException("Staff with the email - "+email+" - doesn't exists.");
        return o;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
    public Owner deleteOwner(String email) {
        Owner o = getOwner(email);
        personRepo.delete(o);
        return o;
    }

}
