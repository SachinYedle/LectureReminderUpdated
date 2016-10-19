package com.example.admin1.datastoragedemo;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DisplayData extends AppCompatActivity {

    private Toolbar toolbar;
    private String valuesToDisplay;
    private String name;
    private int age;
    private boolean flg;
    private float avg;
    private long value;
    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;
    private Person person;
    private SharedPreferences preferences;
    private DatabaseHelper helper;
    private TextView sharedPrefData, internalStorageData, externalStorageData,databaseData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        preferences = ((MyApplicationClass)this.getApplication()).getSharedPreferences();
        helper =((MyApplicationClass)this.getApplication()).getHelper();

        sharedPrefData = (TextView)findViewById(R.id.shared_pref_data);
        internalStorageData = (TextView)findViewById(R.id.internal_data);
        externalStorageData = (TextView)findViewById(R.id.external_data);
        databaseData = (TextView)findViewById(R.id.database_data);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Display Activity");

        fromSharedPreferenceData();
        fromInternalStorageData();
        fromExternalStorageData();
        fromSQLiteDatabase();
    }

    /** display shared preferences data **/
    public void fromSharedPreferenceData(){

        name = preferences.getString("name","");
        flg = preferences.getBoolean("flag",false);
        age = preferences.getInt("age",0);
        avg = preferences.getFloat("avg",0);
        value = preferences.getLong("value",0);

        valuesToDisplay = "Shared Pref Data:\nName: "+name+"\nAge: "+age+"\nAvg: "+avg+"\nValue: "+value+"\nFlag: "+flg;

        sharedPrefData.setText(valuesToDisplay);
    }

    /** display Internal Storage Data **/
    public void fromInternalStorageData(){

        try {
            fileInputStream = openFileInput("person.ser");
            objectInputStream = new ObjectInputStream(fileInputStream);
            person = (Person)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            valuesToDisplay = "\n\nInternalSorageData:\nName: "+person.getName()+"\nAge: "+person.getAge()+"\nAvg: "+person.getAvg()+"\nValue: "+person.getValue()+"\nFlag: "+person.getFlag();
            internalStorageData.setText(valuesToDisplay);

        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException:",e.getLocalizedMessage(),e);
        } catch (ClassNotFoundException e) {
            Log.e("ClassNotFoundException:",e.getLocalizedMessage(),e);
        } catch (IOException e) {
            Log.e("IOException:",e.getLocalizedMessage(),e);
        }
    }

    /** display External Storage Data **/
    public void fromExternalStorageData(){
        try {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString();
            fileInputStream = new FileInputStream(path + "/person.ser");
            objectInputStream = new ObjectInputStream(fileInputStream);
            person = (Person)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            valuesToDisplay = "\n\nExternalSorageData:\nName: "+person.getName()+"\nAge: "+person.getAge()+"\nAvg: "+person.getAvg()+"\nValue: "+person.getValue()+"\nFlag: "+person.getFlag();
            externalStorageData.setText(valuesToDisplay);

        } catch (Exception e) {
            Log.e("File", e.getLocalizedMessage(), e);
        }
    }

    /** display Data from Database **/
    public void fromSQLiteDatabase(){

        Cursor data = helper.getAllData();

        if(data.getCount()==0){

            return;
        }
        while(data.moveToNext()){
            name = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_NAME));
            age = data.getInt(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_AGE));
            avg = data.getFloat(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_AVG));
            value = data.getLong(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_VALUE));
            String flagValue = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_FLAG));
            if(flagValue.equals("true")){
                flg = true;
            }else
                flg = false;
            valuesToDisplay = "\n\nSQLiteDatabaseData:\nName: "+name+"\nAge: "+age+"\nAvg: "+avg+"\nValue: "+value+"\nFlag: "+flg;
        }

        databaseData.setText(valuesToDisplay);
    }
}
