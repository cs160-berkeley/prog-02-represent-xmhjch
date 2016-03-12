package com.example.hjc.represent;



        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.io.UnsupportedEncodingException;
        import java.net.URI;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.drawable.Drawable;
        import android.net.Uri;
        import android.os.AsyncTask;
        import android.util.Log;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.view.View;
        import com.example.hjc.represent.crlist;

        import javax.net.ssl.HttpsURLConnection;


public class getJson extends AsyncTask<String, Void, String> {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    private Activity act;
    private String zipcode;
    private String location = "";
    String option = "";
    public static ArrayList<String[]> crl = new ArrayList<String[]>();
    int crcount;
    public static String crlistjson = "nah";




    // constructor
    public getJson(Activity act) {
        this.act = act;
    }

    @Override
    protected String doInBackground(String[] params) {
        // do above Server call here
        option = params[1];
        getJson jParser = new getJson(act);

//        if(option.equals("crlist")){
//            JSONObject json = jParser.getJSONFromUrl(params[0]);
//
//            JSONArray result;
//
//
//            try {
//                // Getting JSON Array
//                result = json.getJSONArray("results");
//                crcount = Integer.parseInt(json.getString("count"));
//
//                for (int i = 0; i<crcount; i++){
//                    String bioguide;
//                    String name;
//                    String party;
//                    String twitter;
//                    String pic;
//                    JSONObject r = result.getJSONObject(i);
//                    bioguide = r.getString("bioguide_id");
//                    name = r.getString("first_name") + r.getString("last_name");
//                    party = r.getString("party");
//                    twitter = r.getString("twitter_id");
//                    twitter job = new twitter(this.act);
//                    String[] tempin = {twitter, "pic"};
//                    pic = job.helprun(tempin);
//                    String[] temp = {name, party, pic, bioguide};
//                    crl.add(temp);
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//
//            return "ha";
//        }

        JSONObject json = jParser.getJSONFromUrl(params[0]);



        if (option.equals("gps")){
            JSONArray result;


            try {
                // Getting JSON Array
                result = json.getJSONArray("results");
                JSONObject r1 = result.getJSONObject(0);
                JSONArray r = r1.getJSONArray("address_components");

                JSONObject r2 = r.getJSONObject(6);

                zipcode = r2.getString("long_name");






            } catch (JSONException e) {
                e.printStackTrace();
            }


            return zipcode;

        }else if(option.equals("zip")){
            JSONArray result;
            String zip = "00000";

            try {
                // Getting JSON Array
                result = json.getJSONArray("results");
                JSONObject r1 = result.getJSONObject(0);
                JSONArray r = r1.getJSONArray("address_components");
                JSONObject r2 = r.getJSONObject(1);
                location = r2.getString("long_name");



            } catch (JSONException e) {
                e.printStackTrace();
            }



            return location;


        }

        return "nah";
    }

    @Override
    protected void onPostExecute(String message) {
        //process message
        super.onPostExecute(message);
        if(option.equals("gps")){
            MainActivity.json(zipcode);
        }else if(option.equals("zip")){
            confirmlocation.zipjson(location);
            TextView dis_location = (TextView) this.act.findViewById(R.id.display_location);
            dis_location.setText(location);

        }else if(option.equals("crlist")) {
        }

    }

    public JSONObject getJSONFromUrl(String url) {

        HttpsURLConnection connection = null;


        // Making HTTP request
        try {
            URL u = new URL(url);

            connection = (HttpsURLConnection) u.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");



            try {


                BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    result.append(line);
                }
                r.close();
                json = result.toString();
            }
            catch (IOException e) {}


//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(url);
//
//            HttpResponse httpResponse = httpClient.execute(httpPost);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    is, "iso-8859-1"), 8);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//            is.close();
//            json = sb.toString();
//        } catch (Exception e) {
//            Log.e("Buffer Error", "Error converting result " + e.toString());
//        }


        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data" + e.toString() + json);
        }

        // return JSON String
        return jObj;

    }





}