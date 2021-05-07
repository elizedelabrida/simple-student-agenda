package com.elize.simple_student_agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.elize.simple_student_agenda.R;
import com.elize.simple_student_agenda.asynctask.GetStudentPhonesTask;
import com.elize.simple_student_agenda.asynctask.SaveStudentTask;
import com.elize.simple_student_agenda.asynctask.UpdateStudentTask;
import com.elize.simple_student_agenda.database.StudentAgendaDatabase;
import com.elize.simple_student_agenda.database.dao.RoomPhoneDAO;
import com.elize.simple_student_agenda.database.dao.RoomStudentDAO;
import com.elize.simple_student_agenda.model.Phone;
import com.elize.simple_student_agenda.model.PhoneType;
import com.elize.simple_student_agenda.model.Student;

import java.util.ArrayList;
import java.util.List;

import static com.elize.simple_student_agenda.ui.activity.Constants.STUDENT_KEY;

public class RegisterStudentActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE_NEW_STUDENT = "Student Register";
    public static final String APPBAR_TITLE_EDIT_STUDENT = "Student Update";
    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextLandlineNumber;
    private EditText editTextMobileNumber;
    private EditText editTextEmail;
    private RoomStudentDAO studentDAO;
    Student student = null;
    private RoomPhoneDAO phoneDAO;
    List<Phone> studentPhones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        studentDAO = StudentAgendaDatabase.getInstance(this).getRoomStudentDAO();
        phoneDAO = StudentAgendaDatabase.getInstance(this).getRoomPhoneDAO();
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
            student = data.getParcelableExtra(STUDENT_KEY);
            fillFields();
        } else {
            setTitle(APPBAR_TITLE_NEW_STUDENT);
            student = new Student();
        }
    }

    private void fillFields() {
        editTextName.setText(student.getName());
        editTextSurname.setText(student.getSurname());
        editTextEmail.setText(student.getMail());
        fillPhoneFields();
    }

    private void fillPhoneFields() {
        new GetStudentPhonesTask(phoneDAO, student.getId(), phones -> {
            this.studentPhones = phones;
            for (Phone phone : studentPhones) {
                if (phone.getType() == PhoneType.LANDLINE
                ) {
                    editTextLandlineNumber.setText(phone.getNumber());
                } else if (phone.getType() == PhoneType.MOBILE) {
                    editTextMobileNumber.setText(phone.getNumber());
                }
            }
        }).execute();
    }

    private void finishRegisterStudent() {
        fillStudent();
        Phone landlineNumber = createPhone(editTextLandlineNumber, PhoneType.LANDLINE);
        Phone mobileNumber = createPhone(editTextMobileNumber, PhoneType.MOBILE);
        if (student.hasId()) {
            updateStudent(landlineNumber, mobileNumber);

        } else {
            saveStudent(landlineNumber, mobileNumber);
        }
    }

    private void saveStudent(Phone landlineNumber, Phone mobileNumber) {
        new SaveStudentTask(studentDAO, student, landlineNumber, mobileNumber,
                phoneDAO, this::finish).execute();

    }

    private void updateStudent(Phone landlineNumber, Phone mobileNumber) {
        new UpdateStudentTask(student, studentDAO, landlineNumber,
                mobileNumber, phoneDAO, studentPhones, this::finish).execute();
    }


    private Phone createPhone(EditText editTextPhone, PhoneType type) {
        return new Phone(editTextPhone.getText().toString(),
                type);
    }

    private void initializeEditTexts() {
        editTextName = findViewById(R.id.activity_list_student_name);
        editTextSurname = findViewById(R.id.activity_list_student_surname);
        editTextLandlineNumber = findViewById(R.id.activity_list_student_landline_number);
        editTextMobileNumber = findViewById(R.id.activity_list_student_mobile_number);
        editTextEmail = findViewById(R.id.activity_list_student_email);
    }

    private void fillStudent() {
        student.setName(editTextName.getText().toString());
        student.setSurname(editTextSurname.getText().toString());
        student.setMail(editTextEmail.getText().toString());
    }
}