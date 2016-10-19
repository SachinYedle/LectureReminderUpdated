package com.example.admin1.tablayoutrecyclerviewdemo;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by admin1 on 4/10/16.
 */

public class DatabaseHelper extends SQLiteAssetHelper {

    public static final String DATABASE_NAME = "CKD-db.sqlite";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
