package com.example.hjc.represent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.hjc.represent.getJson;


public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private String requesturl;
    private String lon;
    private String lat;
    public static String zipcode = "";

    private static final String TAG_RE = "results";
    private static final String TAG_CA = "address_components";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";

    JSONArray result = null;
    JSONArray content = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds


    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }
    private void handleNewLocation(Location location) {
        Log.d(MainActivity.class.getSimpleName(), location.toString());

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        lat = Double.toString(currentLatitude);
        lon = Double.toString(currentLongitude);
        requesturl = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lon+"&key=AIzaSyBD-wpB3zcQZvpnXu5z89I0rJPyc28jGIY";
        System.out.println(requesturl);
        getJson job = new getJson(this);
        job.execute(requesturl, "gps");



    }

    public static void json(String code){
        zipcode = code;

    }

    @Override
    public void onConnected(Bundle bundle) {
        int permissionCheck =  ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            while(location == null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
                location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            }
        }
        handleNewLocation(location);

    }

    @Override
    public void onConnectionSuspended(int cause){
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result){
        mGoogleApiClient.connect();
    }

//    public String getJSON(String url, int timeout) {
//        HttpURLConnection c = null;
//        try {
//            URL u = new URL(url);
//            c = (HttpURLConnection) u.openConnection();
//            c.setRequestMethod("GET");
//            c.setRequestProperty("Content-length", "0");
//            c.setUseCaches(false);
//            c.setAllowUserInteraction(false);
//            c.setConnectTimeout(timeout);
//            c.setReadTimeout(timeout);
//            c.connect();
//            int status = c.getResponseCode();
//
//            switch (status) {
//                case 200:
//                case 201:
//                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
//                    StringBuilder sb = new StringBuilder();
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        sb.append(line+"\n");
//                    }
//                    br.close();
//                    return sb.toString();
//            }
//
//        } catch (java.net.MalformedURLException ex) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            if (c != null) {
//                try {
//                    c.disconnect();
//                } catch (Exception ex) {
//                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        return null;
//    }

    public void enter(View view){
        Intent intent = new Intent(this, enterlocation.class);
        startActivity(intent);
    }

    public void get_location(View view){
        Intent intent = new Intent(this, confirmlocation.class);
        intent.putExtra("Zipcode", zipcode);
        System.out.println(zipcode);
        startActivity(intent);
    }


}
