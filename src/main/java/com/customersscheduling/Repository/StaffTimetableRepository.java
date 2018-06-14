package com.customersscheduling.Repository;

import com.customersscheduling.Domain.StaffTimetable;
import com.customersscheduling.Domain.StaffTimetablePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffTimetableRepository extends JpaRepository<StaffTimetable, StaffTimetablePK> {

    @Override
    StaffTimetable save(StaffTimetable entity);
}
