package com.example.admin1.gcmdemoapplication;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by admin1 on 23/9/16.
 */

public class GetRegistrationToken extends FirebaseInstanceIdService{
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.i("getToken","token");
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.i("Token: ",""+token);
    }
}
