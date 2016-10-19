package com.example.admin1.servicedemo;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by admin1 on 23/9/16.
 */

public class BroadcastDemo extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle =intent.getExtras();
        if(bundle != null){
            String state = bundle.getString(BluetoothDevice.ACTION_FOUND);
                Log.i("Bluetooth:",""+state);
        }
    }
}