package com.customersscheduling.Service;

import com.customersscheduling.DTO.*;
import com.customersscheduling.HALObjects.BusinessHAL;

public interface IBusinessService {
    BusinessHAL insertBusiness(Business business);
    BusinessHAL insertStore(Store store);
    void insertServiceForStore(StoreServices s);
    void insertBook(Booking booking);
}
