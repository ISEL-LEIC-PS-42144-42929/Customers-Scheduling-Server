package com.customersscheduling.Service;

import com.customersscheduling.Domain.Owner;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.Repository.ClientStoresRepository;
import com.customersscheduling.Repository.PersonRepository;
import com.customersscheduling.Repository.StaffTimetableRepository;
import com.customersscheduling.Repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class OwnerService implements IOwnerService {

    @Autowired
    PersonRepository personRepo;

    @Override
    public Owner insertOwner(Owner owner) {
        return (Owner)personRepo.save(owner);
    }

    @Override
    public Owner getOwner(String email) {
        Owner o=  (Owner)personRepo.findByEmail(email);
        if(o == null) throw new ResourceNotFoundException("Staff with the email - "+email+" - doesn't exists.");
        return o;
    }

    @Override
    public Owner deleteOwner(String email) {
        Owner o = getOwner(email);
        personRepo.delete(o);
        return o;
    }

}
