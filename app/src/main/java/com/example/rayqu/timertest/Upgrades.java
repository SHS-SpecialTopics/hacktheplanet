package com.example.rayqu.timertest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * Created by Owner on 2/17/2016.
 */
public class Upgrades extends AppCompatActivity /*implements View.OnClickListener*/ {
    ViewPager pager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.networking_upgrades);

        pager= (ViewPager) findViewById(R.id.view_pager);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        // Fragment manager to add fragment in viewpager we will pass object of Fragment manager to adpater class.
        FragmentManager manager=getSupportFragmentManager();

        //object of PagerAdapter passing fragment manager object as a parameter of constructor of PagerAdapter class.
        PagerAdapter adapter=new PagerAdapter(manager);

        //set Adapter to view pager
        pager.setAdapter(adapter);

        //set tablayout with viewpager
        tabLayout.setupWithViewPager(pager);

        // adding functionality to tab and viewpager to manage each other when a page is changed or when a tab is selected
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Setting tabs from adpater
        tabLayout.setTabsFromPagerAdapter(adapter);

    }
}

   /* Button[] networkButtons = new Button[15];
    Button[] tabButtons = new Button[3];
    LinearLayout[] networkingLayouts = new LinearLayout[5];
    Button[] circularLethalityButtons = new Button[7];
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
        setContentView(R.layout.networking_upgrades);
        findViewById(R.id.popUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.programsButton).setVisibility(View.VISIBLE);
            }
        }); ;
        findViewById(R.id.programsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infectedPrograms = true;
                if (LAN) {
                    findViewById(R.id.manButton).setVisibility(View.VISIBLE);
                }
            }
        }); ;
        findViewById(R.id.lanButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LAN=true;
                if(infectedPrograms){
                    findViewById(R.id.manButton).setVisibility(View.VISIBLE);
                }
            }
        }); ;
        findViewById(R.id.usbButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.lanButton).setVisibility(View.VISIBLE);
            }
        }); ;
        findViewById(R.id.manButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MAN=true;
                if(satellites && mainframes && cables){
                    findViewById(R.id.cloudButton).setVisibility(View.VISIBLE);
                }
            }
        }); ;
        findViewById(R.id.serversTCButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.satelliteButton).setVisibility(View.VISIBLE);
            }
        }); ;
        findViewById(R.id.satelliteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                satellites=true;
                if(MAN && mainframes && cables){
                    findViewById(R.id.cloudButton).setVisibility(View.VISIBLE);
                }
            }
        }); ;
        findViewById(R.id.mainframeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainframes = true;
                if (MAN && satellites && cables) {
                    findViewById(R.id.cloudButton).setVisibility(View.VISIBLE);
                }
            }
        }); ;
        findViewById(R.id.serversBuisnessButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.mainframeButton).setVisibility(View.VISIBLE);
            }
        }); ;

        findViewById(R.id.atlanticCablesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cables = true;
                if (MAN && satellites && mainframes) {
                    findViewById(R.id.cloudButton).setVisibility(View.VISIBLE);
                }
            }
        }); ;
        findViewById(R.id.homeRoutersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.buisnessRoutersButton).setVisibility(View.VISIBLE);
            }
        }); ;
        findViewById(R.id.buisnessRoutersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businessRouters = true;
                if (cellTowers) {
                    findViewById(R.id.atlanticCablesButton).setVisibility(View.VISIBLE);
                }
            }
        }); ;
        findViewById(R.id.cellTowersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellTowers = true;
                if (businessRouters) {
                    findViewById(R.id.atlanticCablesButton).setVisibility(View.VISIBLE);
                }
            }
        }); ;
        findViewById(R.id.internetProvidersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.cellTowersButton).setVisibility(View.VISIBLE);
            }
        }); ;

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

        findViewById(R.id.programsButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.lanButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.manButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.satelliteButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.cloudButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.mainframeButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.atlanticCablesButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.buisnessRoutersButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.cellTowersButton).setVisibility(View.INVISIBLE);

        circularLethalityButtons[0] = (Button) findViewById(R.id.LaptopButton) ;
        circularLethalityButtons[0].setOnClickListener(this);
        circularLethalityButtons[1] = (Button) findViewById(R.id.DesktopButton) ;
        circularLethalityButtons[1].setOnClickListener(this);
        circularLethalityButtons[2] = (Button) findViewById(R.id.StationsButton) ;
        circularLethalityButtons[2].setOnClickListener(this);
        circularLethalityButtons[3] = (Button) findViewById(R.id.MobilePhonesButton) ;
        circularLethalityButtons[3].setOnClickListener(this);
        circularLethalityButtons[4] = (Button) findViewById(R.id.SmartPhonesButton) ;
        circularLethalityButtons[4].setOnClickListener(this);
        circularLethalityButtons[5] = (Button) findViewById(R.id.TablesButton) ;
        circularLethalityButtons[5].setOnClickListener(this);
        circularLethalityButtons[6] = (Button) findViewById(R.id.UCCButton) ;
        circularLethalityButtons[6].setOnClickListener(this);

    }
    public void onClick(View v) {
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
    }*/
/*}*/
