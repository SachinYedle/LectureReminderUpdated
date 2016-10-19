package com.example.admin1.datastoragedemo;

import java.io.Serializable;

/**
 * Created by admin1 on 27/9/16.
 */

public class Person implements Serializable {
    private String name;
    private int age;
    private float avg;
    private long value;
    private boolean flag;
    Person(String name, int age,float avg,long value,boolean flag){
        this.name = name;
        this.age = age;
        this.avg = avg;
        this.value = value;
        this.flag = flag;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setValue(long value){
        this.value = value;
    }
    public void setFlag(boolean flag){
        this.flag = flag;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public float getAvg(){
        return avg;
    }
    public long getValue(){
        return value;
    }
    public boolean getFlag(){
        return flag;
    }
}

