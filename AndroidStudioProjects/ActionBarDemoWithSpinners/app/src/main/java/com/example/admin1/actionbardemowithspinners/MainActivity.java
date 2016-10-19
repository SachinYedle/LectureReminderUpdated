package com.example.admin1.actionbardemowithspinners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Spinner spinner;

    String []values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        values = new String[]{"Home","Work","other"};
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        spinner = (Spinner)findViewById(R.id.spinner);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_layout_for_toolbar,null);
        if(toolbar != null){
            Log.i("position","if");
            setSupportActionBar(toolbar);
            toolbar.addView(view);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }

        spinner.setAdapter(new MyAdapter(this,values));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this,""+item,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void nextActivity(View view){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                Toast.makeText(this,"Search",Toast.LENGTH_SHORT).show();
                break;
            case R.id.call:
                Toast.makeText(this,"Call",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public static class MyAdapter extends BaseAdapter{

        Context context;
        String []values;
        MyAdapter(Context context,String []values){
            this.context = context;
            this.values = values;
        }
        @Override
        public int getCount() {
            return values.length;
        }

        @Override
        public Object getItem(int i) {
            return values[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            View layout = inflater.inflate(R.layout.spinner_row,viewGroup,false);
            TextView textView = (TextView)layout.findViewById(R.id.text);
            textView.setText(values[i]);
            return layout;
        }
    }
}
