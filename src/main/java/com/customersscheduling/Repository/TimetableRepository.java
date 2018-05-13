package com.customersscheduling.Repository;

import com.customersscheduling.DTO.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {

    @Override
    Timetable save(Timetable entity);

    @Query("select t from Timetable t where t.openHour = :openhour and " +
                                            "t.closeHour = :closehour and " +
                                            "t.initBreak = :initbreak and " +
                                            "t.finishBreak = :finishbreak and " +
                                            "t.weekDay=:weekday")
    Timetable findByTimetableDay(@Param("openhour") double oh,
                                 @Param("closehour") double ch,
                                 @Param("initbreak") double ib,
                                 @Param("finishbreak") double fb,
                                 @Param("weekday") String wd);
}
