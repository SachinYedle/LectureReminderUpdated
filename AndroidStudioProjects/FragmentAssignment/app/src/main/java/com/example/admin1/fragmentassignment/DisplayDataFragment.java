package com.example.admin1.fragmentassignment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by admin1 on 6/9/16.
 */
public class DisplayDataFragment extends Fragment implements View.OnClickListener{
    private TextView displayData;
    private Button submit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.display_data,container,false);

        displayData = (TextView) view.findViewById(R.id.display_all_data);
        submit = (Button)view.findViewById(R.id.submit_button);

        submit.setOnClickListener(this);

        Person person = (Person) getArguments().getSerializable("data");
        String personData = "Name: " + person.getName() + "\nEmail: " + person.getEmail() + "\nPhone: " + person.getPhone() + "\nGender: " + person.getGender();

        displayData.setText(personData);
        return view;

    }

    @Override
    public void onClick(View view) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        GetDataFragment getDataFragment = new GetDataFragment();
        transaction.replace(R.id.show_data, getDataFragment);
        transaction.addToBackStack("show");
        transaction.commit();
    }
}
