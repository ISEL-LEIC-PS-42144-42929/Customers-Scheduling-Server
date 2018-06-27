package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.*;

import java.util.List;

public interface IPersonService {

    ClientResource insertClient(Client client);
    OwnerResource insertOwner(Owner owner);
    StaffResource insertStaff(Staff staff);
    StaffResource insertStaffTimetable(StaffTimetable staff);
    List<Store> getStoresByEmail(String email);

    StaffTimetableResource getStaffTimetable(String email);
}
