package com.example.admin1.navigationdrawerdemo;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.HashMap;


/**
 * Created by admin1 on 18/10/16.
 */

public class DrawerLayoutFragment extends Fragment implements ItemClickListener{

    private String []textValues;
    private HashMap<String,Fragment> map;
    private Fragment fragment;
    private TextView textView;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drawer_layout,container,false);

        textValues = new String[]{"Cricket","Tennis","Football"};

        map = new HashMap<String, Fragment>();
        map.put(textValues[0],new CricketFragment());
        map.put(textValues[1],new TennisFragment());
        map.put(textValues[2],new FootballFragment());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyRecyclerViewAdapter(getActivity(),textValues);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view, int position) {

        fragment = map.get(textValues[position]);
        FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,fragment).commit();
        getActivity().setTitle(textValues[position]);
        MainActivity.drawerLayout.closeDrawer(GravityCompat.START);
        textValues [position] = "Cricket";
        adapter.notifyDataSetChanged();
    }
}
