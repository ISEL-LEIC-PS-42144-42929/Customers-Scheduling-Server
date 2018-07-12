package com.customersscheduling.Service;

import com.customersscheduling.Domain.Staff;
import com.customersscheduling.Domain.StaffTimetable;
import com.customersscheduling.Domain.Timetable;
import com.customersscheduling.ExceptionHandling.CustomExceptions.InvalidBodyException;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = ResourceNotFoundException.class)
public class StaffService implements IStaffService {


    @Autowired
    PersonRepository personRepo;

    @Autowired
    StoreRepository storeRepo;

    @Autowired
    TimetableRepository timetableRepo;

    @Autowired
    StaffTimetableRepository staffTimetableRepo;

    @Autowired
    StaffServicesRepository staffServsRepo;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Staff insertStaff(Staff staff) {
        staff.setStore(storeRepo.findById(staff.getStore().getNif()).orElseThrow( () ->
                new ResourceNotFoundException("Couldn't insert staff because the store with NIF "+staff.getStore().getNif()+" doesn't exists."))
        );
        return (Staff)personRepo.save(staff);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Staff insertStaffTimetable(StaffTimetable staffTimeTable) {
        Timetable tt = staffTimeTable.getPk().getTimetable();
        timetableRepo.findByTimetableDay(tt.getOpenHour(), tt.getCloseHour(), tt.getInitBreak(), tt.getFinishBreak(), tt.getWeekDay()).ifPresent(existingTt ->
                tt.setId(existingTt.getId())
        );

        timetableRepo.save(staffTimeTable.getPk().getTimetable());
        staffTimetableRepo.save(staffTimeTable);
        return (Staff) personRepo.findByEmail(staffTimeTable.getPk().getStaff().getEmail()).orElseThrow(()->
                new ResourceNotFoundException("Staff "+staffTimeTable.getPk().getStaff().getEmail()+" doesn't exists.")
        );
    }


    @Override
    @Cacheable(value = "stafftimetables")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public List<StaffTimetable> getStaffTimetable(String email) {
        Staff staff = (Staff) personRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Staff "+email+" doesn't exists."));
        if(staff == null) throw new ResourceNotFoundException("Could not find staff with the email '"+email+"'.");
        List<StaffTimetable> lstaff = staffTimetableRepo.findByPk_Staff(staff);
        if(lstaff.size()==0) throw new ResourceNotFoundException("Could not find any timetable for the user with the email '"+email+"'.");
        return lstaff;
    }

    @Override
    @Cacheable(value = "staffs")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true )
    public Staff getStaff(String email) {
        Staff s = (Staff)personRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Staff "+email+" doesn't exists."));
        if(s==null) throw new ResourceNotFoundException("Staff with the email - "+email+" - doesn't exist.");
        return s;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Staff updateStaff(String email, Staff oldStaff) {
        if(oldStaff.getEmail()==null || !oldStaff.getEmail().equals(email)) throw new InvalidBodyException("Staff object sent on body is incompleted");
        Staff s = (Staff)personRepo.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("Staff "+email+" doesn't exists.")
        );
        return (Staff)personRepo.save(oldStaff);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Staff updateStaffTimetable(StaffTimetable staffTimetable) {
        StaffTimetable staffTt = staffTimetableRepo.findByPk_StaffAndPk_Timetable_WeekDay(staffTimetable.getPk().getStaff(), staffTimetable.getPk().getTimetable().getWeekDay()).orElseThrow(()->
                new ResourceNotFoundException("Timetable with the specified Staff and Weekday can't be updated because doesn't exists.")
        );
        Timetable newTimetable = staffTimetable.getPk().getTimetable();
        newTimetable.setId(staffTt.getPk().getTimetable().getId());
        staffTt.getPk().setTimetable(newTimetable);
        timetableRepo.save(newTimetable);
        staffTimetableRepo.save(staffTt);
        return (Staff) personRepo.findByEmail(staffTimetable.getPk().getStaff().getEmail()).orElseThrow(()->
                new ResourceNotFoundException("Staff "+staffTimetable.getPk().getStaff().getEmail()+" doesn't exists.")
        );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Staff deleteStaff(String email) {
        Staff s = getStaff(email);
        staffServsRepo.deleteByPk_Staff_Email(email);
        staffTimetableRepo.deleteByPk_Staff_Email(email);
        personRepo.delete(s);
        return s;
    }

}
