package com.example.admin1.fragmentassignment;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

/**
 * Created by admin1 on 6/9/16.
 */
public class GetDataFragment extends Fragment implements View.OnClickListener{

    private EditText nameEditText, emailEditText, phoneEditText, passwordFirst, passwordSecond;
    private int radioButtonId;
    private RadioGroup radioGroup;
    private RadioButton genderEditText;
    private View radioGroupItem;
    private CheckBox termsAndConditions;
    private String name, email, phone, password,gender;
    private Button submit;
    private View view;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("Frag-1","onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Frag-1","onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            Log.i("Frag-1","savedstate");

        }

        view = inflater.inflate(R.layout.get_data, container, false);
        nameEditText = (EditText) view.findViewById(R.id.name_edittext);
        emailEditText = (EditText) view.findViewById(R.id.email_edittext);
        phoneEditText = (EditText) view.findViewById(R.id.phone_edittext);
        passwordFirst = (EditText) view.findViewById(R.id.password_first);
        passwordSecond = (EditText) view.findViewById(R.id.password_second);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroupItem = radioGroup.findViewById(radioButtonId);
        termsAndConditions = (CheckBox) view.findViewById(R.id.terms_conditions);
        submit = (Button) view.findViewById(R.id.submit_button);
        submit.setOnClickListener(this);
        Log.i("Frag-1","createView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Frag-1","onactivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Frag-1","onstart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Frag-1","onresume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Frag-1","onpause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Frag-1","onstop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Frag-1","destroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Frag-1","ondestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Frag-1","ondetach");
    }

    @Override
    public void onClick(View buttonView) {
        name = nameEditText.getText().toString();
        email = emailEditText.getText().toString();
        phone = phoneEditText.getText().toString();
        password = passwordFirst.getText().toString();
        try {
            radioButtonId = radioGroup.getCheckedRadioButtonId();
            genderEditText = (RadioButton) view.findViewById(radioButtonId);
            gender = genderEditText.getText().toString();
        }catch (NullPointerException e){
            System.out.println("Exception: "+e);
            gender = "Not Selected";
        }
        Person person = new Person(name,email,phone,password,gender);

        DisplayDataFragment displayDataFragment = new DisplayDataFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",person);
        displayDataFragment.setArguments(bundle);



        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.show_data, displayDataFragment);
        transaction.addToBackStack("show");
        transaction.commit();


    }

}
class Person implements Serializable {
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