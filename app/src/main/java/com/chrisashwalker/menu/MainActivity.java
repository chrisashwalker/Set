package com.chrisashwalker.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playGame(View view) {
        setContentView(R.layout.activity_play);
    }

    public String chooseValue() {
        Random rnd = new Random();
        return Integer.toString(rnd.nextInt(9) + 1);
    }

    public void refreshCard1(View view) {
        TextView card1 = findViewById(R.id.card1);
        card1.setText(chooseValue());
    }

    public void refreshCard2(View view) {
        TextView card2 = findViewById(R.id.card2);
        card2.setText(chooseValue());
    }

    public void refreshCard3(View view) {
        TextView card3 = findViewById(R.id.card3);
        card3.setText(chooseValue());
    }

    public void refreshCard4(View view) {
        TextView card4 = findViewById(R.id.card4);
        card4.setText(chooseValue());
    }

    public void refreshCard5(View view) {
        TextView card5 = findViewById(R.id.card5);
        card5.setText(chooseValue());
    }

    public void refreshCard6(View view) {
        TextView card6 = findViewById(R.id.card6);
        card6.setText(chooseValue());
    }

    public void refreshCard7(View view) {
        TextView card7 = findViewById(R.id.card7);
        card7.setText(chooseValue());
    }

    public void refreshCard8(View view) {
        TextView card8 = findViewById(R.id.card8);
        card8.setText(chooseValue());
    }

    public void confirmCards(View view) {
        TextView confirm = findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);
        TextView ocard1 = findViewById(R.id.ocard1);
        ocard1.setVisibility(View.VISIBLE);
        TextView ocard2 = findViewById(R.id.ocard2);
        ocard2.setVisibility(View.VISIBLE);
        TextView ocard3 = findViewById(R.id.ocard3);
        ocard3.setVisibility(View.VISIBLE);
        TextView ocard4 = findViewById(R.id.ocard4);
        ocard4.setVisibility(View.VISIBLE);
        TextView ocard5 = findViewById(R.id.ocard5);
        ocard5.setVisibility(View.VISIBLE);
        TextView ocard6 = findViewById(R.id.ocard6);
        ocard6.setVisibility(View.VISIBLE);
        TextView ocard7 = findViewById(R.id.ocard7);
        ocard7.setVisibility(View.VISIBLE);
        TextView ocard8 = findViewById(R.id.ocard8);
        ocard8.setVisibility(View.VISIBLE);
    }
}