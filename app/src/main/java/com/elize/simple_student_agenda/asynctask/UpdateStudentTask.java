package com.elize.simple_student_agenda.asynctask;

import com.elize.simple_student_agenda.database.dao.RoomPhoneDAO;
import com.elize.simple_student_agenda.database.dao.RoomStudentDAO;
import com.elize.simple_student_agenda.model.Phone;
import com.elize.simple_student_agenda.model.PhoneType;
import com.elize.simple_student_agenda.model.Student;

import java.util.List;

public class UpdateStudentTask extends BaseStudentPhoneTask {
    private final Student student;
    private final RoomStudentDAO studentDAO;
    private final Phone landlineNumber;
    private final Phone mobileNumber;
    private final RoomPhoneDAO phoneDAO;
    private final List<Phone> studentPhones;

    public UpdateStudentTask(Student student, RoomStudentDAO studentDAO, Phone landlineNumber,
                             Phone mobileNumber, RoomPhoneDAO phoneDAO, List<Phone> studentPhones,
                             DoneListener listener) {
        super(listener);
        this.student = student;
        this.studentDAO = studentDAO;
        this.landlineNumber = landlineNumber;
        this.mobileNumber = mobileNumber;
        this.phoneDAO = phoneDAO;
        this.studentPhones = studentPhones;
        this.listener = listener;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        studentDAO.edit(student);
        addStudentToPhones(student.getId(), landlineNumber, mobileNumber);
        updatePhonesIds(landlineNumber, mobileNumber);
        phoneDAO.update(landlineNumber, mobileNumber);
        return null;
    }

    private void updatePhonesIds(Phone landlineNumber, Phone mobileNumber) {
        for (Phone phone : studentPhones) {
            if (phone.getType() == PhoneType.LANDLINE
            ) {
                landlineNumber.setId(phone.getId());
            } else if (phone.getType() == PhoneType.MOBILE) {
                mobileNumber.setId(phone.getId());
            }
        }
    }
}
