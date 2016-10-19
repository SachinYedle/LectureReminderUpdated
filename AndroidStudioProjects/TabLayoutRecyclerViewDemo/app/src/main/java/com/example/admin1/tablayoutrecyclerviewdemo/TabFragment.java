package com.example.admin1.tablayoutrecyclerviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by admin1 on 4/10/16.
 */

public class TabFragment extends Fragment {
    public TextView textView;
    private int position;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout,container,false);
        textView = (TextView)view.findViewById(R.id.text);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity()));
        String []textValues = ((MainActivity) getActivity()).getRecyclerviewdata(position);
        recyclerView.setAdapter(new MyAdapter(getActivity(),textValues));
        //Log.i("oncreate","dsa");

        setTextViewData();
        return view;
    }
    public void setTextViewData(){
        String string =((MainActivity) getActivity()).getValue(position);
        //Log.i("set position",""+position);
        textView.setText(string);
    }

    public void setPosition(int position){
        this.position = position;
        //Log.i("position: frag",""+position);
    }
    public int getPosition(){
        return position;
    }
}
