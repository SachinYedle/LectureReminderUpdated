package com.example.admin1.jsonparsingdemo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin1 on 27/9/16.
 */

public class MyApplicationClass extends Application {
    private DatabaseHelper helper;
    @Override
    public void onCreate() {
        super.onCreate();
        helper = new DatabaseHelper(this);
    }

    public void setHelper(DatabaseHelper helper){
        this.helper = helper;
    }
    public DatabaseHelper getHelper(){
        return helper;
    }
}
