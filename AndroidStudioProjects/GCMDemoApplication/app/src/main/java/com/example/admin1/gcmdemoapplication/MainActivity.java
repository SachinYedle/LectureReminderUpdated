package com.example.admin1.gcmdemoapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

//import com.google.firebase.crash.FirebaseCrash;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String token = FirebaseInstanceId.getInstance().getToken();
        //Log.i("TokenMain",""+token);
        //FirebaseCrash.report(new Exception("My first Android non-fatal error"));

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals("success")){
                    String token = intent.getStringExtra("token");
                    Toast.makeText(getApplicationContext(),""+token,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_LONG).show();
                }

            }
        };

        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if(ConnectionResult.SUCCESS != resultCode) {

            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                Toast.makeText(getApplicationContext(), "Google Play Service is not install/enabled in this device!", Toast.LENGTH_LONG).show();
                GooglePlayServicesUtil.showErrorNotification(resultCode, getApplicationContext());
            } else {
                Toast.makeText(getApplicationContext(), "This device does not support for Google Play Service!", Toast.LENGTH_LONG).show();
            }

        } else {
            Intent itent = new Intent(this, GCMRegistration.class);
            startService(itent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("success"));
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("error"));

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
