package com.customersscheduling.Service;

import com.customersscheduling.DTO.Client;
import com.customersscheduling.DTO.Owner;
import com.customersscheduling.DTO.Staff;
import com.customersscheduling.DTO.Timetable;
import com.customersscheduling.Repository.PersonRepository;
import com.customersscheduling.Repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Iterator;
import java.util.Set;

@Service
public class PersonService implements IPersonService {

    @Autowired
    PersonRepository personRepo;

    @Autowired
    TimetableRepository timetableRepo;

    @Override
    public void insertClient(Client client) {
        personRepo.save(client);
    }

    @Override
    public void insertOwner(Owner owner) {
        personRepo.save(owner);
    }

    @Override
    public void insertStaff(Staff staff) {
        personRepo.save(staff);
    }

    @Override
    public void insertStaffTimetable(Staff staff) {
        staff.getTimetable().iterator().forEachRemaining(i->{
            Timetable t;
            if((t=timetableRepo.findByTimetableDay(i.getOpenHour(), i.getCloseHour(), i.getInitBreak(), i.getFinishBreak(), i.getWeekDay()))!=null){
                i.setId(t.getId());
            }
        });
        personRepo.save(staff);
    }
}
