package com.customersscheduling.Service;

import com.customersscheduling.Domain.Staff;
import com.customersscheduling.Domain.StaffTimetable;

import java.util.List;

public interface IStaffService {
    Staff insertStaff(Staff staff);
    Staff insertStaffTimetable(StaffTimetable staff);
    List<StaffTimetable> getStaffTimetable(String email);
}
