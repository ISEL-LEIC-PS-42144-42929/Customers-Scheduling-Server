package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Staff;
import com.customersscheduling.Domain.StaffTimetable;
import com.customersscheduling.Domain.StaffTimetablePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffTimetableRepository extends JpaRepository<StaffTimetable, StaffTimetablePK> {

    @Override
    StaffTimetable save(StaffTimetable entity);

    List<StaffTimetable> findByPk_Staff(Staff staff);
}
