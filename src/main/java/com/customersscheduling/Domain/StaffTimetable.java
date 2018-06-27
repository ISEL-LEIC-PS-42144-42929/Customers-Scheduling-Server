package com.customersscheduling.Domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "timetable_has_staff")
public class StaffTimetable {

    @EmbeddedId
    private StaffTimetablePK pk;

    public StaffTimetable(){}

    public StaffTimetable(StaffTimetablePK pk) {
        this.pk = pk;
    }

    public StaffTimetablePK getPk() {
        return pk;
    }

    public void setPk(StaffTimetablePK pk) {
        this.pk = pk;
    }
}
