package com.example.mediplus.Database;

public class PatientHelperclass {


        String fullName, address, email, phoneNo, password, date, gender;

        public PatientHelperclass() {}

        public PatientHelperclass(String fullName, String address, String email, String phoneNo, String password, String date, String gender) {
            this.fullName = fullName;
            this.address = address;
            this.email = email;
            this.phoneNo = phoneNo;
            this.password = password;
            this.date = date;
            this.gender = gender;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getUsername() {
            return address;
        }

        public void setUsername(String username) {
            this.address = address;
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

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }


    }

