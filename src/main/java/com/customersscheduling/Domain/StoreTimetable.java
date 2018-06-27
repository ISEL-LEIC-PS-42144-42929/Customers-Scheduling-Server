package com.customersscheduling.Domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "store_has_timetable")
public class StoreTimetable {
    @EmbeddedId
    private StoreTimetablePK pk;

    public StoreTimetable(){
    }

    public StoreTimetable(StoreTimetablePK pk){
        this.pk = pk;
    }

    public StoreTimetablePK getPk() {
        return pk;
    }

    public void setPk(StoreTimetablePK pk) {
        this.pk = pk;
    }
}
