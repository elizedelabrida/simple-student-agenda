package com.elize.simple_student_agenda.asynctask;

import android.os.AsyncTask;

import com.elize.simple_student_agenda.database.dao.RoomPhoneDAO;
import com.elize.simple_student_agenda.model.Phone;

import java.util.List;

public class GetStudentPhonesTask extends AsyncTask<Void, Void, List<Phone>> {
    private final RoomPhoneDAO phoneDAO;
    private final int studentId;
    private final GetStudentPhonesTaskListener listener;

    public GetStudentPhonesTask(RoomPhoneDAO phoneDAO, int studentId, GetStudentPhonesTaskListener listener) {
        this.phoneDAO = phoneDAO;
        this.studentId = studentId;
        this.listener = listener;
    }

    @Override
    protected List<Phone> doInBackground(Void... voids) {
        return phoneDAO.getStudentPhones(studentId);
    }

    @Override
    protected void onPostExecute(List<Phone> phones) {
        super.onPostExecute(phones);
        listener.whenDone(phones);

    }

    public interface GetStudentPhonesTaskListener {
        void whenDone(List<Phone> phones);
    }

}
