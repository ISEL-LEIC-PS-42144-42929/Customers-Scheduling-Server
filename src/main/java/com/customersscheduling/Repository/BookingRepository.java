package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Override
    Booking save(Booking entity);

    @Override
    Booking getOne(Integer integer);

    Optional<Booking> findById(int id);

    List<Booking> findByService_Id(int id);

    List<Booking> findByDate(Date d);

    void deleteByDate(Date d);

    void delete(Booking entity);

    List<Booking> findByClient_Email(String email);

    void deleteByStore_Nif(String nif);

    void deleteByClient_Email(String email);
}