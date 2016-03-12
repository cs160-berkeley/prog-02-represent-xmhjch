package com.example.hjc.represent;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.view.View;

import javax.net.ssl.HttpsURLConnection;


public class twitter extends AsyncTask<String, Void, String> {

    static InputStream is = null;
    static JSONArray jObj = null;
    static String json = "";
    private Activity act;
    private static String pic;
    private String location = "";
    String option = "";
    static String bearerToken = "";

    // constructor
    public twitter(Activity act) {
        this.act = act;
    }

    public String helprun(String[] params){
        return doInBackground(params);
    }

    @Override
    protected String doInBackground(String[] params) {
        // do above Server call here
       //getJson jParser = new getJson(act);
        JSONArray json = new JSONArray();
        option = params[1];
        //jParser.getJSONFromUrl(params[0]);
        try {
            bearerToken = requestBearerToken();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            json = fetchTimelineTweet(params[0], bearerToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (option.equals("pic")){
            JSONArray result;
            try {
                // Getting JSON Array
                JSONObject r1 = json.getJSONObject(0);
                JSONObject r = r1.getJSONObject("user");


                pic = r.getString("profile_image_url");
                String str = pic.substring(0, pic.length() - 11);
                String st2 = str + "bigger.jpeg";
                pic = st2;
                return pic;





            } catch (JSONException e) {
                e.printStackTrace();
            }
        }




        return "meh";
        }



    @Override
    protected void onPostExecute(String message) {
        //process message
        super.onPostExecute(message);

        if (option.equals("pic")){
            TextView dis_location = (TextView) this.act.findViewById(R.id.display_location);
            dis_location.setText(pic);
        }




    }


    private static String encodeKeys(String consumerKey, String consumerSecret) {
        try {
            String encodedConsumerKey = URLEncoder.encode(consumerKey, "UTF-8");
            String encodedConsumerSecret = URLEncoder.encode(consumerSecret, "UTF-8");
            String fullKey = encodedConsumerKey + ":" + encodedConsumerSecret;
            byte[] encodedBytes = Base64.encodeBase64(fullKey.getBytes());
            return new String(encodedBytes);
        }
        catch (UnsupportedEncodingException e) {
            return new String();
        }
    }

    private static String requestBearerToken() throws IOException, JSONException {
        HttpsURLConnection connection = null;
        String encodedCredentials = encodeKeys("KOA0LXa21QyMohLksH7jVZFMq","GwahvEWE1gnjRxOxctuR5dizRucWJvdzuqAofQrebhmBmYSr6o");

        HttpClient httpclient = new DefaultHttpClient();
//Append twitter api url here.
        String uriString = "https://api.twitter.com/oauth2/token";
        HttpPost httppost = new HttpPost(uriString);
        HttpParams httpParams = httppost.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
        HttpConnectionParams.setSoTimeout(httpParams, 15000);
//Append EncodedString to Authorization Header.
        httppost.setHeader("Authorization", "Basic " +encodedCredentials);
//Set Content type here.
        httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        HttpResponse response =null;

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("grant_type", "client_credentials"));
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
        response = httpclient.execute(httppost);
        String returnedJsonStr = EntityUtils.toString(response.getEntity());

        int statusCode = response.getStatusLine().getStatusCode();
        Log.v("response",returnedJsonStr);
        Log.v("response",Integer.toString(statusCode));
        JSONObject jsonObject = new JSONObject(returnedJsonStr);
//Receive Bearer token here.

        return jsonObject.getString("access_token");
    }

    // Writes a request to a connection
    private static boolean writeRequest(HttpsURLConnection connection, String textBody) {
        try {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            wr.write(textBody);
            wr.flush();
            wr.close();

            return true;
        }
        catch (IOException e) { return false; }
    }


    // Reads a response for a given connection and returns it as a string.
    private static String readResponse(HttpsURLConnection connection) {
        try {
            StringBuilder str = new StringBuilder();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while((line = br.readLine()) != null) {
                str.append(line + System.getProperty("line.separator"));
            }
            return str.toString();
        }
        catch (IOException e) { return new String(); }
    }

    // Fetches the first tweet from a given user's timeline
    private static JSONArray fetchTimelineTweet(String id, String bear) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
//Append twitter count and screen name with api URL
        HttpGet httpget = new HttpGet("https://api.twitter.com/1.1/statuses/user_timeline.json?count=1&screen_name=" + id);
//Append bearer token here.
        httpget.setHeader("Authorization", "Bearer " + bear);
        HttpResponse response;

        try {
            //Receive response here and make it .
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                is = entity.getContent();
            }
        } catch (Exception e) {
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }


        try {
            jObj = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;
    }





}