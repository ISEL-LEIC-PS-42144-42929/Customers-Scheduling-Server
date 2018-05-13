package com.customersscheduling.DTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "staff")
@PrimaryKeyJoinColumn(name="person_email")
public class Staff extends Person {

    //Timetable N-N
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "timetable_has_staff",
            joinColumns = { @JoinColumn(name = "staff_person_email", referencedColumnName = "person_email") },
            inverseJoinColumns = { @JoinColumn(name = "timetable_idtimetable", referencedColumnName = "idtimetable") }
    )
    private Set<Timetable> timetable = new HashSet<>();

    public Staff(){
        super("defaultemail", "default_name");
    }

    public Staff(String email, String name) {
        super(email, name);
    }

    public Set<Timetable> getTimetable() {
        return timetable;
    }

    public void setTimetable(Set<Timetable> timetable) {
        this.timetable = timetable;
    }
}
