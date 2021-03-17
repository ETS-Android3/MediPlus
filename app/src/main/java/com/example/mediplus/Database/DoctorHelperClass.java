package com.example.mediplus.Database;

public class DoctorHelperClass implements Comparable<DoctorHelperClass>  {


    String fullName, email, phoneNo, password,speciality;

    public DoctorHelperClass() {}

    public DoctorHelperClass(String fullName, String email, String phoneNo, String password ,String speciality) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.speciality= speciality;


    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

  @Override
    public int compareTo(DoctorHelperClass o) {
        return this.getFullName().compareTo(o.getFullName());
    }

    }


