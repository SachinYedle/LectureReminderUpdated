package com.example.admin1.servicedemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by admin1 on 23/9/16.
 */

public class ServiceClass extends Service {

    ImplementBinder binder = new ImplementBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(),"Startbind",Toast.LENGTH_LONG).show();
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(),"StartCommand",Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(),"Destroy",Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    public class ImplementBinder extends Binder{
        ServiceClass getService(){
            return  ServiceClass.this;
        }
    }
    public void setColor(RelativeLayout layout){

        layout.setBackgroundColor(Color.parseColor("#FFBB00"));
        return;
    }
}
