package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;

import java.util.Arrays;
import java.util.List;

public interface IStoreService {

    Store insertStore(Store store);
    Store getStore(String nif);
    List<Store> getStoresOfUser(String email);
    List<Client> getPendentRequests(String nif);
    Store insertClientForStore(ClientStores cs);
    Store insertStoreTimetable(StoreTimetable storeTimetable);
    List<StoreTimetable> getStoreTimetable(String nif);
    List<StoreServices> getServicesOfStore(String nif);

    Store updateStoreTimetable(StoreTimetable storeTimetable);

    Store updateStoreAddress(String nif, Address address);

    Store updateStore(String nif, Store store);

    Store deleteStore(String nif);
}
