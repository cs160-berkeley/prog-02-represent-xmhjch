package com.example.hjc.represent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class crlist extends Activity{


    public static String zip;
    public static List<String[]> crl = new ArrayList<String[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crlist);

        LinearLayout ll1 = (LinearLayout) findViewById(R.id.crlist_b1);
        LinearLayout ll2 = (LinearLayout) findViewById(R.id.crlist_b2);
        LinearLayout ll3 = (LinearLayout) findViewById(R.id.crlist_b3);

        ll1.setVisibility(View.INVISIBLE);
        ll2.setVisibility(View.INVISIBLE);
        ll3.setVisibility(View.INVISIBLE);

        Intent intent=getIntent();
        zip = intent.getStringExtra("zipcode");
        crl = findlist(zip);


        int crlen = crl.size();

        if (crlen > 0){
            ImageView pic1 = (ImageView) findViewById(R.id.crpic1);
            TextView name1 = (TextView) findViewById(R.id.crname1);
            name1.setText(crl.get(0)[0]);
            if (crl.get(0)[1] == "D"){
                ll1.setBackgroundColor(0xFF333399);
            }else if(crl.get(0)[1] == "R"){
                ll1.setBackgroundColor(0xFFcc3333);
            }else{
                ll1.setBackgroundColor(0xFF4ed93f);
            }
            ll1.setVisibility(View.VISIBLE);
            int id = getResources().getIdentifier(crl.get(0)[2], "drawable", getPackageName());
            Drawable drawable = getDrawable(id);
            pic1.setImageDrawable(drawable);
        }

        if (crlen > 1){
            ImageView pic2 = (ImageView) findViewById(R.id.crpic2);
            TextView name2 = (TextView) findViewById(R.id.crname2);
            name2.setText(crl.get(1)[0]);
            if (crl.get(1)[1] == "D"){
                ll2.setBackgroundColor(0xFF333399);
            }else if(crl.get(1)[1] == "R"){
                ll2.setBackgroundColor(0xFFcc3333);
            }else{
                ll2.setBackgroundColor(0xFF4ed93f);
            }
            ll2.setVisibility(View.VISIBLE);
            int id = getResources().getIdentifier(crl.get(1)[2], "drawable", getPackageName());
            Drawable drawable = getDrawable(id);
            pic2.setImageDrawable(drawable);
        }

        if (crlen > 2){
            ImageView pic3 = (ImageView) findViewById(R.id.crpic3);
            TextView name3 = (TextView) findViewById(R.id.crname3);
            name3.setText(crl.get(2)[0]);
            if (crl.get(2)[1] == "D"){
                ll3.setBackgroundColor(0xFF333399);
            }else if(crl.get(2)[1] == "R"){
                ll3.setBackgroundColor(0xFFcc3333);
            }else{
                ll3.setBackgroundColor(0xFF4ed93f);
            }
            ll3.setVisibility(View.VISIBLE);
            int id = getResources().getIdentifier(crl.get(2)[2], "drawable", getPackageName());
            Drawable drawable = getDrawable(id);
            pic3.setImageDrawable(drawable);
        }

    }

    public List<String[]> findlist(String zipcode){
        List<String[]> temp = new ArrayList<String[]>();

        String[] t1 = {"Barbara Lee", "D", "qk_bigger", "L000551"};
        String[] t2 = {"Barbara Boxer", "D", "pmp_bigger", "B000711"};
        String[] t3 = {"Dianne Feinstein", "D", "ez8_bigger", "F000062"};
        temp.add(t1);
        temp.add(t2);
        temp.add(t3);




        return temp;
    }


    public void cr1(View view){
        Intent intent = new Intent(getBaseContext(), WatchToPhoneService.class);
        intent.putExtra("SEND", "crname");
        intent.putExtra("crname", "0");
        startService(intent);
    }

    public void cr2(View view){
        Intent intent = new Intent(getBaseContext(), WatchToPhoneService.class);
        intent.putExtra("SEND", "crname");
        intent.putExtra("crname", "1");
        startService(intent);
    }

    public void cr3(View view){
        Intent intent = new Intent(getBaseContext(), WatchToPhoneService.class);
        intent.putExtra("SEND", "crname");
        intent.putExtra("crname", "2");
        startService(intent);
    }

    public void vote(View view){
        Intent intent = new Intent(getBaseContext(), vote.class);
        intent.putExtra("zipcode", zip);
        startActivity(intent);
    }

//    @Override
//    public void onShake() {
//        Intent intent = new Intent(getBaseContext(), WatchToPhoneService.class);
//        intent.putExtra("SEND", "zipcode");
//        intent.putExtra("zipcode", 8854);
//        startService(intent);
//    }
}
