package com.elize.simple_student_agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.elize.simple_student_agenda.R;
import com.elize.simple_student_agenda.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.elize.simple_student_agenda.ui.activity.Constants.STUDENT_KEY;

public class ListStudentActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Student List";
    private final ListStudentView listStudentView = new ListStudentView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(APPBAR_TITLE);
        setContentView(R.layout.activity_list_student);
        configureFabNewStudentButton();
        configureStudentList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_list_student_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.activity_list_student_menu_remove) {
            listStudentView.confirmRemoval(item);
        }
        return super.onContextItemSelected(item);
    }


    private void configureFabNewStudentButton() {
        FloatingActionButton buttonRegisterStudent =
                findViewById(R.id.activity_list_student_fab_new_student);
        buttonRegisterStudent.setOnClickListener(view -> openRegisterStudentForCreate());
    }

    private void openRegisterStudentForCreate() {
        startActivity(new
                Intent(this, RegisterStudentActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listStudentView.updateStudent();
    }

    private void configureStudentList() {
        ListView listViewStudents = findViewById(R.id.activity_list_student_listView);
        listStudentView.configureAdapter(listViewStudents);
        configureItemClickListener(listViewStudents);
        registerForContextMenu(listViewStudents);
    }

    private void configureItemClickListener(ListView listViewStudents) {
        listViewStudents.setOnItemClickListener((adapterView, view, position, id) -> {
            Student selectedStudent = (Student) adapterView.getItemAtPosition(position);
            openRegisterStudentForEdit(selectedStudent);
        });
    }

    private void openRegisterStudentForEdit(Student selectedStudent) {
        Intent registerStudent =
                new Intent(ListStudentActivity.this, RegisterStudentActivity.class);
        registerStudent.putExtra(STUDENT_KEY, selectedStudent);
        startActivity(registerStudent);
    }
}
