package com.example.admin1.greendaodemo.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "STUDENT".
 */
public class student {

    private Long id;
    /** Not-null value. */
    private String rollno;
    /** Not-null value. */
    private String name;

    public student() {
    }

    public student(Long id) {
        this.id = id;
    }

    public student(Long id, String rollno, String name) {
        this.id = id;
        this.rollno = rollno;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getRollno() {
        return rollno;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    /** Not-null value. */
    public String getName() {
        return name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName(String name) {
        this.name = name;
    }

}
