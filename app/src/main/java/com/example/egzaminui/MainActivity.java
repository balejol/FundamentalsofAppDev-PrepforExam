package com.example.egzaminui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // new for button
// new for menu :
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
//

public class MainActivity extends AppCompatActivity {

    //- - - MENU - - -
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.miItem1:
//                Intent ingredientsIntent = new Intent(MainActivity.this, SecondActivity.class);
//                startActivity(ingredientsIntent);
//                return true;
//
//            case R.id.miItem2:
//                Toast.makeText(MainActivity.this, "Second", Toast.LENGTH_SHORT).show();
//                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //- - - Random Number Generator - - -
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RNGActivity.class);
                startActivity(intent);
            }
        });

        //- - - Calendar - - -
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        //- - - Multimedia - - -
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MultimediaActivity.class);
                startActivity(intent);
            }
        });

        //- - - Drawing - - -
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DrawingActivity.class);
                startActivity(intent);
            }
        });

        //- - - Animation - - -
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
                startActivity(intent);
            }
        });

        //- - - Sample of Exam Task - - -
        Button samplBtn = findViewById(R.id.test_btn);
        samplBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        //- - - Flags (exam) - - -
        Button flagsBtn = findViewById(R.id.flags_btn);
        flagsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FlagsActivity.class);
                startActivity(intent);
            }
        });
    }
}