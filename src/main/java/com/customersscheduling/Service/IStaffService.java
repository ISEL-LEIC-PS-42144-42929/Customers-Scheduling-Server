package com.customersscheduling.Service;

import com.customersscheduling.Controller.InputModels.PersonInputModel;
import com.customersscheduling.Domain.Booking;
import com.customersscheduling.Domain.Staff;
import com.customersscheduling.Domain.StaffTimetable;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public interface IStaffService {
    Staff insertStaff(Staff staff);
    Staff insertStaffTimetable(StaffTimetable staff);
    List<StaffTimetable> getStaffTimetable(String email);
    Staff getStaff(String email);
    Staff updateStaff(String email, Staff oldStaff);
    Staff updateStaffTimetable(StaffTimetable staffTimetable);
}
