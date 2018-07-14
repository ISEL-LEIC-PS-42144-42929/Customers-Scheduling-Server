package com.customersscheduling.Service;

import com.customersscheduling.Domain.*;

import java.util.Date;

public interface IBookingService {

    void dailyUpdate();
    void updateBookingOfStore(Store s, Date d);
    void updateBookingOfStore(StaffServices staffServs);
    Booking getBookingById(int i);
    Booking insertBook(int id, String email);

    Booking deleteBook(int id);
}
