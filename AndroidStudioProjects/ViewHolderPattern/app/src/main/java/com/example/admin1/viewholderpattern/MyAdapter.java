package com.example.admin1.viewholderpattern;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
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
    static Context context;
    MyAdapter(Context context, String[]textValues, int []imageValues) {
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
        ViewHolder viewHolder;
        if(view ==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(R.layout.list_view_layout,viewGroup,false);
            viewHolder = new ViewHolder(view);
            Log.i("Position","if"+i);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
            Log.i("Position","else"+i);
        }
        Log.i("Position","getView"+i);
        viewHolder.textView.setText(textValues[i]);
        viewHolder.textView.setTag("textView"+i);
        viewHolder.imageView.setImageResource(imageValues[i]);
        viewHolder.imageView.setTag(i);


        return view;
    }

    static class ViewHolder implements View.OnClickListener{
        TextView textView;
        ImageView imageView;

        ViewHolder(View view){
            textView = (TextView)view.findViewById(R.id.text);
            textView.setOnClickListener(this);
            imageView = (ImageView)view.findViewById(R.id.image);
            imageView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            String str = view.getTag().toString();
            if(str.contains("text")){
                Toast.makeText(context,""+str,Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(context,"ImageView"+str,Toast.LENGTH_SHORT).show();
        }
    }
}

