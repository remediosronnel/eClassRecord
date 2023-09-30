package com.remedios.eclassrecordteacher.classes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class Users {

    @PrimaryKey(autoGenerate = true)
    private final int id;
    private final String className;
    private final String teacherName;
    private final Date startDate;
    private final Date endDate;

    private int studentNumber;

    public Users(int id, String className, String teacherName, Date startDate, Date endDate, int studentNumber){
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

    public String getTeacherName() {
        return teacherName;
    }

    public String getClassName() {
        return className;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }
}
