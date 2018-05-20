package com.customersscheduling.Service;

import com.customersscheduling.DTO.*;
import com.customersscheduling.HALObjects.ClientResource;
import com.customersscheduling.HALObjects.OwnerResource;
import com.customersscheduling.HALObjects.StaffResource;

import java.util.List;

public interface IPersonService {

    ClientResource insertClient(Client client);
    OwnerResource insertOwner(Owner owner);
    StaffResource insertStaff(Staff staff);
    StaffResource insertStaffTimetable(StaffTimetable staff);
    List<Store> getStoresByEmail(String email);
}
