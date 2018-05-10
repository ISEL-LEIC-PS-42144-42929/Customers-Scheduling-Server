package com.customersscheduling.DTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "staff")
@PrimaryKeyJoinColumn(name="person_email")
public class StaffDto extends PersonDto {

    //Timetable N-N
    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(
            name = "timetable_has_staff",
            joinColumns = { @JoinColumn(name = "staff_person_email", referencedColumnName = "person_email") },
            inverseJoinColumns = { @JoinColumn(name = "timetable_idtimetable", referencedColumnName = "idtimetable") }
    )
    private Set<TimetableDto> timetable = new HashSet<>();

    public StaffDto(){
        super("defaultemail", "default_name");
    }

    public StaffDto(String email, String name) {
        super(email, name);
    }

    public Set<TimetableDto> getTimetable() {
        return timetable;
    }

    public void setTimetable(Set<TimetableDto> timetable) {
        this.timetable = timetable;
    }
}
