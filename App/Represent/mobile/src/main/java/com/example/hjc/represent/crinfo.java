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
        String[] temp2 = {"Rep.Lee@opencongress.org", "202-225-2661"};
        String[] temp3 = {"On #InternationalWomensDay & every day, Congress must work to advance #humanrights and justice. We deserve #equality."};
        String[] temp4 = {"qk_bigger"};
        temp.add(temp1);
        temp.add(temp2);
        temp.add(temp3);
        temp.add(temp4);

        List<String[]> temp0 = new ArrayList<String[]>();
        String[] temp01 = {"Democrat"};
        String[] temp02 = {"Sen.Boxer@opencongress.org", "202-224-3553"};
        String[] temp03 = {"Today I was pleased to introduce a bill to provide assistance to our crab fishing communities. "};
        String[] temp04 = {"pmp_bigger"};
        temp0.add(temp01);
        temp0.add(temp02);
        temp0.add(temp03);
        temp0.add(temp04);

        List<String[]> tem1 = new ArrayList<String[]>();
        String[] temp11 = {"Democrat"};
        String[] temp12 = {"Sen.Feinstein@opencongress.org", "202-224-3841"};
        String[] temp13 = {"I’m asking Senate Republicans to #DoYourJob and give a #SCOTUS nominee careful consideration. http://youtu.be/Gnj75bbDZz8 "};
        String[] temp14 = {"ez8_bigger"};
        tem1.add(temp11);
        tem1.add(temp12);
        tem1.add(temp13);
        tem1.add(temp14);


        if (name.equals("Barbara Lee")){
            return temp;
        }else if(name.equals("Barbara Boxer")){
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
