package com.example.admin1.viewpagerdemo;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by admin1 on 14/9/16.
 */
public class MyAdapter extends PagerAdapter {

    Context context;
    String [] pages;
    LayoutInflater inflater;
    MyAdapter(Context context,String []pages){
        this.context = context;
        this.pages = pages;
    }
    @Override
    public int getCount() {
        return pages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView textView;
        inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.view_pager_layout,container,false);
        textView = (TextView) view.findViewById(R.id.page_number);
        textView.setText(pages[position]);
        container.addView(view);
        Log.i("position: "," "+position);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.i("destroyItem  position: "," "+position);
        container.removeView((RelativeLayout) object);    }
}
