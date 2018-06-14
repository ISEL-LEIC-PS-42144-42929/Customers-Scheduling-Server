package com.customersscheduling.Domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "store_has_timetable")
public class StoreTimetable {
    @EmbeddedId
    private StoreTimetablePK pk;
}
