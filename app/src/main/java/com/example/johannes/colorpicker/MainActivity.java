package com.example.johannes.colorpicker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar r;
    SeekBar g;
    SeekBar b;
    TextView rText;
    TextView gText;
    TextView bText;
    int rValue;
    int gValue;
    int bValue;
    LinearLayout varilaatikko;
    FloatingActionButton emailButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_layout);

        //int color = getResources().getColor(R.color.random);

        initializeVariables();
        r.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rValue = progress;
                rText.setText("" + rValue);
                Log.i("JOHANNES", "R: " + gValue);
                setColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        g.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gValue = progress;
                gText.setText("" + gValue);
                Log.i("JOHANNES", "G: " + gValue);
                setColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                bValue = progress;
                bText.setText("" + bValue);
                Log.i("JOHANNES", "B: " + gValue);
                setColor();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setColor() {

        String hex = String.format("#%02x%02x%02x", rValue, gValue, bValue);
        Log.i("JOHANNES", hex);
        varilaatikko.setBackgroundColor(Color.parseColor(hex));
    }

    private void initializeVariables() {

        varilaatikko  = (LinearLayout) findViewById(R.id.varilaatikko);
        r = (SeekBar)findViewById(R.id.rSeekBar);
        g = (SeekBar)findViewById(R.id.gSeekBar);
        b = (SeekBar)findViewById(R.id.bSeekBar);
        rValue = 0;
        gValue = 0;
        bValue = 0;
        rText = (TextView)findViewById(R.id.rText);
        gText = (TextView)findViewById(R.id.gText);
        bText = (TextView)findViewById(R.id.bText);
        rText.setText(""+rValue);
        gText.setText(""+gValue);
        bText.setText(""+bValue);
        emailButton = (FloatingActionButton)findViewById(R.id.emailButton);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
