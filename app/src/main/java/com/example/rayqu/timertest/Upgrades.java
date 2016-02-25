package com.example.rayqu.timertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by Owner on 2/17/2016.
 */
public class Upgrades extends AppCompatActivity implements View.OnClickListener {
    Button[] buttons = new Button[15];
    boolean infectedPrograms=false;
    boolean LAN =false;
    boolean businessRouters =false;
    boolean cellTowers=false;
    boolean satellites=false;
    boolean MAN =false;
    boolean mainframes=false;
    boolean cables=false;
    public static double networking =-.5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upgrades);
        buttons[0] = (Button) findViewById(R.id.popUpButton) ;
        buttons[0].setOnClickListener(this);
        buttons[1] = (Button) findViewById(R.id.programsButton) ;
        buttons[1].setOnClickListener(this);
        buttons[2] = (Button) findViewById(R.id.lanButton) ;
        buttons[2].setOnClickListener(this);
        buttons[3] = (Button) findViewById(R.id.usbButton) ;
        buttons[3].setOnClickListener(this);
        buttons[4] = (Button) findViewById(R.id.manButton) ;
        buttons[4].setOnClickListener(this);
        buttons[5] = (Button) findViewById(R.id.serversTCButton) ;
        buttons[5].setOnClickListener(this);
        buttons[6] = (Button) findViewById(R.id.satelliteButton) ;
        buttons[6].setOnClickListener(this);
        buttons[7] = (Button) findViewById(R.id.cloudButton) ;
        buttons[7].setOnClickListener(this);
        buttons[8] = (Button) findViewById(R.id.mainframeButton) ;
        buttons[8].setOnClickListener(this);
        buttons[9] = (Button) findViewById(R.id.serversBuisnessButton) ;
        buttons[9].setOnClickListener(this);
        buttons[10] = (Button) findViewById(R.id.atlanticCablesButton) ;
        buttons[10].setOnClickListener(this);
        buttons[11] = (Button) findViewById(R.id.homeRoutersButton) ;
        buttons[11].setOnClickListener(this);
        buttons[12] = (Button) findViewById(R.id.buisnessRoutersButton) ;
        buttons[12].setOnClickListener(this);
        buttons[13] = (Button) findViewById(R.id.cellTowersButton) ;
        buttons[13].setOnClickListener(this);
        buttons[14] = (Button) findViewById(R.id.internetProvidersButton) ;
        buttons[14].setOnClickListener(this);

        buttons[1].setVisibility(View.INVISIBLE);
        buttons[2].setVisibility(View.INVISIBLE);
        buttons[4].setVisibility(View.INVISIBLE);
        buttons[6].setVisibility(View.INVISIBLE);
        buttons[7].setVisibility(View.INVISIBLE);
        buttons[8].setVisibility(View.INVISIBLE);
        buttons[10].setVisibility(View.INVISIBLE);
        buttons[12].setVisibility(View.INVISIBLE);
        buttons[13].setVisibility(View.INVISIBLE);
    }
    public void onClick(View v) {
        if(v==buttons[0]){
            buttons[1].setVisibility(View.VISIBLE);
        }
        if(v==buttons[1]) {
            infectedPrograms=true;
            if(LAN)
                buttons[4].setVisibility(View.VISIBLE);
        }
        if(v==buttons[2]) {
            LAN=true;
            if(infectedPrograms)
                buttons[4].setVisibility(View.VISIBLE);
        }
        if(v==buttons[3]){
            buttons[2].setVisibility(View.VISIBLE);
        }
        if(v==buttons[4]){
            MAN=true;
            if(satellites && mainframes && cables){
                buttons[7].setVisibility(View.VISIBLE);
            }
        }
        if(v==buttons[5]){
            buttons[6].setVisibility(View.VISIBLE);
        }
        if(v==buttons[6]){
            satellites=true;
            if(MAN && mainframes && cables){
                buttons[7].setVisibility(View.VISIBLE);
            }
        }
        if(v==buttons[8]){
            mainframes=true;
            if(MAN && satellites && cables){
                buttons[7].setVisibility(View.VISIBLE);
            }
        }
        if(v==buttons[9]){
            buttons[8].setVisibility(View.VISIBLE);
        }
        if(v==buttons[10]){
            cables=true;
            if(MAN && satellites && mainframes){
                buttons[7].setVisibility(View.VISIBLE);
            }
        }
        if(v==buttons[11]){
            buttons[12].setVisibility(View.VISIBLE);
        }
        if(v==buttons[14]){
            buttons[13].setVisibility(View.VISIBLE);
        }
        if(v==buttons[12]) {
            businessRouters=true;
            if(cellTowers)
                buttons[10].setVisibility(View.VISIBLE);
        }
        if(v==buttons[13]) {
            cellTowers=true;
            if(businessRouters)
                buttons[10].setVisibility(View.VISIBLE);
        }

    }
    public static double getNetworking(){
        return networking;
    }
}
