package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;

import java.util.List;

public interface IServicesOfStoreService {

    StaffServices insertStaffForService(StaffServices s);
    StoreServices insertServiceForStore(StoreServices s);
    List<Booking> getServiceDisp(int id);
    List<Staff> getStaffOfService(int id, String nif);
    Service getService(int id);

    StoreServices updateService(StoreServices ss, int id);

    Service deleteService(String nif, int id);

    Service removeStaffOfService(String email, int id);
}
