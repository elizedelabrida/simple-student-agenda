package com.elize.simple_student_agenda.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity
public class Student implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String name;
    private String surname;
    private String mail;
    private Calendar dateCreated = Calendar.getInstance();

    public Student() {
    }

    public Student(Parcel in) {
        id = in.readInt();
        name = in.readString();
        surname = in.readString();
        mail = in.readString();
        dateCreated.setTimeInMillis(in.readLong());
    }

    public static final Parcelable.Creator<Student>
            CREATOR = new Parcelable.Creator<Student>() {

        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(mail);
        dest.writeLong(dateCreated.getTimeInMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCompleteName() {
        return name + " " + surname;
    }

    public boolean hasId() {
        return id > 0;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
