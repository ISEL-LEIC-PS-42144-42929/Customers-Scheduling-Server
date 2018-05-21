package com.customersscheduling.Repository;

import com.customersscheduling.DTO.Booking;
import com.customersscheduling.DTO.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Override
    Booking save(Booking entity);

    @Override
    Booking getOne(Integer integer);

    List<Booking> findById(int id);
}