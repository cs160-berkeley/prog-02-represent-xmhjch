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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class crlist extends Activity {

    public static int zip;
    public static List<String[]> crlist = new ArrayList<String[]>();

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
        zip = intent.getIntExtra("zipcode", 0);
        crlist = findlist(zip);
        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
        sendIntent.putExtra("zipcode", zip);
        startService(sendIntent);

        int crlen = crlist.size();

        if (crlen > 0){
            ImageView pic1 = (ImageView) findViewById(R.id.crpic1);
            TextView name1 = (TextView) findViewById(R.id.crname1);
            name1.append(crlist.get(0)[0]);
            if (crlist.get(0)[1] == "D"){
                ll1.setBackgroundColor(Color.BLUE);
            }else{
                ll1.setBackgroundColor(Color.RED);
            }
            ll1.setVisibility(View.VISIBLE);
            int id = getResources().getIdentifier(crlist.get(0)[2], "drawable", getPackageName());
            Drawable drawable = getDrawable(id);
            pic1.setImageDrawable(drawable);
            //pic1.setImageResource(R.drawable.loginbtn);
        }

        if (crlen > 1){
            ImageView pic2 = (ImageView) findViewById(R.id.crpic2);
            TextView name2 = (TextView) findViewById(R.id.crname2);
            name2.append(crlist.get(1)[0]);
            if (crlist.get(1)[1] == "D"){
                ll2.setBackgroundColor(Color.BLUE);
            }else{
                ll2.setBackgroundColor(Color.RED);
            }
            ll2.setVisibility(View.VISIBLE);
            int id = getResources().getIdentifier(crlist.get(1)[2], "drawable", getPackageName());
            Drawable drawable = getDrawable(id);
            pic2.setImageDrawable(drawable);
            //pic2.setImageResource(R.drawable.loginbtn);
        }

        if (crlen > 2){
            ImageView pic3 = (ImageView) findViewById(R.id.crpic3);
            TextView name3 = (TextView) findViewById(R.id.crname3);
            name3.append(crlist.get(2)[0]);
            if (crlist.get(2)[1] == "D"){
                ll3.setBackgroundColor(Color.BLUE);
            }else{
                ll3.setBackgroundColor(Color.RED);
            }
            ll3.setVisibility(View.VISIBLE);
            int id = getResources().getIdentifier(crlist.get(2)[2], "drawable", getPackageName());
            Drawable drawable = getDrawable(id);
            pic3.setImageDrawable(drawable);
            //pic3.setImageResource(R.drawable.loginbtn);
        }

    }

    public List<String[]> findlist(int zipcode){
        List<String[]> temp = new ArrayList<String[]>();
        List<String[]> temp0 = new ArrayList<String[]>();
        String[] temp1 = {"Frank Underwood", "D", "cr1"};
        String[] temp2 = {"Hector Mendoza", "R", "cr2"};
        String[] temp3 = {"Peter Russo", "D", "cr3"};
        temp.add(temp1);
        temp.add(temp2);
        temp0.add(temp3);
        if (zipcode!=94720){
            return temp0;
        }

        return temp;
    }


    public void cr1(View view){
        Intent intent = new Intent(this, crinfo.class);
        intent.putExtra("crname", crlist.get(0)[0]);
        startActivity(intent);
    }

    public void cr2(View view){
        Intent intent = new Intent(this, crinfo.class);
        intent.putExtra("crname", crlist.get(1)[0]);
        startActivity(intent);
    }

    public void cr3(View view){
        Intent intent = new Intent(this, crinfo.class);
        intent.putExtra("crname", crlist.get(2)[0]);
        startActivity(intent);
    }
}
