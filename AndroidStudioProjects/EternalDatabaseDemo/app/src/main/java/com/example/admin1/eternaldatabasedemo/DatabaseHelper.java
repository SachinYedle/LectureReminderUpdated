package com.example.admin1.eternaldatabasedemo;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by admin1 on 1/10/16.
 */

public class DatabaseHelper extends SQLiteAssetHelper {

    public static final String DATABASE_NAME = "personDatabase.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
