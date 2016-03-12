package com.example.hjc.represent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class moreinfo extends Activity {

    public static String crname;
    public static List<String[]> crinfo = new ArrayList<String[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreinfo);

        Intent intent=getIntent();
        crname = intent.getStringExtra("crname");
        crinfo = findmoreinfo(crname);

        TextView position = (TextView) findViewById(R.id.crinfo_position);
        TextView past = (TextView) findViewById(R.id.crinfo_past);
        TextView bill = (TextView) findViewById(R.id.crinfo_bill);

        int poslen = crinfo.get(0).length;

        for(int i = 0;i<poslen; i++){
            position.append(crinfo.get(0)[i]);
            if(i%2 !=0){
                position.append("\n");
            }
        }

        poslen = crinfo.get(1).length;
        for(int i = 0;i<poslen; i++){
            past.append(crinfo.get(1)[i]);
            if(i%2 !=0){
                past.append("\n");
            }
        }

        poslen = crinfo.get(2).length;

        for (String c:crinfo.get(2)){
            bill.append(c);
            bill.append("\n");
        }

    }

    public List<String[]> findmoreinfo(String name){
        List<String[]> temp = new ArrayList<String[]>();
        String[] temp1 = {"Member of the U.S. House of Representatives"};
        String[] temp2 = {"Member of the California State Assembly", "December 7, 1992 – November 30, 1996"};
        String[] temp3 = {""};

        temp.add(temp1);
        temp.add(temp2);
        temp.add(temp3);


        List<String[]> temp0 = new ArrayList<String[]>();
        String[] temp01 = {"Chairwoman of the Senate Environment Committee", "January 3, 2007 – January 3, 2015"};
        String[] temp02 = {"Member of the U.S. House of Representatives", "January 3, 1983 – January 3, 1993"};
        String[] temp03 = {"HIV Organ Policy Equity Act (S. 330; 113th Congress)"};

        temp0.add(temp01);
        temp0.add(temp02);
        temp0.add(temp03);


        List<String[]> tem1 = new ArrayList<String[]>();
        String[] temp11 = {"Chairman of the Senate Intelligence Committee", "January 3, 2009 – January 3, 2015"};
        String[] temp12 = {"Chairman of the Senate Rules Committee", "January 3, 2007 – January 3, 2009", "38th Mayor of San Francisco", "December 4, 1978 – January 8, 1988"};
        String[] temp13 = {"Fisa Improvements Act bill 2013"};

        tem1.add(temp11);
        tem1.add(temp12);
        tem1.add(temp13);



        if (name.equals("Barbara Lee")){
            return temp;
        }else if(name.equals("Barbara Boxer")){
            return tem1;
        }else{
            return temp0;
        }
    }
}
