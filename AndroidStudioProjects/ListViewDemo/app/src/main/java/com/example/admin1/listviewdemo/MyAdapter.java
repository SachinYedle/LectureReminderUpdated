package com.example.admin1.listviewdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by admin1 on 14/9/16.
 */
public class MyAdapter extends BaseAdapter {
    Context context;
    String []values;
    TextView textView;
    MyAdapter(Context context,String [] values){
        this.values = values;
        this.context = context;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.list_vew_custom,viewGroup,false);
        textView = (TextView) layout.findViewById(R.id.textvew);
        textView.setText(values[i]);

        return layout;
    }
}
