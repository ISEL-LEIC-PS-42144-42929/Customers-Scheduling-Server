package com.customersscheduling.Service;

import com.customersscheduling.DTO.*;

public interface IPersonService {

    void insertClient(Client client);
    void insertOwner(Owner owner);
    void insertStaff(Staff staff);
    void insertStaffTimetable(Staff staff);
}
