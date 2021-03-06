package com.customersscheduling.Domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class StaffTimetablePK implements Serializable {

    @ManyToOne
    @JoinColumn(name="timetable_idtimetable",referencedColumnName="idtimetable",nullable=false, insertable = false, updatable = false)
    private Timetable timetable;

    @ManyToOne
    @JoinColumn(name="staff_person_email",referencedColumnName="person_email",nullable=false, insertable = false, updatable = false)
    private Staff staff;

    public StaffTimetablePK(){

    }

    public StaffTimetablePK(Timetable timetable, Staff staff) {
        this.timetable = timetable;
        this.staff = staff;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
