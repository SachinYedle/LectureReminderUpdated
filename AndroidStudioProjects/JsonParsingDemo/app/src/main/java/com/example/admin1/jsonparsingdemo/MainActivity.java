package com.example.admin1.jsonparsingdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String jsonString ;
    private DatabaseHelper helper ;

    private int id;
    private TextView textView;
    private String name;
    private String lat ,parent_id,support;
    private String radius ,big_radius;
    private String lon;
    private String suggestion,imageurl ,thumbnail;
    private String bigimage ,background,banner;
    private String intermediate_thumb,itemid,androiditemid;
    private String description,app_description, hourdetails;
    private String additional_info,contact_info,map_url;
    private String reverse_direction,created_at,updated_at,language;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = ((MyApplicationClass)this.getApplication()).getHelper();
        textView = (TextView)findViewById(R.id.text);

        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name","Sachin");
        editor.commit();


        if(helper!=null){
            Log.i("Helper","not null");
        }
        if(textView!=null){
            Log.i("Textview :","not null");
        }

    }
    public void onClick(View view){
        //String url = "http://dna-dev.elasticbeanstalk.com/api/refuges/get-other-refuges";
        //String timeParam =  "2016-04-07 2009:14:20";
        //String language = "english";
        //String parameter =  getUrl(timeParam,language);

        String time = "2016-04-07 09:14:20";
        String languge = "english";
        String SpecifiedUrl = getUrl(time,languge);

        getJsonText(SpecifiedUrl);
    }
    public String getUrl(String time,String language){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("dna-dev.elasticbeanstalk.com")
                .appendPath("api")
                .appendPath("refuges")
                .appendPath("get-other-refuges")
                .appendQueryParameter("time",time)
                .appendQueryParameter("language",language);
        String url = builder.build().toString();
        return  url;
    }

    public void getJsonText(String url){

        new ReadUrl(this).execute(url);

    }



    private  class ReadUrl extends AsyncTask<String,Void,String> {

        ProgressDialog dialog;
        Context context;
        ReadUrl(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setTitle("Please wait....");
            dialog.setMessage("Loading data");
            dialog.setIndeterminate(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    cancel(true);
                }
            });

        }

        @Override
        protected String doInBackground(String... strings) {
            try {

                ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    URL url = new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if (responseCode != 200) {
                        Log.i("Response", "Network Error");
                    }
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuffer buffer = new StringBuffer();
                    while ((inputLine = reader.readLine()) != null) {
                        buffer.append(inputLine);
                    }
                    reader.close();
                    return buffer.toString();
                } else {
                    Toast.makeText(context,"No network connection available.",Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("Exception:",e.getLocalizedMessage(),e);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            if(s!=null) {
                setJsonString(s);
            }
        }
    }


    public void setJsonString(String jsonString){
        this.jsonString = jsonString;
        textView.setText("Done");
        parseJson();

    }
    public void parseJson(){
        try {
            JSONObject jsonRootObject = new JSONObject(jsonString);
            String code = jsonRootObject.getString("code");
            String success = jsonRootObject.getString("success");
            String message = jsonRootObject.getString("message");
            String current_time = jsonRootObject.getString("current_time");
            JSONArray jsonArray = jsonRootObject.optJSONArray("data");


            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                id = jsonObject.getInt("id");
                Log.i("values1",": "+id);

                name = jsonObject.getString("name");
                Log.i("values1",": "+name);

                lon = jsonObject.getString("lon");
                Log.i("values2",": "+lon);
                radius = jsonObject.getString("radius");

                Log.i("values3",": "+radius);

                big_radius = jsonObject.getString("big_radius");
                Log.i("values4",": "+big_radius);

                lat = jsonObject.getString("lat");
                Log.i("values5",": "+lat);

                parent_id = jsonObject.getString("parent_id");
                Log.i("values6",": "+parent_id);

                support = jsonObject.getString("support");
                Log.i("values7",": "+support);

                suggestion = jsonObject.getString("suggestion");
                Log.i("values8",": "+suggestion);

                imageurl = jsonObject.getString("imageurl");
                Log.i("values9",": "+imageurl);

                thumbnail = jsonObject.getString("thumbnail");
                Log.i("values10",": "+thumbnail);



                bigimage = jsonObject.getString("bigimage");
                Log.i("values11",": "+bigimage);

                background = jsonObject.getString("background");
                Log.i("values12",": "+background);

                banner = jsonObject.getString("banner");
                Log.i("values13",": "+banner);

                intermediate_thumb = jsonObject.getString("bigimage");
                Log.i("values14",": "+intermediate_thumb);

                itemid = jsonObject.getString("itemid");
                Log.i("values15",": "+itemid);

                androiditemid = jsonObject.getString("androiditemid");
                Log.i("values16",": "+androiditemid);

                description = jsonObject.getString("description");
                Log.i("values17",": "+description);

                app_description = jsonObject.getString("app_description");
                Log.i("values18",": "+app_description);

                hourdetails = jsonObject.getString("hourdetails");

                Log.i("values19",": "+hourdetails);

                additional_info = jsonObject.getString("additional_info");
                Log.i("values20",": "+additional_info);

                contact_info = jsonObject.getString("contact_info");
                Log.i("values21",": "+contact_info);

                map_url = jsonObject.getString("map_url");
                Log.i("values22",": "+map_url);


                reverse_direction = jsonObject.getString("reverse_direction_flag");
                Log.i("values23",": "+reverse_direction);

                created_at = jsonObject.getString("created_at");
                Log.i("values24",": "+created_at);

                updated_at = jsonObject.getString("updated_at");
                Log.i("values25",": "+updated_at);

                language = jsonObject.getString("language");
                Log.i("values26",": "+language);

                if(textView == null){
                    Log.i("Textview :","is null");
                }



                if(helper!=null){
                    boolean result = helper.insertData(id,name,lat,lon,radius,big_radius,suggestion,imageurl,
                            thumbnail,bigimage,background,banner,intermediate_thumb,itemid
                            ,androiditemid,description,app_description,hourdetails,additional_info
                            ,contact_info,map_url,reverse_direction,created_at,updated_at,parent_id,
                            language,support);

                    if(result){
                        Log.i("Result: ","Data Inserted");

                    }else {
                        Log.i("Result: ","Data Not Inserted");
                    }
                }else {
                    Toast.makeText(this,"helper is null",Toast.LENGTH_SHORT).show();
                    Log.e("helper:","helper is null");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
         displayData();
    }
    public void displayData(){
        Cursor data= helper.getAllData();
        if(data.getCount()==0){

            return;
        }
        String dataString = "";
        while(data.moveToNext()) {
            id = data.getInt(0);
            name = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_NAME));
            lat = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_LAT));
            lon = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_LON));
            radius = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_RADIUS));
            big_radius = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_BIG_RADIUS));
            suggestion = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_SUGGESTION));
            imageurl = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_IMAGE_URL));
            thumbnail = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_THUMBNAIL));
            bigimage = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_BIG_IMAGE));
            background = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_BACKGROUND));
            banner = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_BANNER));
            intermediate_thumb = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_INTERMEDIATE_THUMB));
            itemid = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_ITEM_ID));
            androiditemid = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_ANDROID_ITEM_ID));
            description = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_DESCRIPTION));
            app_description = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_APP_DESCRIPTION));
            hourdetails = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_HOUR_DETAILS));
            additional_info = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_ADDITIONAL_INFO));
            contact_info = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_CONTACT_INFO));
            map_url = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_MAP_URL));
            reverse_direction = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_REVERSE_DIRECTION_FLAG));
            created_at = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_CREATED_AT));
            updated_at = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_UPDATED_AT));
            parent_id = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_PARENT_ID));
            language = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_LANGUAGE));
            support = data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_NAME_SUPPORT));

            String displayData = "All Data\n\nID: "+id+"\n\nName: " + name +"\n\nLat: "+lat+
                    "\n\nLon: "+lon+"\n\nRadius: "+radius+"\n\nBig Radius: "+big_radius+"\n\nSuggestion: "+
                    suggestion+"\n\nImage Url:"+imageurl+"\n\nThumbNail:"+thumbnail+
                    "\n\nBigImage:"+bigimage+"\n\nBackground: "+background+"\n\nBanner: "+banner+
                    "\n\nIntermediate thumbnail: "+intermediate_thumb+"\n\nItem id: "+itemid+
                    "\n\nAndroid Id: "+androiditemid+"\n\nDescription: "+description+
                    "\n\nApp Description: "+app_description+"\n\n Hour details: "+hourdetails+
                    "\n\nAdditional info: "+additional_info+"\n\nContact info:"+contact_info+
                    "\n\nMap Url: "+map_url+"\n\nReverse dir flg:"+reverse_direction+"\n\nCreated At: "+
                    created_at+"\n\nUpdated At: "+updated_at+"\n\nParent id: "+parent_id+
                    "\n\nLanguage: "+language+"\n\nsupport: "+support;
            dataString += "\n\n"+displayData;

        }
        data.close();
        textView.setText(dataString);

        //int res = helper.deleteAllData();
        //Log.i("Log","res"+res);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","onStop");
    }
}
