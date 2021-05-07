package com.elize.simple_student_agenda.asynctask;

import android.os.AsyncTask;

import com.elize.simple_student_agenda.database.dao.RoomPhoneDAO;
import com.elize.simple_student_agenda.model.Phone;

public class GetStudentFirstPhoneTask extends AsyncTask<Void, Void, Phone> {

    private final int studentId;
    private final RoomPhoneDAO roomPhoneDAO;
    private final GetStudentFirstPhoneListener listener;

    public GetStudentFirstPhoneTask(int studentId, RoomPhoneDAO roomPhoneDAO, GetStudentFirstPhoneListener listener) {
        this.studentId = studentId;
        this.roomPhoneDAO = roomPhoneDAO;
        this.listener = listener;
    }

    @Override
    protected Phone doInBackground(Void... voids) {
        return roomPhoneDAO.getStudentFirstPhone(studentId);
    }

    @Override
    protected void onPostExecute(Phone phone) {
        super.onPostExecute(phone);
        listener.whenDone(phone);
    }

    public interface GetStudentFirstPhoneListener {
        void whenDone(Phone phone);
    }
}
