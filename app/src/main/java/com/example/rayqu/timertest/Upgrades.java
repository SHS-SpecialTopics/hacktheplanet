package com.example.rayqu.timertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * Created by Owner on 2/17/2016.
 */
public class Upgrades extends AppCompatActivity implements View.OnClickListener {
    Button[] networkButtons = new Button[15];
    Button[] tabButtons = new Button[3];
    LinearLayout[] networkingLayouts = new LinearLayout[5];
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
        networkButtons[0] = (Button) findViewById(R.id.popUpButton) ;
        networkButtons[0].setOnClickListener(this);
        networkButtons[1] = (Button) findViewById(R.id.programsButton) ;
        networkButtons[1].setOnClickListener(this);
        networkButtons[2] = (Button) findViewById(R.id.lanButton) ;
        networkButtons[2].setOnClickListener(this);
        networkButtons[3] = (Button) findViewById(R.id.usbButton) ;
        networkButtons[3].setOnClickListener(this);
        networkButtons[4] = (Button) findViewById(R.id.manButton) ;
        networkButtons[4].setOnClickListener(this);
        networkButtons[5] = (Button) findViewById(R.id.serversTCButton) ;
        networkButtons[5].setOnClickListener(this);
        networkButtons[6] = (Button) findViewById(R.id.satelliteButton) ;
        networkButtons[6].setOnClickListener(this);
        networkButtons[7] = (Button) findViewById(R.id.cloudButton) ;
        networkButtons[7].setOnClickListener(this);
        networkButtons[8] = (Button) findViewById(R.id.mainframeButton) ;
        networkButtons[8].setOnClickListener(this);
        networkButtons[9] = (Button) findViewById(R.id.serversBuisnessButton) ;
        networkButtons[9].setOnClickListener(this);
        networkButtons[10] = (Button) findViewById(R.id.atlanticCablesButton) ;
        networkButtons[10].setOnClickListener(this);
        networkButtons[11] = (Button) findViewById(R.id.homeRoutersButton) ;
        networkButtons[11].setOnClickListener(this);
        networkButtons[12] = (Button) findViewById(R.id.buisnessRoutersButton) ;
        networkButtons[12].setOnClickListener(this);
        networkButtons[13] = (Button) findViewById(R.id.cellTowersButton) ;
        networkButtons[13].setOnClickListener(this);
        networkButtons[14] = (Button) findViewById(R.id.internetProvidersButton) ;
        networkButtons[14].setOnClickListener(this);

        tabButtons[0] = (Button) findViewById(R.id.networkingButton) ;
        tabButtons[0].setOnClickListener(this);
        tabButtons[1]= (Button) findViewById(R.id.lethalityButton) ;
        tabButtons[1].setOnClickListener(this);
        tabButtons[2]=(Button) findViewById(R.id.securityButton) ;
        tabButtons[2].setOnClickListener(this);

        networkingLayouts[0]= (LinearLayout) findViewById(R.id.firstRowLinearLayout) ;
        networkingLayouts[1]= (LinearLayout) findViewById(R.id.secondRowlinearLayout) ;
        networkingLayouts[2]= (LinearLayout) findViewById(R.id.thirdRowlinearLayout) ;
        networkingLayouts[3]= (LinearLayout) findViewById(R.id.fourthRowLinearLayout) ;
        networkingLayouts[4]= (LinearLayout) findViewById(R.id.fifthRowLinearLayout) ;

        networkButtons[1].setVisibility(View.INVISIBLE);
        networkButtons[2].setVisibility(View.INVISIBLE);
        networkButtons[4].setVisibility(View.INVISIBLE);
        networkButtons[6].setVisibility(View.INVISIBLE);
        networkButtons[7].setVisibility(View.INVISIBLE);
        networkButtons[8].setVisibility(View.INVISIBLE);
        networkButtons[10].setVisibility(View.INVISIBLE);
        networkButtons[12].setVisibility(View.INVISIBLE);
        networkButtons[13].setVisibility(View.INVISIBLE);
    }
    public void onClick(View v) {
        if(v==networkButtons[0]){
            networkButtons[1].setVisibility(View.VISIBLE);
        }
        if(v==networkButtons[1]) {
            infectedPrograms=true;
            if(LAN)
                networkButtons[4].setVisibility(View.VISIBLE);
        }
        if(v==networkButtons[2]) {
            LAN=true;
            if(infectedPrograms)
                networkButtons[4].setVisibility(View.VISIBLE);
        }
        if(v==networkButtons[3]){
            networkButtons[2].setVisibility(View.VISIBLE);
        }
        if(v==networkButtons[4]){
            MAN=true;
            if(satellites && mainframes && cables){
                networkButtons[7].setVisibility(View.VISIBLE);
            }
        }
        if(v==networkButtons[5]){
            networkButtons[6].setVisibility(View.VISIBLE);
        }
        if(v==networkButtons[6]){
            satellites=true;
            if(MAN && mainframes && cables){
                networkButtons[7].setVisibility(View.VISIBLE);
            }
        }
        if(v==networkButtons[8]){
            mainframes=true;
            if(MAN && satellites && cables){
                networkButtons[7].setVisibility(View.VISIBLE);
            }
        }
        if(v==networkButtons[9]){
            networkButtons[8].setVisibility(View.VISIBLE);
        }
        if(v==networkButtons[10]){
            cables=true;
            if(MAN && satellites && mainframes){
                networkButtons[7].setVisibility(View.VISIBLE);
            }
        }
        if(v==networkButtons[11]){
            networkButtons[12].setVisibility(View.VISIBLE);
        }
        if(v==networkButtons[14]){
            networkButtons[13].setVisibility(View.VISIBLE);
        }
        if(v==networkButtons[12]) {
            businessRouters=true;
            if(cellTowers)
                networkButtons[10].setVisibility(View.VISIBLE);
        }
        if(v==networkButtons[13]) {
            cellTowers=true;
            if(businessRouters)
                networkButtons[10].setVisibility(View.VISIBLE);
        }
        if(v==tabButtons[1]){
            for(LinearLayout l: networkingLayouts){
                l.setVisibility(View.GONE);
            }
        }
        if(v==tabButtons[0]){
            for(LinearLayout l: networkingLayouts){
                l.setVisibility(View.VISIBLE);
            }
        }
    }
    public static double getNetworking(){
        return networking;
    }
}
