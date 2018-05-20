package com.customersscheduling.Repository;

import com.customersscheduling.DTO.StaffTimetable;
import com.customersscheduling.DTO.StaffTimetablePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffTimetableRepository extends JpaRepository<StaffTimetable, StaffTimetablePK> {

    @Override
    StaffTimetable save(StaffTimetable entity);
}
