package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;
import com.customersscheduling.HALObjects.BookingResource;
import com.customersscheduling.HALObjects.ServiceResource;
import com.customersscheduling.HALObjects.StoreResource;
import com.customersscheduling.HALObjects.StoreTimetableResource;

import java.util.List;

public interface IStoreService {

    StoreResource insertStore(Store store);
    ServiceResource insertStafForService(StaffServices s);

    ServiceResource insertServiceForStore(StoreServices s);

    BookingResource insertBook(Booking booking);
    Booking getBookingById(int i);

    StoreResource getStoreByNif(String nif);

    List<Store> getStoresOfUser(String email);

    List<Client> getPendentRequests(String nif);

    List<Booking> getServiceDisp(int id);

    StoreResource insertClientForStore(ClientStores cs);

    StoreResource insertStoreTimetable(StoreTimetable storeTimetable);

    StoreTimetableResource getStoreTimetable(String nif);

    List<ServiceResource> getServicesOfStore(String nif);
}
