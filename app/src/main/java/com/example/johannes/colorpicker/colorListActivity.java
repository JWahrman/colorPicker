package com.example.johannes.colorpicker;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class colorListActivity extends AppCompatActivity {

    ArrayList<Colour> colors;
    String message;
    int r;
    int g;
    int b;
    int[] rgb;
    Colour colour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_list);

        getColors();
        Button send = (Button) findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(v);
            }
        });

        List<Task> tasks = addTaskToList();
        RecyclerView tasklist = (RecyclerView) findViewById(R.id.tasklist);
        tasklist.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        tasklist.setLayoutManager(llm);
        RVAdapter adapter = new RVAdapter(tasks);
        tasklist.setAdapter(adapter);


        //showColors();

        FloatingActionButton backButton = (FloatingActionButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(colorListActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra("colorslist", colors);
                startActivity(intent);
            }
        });
    }

    private List<Task> addTaskToList() {
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < colors.size(); i++) {
            Task task = new Task();
            task.setOtsikko(colors.get(i).getHex());
            tasks.add(task);
        }

        return tasks;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void sendEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        String[] TO = {""};
        String[] CC = {""};

        EditText editText = (EditText) findViewById(R.id.editText);
        String emailAddress = editText.getText().toString();
        TO[0] = emailAddress;
        message = "Colors: ";
        for (int i = 0; i < colors.size(); i++) {
            colour = colors.get(i);
            rgb = colour.getRgb();
            r = rgb[0];
            g = rgb[1];
            b = rgb[2];
            message = message + System.lineSeparator() + (i + 1) + ". " + colour.getHex() + ", RGB(" + r + ", " + g + ", " + b + ")";
        }

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Colors");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color_list, menu);
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

    private void getColors() {
        Intent intent = getIntent();
        if (intent.getParcelableArrayListExtra("colorlist") != null) {
            colors = intent.getParcelableArrayListExtra("colorlist");
        }
    }
}
