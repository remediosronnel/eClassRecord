package com.remedios.eclassrecordteacher.classes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Users {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String className;
    private String teacherName;

    private String startDate;
    private String endDate;
    private int studentNumber;

    public Users(int id, String className, String teacherName, String startDate, String endDate, int studentNumber){
        this.id = id;
        this.teacherName = teacherName;
        this.className = className;
        this.startDate = startDate;
        this.endDate = endDate;
        this.studentNumber = studentNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(){
        this.teacherName = teacherName;
    }

    public String getClassName() {
        return className;
    }


    public int getStudentNumber() {
        return studentNumber;
    }
    public void setStudentNumber(){
        this.studentNumber = studentNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(){
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(){
        this.endDate = endDate;
    }
}
