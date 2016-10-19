package com.example.admin1.tablayoutrecyclerviewdemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin1 on 4/10/16.
 */

public class DatabaseAccess {
    private SQLiteOpenHelper helper;
    private static DatabaseAccess access;
    private SQLiteDatabase database;
    private DatabaseAccess(Context context){
        helper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getAccess(Context context){
        if(access == null){
            access = new DatabaseAccess(context);
        }
        return access;
    }

    public void open(){
        database = helper.getWritableDatabase();
    }

    public Cursor getData(){
        Cursor result = database.rawQuery("SELECT * FROM DB_STAGE_INFO",null);
        return result;
    }


    public void close(){
        if (database != null){
            database.close();
        }
    }
}
