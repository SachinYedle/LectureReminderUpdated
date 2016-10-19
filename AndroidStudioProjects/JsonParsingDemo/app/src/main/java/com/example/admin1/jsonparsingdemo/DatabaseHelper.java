package com.example.admin1.jsonparsingdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by admin1 on 29/9/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DNADatabase.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "jsonData";

    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_LAT = "lat";
    public static final String COLUMN_NAME_LON = "lon";
    public static final String COLUMN_NAME_RADIUS = "radius";
    public static final String COLUMN_NAME_BIG_RADIUS = "big_radius";
    public static final String COLUMN_NAME_SUGGESTION = "suggestion";
    public static final String COLUMN_NAME_IMAGE_URL = "imageurl";
    public static final String COLUMN_NAME_THUMBNAIL = "thumbnail";
    public static final String COLUMN_NAME_BIG_IMAGE = "baigimage";
    public static final String COLUMN_NAME_BACKGROUND = "background";
    public static final String COLUMN_NAME_BANNER = "banner";
    public static final String COLUMN_NAME_INTERMEDIATE_THUMB = "intermediate_thumb";
    public static final String COLUMN_NAME_ITEM_ID = "itemid";
    public static final String COLUMN_NAME_ANDROID_ITEM_ID = "androiditemid";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_APP_DESCRIPTION = "app_description";
    public static final String COLUMN_NAME_HOUR_DETAILS = "hourdetails";
    public static final String COLUMN_NAME_ADDITIONAL_INFO = "additional_info";
    public static final String COLUMN_NAME_CONTACT_INFO = "contact_info";
    public static final String COLUMN_NAME_MAP_URL = "map_url";
    public static final String COLUMN_NAME_REVERSE_DIRECTION_FLAG = "reverse_direction_flag";
    public static final String COLUMN_NAME_CREATED_AT = "created_at";
    public static final String COLUMN_NAME_UPDATED_AT = "updated_at";
    public static final String COLUMN_NAME_PARENT_ID = "parent_id";
    public static final String COLUMN_NAME_LANGUAGE = "language";
    public static final String COLUMN_NAME_SUPPORT = "support";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
            +COLUMN_NAME_ID +" INTEGER PRIMARY KEY, " +COLUMN_NAME_NAME + " TEXT, "
            +COLUMN_NAME_LAT +" TEXT, "+ COLUMN_NAME_LON +" TEXT, "
            +COLUMN_NAME_RADIUS+" TEXT, "+COLUMN_NAME_BIG_RADIUS +" TEXT, "
            +COLUMN_NAME_SUGGESTION +" TEXT, "+ COLUMN_NAME_IMAGE_URL +" TEXT, "
            +COLUMN_NAME_THUMBNAIL +" TEXT, "+ COLUMN_NAME_BIG_IMAGE +" TEXT, "
            +COLUMN_NAME_BACKGROUND +" TEXT, "+ COLUMN_NAME_BANNER +" TEXT, "
            +COLUMN_NAME_INTERMEDIATE_THUMB +" TEXT, "+COLUMN_NAME_ITEM_ID + " TEXT, "
            +COLUMN_NAME_ANDROID_ITEM_ID +" TEXT, "+COLUMN_NAME_DESCRIPTION + " TEXT, "
            +COLUMN_NAME_APP_DESCRIPTION +" TEXT, "+COLUMN_NAME_HOUR_DETAILS +" TEXT,"
            +COLUMN_NAME_ADDITIONAL_INFO + " TEXT, "+COLUMN_NAME_CONTACT_INFO +" TEXT, "
            +COLUMN_NAME_MAP_URL+" TEXT, "+COLUMN_NAME_REVERSE_DIRECTION_FLAG+" TEXT, "
            +COLUMN_NAME_CREATED_AT+" TEXT, "+COLUMN_NAME_UPDATED_AT+" TEXT, "
            +COLUMN_NAME_PARENT_ID + " TEXT, " +COLUMN_NAME_LANGUAGE +" TEXT, "
            +COLUMN_NAME_SUPPORT +" TEXT )";


    private static final String SELECT_DATA = "SELECT * FROM "+ TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("DatabaseHelper","In contructor");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertData(int id,String name,String lat ,String lon ,String radius ,String big_radius,
                           String suggestion, String imageurl ,String thumbnail,
                           String bigimage ,String background,String banner,
                           String intermediate_thumb,String itemid,String androiditemid,
                           String description,String app_description,String hourdetails,
                           String additional_info,String contact_info,String map_url,
                           String reverse_direction,String created_at,String updated_at,
                           String parent_id,String language ,String support){


        if(!isIdInserted(id)){
            Log.i("Log", "in Update");
            return updateData(id,name,lat,lon,radius,big_radius,suggestion,imageurl,
                    thumbnail,bigimage,background,banner,intermediate_thumb,itemid
                    ,androiditemid,description,app_description,hourdetails,additional_info
                    ,contact_info,map_url,reverse_direction,created_at,updated_at,parent_id,
                    language,support);
        }else {

            Log.i("Log", "in insertdata");

            SQLiteDatabase database = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_ID, id);
            values.put(COLUMN_NAME_NAME, name);
            values.put(COLUMN_NAME_LAT, lat);
            values.put(COLUMN_NAME_LON, lon);
            values.put(COLUMN_NAME_RADIUS, radius);
            values.put(COLUMN_NAME_BIG_RADIUS, big_radius);
            values.put(COLUMN_NAME_SUGGESTION, suggestion);
            values.put(COLUMN_NAME_IMAGE_URL, imageurl);
            values.put(COLUMN_NAME_THUMBNAIL, thumbnail);
            values.put(COLUMN_NAME_BIG_IMAGE, bigimage);
            values.put(COLUMN_NAME_BACKGROUND, background);
            values.put(COLUMN_NAME_BANNER, banner);
            values.put(COLUMN_NAME_INTERMEDIATE_THUMB, intermediate_thumb);
            values.put(COLUMN_NAME_ITEM_ID, itemid);
            values.put(COLUMN_NAME_ANDROID_ITEM_ID, androiditemid);
            values.put(COLUMN_NAME_DESCRIPTION, description);
            values.put(COLUMN_NAME_APP_DESCRIPTION, app_description);
            values.put(COLUMN_NAME_HOUR_DETAILS, hourdetails);
            values.put(COLUMN_NAME_ADDITIONAL_INFO, additional_info);
            values.put(COLUMN_NAME_CONTACT_INFO, contact_info);
            values.put(COLUMN_NAME_MAP_URL, map_url);
            values.put(COLUMN_NAME_REVERSE_DIRECTION_FLAG, reverse_direction);
            values.put(COLUMN_NAME_CREATED_AT, created_at);
            values.put(COLUMN_NAME_UPDATED_AT, updated_at);
            values.put(COLUMN_NAME_PARENT_ID, parent_id);
            values.put(COLUMN_NAME_LANGUAGE, language);
            values.put(COLUMN_NAME_SUPPORT, support);
            database.insert(TABLE_NAME, null, values);
            return true;
        }
    }

    public boolean isIdInserted(int id){
        SQLiteDatabase database = getReadableDatabase();
        Cursor result = database.rawQuery("SELECT id FROM "+TABLE_NAME +" WHERE id = "+id,null);

        if(result.getCount()==0){
            Log.i("Log", "in insertdata if "+id+": res");
            return true;

        }
        else{
            Log.i("Log", "in insertdata else"+id+": res");
            return false;
        }

    }
    public Cursor getAllData(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor result = database.rawQuery(SELECT_DATA,null);
        return result;
    }

    public boolean updateData(int id,String name,String lat ,String lon ,String radius ,String big_radius,
                              String suggestion, String imageurl ,String thumbnail,
                              String bigimage ,String background,String banner,
                              String intermediate_thumb,String itemid,String androiditemid,
                              String description,String app_description,String hourdetails,
                              String additional_info,String contact_info,String map_url,
                              String reverse_direction,String created_at,String updated_at,
                              String parent_id,String language ,String support){


        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ID, id);
        values.put(COLUMN_NAME_NAME, name);
        values.put(COLUMN_NAME_LAT, lat);
        values.put(COLUMN_NAME_LON, lon);
        values.put(COLUMN_NAME_RADIUS, radius);
        values.put(COLUMN_NAME_BIG_RADIUS, big_radius);
        values.put(COLUMN_NAME_SUGGESTION, suggestion);
        values.put(COLUMN_NAME_IMAGE_URL, imageurl);
        values.put(COLUMN_NAME_THUMBNAIL, thumbnail);
        values.put(COLUMN_NAME_BIG_IMAGE, bigimage);
        values.put(COLUMN_NAME_BACKGROUND, background);
        values.put(COLUMN_NAME_BANNER, banner);
        values.put(COLUMN_NAME_INTERMEDIATE_THUMB, intermediate_thumb);
        values.put(COLUMN_NAME_ITEM_ID, itemid);
        values.put(COLUMN_NAME_ANDROID_ITEM_ID, androiditemid);
        values.put(COLUMN_NAME_DESCRIPTION, description);
        values.put(COLUMN_NAME_APP_DESCRIPTION, app_description);
        values.put(COLUMN_NAME_HOUR_DETAILS, hourdetails);
        values.put(COLUMN_NAME_ADDITIONAL_INFO, additional_info);
        values.put(COLUMN_NAME_CONTACT_INFO, contact_info);
        values.put(COLUMN_NAME_MAP_URL, map_url);
        values.put(COLUMN_NAME_REVERSE_DIRECTION_FLAG, reverse_direction);
        values.put(COLUMN_NAME_CREATED_AT, created_at);
        values.put(COLUMN_NAME_UPDATED_AT, updated_at);
        values.put(COLUMN_NAME_PARENT_ID, parent_id);
        values.put(COLUMN_NAME_LANGUAGE, language);
        values.put(COLUMN_NAME_SUPPORT, support);
        database.update(TABLE_NAME,values,COLUMN_NAME_ID+ " = "+ id,null);
        return true;
    }
    public int deleteAllData(){
        Log.i("Log","in All delete");

        SQLiteDatabase database = getReadableDatabase();
        int res = database.delete(TABLE_NAME,null,null);
        return res;
    }
    public int deleteId( int id){
        Log.i("Log","in delete" + id);
        SQLiteDatabase database = getReadableDatabase();
        int res = database.delete(TABLE_NAME,COLUMN_NAME_ID +" = "+id,null);
        return res;
    }
}
