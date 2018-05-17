package com.customersscheduling.Service;

import com.customersscheduling.DTO.*;

import java.util.List;

public interface IBusinessService {
    void insertBusiness(Business business);
    void insertStore(Store store);
    void insertServiceForStore(StoreServices s);
    void insertBook(Booking booking);
    Booking getBookingById(int i);

    List<Store> getStoresByNif(int nif);
}
