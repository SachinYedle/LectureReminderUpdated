package com.example.admin1.contentprovidersdemo;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText rollno,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollno = (EditText)findViewById(R.id.rollno);
        name = (EditText)findViewById(R.id.name);
    }
    public void onClick(View view){
        ContentValues values = new ContentValues();
        values.put(MyContentProvider.ROLLNO,rollno.getText().toString());
        values.put(MyContentProvider.NAME,name.getText().toString());
        Uri uri = getContentResolver().insert(MyContentProvider.CONTENT_URI,values);
        rollno.setText("");
        name.setText("");
        Toast.makeText(MainActivity.this,"Data Inserted...",Toast.LENGTH_SHORT).show();
        Log.i("URI",""+uri);
    }
}
