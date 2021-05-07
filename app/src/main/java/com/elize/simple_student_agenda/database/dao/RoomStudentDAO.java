package com.elize.simple_student_agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.elize.simple_student_agenda.model.Student;

import java.util.List;

@Dao
public interface RoomStudentDAO {
    @Insert
    Long save(Student student);

    @Delete
    void remove(Student student);

    @Query("SELECT * FROM Student")
    List<Student> listAll();

    @Update
    void edit(Student student);
}
