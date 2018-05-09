package com.customersscheduling.Service;

import com.customersscheduling.DTO.*;

public interface IPersonService {

    void insertClient(ClientDto client);
    void insertOwner(OwnerDto owner);
    void insertStaff(StaffDto staff);
    void insertStaffTimetable(TimetableDto timetable);
}
