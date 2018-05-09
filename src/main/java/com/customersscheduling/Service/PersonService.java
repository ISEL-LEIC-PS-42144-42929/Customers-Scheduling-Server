package com.customersscheduling.Service;

import com.customersscheduling.DTO.ClientDto;
import com.customersscheduling.DTO.OwnerDto;
import com.customersscheduling.DTO.StaffDto;
import com.customersscheduling.DTO.TimetableDto;
import com.customersscheduling.Repository.PersonRepository;
import com.customersscheduling.Repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {

    @Autowired
    PersonRepository personRepo;

    @Autowired
    TimetableRepository timetableRepo;

    @Override
    public void insertClient(ClientDto client) {
        personRepo.save(client);
    }

    @Override
    public void insertOwner(OwnerDto owner) {
        personRepo.save(owner);
    }

    @Override
    public void insertStaff(StaffDto staff) {
        personRepo.save(staff);
    }

    @Override
    public void insertStaffTimetable(TimetableDto timetable) {
        timetableRepo.save(timetable);
    }
}
