package com.example.admin1.servicedemo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout;
    ServiceClass serviceClass;
    boolean bound= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent= new Intent(getApplicationContext(), ServiceClass.class);
        bindService(intent, serviceConnection,BIND_AUTO_CREATE);
        //startService(intent);

        //LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter("send_message"));
        registerReceiver(receiver,new IntentFilter("com.example.admin1.servicedemo.Send"));
    }

    public void startService(View view){
        startService(new Intent(this, Message.class));
        if(bound){
            layout = (RelativeLayout)findViewById(R.id.activity_main);
            serviceClass.setColor(layout);
            //Toast.makeText(this,"Message:  "+,Toast.LENGTH_LONG).show();
        }
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("message");
            Toast.makeText(MainActivity.this,"Message: "+msg,Toast.LENGTH_LONG).show();
        }
    };

    ServiceConnection serviceConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ServiceClass.ImplementBinder binder = (ServiceClass.ImplementBinder)iBinder;
            serviceClass = binder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bound = false;
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(bound) {
            unbindService(serviceConnection);
            bound = false;
        }
    }



    public static class Message extends Service{

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            sendMessage();
            return super.onStartCommand(intent, flags, startId);
        }
        public void sendMessage(){
            Log.i("sendMessage","Broadcastng message");
            Intent intent=new Intent();
            intent.setAction("com.example.admin1.servicedemo.Send");
            intent.putExtra("message","This is broadcast receiver");
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            sendBroadcast(intent);
        }
    }
}

