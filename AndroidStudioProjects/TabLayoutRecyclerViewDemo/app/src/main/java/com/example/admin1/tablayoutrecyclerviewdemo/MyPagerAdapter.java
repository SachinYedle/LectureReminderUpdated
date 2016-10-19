package com.example.admin1.tablayoutrecyclerviewdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by admin1 on 5/10/16.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private int pageCount;

    public MyPagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.pageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        TabFragment fragment = new TabFragment();
        fragment.setPosition(position);
        //Log.i("Position: frag: ",""+position);
        return fragment;
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab" + (position + 1);
    }
}
