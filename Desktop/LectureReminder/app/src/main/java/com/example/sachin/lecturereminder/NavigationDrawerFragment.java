package com.example.sachin.lecturereminder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by Sachin on 19/10/16.
 */

public class NavigationDrawerFragment extends Fragment implements ItemClickListener{

    private ArrayList<String> recyclerViewData;
    private RecyclerView recyclerView;
    private NavigationDrawerRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drawer_layout,container,false);

        SharedPreferences preferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String name = preferences.getString("name","Sachin");
        String className = preferences.getString("className","Final Year");
        String email = preferences.getString("email","sachin.yedle@riktamtech.com");
        String mobile = preferences.getString("mobile","9637060452");
        String bloodGroup = preferences.getString("bloodGroup","AB+");

        recyclerViewData = new ArrayList<String>();
        recyclerViewData.add(name);
        recyclerViewData.add(className);
        recyclerViewData.add(email);
        recyclerViewData.add(mobile);
        recyclerViewData.add(bloodGroup);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new NavigationDrawerRecyclerViewAdapter(getActivity(),recyclerViewData);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view, int position) {
        Toast.makeText(getActivity(),""+recyclerViewData.get(position),Toast.LENGTH_LONG).show();
    }
}
