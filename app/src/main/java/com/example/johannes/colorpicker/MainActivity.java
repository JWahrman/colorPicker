package com.example.johannes.colorpicker;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
    ArrayList<Colour> colors;
    String hex;
    TextView hexView;


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
                rText.setText("R: " + rValue);
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
                gText.setText("G: " + gValue);
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
                bText.setText("B: " + bValue);
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
                int[] rgb = new int[3];
                rgb[0] = rValue;
                rgb[1] = gValue;
                rgb[2] = bValue;
                Colour colour = new Colour(hex, rgb);
                boolean sameHex = checkHexes();

                if (sameHex == false && colors.size() < 15) {
                    colors.add(colour);
                    Toast.makeText(MainActivity.this, "New color added to list !", Toast.LENGTH_SHORT).show();
                }else if(sameHex == false && colors.size() == 15){
                    Toast.makeText(MainActivity.this, "Colorlist is full !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getColors(){
        Intent intent = getIntent();
        if(intent.getParcelableArrayListExtra("colorslist") != null){
            colors = intent.getParcelableArrayListExtra("colorslist");
        }else{
            colors = new ArrayList<>();
        }
    }

    private void moveToColorlist(){
        Intent intent = new Intent(this, colorListActivity.class);
        intent.putParcelableArrayListExtra("colorlist", colors);
        startActivity(intent);
    }

    private boolean checkHexes() {
       boolean sameHex = false;
        for(int i=0; i < colors.size(); i++){
            if(hex.equals(colors.get(i).getHex())){
                sameHex = true;
            }
        }
        return sameHex;
    }

    private void setColor() {

        hex = String.format("#%02X%02X%02X", rValue, gValue, bValue);
        Log.i("JOHANNES", hex);
        varilaatikko.setBackgroundColor(Color.parseColor(hex));
        hexView.setText("HEX: "+hex);
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
        rText.setText("R: "+rValue);
        gText.setText("G: "+gValue);
        bText.setText("B: "+bValue);
        emailButton = (FloatingActionButton)findViewById(R.id.emailButton);
        getColors();
        hexView = (TextView)findViewById(R.id.hexValue);
        setColor();
        hexView.setText("HEX: "+hex);
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
        }else if(id == R.id.action_email){
            moveToColorlist();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
