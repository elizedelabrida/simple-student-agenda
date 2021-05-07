package com.elize.simple_student_agenda.database.converter;

import androidx.room.TypeConverter;

import com.elize.simple_student_agenda.model.PhoneType;

public class PhoneTypeConverter {
    @TypeConverter
    public String toString(PhoneType type) {
       return type.name();
    }

    @TypeConverter
    public PhoneType toPhoneType(String value) {
        if (value != null) {
            return PhoneType.valueOf(value);
        }
        return PhoneType.LANDLINE;
    }
}
