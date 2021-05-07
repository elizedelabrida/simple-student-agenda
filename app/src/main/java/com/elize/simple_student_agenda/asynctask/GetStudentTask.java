package com.elize.simple_student_agenda.asynctask;

import android.os.AsyncTask;

import com.elize.simple_student_agenda.database.dao.RoomStudentDAO;
import com.elize.simple_student_agenda.model.Student;
import com.elize.simple_student_agenda.ui.activity.adapter.ListStudentAdapter;

import java.util.List;

public class GetStudentTask extends AsyncTask<Void, Void, List<Student>> {
    private final RoomStudentDAO studentDAO;
    private final ListStudentAdapter adapter;

    public GetStudentTask(RoomStudentDAO studentDAO, ListStudentAdapter adapter) {
        this.studentDAO = studentDAO;
        this.adapter = adapter;
    }

    @Override
    protected List<Student> doInBackground(Void[] objects) {
        return studentDAO.listAll();
    }

    @Override
    protected void onPostExecute(List<Student> students) {
        adapter.update(students);
        super.onPostExecute(students);
    }
}
