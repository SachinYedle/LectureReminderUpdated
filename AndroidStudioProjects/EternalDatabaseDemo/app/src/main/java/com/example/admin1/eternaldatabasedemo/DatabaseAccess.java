package com.example.admin1.eternaldatabasedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin1 on 1/10/16.
 */

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private  static  DatabaseAccess access;
    private DatabaseAccess(Context context){

        openHelper = new DatabaseHelper(context);
    }
    public void open() {
        database = openHelper.getWritableDatabase();
    }

    public static DatabaseAccess getAccess(Context context){
        if(access == null){
            access = new DatabaseAccess(context);
        }
        return access;
    }
    public String getData(){
        Cursor data = database.rawQuery("SELECT * FROM person", null);
        if(data.getCount()==0){
            return "";
        }

        boolean flg;
        String valuesToDisplay = "SQLiteDatabaseData: " ;
        while(data.moveToNext()){
           String  name = data.getString(1);
            int age = data.getInt(2);
            float avg = data.getFloat(3);
            long value = data.getLong(4);
            String flagValue = data.getString(5);
            if(flagValue.equals("true")){
                flg = true;
            }else
                flg = false;
            valuesToDisplay += "\nName: "+name+"\nAge: "+age+"\nAvg: "+avg+"\nValue: "+value+"\nFlag: "+flg;
        }
        return valuesToDisplay;

    }
    public void close() {
        if (database != null) {
            database.close();
        }
    }
}
