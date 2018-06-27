package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.*;
import com.customersscheduling.Repository.PersonRepository;
import com.customersscheduling.Repository.StaffTimetableRepository;
import com.customersscheduling.Repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired
    PersonRepository personRepo;

    @Autowired
    TimetableRepository timetableRepo;

    @Autowired
    StaffTimetableRepository staffTimetableRepo;

    @Override
    public ClientResource insertClient(Client client) {
        personRepo.save(client);
        Client c = (Client) personRepo.findByEmail(client.getEmail());
        return c.toResource();
    }

    @Override
    public OwnerResource insertOwner(Owner owner) {
        personRepo.save(owner);
        Owner o = (Owner)personRepo.findByEmail(owner.getEmail());
        return o.toResource();
    }

    @Override
    public StaffResource insertStaff(Staff staff) {
        personRepo.save(staff);
        Staff s = (Staff) personRepo.findByEmail(staff.getEmail());
        return s.toResource();
    }

    @Override
    public StaffResource insertStaffTimetable(StaffTimetable staffTimeTable) {
        Timetable tt = staffTimeTable.getPk().getTimetable();
        Timetable onDb = null;
        if((onDb=timetableRepo.findByTimetableDay(tt.getOpenHour(), tt.getCloseHour(), tt.getInitBreak(), tt.getFinishBreak(), tt.getWeekDay()))!=null){
            tt.setId(onDb.getId());
        }
        timetableRepo.save(staffTimeTable.getPk().getTimetable());
        staffTimetableRepo.save(staffTimeTable);
        Staff staff = (Staff) personRepo.findByEmail(staffTimeTable.getPk().getStaff().getEmail());
        return staff.toResource();
    }

    @Override
    public List<Store> getStoresByEmail(String email) {
        return null;
    }

    @Override
    public StaffTimetableResource getStaffTimetable(String email) {
        Staff staff = (Staff) personRepo.findByEmail(email);
        List<StaffTimetable> stafftts = staffTimetableRepo.findByPk_Staff(staff);
        List<Timetable> tts = new ArrayList<>();
        stafftts.forEach( i -> tts.add(timetableRepo.findById(i.getPk().getTimetable().getId())));
        return new StaffTimetableResource(tts, staff.toResource());
    }
}
