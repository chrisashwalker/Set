package com.chrisashwalker.set;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Options extends AppCompatActivity {

    static boolean timedGame;
    static int humans = 1;
    static int robots = 1;
    static int cards = 42;
    static Intent gameOptionsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Intent gameModeIntent = getIntent();
        timedGame = gameModeIntent.getBooleanExtra("timedGame",false);
        gameOptionsIntent = new Intent(this, Game.class);
    }

    public void startGame(View view) { //TODO: Needs error handling!
        EditText humanNumberEdit = findViewById(R.id.humanNumberEdit);
        EditText robotNumberEdit = findViewById(R.id.robotNumberEdit);
        EditText cardNumberEdit = findViewById(R.id.cardNumberEdit);
        if (humanNumberEdit.getText().length() > 0) {
            humans = Integer.parseInt(humanNumberEdit.getText().toString());
        }
        if (robotNumberEdit.getText().length() > 0) {
            robots = Integer.parseInt(robotNumberEdit.getText().toString());
        }
        if (cardNumberEdit.getText().length() > 0) {
            cards = Integer.parseInt(cardNumberEdit.getText().toString());
        }
        startDefaultGame(view);
    }

    public void startDefaultGame(View view) {
        gameOptionsIntent.putExtra("timedGame", timedGame);
        gameOptionsIntent.putExtra("humans",humans);
        gameOptionsIntent.putExtra("robots",robots);
        gameOptionsIntent.putExtra("cards",cards);
        startActivity(gameOptionsIntent);
    }

}
