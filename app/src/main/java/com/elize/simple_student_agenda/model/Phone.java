package com.elize.simple_student_agenda.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Student.class,
        parentColumns = "id",
        childColumns = "student_id",
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE))
public class Phone {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String number;
    private final PhoneType type;

    @ColumnInfo(name = "student_id")
    private int studentId;

    public Phone(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public PhoneType getType() {
        return type;
    }
}
