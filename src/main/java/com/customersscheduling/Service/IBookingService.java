package com.customersscheduling.Service;

import com.customersscheduling.Domain.Store;

import java.util.Date;

public interface IBookingService {

    void dailyUpdate();
    void updateBookingOfStore(Store s, Date d);

}
