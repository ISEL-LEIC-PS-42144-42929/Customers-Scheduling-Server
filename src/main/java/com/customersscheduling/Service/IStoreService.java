package com.customersscheduling.Service;

import com.customersscheduling.DTO.*;
import com.customersscheduling.HALObjects.BookingResource;
import com.customersscheduling.HALObjects.ServiceResource;
import com.customersscheduling.HALObjects.StoreResource;

import java.util.List;

public interface IStoreService {
    StoreResource insertStore(Store store);
    ServiceResource insertServiceForStore(StoreServices s);
    BookingResource insertBook(Booking booking);
    Booking getBookingById(int i);

    Store getStoreByNif(String nif);

    List<Store> getStoresOfUser(String email);

    List<Client> getPendentRequests(String nif);

    List<Booking> getServiceDisp(String nif, int id);
}
