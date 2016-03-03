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
        String[] temp1 = {"United States House of Representatives", "(2011-2013)", "House Majority Whip", "(2011-2013)"};
        String[] temp2 = {"United States House of Representatives", "(1995-2011)"};
        String[] temp3 = {"Education Bill 2013"};
        temp.add(temp1);
        temp.add(temp2);
        temp.add(temp3);
        return temp;
    }
}
