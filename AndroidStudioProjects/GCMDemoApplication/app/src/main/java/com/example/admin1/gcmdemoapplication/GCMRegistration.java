package com.example.admin1.gcmdemoapplication;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.support.v4.content.LocalBroadcastManager;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by admin1 on 26/9/16.
 */

public class GCMRegistration extends IntentService{
    GCMRegistration(){
        super("GCMRegistration");
    }
    @Override
    protected void onHandleIntent(Intent intent) {

        Intent registration = null;
        String token = null;
        try{
            InstanceID instanceID = InstanceID.getInstance(this);
            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
            Log.i("Token: ",""+token);
            registration = new Intent("success");
            registration.putExtra("token",token);
        }catch (IOException e){
            System.out.println("Exception : "+e);
            registration = new Intent("error");
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(registration);
    }
}
