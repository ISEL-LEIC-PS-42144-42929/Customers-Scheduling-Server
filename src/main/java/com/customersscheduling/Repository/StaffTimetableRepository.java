package com.customersscheduling.Repository;

import com.customersscheduling.Domain.Staff;
import com.customersscheduling.Domain.StaffTimetable;
import com.customersscheduling.Domain.StaffTimetablePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StaffTimetableRepository extends JpaRepository<StaffTimetable, StaffTimetablePK> {

    @Override
    StaffTimetable save(StaffTimetable entity);

    List<StaffTimetable> findByPk_Staff(Staff staff);

    Optional<StaffTimetable> findByPk_StaffAndPk_Timetable_WeekDay(Staff staff, int wd);

    void deleteByPk_Staff_Email(String email);
}
