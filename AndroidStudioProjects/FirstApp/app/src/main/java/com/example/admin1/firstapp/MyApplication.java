package com.example.admin1.firstapp;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.Toast;

/**
 * Created by admin1 on 25/8/16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(),"onCreate",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onLowMemory (){
        super.onLowMemory();
        Toast.makeText(getApplicationContext(),"OnLowMemory",Toast.LENGTH_SHORT).show();
    }
    public void onConfigurationChanged(Configuration configuration){
        super.onConfigurationChanged(configuration);
        Toast.makeText(getApplicationContext(),"OnConfiguraionChanged",Toast.LENGTH_SHORT).show();
    }
    public void onTrimMemory(int level){
        super.onTrimMemory(level);
        Toast.makeText(getApplicationContext(),"OnTrimMemory",Toast.LENGTH_SHORT).show();
    }
    public void onTerminate(){
        super.onTerminate();
        Toast.makeText(getApplicationContext(),"Onterminate",Toast.LENGTH_SHORT).show();
    }
    public void registerActivityLifecycleCallbacks (Application.ActivityLifecycleCallbacks callback){
        super.registerActivityLifecycleCallbacks(callback);
        Toast.makeText(getApplicationContext(),"registerActivityLifecycleCallbacks",Toast.LENGTH_SHORT).show();
    }
    public void registerComponentCallbacks(ComponentCallbacks componentCallbacks){
        super.registerComponentCallbacks(componentCallbacks);
        Toast.makeText(getApplicationContext(),"ComponentCallbacks",Toast.LENGTH_SHORT).show();
    }
    public void registerOnProvideAssistDataListener(Application.OnProvideAssistDataListener callbacks){
        super.registerOnProvideAssistDataListener(callbacks);
        Toast.makeText(getApplicationContext(),"provide assistent listener",Toast.LENGTH_SHORT).show();

    }
    public void unregisterActivityLifecycleCallbacks (Application.ActivityLifecycleCallbacks callback){
        super.unregisterActivityLifecycleCallbacks(callback);
        Toast.makeText(getApplicationContext(),"unregisterActivityLifecycleCallbacks",Toast.LENGTH_SHORT).show();
    }
    public void unregisterComponentCallbacks(ComponentCallbacks componentCallbacks){
        super.unregisterComponentCallbacks(componentCallbacks);
        Toast.makeText(getApplicationContext(),"unregister ComponentCallbacks",Toast.LENGTH_SHORT).show();
    }
    public void unregisterOnProvideAssistDataListener(Application.OnProvideAssistDataListener callbacks){
        super.unregisterOnProvideAssistDataListener(callbacks);
        Toast.makeText(getApplicationContext()," un register provide assistent listener",Toast.LENGTH_SHORT).show();

    }


}
