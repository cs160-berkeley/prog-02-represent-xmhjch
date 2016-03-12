package com.example.hjc.represent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.hjc.represent.twitter;

import org.w3c.dom.Text;
import com.example.hjc.represent.getJson;

public class confirmlocation extends Activity {

    public static int zip;
    public static String zip_str;
    public static String location = "";
    String requesturl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmlocation);

        Intent intent=getIntent();
        zip_str = intent.getStringExtra("Zipcode");

        requesturl = "https://maps.googleapis.com/maps/api/geocode/json?address="+zip_str+"&key=AIzaSyBD-wpB3zcQZvpnXu5z89I0rJPyc28jGIY";

        getJson job = new getJson(this);
        job.execute(requesturl, "zip");

        TextView dis_location = (TextView) findViewById(R.id.display_location);
        dis_location.append("");


    }


    public static void zipjson(String l){

        location = l;


    }

    public void changeview(){
        TextView dis_location = (TextView) findViewById(R.id.display_location);
        dis_location.setText(location);
    }

    public void enter(View view){
        Intent intent = new Intent(this, crlist.class);
        intent.putExtra("zipcode", zip_str);
        startActivity(intent);
        Intent intent1 = new Intent(getBaseContext(), PhoneToWatchService.class);
        intent1.putExtra("zipcode", zip_str);
        startService(intent1);

    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
