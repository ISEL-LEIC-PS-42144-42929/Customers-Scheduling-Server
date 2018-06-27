package com.customersscheduling.Domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class StoreTimetablePK implements Serializable {

    @ManyToOne
    @JoinColumn(name="timetable_idtimetable",referencedColumnName="idtimetable",nullable=false, insertable = false, updatable = false)
    private Timetable timetable;

    @ManyToOne
    @JoinColumn(name="store_nif",referencedColumnName="nif",nullable=false, insertable = false, updatable = false)
    private Store store;

    public StoreTimetablePK() {
    }

    public StoreTimetablePK(Timetable timetable, Store store) {
        this.timetable = timetable;
        this.store = store;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
