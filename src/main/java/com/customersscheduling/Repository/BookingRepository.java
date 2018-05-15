package com.customersscheduling.Repository;

import com.customersscheduling.DTO.Booking;
import com.customersscheduling.DTO.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Override
    Booking save(Booking entity);
}