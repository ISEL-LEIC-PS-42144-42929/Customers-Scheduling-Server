package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.*;

import java.util.List;

public interface IPersonService {

    Client insertClient(Client client);
    Owner insertOwner(Owner owner);
    Staff insertStaff(Staff staff);
    Staff insertStaffTimetable(StaffTimetable staff);
    List<Store> getStoresByEmail(String email);
    List<StaffTimetable> getStaffTimetable(String email);
    List<Store> getPendentRequests(String email);

    Client getClient(String email);
}
