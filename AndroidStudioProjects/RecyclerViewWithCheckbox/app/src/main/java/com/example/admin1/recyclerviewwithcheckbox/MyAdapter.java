package com.example.admin1.recyclerviewwithcheckbox;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by admin1 on 6/10/16.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private Context context;
    private String [] checkBoxText;
    private boolean []isChecked;
    MyAdapter(Context context,String [] checkBoxText){
        this.context = context;
        this.checkBoxText = checkBoxText;
        isChecked = new boolean[30];
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.recyler_view_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(isChecked[position]);
        holder.checkBox.setText(checkBoxText[position]);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!isChecked[position]){
                    isChecked[position] = true;
                }else {
                    isChecked[position] = false;
                }
            }
        });
        Log.i("OnBind",""+position);
    }

    @Override
    public int getItemCount() {
        return checkBoxText.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;
        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox)itemView.findViewById(R.id.checkbox);

        }
    }
}
