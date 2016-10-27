package com.example.sachin.lecturereminder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
/**user registration */
public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    private EditText nameEditText,classEditText,emailEditText,mobileNoEditText;
    private Spinner spinner;
    private ArrayList<String> spinnerData;
    private String bloodGroup;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        nameEditText = (EditText)findViewById(R.id.name);
        classEditText = (EditText)findViewById(R.id.student_class);
        emailEditText = (EditText)findViewById(R.id.email);
        mobileNoEditText = (EditText)findViewById(R.id.mobile_number);
        spinner = (Spinner)findViewById(R.id.spinner);
        submitButton = (Button)findViewById(R.id.submit_button);

        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registration");

        submitButton.setOnClickListener(this);

        spinnerData = new ArrayList<String>();
        spinnerData.add("O+");
        spinnerData.add("O-");
        spinnerData.add("A+");
        spinnerData.add("A-");
        spinnerData.add("B+");
        spinnerData.add("B-");
        spinnerData.add("AB+");
        spinnerData.add("AB-");

        spinner.setAdapter(new SpinnerAdapter(RegistrationActivity.this,spinnerData));
        spinner.setOnItemSelectedListener(this);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bloodGroup = parent.getItemAtPosition(position).toString();
        //.makeText(RegistrationActivity.this,""+bloodGroup,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        String name = nameEditText.getText().toString();
        String className = classEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String mobile = mobileNoEditText.getText().toString();
        boolean isValid = validateData(name,className,email,mobile);
        if(isValid) {
            DatabaseAccess access = DatabaseAccess.getDatabaseAccess(RegistrationActivity.this);

            boolean isRegistered = access.checkUserAlreadyRegiseterd(email);
            if(!isRegistered)
            {
                access.insertUser(name,className,email,mobile,bloodGroup);

                SharedPreferences sharedPref = getSharedPreferences("userData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("name",name);
                editor.putString("className",className);
                editor.putString("email",email);
                editor.putString("mobile",mobile);
                editor.putString("bloodGroup",bloodGroup);
                editor.putBoolean("userExist",true);
                editor.commit();

                Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(RegistrationActivity.this,"user with email is already registered..",Toast.LENGTH_SHORT).show();
            }
        }
    }
    /**Validate user data*/
    public boolean validateData(String name,String classname,String email,String mobile){
        boolean isValid = true;
        if(name.isEmpty() || name.length() < 3){
            nameEditText.setError("at least 3 characters");
            isValid = false;
        }else if (classname.isEmpty()){
            classEditText.setError("Not Null");
            isValid = false;
        }else if(mobile.isEmpty() || mobile.length()!=10){
            mobileNoEditText.setError("exact 10 digits with no country code");
            isValid = false;
        }else if (email.isEmpty() || !isValidEmailAddress(email)){
            emailEditText.setError("please enter valid email");
            isValid = false;
        }
        return isValid;
    }

    /** validate email address entered by user*/
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
