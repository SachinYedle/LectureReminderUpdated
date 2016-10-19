package com.example.admin1.tablayoutrecyclerviewdemo;

import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    public String []textViewData;
    public String [][]recyclerViewData;
    private Toolbar toolbar;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(),getCount());
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    public void initializeArrays(){
        textViewData = new String[getCount()];
        recyclerViewData = new String[getCount()][];
    }
    public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count = count;
    }
    public String getValue(int position){
        return textViewData[position];
    }

    public String []getRecyclerviewdata(int positon){
        String []strings = recyclerViewData[positon];
        return strings;
    }
    public void getData(){

        DatabaseAccess databaseAccess = DatabaseAccess.getAccess(MainActivity.this);
        databaseAccess.open();
        Cursor result = databaseAccess.getData();

        if(result.getCount() == 0){
            return;
        }
        setCount(result.getCount());
        int i=0;
        initializeArrays();
        while (result.moveToNext()){
            int id = result.getInt(0);
            String stagename = result.getString(1);
            String short_snap_text = result.getString(2);
            String short_decription = result.getString(3);
            String data = result.getString(4);
            int stage_enum = result.getInt(5);
            parseData(data,i);
            textViewData[i] = "\nID: "+ id +"\nStageName: "
                    + stagename +"\nShort Snap Text: " + short_snap_text
                    + "\n Short decription: " + short_decription
                    + "\n Stage Enum: " + stage_enum;
            i++;
        }
        databaseAccess.close();
        return;
    }
    public void parseData(String data, int looper) {
        try {

            JSONObject jsonRootObject = new JSONObject(data);
            JSONArray jsonArray = jsonRootObject.getJSONArray("data");
            String [] strings = new String[jsonArray.length()];
            for(int i=0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                int order = jsonObject.getInt("order");
                String url = jsonObject.getString("url");
                strings[i] = "\nID: " + id + "\nName: " + name +
                        "\nOrder: " + order + " Url: " + url;
            }
            recyclerViewData[looper] = strings;
            } catch (JSONException e) {
            Log.e("Excepton in parsing",e.getLocalizedMessage(),e);
        }
    }
}
