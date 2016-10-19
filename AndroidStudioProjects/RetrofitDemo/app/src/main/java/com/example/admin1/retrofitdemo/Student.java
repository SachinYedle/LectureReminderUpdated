package com.example.admin1.retrofitdemo;

/**
 * Created by admin1 on 12/10/16.
 */
public class Student {

    //Variables that are in our json
    private int StudentId;
    private String StudentName;
    private String StudentMarks;

    //Getters and setters
    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int bookId) {
        this.StudentId = StudentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String S) {
        this.StudentName = StudentName;
    }

    public String getStudentMarks() {
        return StudentMarks;
    }

    public void setStudentMarks(String studentMarks) {
        this.StudentMarks = StudentMarks;
    }

}
