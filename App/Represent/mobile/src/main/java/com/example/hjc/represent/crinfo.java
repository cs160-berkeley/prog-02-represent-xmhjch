package com.example.hjc.represent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class crinfo extends Activity {

    public static String crname;
    public static List<String[]> crinfo = new ArrayList<String[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crinfo);

        Intent intent=getIntent();
        crname = intent.getStringExtra("crname");
        crinfo = findinfo(crname);

        TextView name = (TextView) findViewById(R.id.crinfo_name);
        TextView party = (TextView) findViewById(R.id.crinfo_party);
        TextView contact = (TextView) findViewById(R.id.crinfo_contact);
        TextView twt = (TextView) findViewById(R.id.crinfo_twt);
        ImageView pic = (ImageView) findViewById(R.id.infopic);

        int id = getResources().getIdentifier(crinfo.get(3)[0], "drawable", getPackageName());
        Drawable drawable = getDrawable(id);
        pic.setImageDrawable(drawable);

        name.append(crname);
        party.append(crinfo.get(0)[0]);
        for (String c:crinfo.get(1)){
            contact.append(c);
            contact.append("\n");
        }
        twt.append(crinfo.get(2)[0]);
    }

    public List<String[]> findinfo(String name){
        List<String[]> temp = new ArrayList<String[]>();
        String[] temp1 = {"Democrat"};
        String[] temp2 = {"Underwood@house.gov", "12345678"};
        String[] temp3 = {"Please don't text and drive near the #peachoid"};
        String[] temp4 = {"cr1"};
        temp.add(temp1);
        temp.add(temp2);
        temp.add(temp3);
        temp.add(temp4);

        List<String[]> temp0 = new ArrayList<String[]>();
        String[] temp01 = {"Republican"};
        String[] temp02 = {"Mendoza@house.gov", "562562254"};
        String[] temp03 = {"president 2016"};
        String[] temp04 = {"cr2"};
        temp0.add(temp01);
        temp0.add(temp02);
        temp0.add(temp03);
        temp0.add(temp04);

        List<String[]> tem1 = new ArrayList<String[]>();
        String[] temp11 = {"Democrat"};
        String[] temp12 = {"P.Russo@house.gov", "23245678"};
        String[] temp13 = {"----------"};
        String[] temp14 = {"cr3"};
        tem1.add(temp11);
        tem1.add(temp12);
        tem1.add(temp13);
        tem1.add(temp14);


        if (name.equals("Frank Underwood")){
            return temp;
        }else if(name.equals("Peter Russo")){
            return tem1;
        }else{
            return temp0;
        }
    }

    public void moreinfo(View view){
        Intent intent = new Intent(this, moreinfo.class);
        intent.putExtra("crname", crname);
        startActivity(intent);
    }
}
