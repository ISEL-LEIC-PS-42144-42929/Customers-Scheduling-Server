package com.customersscheduling.Service;

import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Domain.Person;
import com.customersscheduling.Domain.Staff;
import com.customersscheduling.Domain.StaffTimetable;
import com.customersscheduling.Domain.Timetable;
import com.customersscheduling.ExceptionHandling.CustomExceptions.InvalidBodyException;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.Repository.ClientStoresRepository;
import com.customersscheduling.Repository.PersonRepository;
import com.customersscheduling.Repository.StaffTimetableRepository;
import com.customersscheduling.Repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService implements IStaffService {


    @Autowired
    PersonRepository personRepo;

    @Autowired
    TimetableRepository timetableRepo;

    @Autowired
    StaffTimetableRepository staffTimetableRepo;

    @Override
    public Staff insertStaff(Staff staff) {
        return (Staff)personRepo.save(staff);
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
    public List<StaffTimetable> getStaffTimetable(String email) {
        Staff staff = (Staff) personRepo.findByEmail(email);
        if(staff == null) throw new ResourceNotFoundException("Could not find staff with the email '"+email+"'.");
        List<StaffTimetable> lstaff = staffTimetableRepo.findByPk_Staff(staff);
        if(lstaff.size()==0) throw new ResourceNotFoundException("Could not find any timetable for the user with the email '"+email+"'.");
        return lstaff;
    }

    @Override
    public Staff getStaff(String email) {
        Staff s = (Staff)personRepo.findByEmail(email);
        if(s==null) throw new ResourceNotFoundException("Staff with the email - "+email+" - doesn't exist.");
        return s;
    }

    @Override
    public Staff updateStaff(String email, Staff oldStaff) {
        if(oldStaff.getEmail()==null || !oldStaff.getEmail().equals(email)) throw new InvalidBodyException("Staff object sent on body is incompleted");
        Staff s = (Staff)personRepo.findByEmail(email);
        if(s==null) throw new ResourceNotFoundException("Could not update Staff with the email - "+email+" - because it doesn't exists");
        return (Staff)personRepo.save(oldStaff);
    }

    @Override
    public Staff updateStaffTimetable(StaffTimetable staffTimetable) {
        StaffTimetable staffTt = staffTimetableRepo.findByPk_StaffAndPk_Timetable_WeekDay(staffTimetable.getPk().getStaff(), staffTimetable.getPk().getTimetable().getWeekDay());
        if(staffTt==null) throw new ResourceNotFoundException("Timetable with the specified Staff and Weekday can't be updated because doesn't exists.");
        Timetable newTimetable = staffTimetable.getPk().getTimetable();
        newTimetable.setId(staffTt.getPk().getTimetable().getId());
        staffTt.getPk().setTimetable(newTimetable);
        timetableRepo.save(newTimetable);
        staffTimetableRepo.save(staffTt);
        return (Staff) personRepo.findByEmail(staffTimetable.getPk().getStaff().getEmail());
    }

    @Override
    public Staff deleteStaff(String email) {
        Staff s = getStaff(email);
        personRepo.delete(s);
        return s;
    }

}
