package com.elize.simple_student_agenda.asynctask;

import com.elize.simple_student_agenda.database.dao.RoomPhoneDAO;
import com.elize.simple_student_agenda.database.dao.RoomStudentDAO;
import com.elize.simple_student_agenda.model.Phone;
import com.elize.simple_student_agenda.model.Student;

public class SaveStudentTask extends BaseStudentPhoneTask {
    private final RoomStudentDAO studentDAO;
    private final Student student;
    private final Phone mobileNumber;
    private final Phone landlineNumber;
    private final RoomPhoneDAO phoneDAO;


    public SaveStudentTask(RoomStudentDAO studentDAO, Student student, Phone mobileNumber,
                           Phone landlineNumber, RoomPhoneDAO phoneDAO, DoneListener listener) {

        super(listener);
        this.studentDAO = studentDAO;
        this.student = student;
        this.mobileNumber = mobileNumber;
        this.landlineNumber = landlineNumber;
        this.phoneDAO = phoneDAO;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int studentId = studentDAO.save(student).intValue();
        addStudentToPhones(studentId, landlineNumber, mobileNumber);
        phoneDAO.save(landlineNumber, mobileNumber);
        return null;
    }

}
