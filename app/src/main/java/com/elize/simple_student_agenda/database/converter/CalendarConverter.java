package com.elize.simple_student_agenda.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class CalendarConverter {
    @TypeConverter
    public Long toLong(Calendar value) {
        if (value != null) {
            return value.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar toCalendar(Long value) {
        Calendar calendar = Calendar.getInstance();
        if (value != null) {
            calendar.setTimeInMillis(value);
        }
        return calendar;
    }
}
