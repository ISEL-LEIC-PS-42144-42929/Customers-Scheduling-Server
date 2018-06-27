package com.customersscheduling.Repository;

import com.customersscheduling.Domain.StaffTimetable;
import com.customersscheduling.Domain.StoreTimetable;
import com.customersscheduling.Domain.StoreTimetablePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreTimetableRepository extends JpaRepository<StoreTimetable, StoreTimetablePK> {

    @Override
    StoreTimetable save(StoreTimetable entity);
}
