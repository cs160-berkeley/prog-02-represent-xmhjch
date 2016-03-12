package com.example.hjc.represent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class vote extends Activity {
    public static String zip;
    public static String[] location;
    public static String[] vote;

    private SensorManager mSensorManager;

    private ShakeEventManager mSensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        Intent intent = getIntent();
        zip = intent.getStringExtra("zipcode");
        location = countystate(zip);
        vote = vote(zip);
        TextView left = (TextView) findViewById(R.id.rpercent);
        TextView right = (TextView) findViewById(R.id.dpercent);
        TextView state = (TextView) findViewById(R.id.state);
        TextView county = (TextView) findViewById(R.id.county);
        left.append(vote[0]);
        right.append(vote[1]);
        state.append(location[1]);
        county.append(location[0]);
        Double l = Double.parseDouble(vote[0]);
        Double r = Double.parseDouble(vote[1]);
        l = 1 * 3.8;
        r = r * 3.8;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(380 - (int) Math.round(r), 60);
        LinearLayout.LayoutParams rp = new LinearLayout.LayoutParams((int) Math.round(r), 60);
        left.setLayoutParams(lp);
        right.setLayoutParams(rp);
        left.setBackgroundColor(Color.RED);
        right.setBackgroundColor(Color.BLUE);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventManager();

        mSensorListener.setOnShakeListener(new ShakeEventManager.OnShakeListener() {

            public void onShake() {
                ArrayList<Integer> choice = new ArrayList<Integer>();
                choice.add(94720);
                choice.add(8854);
                Random randomizer = new Random();
                Integer random = choice.get(randomizer.nextInt(choice.size()));
                Intent intent = new Intent(getBaseContext(), WatchToPhoneService.class);
                intent.putExtra("SEND", "zipcode");
                intent.putExtra("zipcode", "94720");
                startService(intent);
            }
        });

    }
    public String[] countystate(String zipcode){
        String[] temp = {"Alameda", "California"};

        return temp;
    }

    public String[] vote(String zipcode){
        String[] temp = {"18.7", "78.5"};

        return temp;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onStop();
    }

//    @Override
//    public void onShake() {
//        Intent intent = new Intent(getBaseContext(), WatchToPhoneService.class);
//        intent.putExtra("SEND", "zipcode");
//        intent.putExtra("zipcode", 8854);
//        startService(intent);
//    }
}
