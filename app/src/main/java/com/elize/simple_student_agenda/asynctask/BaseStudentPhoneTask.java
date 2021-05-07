package com.elize.simple_student_agenda.asynctask;

import android.os.AsyncTask;

import com.elize.simple_student_agenda.model.Phone;

public abstract class BaseStudentPhoneTask extends AsyncTask<Void, Void, Void> {

    protected DoneListener listener;

    protected BaseStudentPhoneTask(DoneListener listener) {
        this.listener = listener;
    }

    protected void addStudentToPhones(int studentId, Phone... phones) {
        for (Phone phone :
                phones) {
            phone.setStudentId(studentId);
        }
    }

    public interface DoneListener {
        void whenDone();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.whenDone();
    }

}
