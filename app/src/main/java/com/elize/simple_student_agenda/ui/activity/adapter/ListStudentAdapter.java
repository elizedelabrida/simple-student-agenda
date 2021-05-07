package com.elize.simple_student_agenda.ui.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.elize.simple_student_agenda.R;
import com.elize.simple_student_agenda.asynctask.GetStudentFirstPhoneTask;
import com.elize.simple_student_agenda.database.StudentAgendaDatabase;
import com.elize.simple_student_agenda.database.dao.RoomPhoneDAO;
import com.elize.simple_student_agenda.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ListStudentAdapter extends BaseAdapter {
    private final List<Student> listStudents = new ArrayList<>();
    private final Context context;
    final RoomPhoneDAO roomPhoneDAO;

    public ListStudentAdapter(Context context) {
        this.context = context;
        roomPhoneDAO = StudentAgendaDatabase.getInstance(context).getRoomPhoneDAO();
    }

    @Override
    public int getCount() {
        return listStudents.size();
    }

    @Override
    public Student getItem(int position) {
        return listStudents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listStudents.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListStudentViewHolder holder;
        View view;
        if (convertView == null) {
            view = LayoutInflater
                    .from(context)
                    .inflate(R.layout.item_student, parent, false);
            holder = new ListStudentViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ListStudentViewHolder) view.getTag();
        }
        fulfillView(position, holder);
        return view;
    }

    private void fulfillView(int position, ListStudentViewHolder holder) {
        Student returnedStudent = listStudents.get(position);
        holder.textViewName.setText(returnedStudent.getCompleteName());
        new GetStudentFirstPhoneTask(returnedStudent.getId(), roomPhoneDAO,
                phone -> holder.textViewPhone.setText(phone.getNumber())).execute();

    }

    public void update(List<Student> listStudentsUpdated) {
        this.listStudents.clear();
        this.listStudents.addAll(listStudentsUpdated);
        notifyDataSetChanged();
    }

    public void remove(Student student) {
        listStudents.remove(student);
        notifyDataSetChanged();
    }
}

