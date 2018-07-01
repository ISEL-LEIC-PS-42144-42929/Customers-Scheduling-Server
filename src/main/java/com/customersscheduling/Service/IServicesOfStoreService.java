package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;

import java.util.List;

public interface IServicesOfStoreService {

    StaffServices insertStaffForService(StaffServices s);
    StoreServices insertServiceForStore(StoreServices s);
    List<Booking> getServiceDisp(int id);
    List<Staff> getStaffOfService(int id, String nif);
    Service getService(int id);
}
