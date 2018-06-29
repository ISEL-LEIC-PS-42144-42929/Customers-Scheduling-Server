package com.customersscheduling.Controller.InputModels;

import com.customersscheduling.Domain.Timetable;

public class TimetableInputModel {
    public double open_hour;
    public double close_hour;
    public double init_break;
    public double finish_break;
    public int week_day;

    public Timetable toDto() {
        return new Timetable(open_hour, close_hour, init_break, finish_break, week_day);
    }
}
