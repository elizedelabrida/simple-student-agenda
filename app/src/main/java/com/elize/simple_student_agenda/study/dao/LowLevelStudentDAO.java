package com.elize.simple_student_agenda.study.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.elize.simple_student_agenda.model.Student;

import java.util.ArrayList;
import java.util.List;

// TODO Class created only with the goal to study all the boilerplate code involved in not using Room
public class LowLevelStudentDAO extends SQLiteOpenHelper {
    public LowLevelStudentDAO(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTable = "CREATE TABLE students (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT," +
                "phone TEXT," +
                "mail TEXT);";
        db.execSQL(sqlCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void save(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
//        values.put("phone", student.getPhone());
        values.put("mail", student.getMail());
        db.insert("students", null, values);
    }

    public void edit(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = getStudentValues(student);
        String[] params = {String.valueOf(student.getId())};
        db.update("students", values, "id = ?", params);
    }

    public List<Student> listAll() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM students";
        Cursor c = db.rawQuery(query, null);

        List<Student> students = new ArrayList<>();

        while (c.moveToNext()) {
            Student student = new Student();
            student.setId(c.getInt(c.getColumnIndex("id")));
            student.setName(c.getString(c.getColumnIndex("name")));
//            student.setPhone(c.getString(c.getColumnIndex("phone")));
            student.setMail(c.getString(c.getColumnIndex("mail")));
            students.add(student);
        }
        c.close();
        return students;
    }

    private ContentValues getStudentValues(Student student) {
        ContentValues values = new ContentValues();
        values.put("id", student.getId());
        values.put("name", student.getName());
        values.put("mail", student.getMail());
        return values;
    }

    public void remove(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(student.getId())};
        db.delete("students", "id = ?", params);
    }

}
