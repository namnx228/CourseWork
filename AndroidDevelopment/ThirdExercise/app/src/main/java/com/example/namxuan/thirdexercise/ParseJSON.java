package com.example.namxuan.thirdexercise;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ParseJSON {

    private String url;
    private OkHttpClient client;
    private String jsonString;
    public ParseJSON(){
    }

    public ParseJSON(String url){
        //this();
        this.url = url;
        Log.i("MCC", "This is the Fucking URL: " + url);
        client = new OkHttpClient();

    }

    private String giveYouTheStringResponse(){
        //try {
        //    Request request = new Request.Builder()
        //            .url(url)
        //            .build();
        //    Response response = client.newCall(request).execute();
        //    return response.body().toString();

        //} catch (IOException e){
        //    Log.d("URL_error", "This error may stem from URL");
        //    e.printStackTrace();
        //}
        GetJsonArray task = new GetJsonArray();
        task.execute(url);
        //Log.d("Di:", jsonString);
        try {
            return task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private ArrayList<JSONObject> convertJsonArray(JSONArray arr){
        ArrayList<JSONObject> res = new ArrayList<>();
        try{
              for(int i = 0; i < arr.length(); i++) {
                  res.add(arr.getJSONObject(i));
              }
        } catch (JSONException e){
            Log.d("MCC", "This is error from JSON");
            e.printStackTrace();
        }
        return res;
    }
    public  ArrayList<JSONObject> giveYouJSONResopnse(){
        try {
            JSONArray tmp = new JSONArray();
            //change jsonarray to arrayList
            String tmpString = giveYouTheStringResponse();
            //String tmpString = "[{\"date\": \"2018-07-22T08:03:54.165Z\", \"photo\": \"https://static.pexels.com/photos/36764/marguerite-daisy-beautiful-beauty.jpg\", \"author\": \"Clyde Kulas\"}, {\"date\": \"2018-05-22T08:17:43.250Z\", \"photo\": \"https://static.pexels.com/photos/36764/marguerite-daisy-beautiful-beauty.jpg\", \"author\": \"Hana Polinsky\"}, {\"date\": \"2018-05-22T08:07:06.121Z\", \"photo\": \"https://static.pexels.com/photos/36764/marguerite-daisy-beautiful-beauty.jpg\", \"author\": \"Brenton Ciesielski\"}, {\"date\": \"2018-05-22T08:15:20.450Z\", \"photo\": \"https://static.pexels.com/photos/36764/marguerite-daisy-beautiful-beauty.jpg\", \"author\": \"Angela Snyder\"}, {\"date\": \"2018-05-22T08:09:43.300Z\", \"photo\": \"https://static.pexels.com/photos/36764/marguerite-daisy-beautiful-beauty.jpg\", \"author\": \"Maren Miles\"}, {\"date\": \"2018-05-27T08:09:05.979Z\", \"photo\": \"https://static.pexels.com/photos/36764/marguerite-daisy-beautiful-beauty.jpg\", \"author\": \"Britney Toothman\"}]";

            return convertJsonArray(new JSONArray(tmpString));
        } catch (Exception e){
            Log.d("MCC", "This error from JSON");
        }
        return null;
    }
    private class sortASC implements Comparator<JSONObject>{

        @Override
        public int compare(JSONObject o1, JSONObject o2) {
            try {
                return o1.getString("author").compareTo(o2.getString("author"));
            } catch(JSONException e){
                Log.d("MCC", "This error from JSON");
                e.printStackTrace();

            }
            return -1;
        }
    }
    private class sortDES implements Comparator<JSONObject>{

        @Override
        public int compare(JSONObject o1, JSONObject o2) {
            try {
                return o2.getString("author").compareTo(o1.getString("author"));
            } catch(JSONException e){
                Log.d("MCC", "This error from JSON");
                e.printStackTrace();

            }
            return -1;
        }
    }
    private class sortDATE implements Comparator<JSONObject>{

        @Override
        public int compare(JSONObject o1, JSONObject o2) {
            try {
               //Nen dung so sanh Date, nhung do format dang Lon > nho -> co the so sanh string
                return o2.getString("date").compareTo(o1.getString("date"));
            } catch(JSONException e){
                Log.d("MCC", "This error from JSON");
                e.printStackTrace();

            }
            return -1;
        }
    }

    public ArrayList<JSONObject> press(ArrayList<JSONObject> jsonArray, int button){
    //    Arrays.s
        switch (button) {
            case 1:
                Collections.sort(jsonArray, new sortASC());
                break;
            case 2:
                Collections.sort(jsonArray, new sortDES());
                break;
            case 3:
                Collections.sort(jsonArray, new sortDATE());
                break;
        }
//        ArrayList<String> listURL = new ArrayList<>();
//        try{
//          for(JSONObject o : jsonArray) {
//              listURL.add(o.getString("photo"));
//          }
//        }catch (JSONException e){
//            Log.d("JSON_error", "This error from JSON");
//            e.printStackTrace();
//        }
        return jsonArray;
    }
    class GetJsonArray extends AsyncTask<String, Void, String> {

        private Exception exception;

        protected String doInBackground(String... urls) {
            try {

                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = client.newCall(request).execute();
                String res = response.body().string();
                return res;

            } catch (Exception e){
                Log.i("MCC", "This error may stem from URL");

            }
                return null;
        }

        protected void onPostExecute(String res) {
            //jsonString = res;
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }
}
