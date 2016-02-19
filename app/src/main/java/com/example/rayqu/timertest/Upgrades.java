package com.example.rayqu.timertest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by Owner on 2/17/2016.
 */
public class Upgrades extends AppCompatActivity implements View.OnClickListener {
    Button[] buttons = new Button[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upgrades);
        buttons[0] = (Button) findViewById(R.id.button1) ;
        buttons[0].setOnClickListener(this);
        buttons[1] = (Button) findViewById(R.id.button2) ;
        buttons[1].setOnClickListener(this);
        buttons[2] = (Button) findViewById(R.id.button3) ;
        buttons[2].setOnClickListener(this);
        buttons[3] = (Button) findViewById(R.id.button4) ;
        buttons[3].setOnClickListener(this);
        buttons[4] = (Button) findViewById(R.id.button5) ;
        buttons[4].setOnClickListener(this);
    }
    public void onClick(View v) {
        if (v == buttons[0]) {
            buttons[0].setText("Hello");
        }
        else if (v == buttons[1]) {
            buttons[1].setText("Hello");
        }
        else if (v == buttons[2]) {
            buttons[2].setText("Hello");
        }
        else if (v == buttons[3]) {
            buttons[3].setText("Hello");
        }
        else if (v == buttons[4]) {
            buttons[4].setText("Hello");
        }
    }
}
