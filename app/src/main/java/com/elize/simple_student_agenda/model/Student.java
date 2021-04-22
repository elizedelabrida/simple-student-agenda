package com.elize.simple_student_agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Student implements Serializable {
    public Student() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id = 0;
    private String name;
    private String phone;
    private String mail;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public Student(String name, String phone, String mail) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public boolean hasId() {
        return id > 0;
    }
}
