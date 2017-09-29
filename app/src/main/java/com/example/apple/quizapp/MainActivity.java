package com.example.apple.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import static android.R.attr.name;
import static android.R.attr.start;

public class MainActivity extends AppCompatActivity {

    boolean start = false;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkCheckBoxStatus(View view) {
        CheckBox check = (CheckBox) findViewById(R.id.checkboxcheck);
        if (check.isChecked()) {
            check.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    public void StartQuiz(View view) {
        CheckBox check = (CheckBox) findViewById(R.id.checkboxcheck);
        start = check.isChecked();
        Log.i("Checkbox Status", String.valueOf(start));
        if (start) {
            TextView txtView = (TextView) findViewById(R.id.editText);
            name = txtView.getText().toString().toUpperCase();
            Log.i("MainActivity",  "Name : " + name);
            Intent i  = new Intent(MainActivity.this, quizGame.class);
            i.putExtra("name", name);
            startActivity(i);
        } else {
            check.setTextColor(Color.parseColor("#f44336"));
        }
    }

}
