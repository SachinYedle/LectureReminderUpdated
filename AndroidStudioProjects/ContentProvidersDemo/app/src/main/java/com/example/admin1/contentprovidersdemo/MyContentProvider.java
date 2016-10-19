package com.example.admin1.contentprovidersdemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.net.URI;
import java.util.HashMap;

/**
 * Created by admin1 on 7/10/16.
 */

public class MyContentProvider extends ContentProvider {
    private DatabaseHelper helper;
    private SQLiteDatabase database;

    private static final String PROVIDER_NAME = "com.example.admin1.contentprovidersdemo.MyContentProvider";
    private static final String URL = "content://" + PROVIDER_NAME + "/student";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String ID = "id";
    public static final String ROLLNO = "rollno";
    public static final String NAME = "name";
    private static final String TABLE_NAME = "student";

    private static HashMap<String,String> valuesMap;
    private static final int STUDENT = 1;
    private static final int STUDENT_ID = 2;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(PROVIDER_NAME,"student",STUDENT);
        uriMatcher.addURI(PROVIDER_NAME,"student/#",STUDENT_ID);
    }
    @Override
    public boolean onCreate() {
        helper = new DatabaseHelper(getContext());
        database = helper.getWritableDatabase();
        if(database == null)
            return false;
        else
            return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_NAME);
        switch (uriMatcher.match(uri)){
            case STUDENT:
                queryBuilder.setProjectionMap(valuesMap);
                break;
            case STUDENT_ID:
                queryBuilder.appendWhere(ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("unknown uri: " + uri);
        }
        if(s1 == null || s1 == ""){
            s1 = ROLLNO;
        }
        Cursor result = queryBuilder.query(database, strings, s, strings1, null, null, s1);
        result.setNotificationUri(getContext().getContentResolver(),uri);
        return result;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case STUDENT:
                return "vnd.android.cursor.dir/vnd.example.student";

            case STUDENT_ID:
                return "vnd.android.cursor.item/vnd.example.student";

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long rowID = database.insert(TABLE_NAME, "", contentValues);
        if(rowID > 0){
            Uri uriChange = ContentUris.withAppendedId(CONTENT_URI,rowID);
            getContext().getContentResolver().notifyChange(uriChange,null);
            return uriChange;
        }
        throw new SQLException("Data not inserted"+uri);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case STUDENT:
                count = database.delete(TABLE_NAME,s,strings);
                break;
            case STUDENT_ID:
                String id = uri.getPathSegments().get(1);
                count = database.delete(TABLE_NAME,ID + "=" + id + (!TextUtils.isEmpty(s) ? "AND (" + s + ')' : ""), strings);
                break;
            default:
            throw new IllegalArgumentException("unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        int count = 0;

        switch (uriMatcher.match(uri)){
            case STUDENT:
                count = database.update(TABLE_NAME, contentValues, s, strings);
                break;

            case STUDENT_ID:
                count = database.update(TABLE_NAME, contentValues, ID + " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(s) ? " AND (" + s + ')' : ""), strings);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }


    private class DatabaseHelper extends SQLiteOpenHelper{
        private static final String DB_NAME = "ContentProvide.sqlite";
        private static final String CREATE_TABLE_STUDENT = "CREATE TABLE student " +
                "( id INTEGER PRIMARY KEY, rollno TEXT, name TEXT)";

        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
