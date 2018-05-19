package com.customersscheduling.Service;

import com.customersscheduling.DTO.*;

import java.util.List;

public interface IStoreService {
    void insertStore(Store store);
    void insertServiceForStore(StoreServices s);
    void insertBook(Booking booking);
    Booking getBookingById(int i);

    Store getStoreByNif(String nif);

    List<Store> getStoresOfUser(String email);

    List<Client> getPendentRequests(String nif);

    List<Booking> getServiceDisp(String nif, int id);
}