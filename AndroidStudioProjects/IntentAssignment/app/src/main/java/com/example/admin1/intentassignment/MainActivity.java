package com.example.admin1.intentassignment;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, phoneEditText, passwordFirst, passwordSecond;
    private int radioButtonId;
    private RadioGroup radioGroup;
    private RadioButton genderEditText;
    private View radioGroupItem;
    private CheckBox termsAndConditions;
    private String name, email, phone, password,gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = (EditText) findViewById(R.id.name_edittext);
        emailEditText = (EditText) findViewById(R.id.email_edittext);
        phoneEditText = (EditText) findViewById(R.id.phone_edittext);
        passwordFirst = (EditText) findViewById(R.id.password_first);
        passwordSecond = (EditText) findViewById(R.id.password_second);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroupItem = radioGroup.findViewById(radioButtonId);
        termsAndConditions = (CheckBox) findViewById(R.id.terms_conditions);

        /*if (savedInstanceState != null) {
            name = savedInstanceState.getString(Constants.NAME);
            email = savedInstanceState.getString(Constants.EMAIL);
            phone = savedInstanceState.getString(Constants.PHONE);
            String passwordOne = savedInstanceState.getString(Constants.PASSWORD_FIRST);
            String passwordTwo = savedInstanceState.getString(Constants.PASSWORD_SECOND);
            boolean terms  = savedInstanceState.getBoolean(Constants.TERMS);
            nameEditText.setText(name);
            emailEditText.setText(email);
            phoneEditText.setText(phone);
            passwordFirst.setText(passwordOne);
            passwordSecond.setText(passwordTwo);
            termsAndConditions.setChecked(terms);
        }*/
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.hi_bye_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.hi:
                Toast.makeText(getApplicationContext(),"Hii",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bye:
                Toast.makeText(getApplicationContext(),"Bye",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void callNextActivity(View view){

        if(!termsAndConditions.isChecked()){
            Toast.makeText(getApplicationContext(),"Please Accept Terms And Conditions",Toast.LENGTH_SHORT).show();
        }
        else if(!passwordSecond.getText().toString().equals(passwordFirst.getText().toString())){
            Toast.makeText(getApplicationContext(),"Password doesn't matches",Toast.LENGTH_SHORT).show();
        }
        else{
            name = nameEditText.getText().toString();
            email = emailEditText.getText().toString();
            phone = phoneEditText.getText().toString();
            password = passwordFirst.getText().toString();
            radioButtonId = radioGroup.getCheckedRadioButtonId();
            genderEditText = (RadioButton) findViewById(radioButtonId);
            gender = genderEditText.getText().toString();
            Person person = new Person(name,email,phone,password,gender);


            Bundle bundle = new Bundle();
            bundle.putSerializable("data",person);

            Intent intent = new Intent(getApplicationContext(),DisplayData.class);
            intent.putExtras(bundle);
            startActivity(intent);

            //Intent intent = new Intent(getApplicationContext(),DisplayData.class);
            //intent.putExtra("person",person);
        }

    }
/*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(getApplicationContext(),"saveinstance",Toast.LENGTH_SHORT).show();
        outState.putString(Constants.NAME,nameEditText.getText().toString());
        outState.putString(Constants.EMAIL,emailEditText.getText().toString());
        outState.putString(Constants.PHONE,phoneEditText.getText().toString());
        outState.putString(Constants.PASSWORD_FIRST,passwordFirst.getText().toString());
        outState.putString(Constants.PASSWORD_SECOND,passwordSecond.getText().toString());
        boolean checkBox = termsAndConditions.isChecked();
        outState.putBoolean(Constants.TERMS,checkBox);
    }
    */
}
class Person implements Serializable{
    private String name;
    private String email;
    private String phone;
    private String password;
    private String gender;
    Person(String name, String email, String phone, String password, String gender){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
    }

    protected Person(Parcel in) {
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        password = in.readString();
        gender = in.readString();
    }


    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
    public String getGender(){
        return gender;
    }

}