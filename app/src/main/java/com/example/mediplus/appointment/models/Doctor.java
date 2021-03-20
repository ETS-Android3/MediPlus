package com.example.mediplus.appointment.models;

public class Doctor implements Comparable<Doctor> {
     String fullName;
     String password;
     String phoneNo;
     String email;
     String speciality;

    public Doctor() {
    }

    public Doctor(String fullName, String email, String phoneNo, String password, String speciality) {
        this.fullName = fullName;
        this.password = password;
        this.phoneNo = phoneNo;
        this.email = email;
        this.speciality = speciality;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String code) {
        this.password = code;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public int compareTo(Doctor o) {
        return this.getFullName().compareTo(o.getFullName());
    }
}
