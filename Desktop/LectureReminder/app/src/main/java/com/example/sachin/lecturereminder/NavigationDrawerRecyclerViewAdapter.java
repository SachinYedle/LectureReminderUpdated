package com.example.sachin.lecturereminder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin1 on 18/10/16.
 */
/**Drawer recycler view adapter*/
public class NavigationDrawerRecyclerViewAdapter extends RecyclerView.Adapter<NavigationDrawerRecyclerViewAdapter.ViewHolder> {
    private Context context;
    public ItemClickListener itemClickListener;
    private ArrayList<String> recyclerViewData;
    NavigationDrawerRecyclerViewAdapter(Context context, ArrayList<String> recyclerViewData){
        this.context = context;
        this.recyclerViewData = recyclerViewData;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.drawer_recycler_view_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView.setText(recyclerViewData.get(position));
    }

    @Override
    public int getItemCount() {
        return recyclerViewData.size();
    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.personal_data);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener != null){
                itemClickListener.onClick(view,getAdapterPosition());
            }
        }
    }
}
