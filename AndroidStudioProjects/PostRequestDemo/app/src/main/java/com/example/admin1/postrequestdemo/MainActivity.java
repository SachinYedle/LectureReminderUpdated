package com.example.admin1.postrequestdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView emailText,passwordText,resPonseText;
    private Button submit;
    private Toolbar toolbar;
    private final static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Post Request Demo");

        resPonseText = (TextView)findViewById(R.id.text);
        emailText = (EditText)findViewById(R.id.email);
        passwordText = (EditText)findViewById(R.id.password);
        submit = (Button)findViewById(R.id.button);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Log.i(TAG,"Onclick");
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        Log.i(TAG,email+" "+password);

        String url = "http://dna-dev.elasticbeanstalk.com/api/login/login";
        String encodedParameters = getEncodedParam(email,password);

        new SendPostRequest().execute(url,encodedParameters);
    }



    public String getEncodedParam(String email,String password){

        String urlParameters = null;
        try {
            urlParameters = URLEncoder.encode("username","UTF-8") +
                    "="+ URLEncoder.encode(email,"UTF-8");
            urlParameters += "&" + URLEncoder.encode("password","UTF-8") +
                    "="+ URLEncoder.encode(password,"UTF-8");
            urlParameters += "&" + URLEncoder.encode("device_id","UTF-8") +
                    "="+ URLEncoder.encode("nexus","UTF-8");
            urlParameters += "&" + URLEncoder.encode("device_token","UTF-8") +
                    "="+ URLEncoder.encode("nexusToken","UTF-8");
            urlParameters += "&" + URLEncoder.encode("device_type","UTF-8") +
                    "="+ URLEncoder.encode("android","UTF-8");
            urlParameters += "&" + URLEncoder.encode("language","UTF-8") +
                    "="+ URLEncoder.encode("english","UTF-8");

        } catch (UnsupportedEncodingException e) {
            Log.i(TAG,"URL Encoding Error");
        }
        return urlParameters;
    }
    private class SendPostRequest extends AsyncTask<String,Void,String>{

        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Please wait....");
            dialog.setMessage("Logging you");
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

            ConnectivityManager connMgr = (ConnectivityManager)(MainActivity.this).getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setReadTimeout(15000);
                    urlConnection.setConnectTimeout(15000);
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);

                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
                    outputStreamWriter.write(strings[1]);
                    outputStreamWriter.flush();
                    outputStreamWriter.close();

                    int response = urlConnection.getResponseCode();
                    if(response == HttpURLConnection.HTTP_OK){
                        BufferedReader reader =new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuffer buffer = new StringBuffer();
                        String line;
                        while ((line = reader.readLine()) != null){
                            buffer.append(line);
                        }
                        reader.close();
                        return buffer.toString();
                    }
                    else {
                        return "false: "+response;
                    }

                } catch (Exception e) {
                    Log.i(TAG,e.getLocalizedMessage(),e);
                }
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
            Log.i(TAG,s);
            if(s.contains("false") || s == null){
                errorOccured(s);
            }
            else{
                successfull(s);
            }
        }
    }
    public void errorOccured(String string){
        JSONObject jsonRootObject = null;
        try {

            jsonRootObject = new JSONObject(string);
            String success = jsonRootObject.getString("success");
            String message_code = jsonRootObject.getString("message_code");
            String message = jsonRootObject.getString("message");
            showAlertDialog(success +" "+message_code,message);

        }catch (Exception e){
            Log.i(TAG,"Json Exception: "+e);
        }
    }
    public void successfull(String string){
        JSONObject jsonRootObject = null;
        try {
            jsonRootObject = new JSONObject(string);

            String message = jsonRootObject.getString("message");
            JSONObject jsonObject = jsonRootObject.getJSONObject("data");

            Log.i(TAG,"array "+jsonObject.toString());

            String first_name = jsonObject.getString("firstname");
            String last_name = jsonObject.getString("lastname");


            Log.i(TAG,"First Name: "+first_name+"\nLast Name: "+last_name);
            showAlertDialog("First Name: "+first_name+"\nLast Name: "+last_name,message);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void showAlertDialog(String message,String success){
        AlertDialog.Builder  dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle(success);
        dialog.setMessage(message);
        dialog.show();
    }
}
