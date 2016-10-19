package com.example.admin1.usingcontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.renderscript.Sampler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks{

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.text);
    }

    public void onClick(View view){
        getSupportLoaderManager().initLoader(1,null,this);

    }
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(this, Uri.parse("content://com.example.admin1.contentprovidersdemo.MyContentProvider/student"),null,null,null,null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        Cursor result = (Cursor)data;
        String values = "";
        while (result.moveToNext()){
            int id = result.getInt(0);
            String rollno = result.getString(1);
            String name = result.getString(2);
            values += "\nID: " + id +"\nRoll No: " + rollno + "\nName: "+name;
        }
        textView.setText(values);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
