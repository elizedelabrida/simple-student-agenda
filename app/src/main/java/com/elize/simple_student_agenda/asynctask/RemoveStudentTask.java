package com.elize.simple_student_agenda.asynctask;

import android.os.AsyncTask;

import com.elize.simple_student_agenda.database.dao.RoomStudentDAO;
import com.elize.simple_student_agenda.model.Student;
import com.elize.simple_student_agenda.ui.activity.adapter.ListStudentAdapter;

public class RemoveStudentTask extends AsyncTask<Void, Void, Void> {
    private final RoomStudentDAO studentDAO;
    private final ListStudentAdapter adapter;
    private final Student student;

    public RemoveStudentTask(RoomStudentDAO studentDAO, ListStudentAdapter adapter, Student student) {
        this.studentDAO = studentDAO;
        this.adapter = adapter;
        this.student = student;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        studentDAO.remove(student);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.remove(student);
    }
}
