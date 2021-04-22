package com.elize.simple_student_agenda.dao;

import com.elize.simple_student_agenda.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final static List<Student> listStudents = new ArrayList<>();
    private static int idCount = 1;

    public void save(Student student) {
        student.setId(idCount);
        listStudents.add(student);
        updateIds();
    }

    private void updateIds() {
        idCount++;
    }

    public void edit(Student student) {
        Student editStudent = getStudentById(student);
        if (editStudent != null) {
            int studentPosition = listStudents.indexOf(editStudent);
            listStudents.set(studentPosition, student);
        }
    }

    private Student getStudentById(Student student) {
        for (Student s : listStudents) {
            if (student.getId() == s.getId()) {
                return s;
            }
        }
        return null;
    }

    public List<Student> listAll() {
        return new ArrayList<>(listStudents);
    }

    public void remove(Student student) {
        Student studentRemoved = getStudentById(student);
        if (studentRemoved != null) {
            listStudents.remove(studentRemoved);
        }
    }
}
