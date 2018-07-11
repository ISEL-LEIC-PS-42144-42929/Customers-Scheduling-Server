package com.customersscheduling.Repository;

import com.customersscheduling.Domain.StaffTimetable;
import com.customersscheduling.Domain.Store;
import com.customersscheduling.Domain.StoreTimetable;
import com.customersscheduling.Domain.StoreTimetablePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreTimetableRepository extends JpaRepository<StoreTimetable, StoreTimetablePK> {

    @Override
    StoreTimetable save(StoreTimetable entity);

    List<StoreTimetable> findByPk_Store(Store store);

    List<StoreTimetable> findByPk_Store_Nif(String nif);

    Optional<StoreTimetable> findByPk_StoreAndPk_Timetable_WeekDay(Store store, int weekDay);
}
