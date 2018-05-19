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

    @Query("select s from Booking s where s.id = :id and s.store.nif")
    List<Booking> findByStoreAndService(@Param("nif")String nif, @Param("id")int id);
}