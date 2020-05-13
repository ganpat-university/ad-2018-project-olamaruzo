package com.example;

public class Student {
    public String fname,lname,contact,email,aadhar,pass,cpass,designation;

    public Student(String fname, String lname, String contact, String email, String aadhar, String pass, String cpass, String designation) {
        this.fname = fname;
        this.lname = lname;
        this.contact = contact;
        this.email = email;
        this.aadhar = aadhar;
        this.pass = pass;
        this.cpass = cpass;
        this.designation = designation;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public Student()
    {

    }
}
