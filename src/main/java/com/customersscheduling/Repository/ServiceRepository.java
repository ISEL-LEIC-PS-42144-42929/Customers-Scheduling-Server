package com.customersscheduling.Repository;

import com.customersscheduling.DTO.Service;
import com.customersscheduling.DTO.Store;
import com.customersscheduling.DTO.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

    @Override
    Service save(Service entity);

    @Query("select s from Service s where s.description = :description and " +
            "s.price = :price and " +
            "s.title = :title and " +
            "s.duration = :duration")
    Service findServiceByParams(@Param("description") String descr,
                                 @Param("price") double price,
                                 @Param("title") String title,
                                 @Param("duration") int duration);

}
