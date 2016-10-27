package com.example.sachin.lecturereminder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.sachin.lecturereminder.dbModel.DaoMaster;
import com.example.sachin.lecturereminder.dbModel.DaoSession;
import com.example.sachin.lecturereminder.dbModel.classData;
import com.example.sachin.lecturereminder.dbModel.classDataDao;
import com.example.sachin.lecturereminder.dbModel.userData;
import com.example.sachin.lecturereminder.dbModel.userDataDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sachin on 10/20/2016.
 */
public class DatabaseAccess  {

    private static classDataDao classDataModelDao;
    private classData classDataModel;
    private DaoSession daoSession;
    private static final String DB_NAME = "studentDB.sqlite";
    private userDataDao userDataModelDao;
    private userData userDataModel;
    private static DatabaseAccess databaseAccess;
    private Context context;

    private DatabaseAccess(Context context){
        this.context = context;
        classDataModelDao = setUpDB(context);
        userDataModelDao = daoSession.getUserDataDao();
    }

    public static DatabaseAccess getDatabaseAccess(Context context) {
        if(databaseAccess == null){
            databaseAccess = new DatabaseAccess(context);
        }
        return databaseAccess;
    }

    /**Set up database connection*/
    public classDataDao setUpDB(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,DB_NAME,null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(database);
        daoSession = master.newSession();
        return daoSession.getClassDataDao();
    }

    /**insert class to database*/
    public void insertClass(String name,String topic,String professor,String date,String time,String location, long id){
        String dtStart = date + time +":00";
        Date formatedDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            formatedDate = format.parse(dtStart);
            System.out.println(date);
        } catch (ParseException e) {
            Log.e("Date formatting error",e.getLocalizedMessage(),e);
        }
        classDataModel = new classData(null,name,topic,professor,formatedDate,location,id);
        long value = classDataModelDao.insert(classDataModel);
        Log.i("Value",""+value);
    }

    /** Insert user to database */
    public void insertUser(String name,String className,String email,String mobile,String bloodGroup){
        userDataModel = new userData(null,name,className,email,mobile,bloodGroup);
        userDataModelDao.insert(userDataModel);
    }

    public long getIdWithEmail(String email){
        List<userData> userDatas = userDataModelDao.queryBuilder()
                .where(userDataDao.Properties.Email.eq(email))
                .list();
        long id = 0;
        if(userDatas.size()>0){
            id = userDatas.get(0).getId();
        }
        return id;
    }

    public boolean checkUserAlreadyRegiseterd(String email){
        boolean isRegistered = false;
        List<userData> userDatas = userDataModelDao.queryBuilder()
                .where(userDataDao.Properties.Email.eq(email))
                .list();
        if(userDatas.size()>0){
            isRegistered = true;
        }
        return isRegistered;
    }
    /**get data from database*/
    public List<classData> getAllData(long id){
        //List<classData> classes = classDataModelDao.loadAll();
        List<classData> classes  = classDataModelDao.queryBuilder()
                .where(classDataDao.Properties.UserDataId.eq(id))
                .orderAsc(classDataDao.Properties.DateTime)
                .list();
        return classes;
    }
    public List<userData> getAllUserData(String email){
        List<userData> users  = userDataModelDao.queryBuilder()
                .where(userDataDao.Properties.Email.eq(email))
                .list();
        return users;
    }
    /**update databse record*/
    public void updateClass(String id ,String name,String topic,String professor,String date,String time,String location,String userDataId){
        String dtStart = date + time +":00";
        Date formatedDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            formatedDate = format.parse(dtStart);
            System.out.println(date);
        } catch (ParseException e) {
            Log.e("Date formatting error",e.getLocalizedMessage(),e);
        }
        long recordId = Long.parseLong(id);
        long userDataIdLong = Long.parseLong(userDataId);
        classDataModel = new classData(recordId,name,topic,professor,formatedDate,location,userDataIdLong);
        classDataModelDao.update(classDataModel);
    }
    /**delete database record*/
    public void delete(Long id){
        classDataModelDao.deleteByKey(id);
    }
}
