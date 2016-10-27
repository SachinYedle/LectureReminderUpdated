package com.example.sachin.lecturereminder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sachin on 10/19/2016.
 */
/**spinner adapter*/
public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> spinnerData;
    SpinnerAdapter(Context context, ArrayList<String> spinnerText){
        this.context = context;
        this.spinnerData = spinnerText;
    }
    @Override
    public int getCount() {
        return spinnerData.size();
    }

    @Override
    public Object getItem(int position) {
        return spinnerData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.spinner_layout,parent,false);
        TextView label = (TextView)view.findViewById(R.id.spinner_text);
        label.setText(spinnerData.get(position));
        return view;
    }
}
