package com.example.admin1.tablayoutrecyclerviewdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by admin1 on 5/10/16.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    private Context context;
    private String []textValues;
    MyAdapter(Context context, String [] textValues){
        this.context = context;
        this.textValues = textValues;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.recycler_view_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        //Log.i("Position","OnCreate");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = textValues[position];
        name =  name.substring(name.indexOf("Name:") + 6, name.indexOf("Order:"));
        //Log.d("name", "onBindViewHolder: "+name);
        holder.textView.setText("Name: "+name);
    }

    @Override
    public int getItemCount() {
        return textValues.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_recycler_view);
            textView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            switch (view.getId()){
                case R.id.text_recycler_view:
                    String urlData = textValues[position];
                    urlData = urlData.substring(urlData.indexOf("U") + 5);
                    Intent intent = new Intent(context,WebViewDemo.class);
                    intent.putExtra("url",urlData);
                    context.startActivity(intent);
                    //Log.i("BindView Holder",""+position);
                    break;
            }
        }
    }
}
