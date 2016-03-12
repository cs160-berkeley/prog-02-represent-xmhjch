package com.example.hjc.represent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class enterlocation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterlocation);
    }

    public void enter(View view){
        Intent intent = new Intent(this, confirmlocation.class);
        EditText zipcode = (EditText) findViewById(R.id.zip_enter);
        String zip = zipcode.getText().toString();
        int zip_code_num = Integer.parseInt(zip);

        intent.putExtra("Zipcode", zip);
        startActivity(intent);

    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
