package com.example.namxuan.thirdexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    TextView textViewURL;
    EditText txtUrl;
    Button sortAsc, sortDes, sortDate;
    GridView imageView;
    private String previousURL = "";
    private ArrayList<JSONObject> jsonArray;


    private void prepare(){
        Log.i("MCC", "Vao duoc noi day");
      textViewURL = (TextView) findViewById(R.id.textViewURL);
      txtUrl = (EditText)findViewById(R.id.txtUrl);
      sortAsc = (Button)findViewById(R.id.sortAsc);
      sortDes = (Button)findViewById(R.id.sortDes);
      sortDate = (Button)findViewById(R.id.sortDate);
      imageView = (GridView) findViewById(R.id.gridview);

      textViewURL.setText(R.string.url);
      sortAsc.setText(R.string.asc);
      sortDes.setText(R.string.des);
      sortDate.setText(R.string.date);

      //txtUrl.setText("https://api.myjson.com/bins/s7200");
       txtUrl.setText("");
    }

    private void process(){
         Log.i("MCC", "Vao duoc noi day: PROCESS");
        sortAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUrl.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "No URL to Parse", Toast.LENGTH_LONG).show();
                    return;
                }
                String url = txtUrl.getText().toString();

                Log.i("MCC", "Vao duoc noi day: " + url);
                ParseJSON parseJSON = new ParseJSON(url);
                //if (url.compareTo(previousURL) != 0)
                  jsonArray = parseJSON.giveYouJSONResopnse();
                  if (jsonArray == null) return;
                for(JSONObject json : jsonArray){
                    Log.d("MCC", "JSON:" +  json.toString());
                }
                ArrayList<JSONObject> urlList = parseJSON.press(jsonArray, 1);
                imageView.setAdapter(new ImageAdapter(MainActivity.this, urlList));
                //dut vao dau do
            }
        });
        sortDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUrl.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "No URL to Parse", Toast.LENGTH_LONG).show();
                    return;
                }
                String url = txtUrl.getText().toString();
                ParseJSON parseJSON = new ParseJSON(url);
                //if (url.compareTo(previousURL) != 0)
                    jsonArray = parseJSON.giveYouJSONResopnse();
                if (jsonArray == null) return;
                for(JSONObject json : jsonArray){
                    Log.d("MCC", "JSON:" +  json.toString());
                }
                ArrayList<JSONObject> urlList = parseJSON.press(jsonArray, 2);
                imageView.setAdapter(new ImageAdapter(MainActivity.this, urlList));
                //dut vao dau do
            }
        });
        sortDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUrl.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "No URL to Parse", Toast.LENGTH_LONG).show();
                    return;
                }
                String url = txtUrl.getText().toString();
                ParseJSON parseJSON = new ParseJSON(url);
                //if (url.compareTo(previousURL) != 0)
                    jsonArray = parseJSON.giveYouJSONResopnse();
                if (jsonArray == null) return;
                for(JSONObject json : jsonArray){
                    Log.d("MCC", "JSON:" +  json.toString());
                }
                ArrayList<JSONObject> urlList = parseJSON.press(jsonArray, 3);
                imageView.setAdapter(new ImageAdapter(MainActivity.this, urlList));
                //dut vao dau do
            }
        });
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MCC", "Cung co the vao day");
        prepare();
        process();
    }



}
