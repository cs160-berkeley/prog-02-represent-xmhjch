package com.example.hjc.represent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class confirmlocation extends Activity {

    public static int zip;
    public static String zip_str;
    public static String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmlocation);

        Intent intent=getIntent();
        zip = intent.getIntExtra("Zipcode", 0);

        location = location(zip);
        TextView dis_location = (TextView) findViewById(R.id.display_location);
        dis_location.append(location);


    }
    public String location(int zipcode){
        if (zipcode!=94720){
            return "Piscataway";
        }
        return "Berkeley";
    }

    public void enter(View view){
        Intent intent = new Intent(this, crlist.class);
        intent.putExtra("zipcode", zip);
        startActivity(intent);
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
