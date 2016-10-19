package com.example.admin1.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by admin1 on 26/9/16.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Broadcast Message",""+intent.getStringExtra("message"));
        Toast.makeText(context,"message From second app: "+intent.getStringExtra("message"),Toast.LENGTH_LONG).show();
    }
}