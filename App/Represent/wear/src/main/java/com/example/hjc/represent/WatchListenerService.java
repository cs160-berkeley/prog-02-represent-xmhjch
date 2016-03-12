package com.example.hjc.represent;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;


public class WatchListenerService extends WearableListenerService {


    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());


        String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);

        Intent intent = new Intent(this, crlist.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("zipcode", value);
        Log.d("T", "about to start watch MainActivity with CAT_NAME: Fred");
        startActivity(intent);


    }
}