package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.ExceptionHandling.Exceptions.ResourceNotFoundException;
import com.customersscheduling.Repository.ClientStoresRepository;
import com.customersscheduling.Repository.PersonRepository;
import com.customersscheduling.Repository.StaffTimetableRepository;
import com.customersscheduling.Repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService implements IPersonService {

    @Autowired
    PersonRepository personRepo;

    @Autowired
    TimetableRepository timetableRepo;

    @Autowired
    StaffTimetableRepository staffTimetableRepo;

    @Autowired
    ClientStoresRepository clientStoresRepo;

    @Override
    public Client insertClient(Client client) {
        personRepo.save(client);
        return (Client) personRepo.findByEmail(client.getEmail());
    }

    @Override
    public Owner insertOwner(Owner owner) {
        personRepo.save(owner);
        return (Owner)personRepo.findByEmail(owner.getEmail());
    }

    @Override
    public Staff insertStaff(Staff staff) {
        personRepo.save(staff);
        return (Staff) personRepo.findByEmail(staff.getEmail());
    }

    @Override
    public Staff insertStaffTimetable(StaffTimetable staffTimeTable) {
        Timetable tt = staffTimeTable.getPk().getTimetable();
        Timetable onDb = null;
        if((onDb=timetableRepo.findByTimetableDay(tt.getOpenHour(), tt.getCloseHour(), tt.getInitBreak(), tt.getFinishBreak(), tt.getWeekDay()))!=null){
            tt.setId(onDb.getId());
        }
        timetableRepo.save(staffTimeTable.getPk().getTimetable());
        staffTimetableRepo.save(staffTimeTable);
        return (Staff) personRepo.findByEmail(staffTimeTable.getPk().getStaff().getEmail());
    }

    @Override
    public List<Store> getStoresByEmail(String email) {
        return clientStoresRepo.findByPk_Client_EmailAndAccepted(email, true)
                                .stream()
                                .map(cs -> cs.getPk().getStore())
                                .collect(Collectors.toList());
    }

    @Override
    public List<StaffTimetable> getStaffTimetable(String email) {
        Staff staff = (Staff) personRepo.findByEmail(email);
        if(staff == null) throw new ResourceNotFoundException("Could not find person with the email '"+email+"'.");
        List<StaffTimetable> lstaff = staffTimetableRepo.findByPk_Staff(staff);
        if(lstaff==null || lstaff.size()==0) throw new ResourceNotFoundException("Could not find any timetable for the user with the email '"+email+"'.");
        return lstaff;
    }

    @Override
    public List<Store> getPendentRequests(String email) {
        return clientStoresRepo.findByPk_Client_EmailAndAccepted(email, false)
                                .stream()
                                .map(cs -> cs.getPk().getStore())
                                .collect(Collectors.toList());
    }

    @Override
    public Client getClient(String email) {
        return (Client)personRepo.findByEmail(email);
    }
}
