package com.example.admin1.datastoragedemo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin1 on 27/9/16.
 */

public class MyApplicationClass extends Application {
    private DatabaseHelper helper;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        helper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("storeName", Context.MODE_PRIVATE);
    }

    public void setHelper(DatabaseHelper helper){
        this.helper = helper;
    }
    public DatabaseHelper getHelper(){
        return helper;
    }
    public  void setSharedPreferences(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }
    public SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }
}
