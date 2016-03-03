package com.example.hjc.represent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enter(View view){
        Intent intent = new Intent(this, enterlocation.class);
        startActivity(intent);
    }

    public void get_location(View view){
        Intent intent = new Intent(this, confirmlocation.class);
        intent.putExtra("Zipcode", usergps());
        startActivity(intent);
    }

    public int usergps(){
        return 94720;
    }


}
