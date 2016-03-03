package com.example.hjc.represent;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;


public class PhoneListenerService extends WearableListenerService {

    public static int crname;

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in PhoneListenerService, got: " + messageEvent.getPath());
        if( messageEvent.getPath().equalsIgnoreCase("crname")){
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            crname = Integer.parseInt(value);
            Intent intent = new Intent(this, crinfo.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("crname",crlist.crlist.get(crname)[0]);
            startActivity(intent);
        } else{
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            crname = Integer.parseInt(value);
            Intent intent = new Intent(this, crlist.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("zip",crname);
            startActivity(intent);
        }





    }
}
