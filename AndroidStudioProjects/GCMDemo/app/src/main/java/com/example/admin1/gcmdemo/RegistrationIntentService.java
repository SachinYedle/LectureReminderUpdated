package com.example.admin1.gcmdemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by admin1 on 21/9/16.
 */

public class RegistrationIntentService extends IntentService {
    private static final String TAG= "RegisterIntentService";
    public RegistrationIntentService(){
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent registrationComplete = null;
        FirebaseInstanceId instanceId = FirebaseInstanceId.getInstance();
        String senderId = getResources().getString(R.string.gcm_defaultSenderId);

        String token = instanceId.getToken();
        Log.d(TAG, "FCM Registration Token: " + token);
        registrationComplete = new Intent("Success");
        registrationComplete.putExtra("token", token);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}
