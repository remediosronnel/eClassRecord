package com.remedios.eclassrecordteacher.exportdata;

import com.remedios.eclassrecordteacher.student.AppDatabase;
import com.remedios.eclassrecordteacher.student.User;

import java.util.ArrayList;

public class KlsModel {
    private ArrayList<AppDatabase> users;

    String studentName;
    String studentLRN;

    String birthDate;
    String enrolledDate;
    String studentGender;
    String studentRemarks;
    String motherName;
    String fatherName;
    String studentAddress;
    String contactNumber;

    public KlsModel(String studentName, String studentLRN, String birthDate, String enrolledDate, String studentGender, String studentRemarks, String motherName, String fatherName, String studentAddress, String contactNumber) {
        this.studentName = studentName;
        this.studentLRN = studentLRN;
        this.birthDate = birthDate;
        this.enrolledDate = enrolledDate;
        this.studentGender = studentGender;
        this.studentRemarks = studentRemarks;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.studentAddress = studentAddress;
        this.contactNumber = contactNumber;
    }


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentLRN() {
        return studentLRN;
    }

    public void setStudentLRN(String studentLRN) {
        this.studentLRN = studentLRN;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEnrolledDate() {
        return enrolledDate;
    }

    public void setEnrolledDate(String enrolledDate) {
        this.enrolledDate = enrolledDate;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentRemarks() {
        return studentRemarks;
    }

    public void setStudentRemarks(String studentRemarks) {
        this.studentRemarks = studentRemarks;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }





}
