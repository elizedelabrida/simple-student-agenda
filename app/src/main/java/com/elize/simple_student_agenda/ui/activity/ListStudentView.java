package com.elize.simple_student_agenda.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.elize.simple_student_agenda.dao.StudentDAO;
import com.elize.simple_student_agenda.model.Student;
import com.elize.simple_student_agenda.ui.activity.adapter.ListStudentAdapter;

public class ListStudentView {

    private final StudentDAO studentDAO;
    private final ListStudentAdapter adapter;
    private final Context context;

    public ListStudentView(Context context) {
        this.context = context;
        this.adapter = new ListStudentAdapter(this.context);
        this.studentDAO = new StudentDAO();
    }

    public void confirmRemoval(@NonNull final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Remove student")
                .setMessage("Proceed removing student?")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Student studentSelected = adapter.getItem(menuInfo.position);
                    removeStudent(studentSelected);
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void updateStudent() {
        adapter.update(studentDAO.listAll());
    }

    private void removeStudent(Student student) {
        studentDAO.remove(student);
        adapter.remove(student);
    }

    public void configureAdapter(ListView listViewStudents) {
        listViewStudents.setAdapter(adapter);
    }
}
