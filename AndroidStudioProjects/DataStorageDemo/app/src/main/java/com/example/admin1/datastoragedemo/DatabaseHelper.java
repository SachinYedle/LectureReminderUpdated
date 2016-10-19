package com.example.admin1.datastoragedemo;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin1 on 27/9/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "personDatabase.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "person";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_AGE = "age";
    public static final String COLUMN_NAME_AVG = "avg";
    public static final String COLUMN_NAME_VALUE = "value";
    public static final String COLUMN_NAME_FLAG = "flag";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "+COLUMN_NAME_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +COLUMN_NAME_NAME + " TEXT NOT NULL, " +
            COLUMN_NAME_AGE + " INT NOT NULL, "+COLUMN_NAME_AVG +" REAL NOT NULL, "+COLUMN_NAME_VALUE+" REAL NOT NULL, "+COLUMN_NAME_FLAG+" TEXT NOT NULL )";

    private static final String SELECT_DATA = "SELECT * FROM "+ TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /** insert data to database **/
    public boolean insertData(String name,int age,float avg,long value,boolean flag){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NAME,name);
        values.put(COLUMN_NAME_AGE,age);
        values.put(COLUMN_NAME_AVG,avg);
        values.put(COLUMN_NAME_VALUE,value);
        String str = "true";
        if(!flag)
            str = "false";
        values.put(COLUMN_NAME_FLAG,str);
        database.insert(TABLE_NAME,null,values);
        return true;
    }
    /** get data from Database **/
    public Cursor getAllData(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor result = database.rawQuery(SELECT_DATA,null);
        return result;
    }
}