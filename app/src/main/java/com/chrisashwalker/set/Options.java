package com.chrisashwalker.set;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Options extends AppCompatActivity {

    private static boolean timedGame;
    private static int humans;
    private static int robots;
    private static int cards;
    private static Intent gameOptionsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Intent gameModeIntent = getIntent();
        timedGame = gameModeIntent.getBooleanExtra(String.valueOf(R.string.timed_game),false);
        setDefaults();
        gameOptionsIntent = new Intent(this, Game.class);
    }

    private void setDefaults() {
        humans = 1;
        robots = 1;
        cards = 42;
    }

    public void startGame(View view) {
        gameOptionsIntent.putExtra(String.valueOf(R.string.timed_game), timedGame);
        gameOptionsIntent.putExtra(String.valueOf(R.string.no_of_human_players),humans);
        gameOptionsIntent.putExtra(String.valueOf(R.string.no_of_robot_players),robots);
        gameOptionsIntent.putExtra(String.valueOf(R.string.no_of_cards),cards);
        startActivity(gameOptionsIntent);
    }

    public void startGameWithOptionalChanges(View view) {
        EditText humanNumberEdit = findViewById(R.id.humanNumberEdit);
        EditText robotNumberEdit = findViewById(R.id.robotNumberEdit);
        EditText cardNumberEdit = findViewById(R.id.cardNumberEdit);
        try {
            if (humanNumberEdit.getText().length() > 0) {
                humans = Integer.parseInt(humanNumberEdit.getText().toString());
            }
            if (robotNumberEdit.getText().length() > 0) {
                robots = Integer.parseInt(robotNumberEdit.getText().toString());
            }
            if (cardNumberEdit.getText().length() > 0) {
                cards = Integer.parseInt(cardNumberEdit.getText().toString());
            }
        }
        catch (Exception e){
            setDefaults();
        }
        startGame(view);
    }

}
