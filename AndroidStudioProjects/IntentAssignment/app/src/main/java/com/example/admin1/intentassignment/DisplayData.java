package com.example.admin1.intentassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayData extends AppCompatActivity {

    private TextView displayData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        displayData = (TextView) findViewById(R.id.display_data);

        Intent intent = this.getIntent();

        /** serializable **/
        Bundle bundle = intent.getExtras();

        Person person = (Person)bundle.getSerializable("data");
        String personData = "Name: " + person.getName() + "\nEmail: " + person.getEmail() + "\nPhone: " + person.getPhone() + "\nGender: " + person.getGender();

        displayData.setText(personData);
    }

}
