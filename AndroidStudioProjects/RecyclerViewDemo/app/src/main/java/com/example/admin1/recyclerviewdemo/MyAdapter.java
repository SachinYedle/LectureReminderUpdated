package com.example.admin1.recyclerviewdemo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by admin1 on 15/9/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    static Context context;
    String []textValues;
    int []imageValues;
    MyAdapter(Context context,String []textValues,int []imageValues){
        this.context = context;
        this.textValues = textValues;
        this.imageValues = imageValues;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        Log.i("Position","OnCreate");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageResource(imageValues[position]);
        holder.textView.setText(textValues[position]);
        Log.i("Position","OnBind");
    }

    @Override
    public int getItemCount() {
        Log.i("Position","getItemCount");
        return textValues.length;

    }

    @Override
    public int getItemViewType(int position) {
        //Toast.makeText(context,"getType"+super.getItemViewType(position),Toast.LENGTH_SHORT).show();
        return super.getItemViewType(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            textView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition()+1;
            switch (view.getId()){
                case R.id.text:
                    Toast.makeText(context,"TextView:"+position,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.image:
                    Toast.makeText(context,"ImageView:"+position,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
