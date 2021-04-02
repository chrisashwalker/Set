package com.chrisashwalker.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static boolean timed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchGame(View view) {
        Intent intent = new Intent(this, Options.class);
        intent.putExtra("Timed", timed);
        startActivity(intent);
    }

    public void launchTimedGame(View view) {
        timed = true;
        launchGame(view);
    }

}
