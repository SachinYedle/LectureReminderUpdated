package com.example.admin1.listviewchildclick;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by admin1 on 15/9/16.
 */
public class MyAdapter extends BaseAdapter {

    String []textValues;
    int[]imageValues;
    Context context;
    MyAdapter(Context context,String[]textValues,int []imageValues) {
        this.textValues = textValues;
        this.imageValues = imageValues;
        this.context = context;
    }
    @Override
    public int getCount() {
        return textValues.length;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.list_view_layout,viewGroup,false);
        TextView textView = (TextView)layout.findViewById(R.id.text);
        ImageView imageView =(ImageView)layout.findViewById(R.id.image);
        textView.setText(textValues[i]);
        imageView.setImageResource(imageValues[i]);
        return layout;
    }
}
