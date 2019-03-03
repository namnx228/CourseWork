package com.example.namxuan.myfirstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView viewCount;
    TextView txtCount;
    EditText textInput;
    Button button;
    private Integer numWord;
    private MainActivity mainActivity;

    private String label = "Count\t\t";

    private Integer countWord(String input){
        Integer output = input.split("\\s+").length;
        return output;
    }
    private void prepare(){
        viewCount = (TextView)findViewById(R.id.viewCount);
        txtCount = (TextView)findViewById(R.id.txtCount);
        textInput = (EditText)findViewById(R.id.textInput);
        textInput.setText("");

        button = (Button)findViewById(R.id.button);
        button.setText("ENTER");
        txtCount.setText("");
       // txtCount.setVisibility(View.INVISIBLE);
        viewCount.setText("Count");

        mainActivity = this;
    }
    private void process(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = textInput.getText().toString();
           //     Toast.makeText(MainActivity.this, "Hello world", Toast.LENGTH_LONG).show();
                numWord = countWord(input);
                //co numWord: build out string
                String output = "Word Count is: " + numWord.toString();
          //      Toast.makeText(mainActivity, output, Toast.LENGTH_LONG).show();
               //// set vao textview:
                ////set textview visible
                //txtCount.setVisibility(View.VISIBLE);
                txtCount.setText( output);
                //viewCount.setLabelFor(output);


            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepare();
        process();
    }
}
