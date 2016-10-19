package com.example.admin1.datastoragedemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RadioGroup radioGroup;
    private int radioButtonId;
    private RadioButton genderEditText;
    private View radioGroupItem;

    private String name;
    private int age;
    private boolean flg;
    private float avg;
    private long value;

    private Person person;
    private DatabaseHelper helper ;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private EditText nameEdittext,avgEdittext,valueEdittext,ageEdittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = ((MyApplicationClass)this.getApplication()).getHelper();

        sharedPreferences = ((MyApplicationClass)this.getApplication()).getSharedPreferences();
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroupItem = radioGroup.findViewById(radioButtonId);
        nameEdittext = (EditText)findViewById(R.id.name);
        avgEdittext = (EditText)findViewById(R.id.avg);
        valueEdittext = (EditText)findViewById(R.id.value);
        ageEdittext = (EditText)findViewById(R.id.age);

        toolbar = (Toolbar)findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.next_actvity:
                getAndSetData();
                Intent intent = new Intent(this,DisplayData.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** get data from edittext **/
    public void getAndSetData(){
        name = nameEdittext.getText().toString();
        age = Integer.parseInt(ageEdittext.getText().toString());
        avg = Float.parseFloat(avgEdittext.getText().toString());
        value = Long.parseLong(valueEdittext.getText().toString());

        radioButtonId = radioGroup.getCheckedRadioButtonId();
        radioButtonId = radioGroup.getCheckedRadioButtonId();
        genderEditText = (RadioButton) findViewById(radioButtonId);
        String flag = genderEditText.getText().toString();
        if(flag.equals("true"))
            flg = true;
        else
            flg = false;

        person = new Person(name,age,avg,value,flg);
        toSharedPreference();
        toInternalStorage();
        toExternalStorage();
        toSQLiteDatabase();
    }

    /** to Shared Preferences **/
    public void toSharedPreference(){
        editor = sharedPreferences.edit();

        editor.putString("name",name);
        editor.putBoolean("flag",flg);
        editor.putFloat("avg",avg);
        editor.putLong("value",value);
        editor.putInt("age",age);
        editor.commit();

        
    }

    /** to Internal Storaage **/
    public void toInternalStorage(){

        try {
            String path = getFilesDir().toString();
            File file = new File(path+"/"+"person.ser");
            Log.i("Internal mem space: ",""+file.getFreeSpace());

            Log.i("Path","Internal Path:"+path);
            FileOutputStream fileOutputStream = openFileOutput("person.ser",Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(person);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException: ", e.getLocalizedMessage(), e);
        } catch (IOException e) {
            Log.e("IOException: ", e.getLocalizedMessage(), e);
        }
    }

    /** Checks if external storage is available for read and write **/
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /** to External Storage **/
    public void toExternalStorage(){
        if(isExternalStorageWritable()){

            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString();
            File file = new File(path,"person.ser");
            FileOutputStream fileOutputStream = null;
            ObjectOutputStream objectOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(person);
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                Log.e("FileNotFoundException",e.getLocalizedMessage(),e);
            } catch (IOException e) {
                Log.e("IOException",e.getLocalizedMessage(),e);
            }
        }

    }
    /** to SQLite database **/
    public void toSQLiteDatabase(){

        boolean result = helper.insertData(name,age,avg,value,flg);
        if(result){
            Log.i("ToSQLite Databse","Data Inserted");
        }
        else{
            Log.e("ToSQLite Databse","Data Insertion Error");
        }
    }
}
