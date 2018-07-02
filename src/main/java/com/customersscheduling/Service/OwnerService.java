package com.customersscheduling.Service;

import com.customersscheduling.Domain.Owner;
import com.customersscheduling.Repository.ClientStoresRepository;
import com.customersscheduling.Repository.PersonRepository;
import com.customersscheduling.Repository.StaffTimetableRepository;
import com.customersscheduling.Repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService implements IOwnerService {

    @Autowired
    PersonRepository personRepo;

    @Override
    public Owner insertOwner(Owner owner) {
        return (Owner)personRepo.save(owner);
    }

    @Override
    public Owner getOwner(String email) {
        return (Owner)personRepo.findByEmail(email);
    }

}
