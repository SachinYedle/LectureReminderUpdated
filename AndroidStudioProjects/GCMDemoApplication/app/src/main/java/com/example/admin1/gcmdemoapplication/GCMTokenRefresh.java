package com.example.admin1.gcmdemoapplication;

/**
 * Created by admin1 on 26/9/16.
 */
import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

public class GCMTokenRefresh extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this,GCMRegistration.class);
        startService(intent);
    }
}
