package com.elize.simple_student_agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.elize.simple_student_agenda.R;
import com.elize.simple_student_agenda.dao.StudentDAO;
import com.elize.simple_student_agenda.model.Student;

import static com.elize.simple_student_agenda.ui.activity.Constants.STUDENT_KEY;

public class RegisterStudentActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE_NEW_STUDENT = "Student Register";
    public static final String APPBAR_TITLE_EDIT_STUDENT = "Student Update";
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private final StudentDAO studentDAO = new StudentDAO();
    Student student = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        initializeEditTexts();
        loadStudent();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_register_student_menu_save) {
            finishRegisterStudent();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_register_student_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void loadStudent() {
        Intent data = getIntent();
        if (data.hasExtra(STUDENT_KEY)) {
            setTitle(APPBAR_TITLE_EDIT_STUDENT);
            student = (Student) data.getSerializableExtra(STUDENT_KEY);
            fillStudentFields();
        } else {
            setTitle(APPBAR_TITLE_NEW_STUDENT);
            student = new Student();
        }
    }

    private void fillStudentFields() {
        editTextName.setText(student.getName());
        editTextEmail.setText(student.getMail());
        editTextPhone.setText(student.getPhone());
    }

    private void finishRegisterStudent() {
        fillStudent();
        if (student.hasId()) {
            studentDAO.edit(student);
        } else {
            studentDAO.save(student);
        }
        finish();
    }

    private void initializeEditTexts() {
        editTextName = findViewById(R.id.activity_list_student_name);
        editTextPhone = findViewById(R.id.activity_list_student_telephone);
        editTextEmail = findViewById(R.id.activity_list_student_email);
    }

    private void fillStudent() {
        student.setName(editTextName.getText().toString());
        student.setPhone(editTextPhone.getText().toString());
        student.setMail(editTextEmail.getText().toString());
    }
}