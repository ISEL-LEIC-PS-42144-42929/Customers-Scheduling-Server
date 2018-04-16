package com.customersscheduling.DTO;

public class StoreTimetableDto {
    private String staffId;
    private int timetableId;

    public StoreTimetableDto(String staffId, int timetableId) {
        this.staffId = staffId;
        this.timetableId = timetableId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public int getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(int timetableId) {
        this.timetableId = timetableId;
    }
}
