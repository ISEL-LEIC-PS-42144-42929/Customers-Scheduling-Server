package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;

import java.util.Arrays;
import java.util.List;

public interface IStoreService {

    Store insertStore(Store store);
    StaffServices insertStafForService(StaffServices s);
    StoreServices insertServiceForStore(StoreServices s);
    Booking insertBook(int id, String email);
    Booking getBookingById(int i);
    Store getStoreByNif(String nif);
    List<Store> getStoresOfUser(String email);
    List<Client> getPendentRequests(String nif);
    List<Booking> getServiceDisp(int id);
    Store insertClientForStore(ClientStores cs);
    Store insertStoreTimetable(StoreTimetable storeTimetable);
    List<StoreTimetable> getStoreTimetable(String nif);
    List<StoreServices> getServicesOfStore(String nif);

    List<Staff> getStaffOfService(int id, String nif);

    Service getService(int id);
}
