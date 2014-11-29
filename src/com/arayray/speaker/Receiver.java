package com.arayray.speaker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent arg1) {
    	// START SERVICE
        Intent serviceIntent = new Intent(context, SpeakerFix.class);
        //Toast.makeText(context, "Called Intercepted", Toast.LENGTH_SHORT).show();
        context.startService(serviceIntent);
    }
}
