package com.elize.simple_student_agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.elize.simple_student_agenda.model.Phone;

import java.util.List;

@Dao
public interface RoomPhoneDAO {
    @Query("SELECT * FROM Phone " +
            "WHERE student_id = :studentId LIMIT 1")
    Phone getStudentFirstPhone(int studentId);

    @Insert
    void save(Phone... phones);

    @Query("SELECT * FROM Phone " +
            "WHERE student_id = :studentId")
    List<Phone> getStudentPhones(int studentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void update(Phone... phones);
}
