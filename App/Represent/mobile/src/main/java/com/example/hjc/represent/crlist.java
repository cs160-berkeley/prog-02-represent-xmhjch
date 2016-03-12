package com.example.hjc.represent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class crlist extends Activity {

    public static String zip;
    public static List<String[]> crl = new ArrayList<String[]>();
    public static String requesturl = "";
    int crcount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crlist);



    }

    @Override
    protected void onResume(){
        super.onResume();
        LinearLayout ll1 = (LinearLayout) findViewById(R.id.crlist_b1);
        LinearLayout ll2 = (LinearLayout) findViewById(R.id.crlist_b2);
        LinearLayout ll3 = (LinearLayout) findViewById(R.id.crlist_b3);

        ll1.setVisibility(View.INVISIBLE);
        ll2.setVisibility(View.INVISIBLE);
        ll3.setVisibility(View.INVISIBLE);

        requesturl = "https://congress.api.sunlightfoundation.com/legislators/locate?zip="+zip+"&apikey=884b6b7404754f428031c24f8f8a1a9f";
        getJson job = new getJson(this);
        job.execute(requesturl, "crlist");
        String[] t1 = {"Barbara Lee", "D", "qk_bigger", "L000551"};
        String[] t2 = {"Barbara Boxer", "D", "ez8_bigger", "B000711"};
        String[] t3 = {"Dianne Feinstein", "D", "pmp_bigger", "F000062"};
        crl.add(t1);
        crl.add(t2);
        crl.add(t3);


        if (crcount > 0){
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

        if (crcount > 1){
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
        if (crcount > 2){
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





    public static void setlist(ArrayList<String[]> list){
        crl = list;
    }



    public void cr1(View view){
        Intent intent = new Intent(this, crinfo.class);
        intent.putExtra("crname", crl.get(0)[0]);
        startActivity(intent);
    }

    public void cr2(View view){
        Intent intent = new Intent(this, crinfo.class);
        intent.putExtra("crname", crl.get(1)[0]);
        startActivity(intent);
    }

    public void cr3(View view){
        Intent intent = new Intent(this, crinfo.class);
        intent.putExtra("crname", crl.get(2)[0]);
        startActivity(intent);
    }
}
