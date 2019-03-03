package com.example.namxuan.secondexercise;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textPortrait;
    TextView textLandscape;
    Switch scrChange;
    int currentOrient;

    private void prepare(){
        textPortrait = (TextView)findViewById(R.id.textPortrait);
        textLandscape = (TextView)findViewById(R.id.textLandscape);
        scrChange = (Switch) findViewById(R.id.scrChange);

        textLandscape.setText(R.string.landscape);
        textPortrait.setText(R.string.portrait);
        scrChange.setText(R.string.Switch);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        checkOrientation();
    }

    private void process(){
        scrChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOrientation();
            }
        });
    }

    private void checkOrientation(){
        currentOrient = getRequestedOrientation();
        if (currentOrient == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            switchLandscape();
        else if (currentOrient == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            switchPortrait();

    }
    private void switchPortrait(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        scrChange.setChecked(false);
        textPortrait.setVisibility(View.VISIBLE);
        textLandscape.setVisibility(View.INVISIBLE);
    }
    private  void switchLandscape(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        scrChange.setChecked(true);
        textPortrait.setVisibility(View.INVISIBLE);
        textLandscape.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepare();
        process();
    }
}
