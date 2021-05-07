package com.elize.simple_student_agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.elize.simple_student_agenda.database.converter.CalendarConverter;
import com.elize.simple_student_agenda.database.dao.RoomPhoneDAO;
import com.elize.simple_student_agenda.database.dao.RoomStudentDAO;
import com.elize.simple_student_agenda.model.Phone;
import com.elize.simple_student_agenda.model.Student;

import static com.elize.simple_student_agenda.database.StudentAgendaMigrations.ALL_MIGRATIONS;

@Database(entities = {Student.class, Phone.class}, version = 5, exportSchema = false)
@TypeConverters({CalendarConverter.class})
public abstract class StudentAgendaDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "simple-student-agenda.db";


    public abstract RoomStudentDAO getRoomStudentDAO();

    public abstract RoomPhoneDAO getRoomPhoneDAO();

    public static StudentAgendaDatabase getInstance(Context context) {
        return Room
                .databaseBuilder(context, StudentAgendaDatabase.class, DATABASE_NAME)
                .addMigrations(ALL_MIGRATIONS).build();


    }


}

