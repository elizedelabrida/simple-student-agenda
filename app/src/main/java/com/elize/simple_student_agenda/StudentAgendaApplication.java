package com.elize.simple_student_agenda;

import android.app.Application;

import com.elize.simple_student_agenda.dao.StudentDAO;
import com.elize.simple_student_agenda.model.Student;

public class StudentAgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        createMockedStudents();
    }

    private void createMockedStudents() {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.save(
                new Student("Elize", "11995200846", "delabrida.elize@hotmail.com"));
        studentDAO.save(
                new Student("Student1", "1199999999", "student@gmail.com"));
    }
}
