package com.elize.simple_student_agenda.ui.activity.adapter;

import android.view.View;
import android.widget.TextView;

import com.elize.simple_student_agenda.R;

public class ListStudentViewHolder {
    public final TextView textViewName;
    public final TextView textViewPhone;

    public ListStudentViewHolder(View view) {
        textViewName = view.findViewById(R.id.item_student_name);
        textViewPhone = view.findViewById(R.id.item_student_phone);
    }
}
