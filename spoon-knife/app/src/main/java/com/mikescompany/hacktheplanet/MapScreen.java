package com.mikescompany.hacktheplanet;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import 	android.view.animation.AnimationSet;
import android.graphics.Matrix;

import java.util.ArrayList;

public class MapScreen extends AppCompatActivity {
    boolean suspended = false;
    public int interval = 1000;
    boolean[][] upgradePoints;
    int[] upCount;
    int progressRegion;
    double worldPerc;
    boolean[] paused;

    public void atDawn() {
        final TextView time_text_view = (TextView) findViewById(R.id.time_text_view);

        pulsing= new boolean[10];
        upgrades=0;
        regionBacks = new ImageView[10];
        regionBacks[0] = (ImageView)findViewById(R.id.as_back);
        regionBacks[2] = (ImageView)findViewById(R.id.ca_back);
        regionBacks[6] = (ImageView)findViewById(R.id.na_back);
        regionBacks[9] = (ImageView)findViewById(R.id.sa_back);
        regionBacks[3] = (ImageView)findViewById(R.id.eu_back);
        regionBacks[4] = (ImageView)findViewById(R.id.me_back);
        regionBacks[7] = (ImageView)findViewById(R.id.rs_back);
        regionBacks[5] = (ImageView)findViewById(R.id.nf_back);
        regionBacks[8] = (ImageView)findViewById(R.id.sf_back);
        regionBacks[1] = (ImageView)findViewById(R.id.oc_back);

        paused=new boolean[10];
        for(int i = 0;i<10;i++) {
            paused[i] = false;
            pulsing[i]=false;
            cureCount[i]=new Counter();
        }

        infections = new Infection[10];
        for(int i = 0; i< infections.length;i++){
            infections[i] = new Infection();
            infections[i].setPerc(.0);
            infections[i].setInfected(false);
        }

        buildProgress();
        setProgressBar(10);
        /*cureCount = new Counter();
        cureCountCA= new Counter();
        cureCountSA = new Counter();
        cureCountEU = new Counter();
        cureCountRS = new Counter();
        cureCountME = new Counter();
        cureCountAS = new Counter();
        cureCountNF = new Counter();
        cureCountSF = new Counter();
        cureCountOC = new Counter();*/
        thePercentageCured = 0.0;
        upgradePoints=new boolean[10][4];
        upCount=new int[10];
        buildCureProg();
        progressRegion = 10;
        worldPerc = 0;
        maxes=new int[10];
        maxes[6] = 36;
        maxes[2] = 8;
        maxes[9] = 21;
        maxes[3]=19;
        maxes[7]=53;
        maxes[4]=17;
        maxes[0] = 36;
        maxes[5]= 29;
        maxes[8]=16;
        maxes[1]=18;
        rows= new int[10];
        rows[6]= 26;
        rows[2]=13;
        rows[9] = 34;
        rows[3]=19;
        rows[7]=19;
        rows[4]=15;
        rows[0]=34;
        rows[5]=19;
        rows[1]=18;
        rows[8]=20;

        buildNA();
        buildCA();
        buildSA();
        buildEU();
        buildRS();
        buildME();
        buildAS();
        buildNF();
        buildSF();
        buildOC();

        for(int i = 0; i< 26; i++)
            naSizes[i]=NA.get(i).size();

        for(int i = 0; i< CA.size(); i++)
            caSizes[i]=CA.get(i).size();

        for(int i = 0; i < SA.size(); i++)
            saSizes[i] = SA.get(i).size();

        for(int i = 0; i < EU.size(); i++)
            euSizes[i] = EU.get(i).size();

        for(int i = 0; i < RS.size(); i++)
            rsSizes[i] = RS.get(i).size();

        for(int i = 0; i < ME.size(); i++)
            meSizes[i] = ME.get(i).size();

        for(int i = 0; i < AS.size(); i++)
            asSizes[i] = AS.get(i).size();

        for(int i = 0; i < NF.size(); i++)
            nfSizes[i] = NF.get(i).size();

        for(int i = 0; i < SF.size(); i++)
            sfSizes[i] = SF.get(i).size();

        for(int i = 0; i < OC.size(); i++)
            ocSizes[i] = OC.get(i).size();

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        while (!suspended) {
                            Thread.sleep(interval);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    time.update();
                                    time_text_view.setText(time.toString());

                                    //Log.d("time", "Day:  "+ time.getTime()+" "+time.toString());


                                    buildNA();
                                    buildCA();
                                    buildSA();
                                    buildEU();
                                    buildRS();
                                    buildME();
                                    buildAS();
                                    buildNF();
                                    buildSF();
                                    buildOC();


                                    if(checkCure(time.getSec())) {
                                        //Log.d("thread", "still running");
                                        suspended=true;
                                        gameOver();
                                    }
                                    else {
                                        //Log.d("thread", "still infected");
                                        infection(checkRegion(time.getTime(),6),NA,6);
                                        infection(checkRegion(time.getTime(),2),CA,2);
                                        infection(checkRegion(time.getTime(),9),SA,9);
                                        infection(checkRegion(time.getTime(),3),EU,3);
                                        infection(checkRegion(time.getTime(),7),RS,7);
                                        infection(checkRegion(time.getTime(),4),ME,4);
                                        infection(checkRegion(time.getTime(),0),AS,0);
                                        infection(checkRegion(time.getTime(),5),NF,5);
                                        infection(checkRegion(time.getTime(),8),SF,8);
                                        infection(checkRegion(time.getTime(),1),OC,1);

                                        checkupgradePoints();
                                        worldPerc=calcWorldPerc();
                                        setCureProgressBar(thePercentageCured);
                                        setProgressBar(progressRegion);
                                    }


                                }
                            });
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    myDate time = new myDate(0);

    int[] rows,maxes;
    ArrayList<ArrayList<View>> NA = new ArrayList<ArrayList<View>>();
    ArrayList<ArrayList<View>> CA = new ArrayList<ArrayList<View>>();
    ArrayList<ArrayList<View>> SA = new ArrayList<ArrayList<View>>();
    ArrayList<ArrayList<View>> EU = new ArrayList<ArrayList<View>>();
    ArrayList<ArrayList<View>> RS = new ArrayList<ArrayList<View>>();
    ArrayList<ArrayList<View>> ME = new ArrayList<ArrayList<View>>();
    ArrayList<ArrayList<View>> AS = new ArrayList<ArrayList<View>>();
    ArrayList<ArrayList<View>> NF = new ArrayList<ArrayList<View>>();
    ArrayList<ArrayList<View>> SF = new ArrayList<ArrayList<View>>();
    ArrayList<ArrayList<View>> OC = new ArrayList<ArrayList<View>>();


    Infection[] infections =new Infection[10];

    public int[] naSizes = new int[26];
    public int[] caSizes = new int[26];
    public int[] saSizes = new int[34];
    public int[] euSizes = new int[19];
    public int[] rsSizes = new int[19];
    public int[] meSizes = new int[15];
    public int[] asSizes = new int[34];
    public int[] nfSizes = new int[19];
    public int[] sfSizes = new int[20];
    public int[] ocSizes = new int[18];

    public void buildNA(){

        for(int i = 0; i<26;i++) {
            NA.add(new ArrayList<View>());
        }

        NA.get(0).add((View) findViewById(R.id.na_dot0));
        NA.get(0).add((View) findViewById(R.id.na_dot1));
        NA.get(0).add((View) findViewById(R.id.na_dot2));
        NA.get(0).add((View) findViewById(R.id.na_dot3));
        NA.get(0).add((View) findViewById(R.id.na_dot4));
        NA.get(0).add((View) findViewById(R.id.na_dot5));
        NA.get(0).add((View) findViewById(R.id.na_dot6));
        NA.get(0).add((View) findViewById(R.id.na_dot7));
        NA.get(0).add((View) findViewById(R.id.na_dot8));
        NA.get(0).add((View) findViewById(R.id.na_dot9));
        NA.get(0).add((View) findViewById(R.id.na_dot10));
        NA.get(0).add((View) findViewById(R.id.na_dot11));

        NA.get(1).add((View) findViewById(R.id.na_dot12));
        NA.get(1).add((View) findViewById(R.id.na_dot13));
        NA.get(1).add((View) findViewById(R.id.na_dot14));
        NA.get(1).add((View) findViewById(R.id.na_dot15));
        NA.get(1).add((View) findViewById(R.id.na_dot16));
        NA.get(1).add((View) findViewById(R.id.na_dot17));
        NA.get(1).add((View) findViewById(R.id.na_dot18));
        NA.get(1).add((View) findViewById(R.id.na_dot19));
        NA.get(1).add((View) findViewById(R.id.na_dot20));
        NA.get(1).add((View) findViewById(R.id.na_dot21));
        NA.get(1).add((View) findViewById(R.id.na_dot22));
        NA.get(1).add((View) findViewById(R.id.na_dot23));
        NA.get(1).add((View) findViewById(R.id.na_dot24));
        NA.get(1).add((View) findViewById(R.id.na_dot25));
        NA.get(1).add((View) findViewById(R.id.na_dot26));
        NA.get(1).add((View) findViewById(R.id.na_dot27));
        NA.get(1).add((View) findViewById(R.id.na_dot28));
        NA.get(1).add((View) findViewById(R.id.na_dot29));

        NA.get(2).add((View) findViewById(R.id.na_dot30));
        NA.get(2).add((View) findViewById(R.id.na_dot31));
        NA.get(2).add((View) findViewById(R.id.na_dot32));
        NA.get(2).add((View) findViewById(R.id.na_dot33));
        NA.get(2).add((View) findViewById(R.id.na_dot34));
        NA.get(2).add((View) findViewById(R.id.na_dot35));
        NA.get(2).add((View) findViewById(R.id.na_dot36));
        NA.get(2).add((View) findViewById(R.id.na_dot37));
        NA.get(2).add((View) findViewById(R.id.na_dot38));
        NA.get(2).add((View) findViewById(R.id.na_dot39));
        NA.get(2).add((View) findViewById(R.id.na_dot40));
        NA.get(2).add((View) findViewById(R.id.na_dot41));
        NA.get(2).add((View) findViewById(R.id.na_dot42));
        NA.get(2).add((View) findViewById(R.id.na_dot43));
        NA.get(2).add((View) findViewById(R.id.na_dot44));
        NA.get(2).add((View) findViewById(R.id.na_dot45));
        NA.get(2).add((View) findViewById(R.id.na_dot46));

        NA.get(3).add((View) findViewById(R.id.na_dot47));
        NA.get(3).add((View) findViewById(R.id.na_dot48));
        NA.get(3).add((View) findViewById(R.id.na_dot49));
        NA.get(3).add((View) findViewById(R.id.na_dot50));
        NA.get(3).add((View) findViewById(R.id.na_dot51));
        NA.get(3).add((View) findViewById(R.id.na_dot52));
        NA.get(3).add((View) findViewById(R.id.na_dot53));
        NA.get(3).add((View) findViewById(R.id.na_dot54));
        NA.get(3).add((View) findViewById(R.id.na_dot55));
        NA.get(3).add((View) findViewById(R.id.na_dot56));
        NA.get(3).add((View) findViewById(R.id.na_dot57));

        NA.get(4).add((View) findViewById(R.id.na_dot58));
        NA.get(4).add((View) findViewById(R.id.na_dot59));
        NA.get(4).add((View) findViewById(R.id.na_dot60));
        NA.get(4).add((View) findViewById(R.id.na_dot61));
        NA.get(4).add((View) findViewById(R.id.na_dot62));
        NA.get(4).add((View) findViewById(R.id.na_dot63));
        NA.get(4).add((View) findViewById(R.id.na_dot64));
        NA.get(4).add((View) findViewById(R.id.na_dot65));
        NA.get(4).add((View) findViewById(R.id.na_dot66));
        NA.get(4).add((View) findViewById(R.id.na_dot67));
        NA.get(4).add((View) findViewById(R.id.na_dot68));
        NA.get(4).add((View) findViewById(R.id.na_dot69));
        NA.get(4).add((View) findViewById(R.id.na_dot70));
        NA.get(4).add((View) findViewById(R.id.na_dot71));
        NA.get(4).add((View) findViewById(R.id.na_dot72));

        NA.get(5).add((View) findViewById(R.id.na_dot73));
        NA.get(5).add((View) findViewById(R.id.na_dot74));
        NA.get(5).add((View) findViewById(R.id.na_dot75));
        NA.get(5).add((View) findViewById(R.id.na_dot76));
        NA.get(5).add((View) findViewById(R.id.na_dot77));
        NA.get(5).add((View) findViewById(R.id.na_dot78));
        NA.get(5).add((View) findViewById(R.id.na_dot79));
        NA.get(5).add((View) findViewById(R.id.na_dot80));
        NA.get(5).add((View) findViewById(R.id.na_dot81));
        NA.get(5).add((View) findViewById(R.id.na_dot82));
        NA.get(5).add((View) findViewById(R.id.na_dot83));
        NA.get(5).add((View) findViewById(R.id.na_dot84));
        NA.get(5).add((View) findViewById(R.id.na_dot85));
        NA.get(5).add((View) findViewById(R.id.na_dot86));
        NA.get(5).add((View) findViewById(R.id.na_dot87));
        NA.get(5).add((View) findViewById(R.id.na_dot88));
        NA.get(5).add((View) findViewById(R.id.na_dot89));
        NA.get(5).add((View) findViewById(R.id.na_dot90));
        NA.get(5).add((View) findViewById(R.id.na_dot91));
        NA.get(5).add((View) findViewById(R.id.na_dot92));
        NA.get(5).add((View) findViewById(R.id.na_dot93));
        NA.get(5).add((View) findViewById(R.id.na_dot94));
        NA.get(5).add((View) findViewById(R.id.na_dot95));
        NA.get(5).add((View) findViewById(R.id.na_dot96));
        NA.get(5).add((View) findViewById(R.id.na_dot97));
        NA.get(5).add((View) findViewById(R.id.na_dot98));
        NA.get(5).add((View) findViewById(R.id.na_dot99));
        NA.get(5).add((View) findViewById(R.id.na_dot100));
        NA.get(5).add((View) findViewById(R.id.na_dot101));
        NA.get(5).add((View) findViewById(R.id.na_dot102));

        NA.get(6).add((View) findViewById(R.id.na_dot103));
        NA.get(6).add((View) findViewById(R.id.na_dot104));
        NA.get(6).add((View) findViewById(R.id.na_dot105));
        NA.get(6).add((View) findViewById(R.id.na_dot106));
        NA.get(6).add((View) findViewById(R.id.na_dot107));
        NA.get(6).add((View) findViewById(R.id.na_dot108));
        NA.get(6).add((View) findViewById(R.id.na_dot109));
        NA.get(6).add((View) findViewById(R.id.na_dot110));
        NA.get(6).add((View) findViewById(R.id.na_dot111));
        NA.get(6).add((View) findViewById(R.id.na_dot112));
        NA.get(6).add((View) findViewById(R.id.na_dot113));
        NA.get(6).add((View) findViewById(R.id.na_dot114));
        NA.get(6).add((View) findViewById(R.id.na_dot115));
        NA.get(6).add((View) findViewById(R.id.na_dot116));
        NA.get(6).add((View) findViewById(R.id.na_dot117));
        NA.get(6).add((View) findViewById(R.id.na_dot118));
        NA.get(6).add((View) findViewById(R.id.na_dot119));
        NA.get(6).add((View) findViewById(R.id.na_dot120));
        NA.get(6).add((View) findViewById(R.id.na_dot121));
        NA.get(6).add((View) findViewById(R.id.na_dot122));
        NA.get(6).add((View) findViewById(R.id.na_dot123));
        NA.get(6).add((View) findViewById(R.id.na_dot124));
        NA.get(6).add((View) findViewById(R.id.na_dot125));
        NA.get(6).add((View) findViewById(R.id.na_dot126));
        NA.get(6).add((View) findViewById(R.id.na_dot127));
        NA.get(6).add((View) findViewById(R.id.na_dot128));
        NA.get(6).add((View) findViewById(R.id.na_dot129));
        NA.get(6).add((View) findViewById(R.id.na_dot130));
        NA.get(6).add((View) findViewById(R.id.na_dot131));
        NA.get(6).add((View) findViewById(R.id.na_dot132));
        NA.get(6).add((View) findViewById(R.id.na_dot133));
        NA.get(6).add((View) findViewById(R.id.na_dot134));
        NA.get(6).add((View) findViewById(R.id.na_dot135));
        NA.get(6).add((View) findViewById(R.id.na_dot136));
        NA.get(6).add((View) findViewById(R.id.na_dot137));
        NA.get(6).add((View) findViewById(R.id.na_dot138));

        NA.get(7).add((View) findViewById(R.id.na_dot139));
        NA.get(7).add((View) findViewById(R.id.na_dot140));
        NA.get(7).add((View) findViewById(R.id.na_dot141));
        NA.get(7).add((View) findViewById(R.id.na_dot142));
        NA.get(7).add((View) findViewById(R.id.na_dot143));
        NA.get(7).add((View) findViewById(R.id.na_dot144));
        NA.get(7).add((View) findViewById(R.id.na_dot145));
        NA.get(7).add((View) findViewById(R.id.na_dot146));
        NA.get(7).add((View) findViewById(R.id.na_dot147));
        NA.get(7).add((View) findViewById(R.id.na_dot148));
        NA.get(7).add((View) findViewById(R.id.na_dot149));
        NA.get(7).add((View) findViewById(R.id.na_dot150));
        NA.get(7).add((View) findViewById(R.id.na_dot151));
        NA.get(7).add((View) findViewById(R.id.na_dot152));
        NA.get(7).add((View) findViewById(R.id.na_dot153));
        NA.get(7).add((View) findViewById(R.id.na_dot154));
        NA.get(7).add((View) findViewById(R.id.na_dot155));
        NA.get(7).add((View) findViewById(R.id.na_dot156));
        NA.get(7).add((View) findViewById(R.id.na_dot157));
        NA.get(7).add((View) findViewById(R.id.na_dot158));
        NA.get(7).add((View) findViewById(R.id.na_dot159));
        NA.get(7).add((View) findViewById(R.id.na_dot160));
        NA.get(7).add((View) findViewById(R.id.na_dot161));
        NA.get(7).add((View) findViewById(R.id.na_dot162));
        NA.get(7).add((View) findViewById(R.id.na_dot163));
        NA.get(7).add((View) findViewById(R.id.na_dot164));
        NA.get(7).add((View) findViewById(R.id.na_dot165));
        NA.get(7).add((View) findViewById(R.id.na_dot166));
        NA.get(7).add((View) findViewById(R.id.na_dot167));
        NA.get(7).add((View) findViewById(R.id.na_dot168));
        NA.get(7).add((View) findViewById(R.id.na_dot169));
        NA.get(7).add((View) findViewById(R.id.na_dot170));
        NA.get(7).add((View) findViewById(R.id.na_dot171));
        NA.get(7).add((View) findViewById(R.id.na_dot172));

        NA.get(8).add((View) findViewById(R.id.na_dot173));
        NA.get(8).add((View) findViewById(R.id.na_dot174));
        NA.get(8).add((View) findViewById(R.id.na_dot175));
        NA.get(8).add((View) findViewById(R.id.na_dot176));
        NA.get(8).add((View) findViewById(R.id.na_dot177));
        NA.get(8).add((View) findViewById(R.id.na_dot178));
        NA.get(8).add((View) findViewById(R.id.na_dot179));
        NA.get(8).add((View) findViewById(R.id.na_dot180));
        NA.get(8).add((View) findViewById(R.id.na_dot181));
        NA.get(8).add((View) findViewById(R.id.na_dot182));
        NA.get(8).add((View) findViewById(R.id.na_dot183));
        NA.get(8).add((View) findViewById(R.id.na_dot184));
        NA.get(8).add((View) findViewById(R.id.na_dot185));
        NA.get(8).add((View) findViewById(R.id.na_dot186));
        NA.get(8).add((View) findViewById(R.id.na_dot187));
        NA.get(8).add((View) findViewById(R.id.na_dot188));
        NA.get(8).add((View) findViewById(R.id.na_dot189));
        NA.get(8).add((View) findViewById(R.id.na_dot190));
        NA.get(8).add((View) findViewById(R.id.na_dot191));
        NA.get(8).add((View) findViewById(R.id.na_dot192));
        NA.get(8).add((View) findViewById(R.id.na_dot193));
        NA.get(8).add((View) findViewById(R.id.na_dot194));
        NA.get(8).add((View) findViewById(R.id.na_dot195));
        NA.get(8).add((View) findViewById(R.id.na_dot196));
        NA.get(8).add((View) findViewById(R.id.na_dot197));
        NA.get(8).add((View) findViewById(R.id.na_dot198));
        NA.get(8).add((View) findViewById(R.id.na_dot199));
        NA.get(8).add((View) findViewById(R.id.na_dot200));
        NA.get(8).add((View) findViewById(R.id.na_dot201));
        NA.get(8).add((View) findViewById(R.id.na_dot202));

        NA.get(9).add((View) findViewById(R.id.na_dot203));
        NA.get(9).add((View) findViewById(R.id.na_dot204));
        NA.get(9).add((View) findViewById(R.id.na_dot205));
        NA.get(9).add((View) findViewById(R.id.na_dot206));
        NA.get(9).add((View) findViewById(R.id.na_dot207));
        NA.get(9).add((View) findViewById(R.id.na_dot208));
        NA.get(9).add((View) findViewById(R.id.na_dot209));
        NA.get(9).add((View) findViewById(R.id.na_dot210));
        NA.get(9).add((View) findViewById(R.id.na_dot211));
        NA.get(9).add((View) findViewById(R.id.na_dot212));
        NA.get(9).add((View) findViewById(R.id.na_dot213));
        NA.get(9).add((View) findViewById(R.id.na_dot214));
        NA.get(9).add((View) findViewById(R.id.na_dot215));
        NA.get(9).add((View) findViewById(R.id.na_dot216));
        NA.get(9).add((View) findViewById(R.id.na_dot217));
        NA.get(9).add((View) findViewById(R.id.na_dot218));
        NA.get(9).add((View) findViewById(R.id.na_dot219));
        NA.get(9).add((View) findViewById(R.id.na_dot220));
        NA.get(9).add((View) findViewById(R.id.na_dot221));
        NA.get(9).add((View) findViewById(R.id.na_dot222));
        NA.get(9).add((View) findViewById(R.id.na_dot223));
        NA.get(9).add((View) findViewById(R.id.na_dot224));
        NA.get(9).add((View) findViewById(R.id.na_dot225));

        NA.get(10).add((View) findViewById(R.id.na_dot226));
        NA.get(10).add((View) findViewById(R.id.na_dot227));
        NA.get(10).add((View) findViewById(R.id.na_dot228));
        NA.get(10).add((View) findViewById(R.id.na_dot229));
        NA.get(10).add((View) findViewById(R.id.na_dot230));
        NA.get(10).add((View) findViewById(R.id.na_dot231));
        NA.get(10).add((View) findViewById(R.id.na_dot232));
        NA.get(10).add((View) findViewById(R.id.na_dot233));
        NA.get(10).add((View) findViewById(R.id.na_dot234));
        NA.get(10).add((View) findViewById(R.id.na_dot235));
        NA.get(10).add((View) findViewById(R.id.na_dot236));
        NA.get(10).add((View) findViewById(R.id.na_dot237));
        NA.get(10).add((View) findViewById(R.id.na_dot238));
        NA.get(10).add((View) findViewById(R.id.na_dot239));
        NA.get(10).add((View) findViewById(R.id.na_dot240));
        NA.get(10).add((View) findViewById(R.id.na_dot244));
        NA.get(10).add((View) findViewById(R.id.na_dot245));
        NA.get(10).add((View) findViewById(R.id.na_dot246));
        NA.get(10).add((View) findViewById(R.id.na_dot247));
        NA.get(10).add((View) findViewById(R.id.na_dot248));

        NA.get(11).add((View) findViewById(R.id.na_dot249));
        NA.get(11).add((View) findViewById(R.id.na_dot250));
        NA.get(11).add((View) findViewById(R.id.na_dot251));
        NA.get(11).add((View) findViewById(R.id.na_dot252));
        NA.get(11).add((View) findViewById(R.id.na_dot253));
        NA.get(11).add((View) findViewById(R.id.na_dot254));
        NA.get(11).add((View) findViewById(R.id.na_dot255));
        NA.get(11).add((View) findViewById(R.id.na_dot256));
        NA.get(11).add((View) findViewById(R.id.na_dot257));
        NA.get(11).add((View) findViewById(R.id.na_dot258));
        NA.get(11).add((View) findViewById(R.id.na_dot259));
        NA.get(11).add((View) findViewById(R.id.na_dot260));
        NA.get(11).add((View) findViewById(R.id.na_dot261));
        NA.get(11).add((View) findViewById(R.id.na_dot262));
        NA.get(11).add((View) findViewById(R.id.na_dot266));
        NA.get(11).add((View) findViewById(R.id.na_dot267));
        NA.get(11).add((View) findViewById(R.id.na_dot268));
        NA.get(11).add((View) findViewById(R.id.na_dot269));
        NA.get(11).add((View) findViewById(R.id.na_dot270));


        NA.get(12).add((View) findViewById(R.id.na_dot271));
        NA.get(12).add((View) findViewById(R.id.na_dot272));
        NA.get(12).add((View) findViewById(R.id.na_dot273));
        NA.get(12).add((View) findViewById(R.id.na_dot274));
        NA.get(12).add((View) findViewById(R.id.na_dot275));
        NA.get(12).add((View) findViewById(R.id.na_dot276));
        NA.get(12).add((View) findViewById(R.id.na_dot277));
        NA.get(12).add((View) findViewById(R.id.na_dot278));
        NA.get(12).add((View) findViewById(R.id.na_dot279));
        NA.get(12).add((View) findViewById(R.id.na_dot280));
        NA.get(12).add((View) findViewById(R.id.na_dot281));
        NA.get(12).add((View) findViewById(R.id.na_dot282));
        NA.get(12).add((View) findViewById(R.id.na_dot283));
        NA.get(12).add((View) findViewById(R.id.na_dot284));
        NA.get(12).add((View) findViewById(R.id.na_dot285));
        NA.get(12).add((View) findViewById(R.id.na_dot286));
        NA.get(12).add((View) findViewById(R.id.na_dot289));
        NA.get(12).add((View) findViewById(R.id.na_dot290));
        NA.get(12).add((View) findViewById(R.id.na_dot291));
        NA.get(12).add((View) findViewById(R.id.na_dot292));
        NA.get(12).add((View) findViewById(R.id.na_dot293));
        NA.get(12).add((View) findViewById(R.id.na_dot294));
        NA.get(12).add((View) findViewById(R.id.na_dot295));
        NA.get(12).add((View) findViewById(R.id.na_dot296));
        NA.get(12).add((View) findViewById(R.id.na_dot297));

        NA.get(13).add((View) findViewById(R.id.na_dot298));
        NA.get(13).add((View) findViewById(R.id.na_dot299));
        NA.get(13).add((View) findViewById(R.id.na_dot300));
        NA.get(13).add((View) findViewById(R.id.na_dot301));
        NA.get(13).add((View) findViewById(R.id.na_dot302));
        NA.get(13).add((View) findViewById(R.id.na_dot303));
        NA.get(13).add((View) findViewById(R.id.na_dot304));
        NA.get(13).add((View) findViewById(R.id.na_dot305));
        NA.get(13).add((View) findViewById(R.id.na_dot306));
        NA.get(13).add((View) findViewById(R.id.na_dot307));
        NA.get(13).add((View) findViewById(R.id.na_dot308));
        NA.get(13).add((View) findViewById(R.id.na_dot309));
        NA.get(13).add((View) findViewById(R.id.na_dot310));
        NA.get(13).add((View) findViewById(R.id.na_dot311));
        NA.get(13).add((View) findViewById(R.id.na_dot312));
        NA.get(13).add((View) findViewById(R.id.na_dot313));
        NA.get(13).add((View) findViewById(R.id.na_dot314));
        NA.get(13).add((View) findViewById(R.id.na_dot315));
        NA.get(13).add((View) findViewById(R.id.na_dot316));
        NA.get(13).add((View) findViewById(R.id.na_dot317));
        NA.get(13).add((View) findViewById(R.id.na_dot318));
        NA.get(13).add((View) findViewById(R.id.na_dot319));
        NA.get(13).add((View) findViewById(R.id.na_dot320));
        NA.get(13).add((View) findViewById(R.id.na_dot321));
        NA.get(13).add((View) findViewById(R.id.na_dot322));
        NA.get(13).add((View) findViewById(R.id.na_dot323));
        NA.get(13).add((View) findViewById(R.id.na_dot324));

        NA.get(14).add((View) findViewById(R.id.na_dot325));
        NA.get(14).add((View) findViewById(R.id.na_dot326));
        NA.get(14).add((View) findViewById(R.id.na_dot327));
        NA.get(14).add((View) findViewById(R.id.na_dot328));
        NA.get(14).add((View) findViewById(R.id.na_dot329));
        NA.get(14).add((View) findViewById(R.id.na_dot330));
        NA.get(14).add((View) findViewById(R.id.na_dot331));
        NA.get(14).add((View) findViewById(R.id.na_dot332));
        NA.get(14).add((View) findViewById(R.id.na_dot333));
        NA.get(14).add((View) findViewById(R.id.na_dot334));
        NA.get(14).add((View) findViewById(R.id.na_dot335));
        NA.get(14).add((View) findViewById(R.id.na_dot336));
        NA.get(14).add((View) findViewById(R.id.na_dot337));
        NA.get(14).add((View) findViewById(R.id.na_dot338));
        NA.get(14).add((View) findViewById(R.id.na_dot339));
        NA.get(14).add((View) findViewById(R.id.na_dot340));
        NA.get(14).add((View) findViewById(R.id.na_dot341));
        NA.get(14).add((View) findViewById(R.id.na_dot342));
        NA.get(14).add((View) findViewById(R.id.na_dot343));
        NA.get(14).add((View) findViewById(R.id.na_dot344));
        NA.get(14).add((View) findViewById(R.id.na_dot345));
        NA.get(14).add((View) findViewById(R.id.na_dot346));
        NA.get(14).add((View) findViewById(R.id.na_dot347));
        NA.get(14).add((View) findViewById(R.id.na_dot348));
        NA.get(14).add((View) findViewById(R.id.na_dot351));
        NA.get(14).add((View) findViewById(R.id.na_dot352));

        NA.get(15).add((View) findViewById(R.id.na_dot353));
        NA.get(15).add((View) findViewById(R.id.na_dot354));
        NA.get(15).add((View) findViewById(R.id.na_dot355));
        NA.get(15).add((View) findViewById(R.id.na_dot356));
        NA.get(15).add((View) findViewById(R.id.na_dot357));
        NA.get(15).add((View) findViewById(R.id.na_dot358));
        NA.get(15).add((View) findViewById(R.id.na_dot359));
        NA.get(15).add((View) findViewById(R.id.na_dot360));
        NA.get(15).add((View) findViewById(R.id.na_dot361));
        NA.get(15).add((View) findViewById(R.id.na_dot362));
        NA.get(15).add((View) findViewById(R.id.na_dot363));
        NA.get(15).add((View) findViewById(R.id.na_dot364));
        NA.get(15).add((View) findViewById(R.id.na_dot365));
        NA.get(15).add((View) findViewById(R.id.na_dot366));
        NA.get(15).add((View) findViewById(R.id.na_dot367));
        NA.get(15).add((View) findViewById(R.id.na_dot368));
        NA.get(15).add((View) findViewById(R.id.na_dot369));
        NA.get(15).add((View) findViewById(R.id.na_dot370));
        NA.get(15).add((View) findViewById(R.id.na_dot371));
        NA.get(15).add((View) findViewById(R.id.na_dot372));
        NA.get(15).add((View) findViewById(R.id.na_dot373));
        NA.get(15).add((View) findViewById(R.id.na_dot374));
        NA.get(15).add((View) findViewById(R.id.na_dot375));
        NA.get(15).add((View) findViewById(R.id.na_dot376));
        NA.get(15).add((View) findViewById(R.id.na_dot377));
        NA.get(15).add((View) findViewById(R.id.na_dot378));
        NA.get(15).add((View) findViewById(R.id.na_dot379));
        NA.get(15).add((View) findViewById(R.id.na_dot380));
        NA.get(15).add((View) findViewById(R.id.na_dot381));

        NA.get(16).add((View) findViewById(R.id.na_dot382));
        NA.get(16).add((View) findViewById(R.id.na_dot383));
        NA.get(16).add((View) findViewById(R.id.na_dot384));
        NA.get(16).add((View) findViewById(R.id.na_dot385));
        NA.get(16).add((View) findViewById(R.id.na_dot386));
        NA.get(16).add((View) findViewById(R.id.na_dot387));
        NA.get(16).add((View) findViewById(R.id.na_dot388));
        NA.get(16).add((View) findViewById(R.id.na_dot389));
        NA.get(16).add((View) findViewById(R.id.na_dot390));
        NA.get(16).add((View) findViewById(R.id.na_dot391));
        NA.get(16).add((View) findViewById(R.id.na_dot392));
        NA.get(16).add((View) findViewById(R.id.na_dot393));
        NA.get(16).add((View) findViewById(R.id.na_dot394));
        NA.get(16).add((View) findViewById(R.id.na_dot395));
        NA.get(16).add((View) findViewById(R.id.na_dot396));
        NA.get(16).add((View) findViewById(R.id.na_dot397));
        NA.get(16).add((View) findViewById(R.id.na_dot398));
        NA.get(16).add((View) findViewById(R.id.na_dot399));
        NA.get(16).add((View) findViewById(R.id.na_dot400));
        NA.get(16).add((View) findViewById(R.id.na_dot401));
        NA.get(16).add((View) findViewById(R.id.na_dot402));
        NA.get(16).add((View) findViewById(R.id.na_dot403));
        NA.get(16).add((View) findViewById(R.id.na_dot404));

        NA.get(17).add((View) findViewById(R.id.na_dot405));
        NA.get(17).add((View) findViewById(R.id.na_dot406));
        NA.get(17).add((View) findViewById(R.id.na_dot407));
        NA.get(17).add((View) findViewById(R.id.na_dot408));
        NA.get(17).add((View) findViewById(R.id.na_dot409));
        NA.get(17).add((View) findViewById(R.id.na_dot410));
        NA.get(17).add((View) findViewById(R.id.na_dot411));
        NA.get(17).add((View) findViewById(R.id.na_dot412));
        NA.get(17).add((View) findViewById(R.id.na_dot413));
        NA.get(17).add((View) findViewById(R.id.na_dot414));
        NA.get(17).add((View) findViewById(R.id.na_dot415));
        NA.get(17).add((View) findViewById(R.id.na_dot416));
        NA.get(17).add((View) findViewById(R.id.na_dot417));
        NA.get(17).add((View) findViewById(R.id.na_dot418));
        NA.get(17).add((View) findViewById(R.id.na_dot419));
        NA.get(17).add((View) findViewById(R.id.na_dot420));
        NA.get(17).add((View) findViewById(R.id.na_dot421));
        NA.get(17).add((View) findViewById(R.id.na_dot422));
        NA.get(17).add((View) findViewById(R.id.na_dot423));
        NA.get(17).add((View) findViewById(R.id.na_dot424));
        NA.get(17).add((View) findViewById(R.id.na_dot425));
        NA.get(17).add((View) findViewById(R.id.na_dot426));
        NA.get(17).add((View) findViewById(R.id.na_dot427));

        NA.get(18).add((View) findViewById(R.id.na_dot428minus1));
        NA.get(18).add((View) findViewById(R.id.na_dot428));
        NA.get(18).add((View) findViewById(R.id.na_dot429));
        NA.get(18).add((View) findViewById(R.id.na_dot430));
        NA.get(18).add((View) findViewById(R.id.na_dot431));
        NA.get(18).add((View) findViewById(R.id.na_dot432));
        NA.get(18).add((View) findViewById(R.id.na_dot433));
        NA.get(18).add((View) findViewById(R.id.na_dot434));
        NA.get(18).add((View) findViewById(R.id.na_dot435));
        NA.get(18).add((View) findViewById(R.id.na_dot436));
        NA.get(18).add((View) findViewById(R.id.na_dot437));
        NA.get(18).add((View) findViewById(R.id.na_dot438));
        NA.get(18).add((View) findViewById(R.id.na_dot439));
        NA.get(18).add((View) findViewById(R.id.na_dot440));
        NA.get(18).add((View) findViewById(R.id.na_dot441));
        NA.get(18).add((View) findViewById(R.id.na_dot442));
        NA.get(18).add((View) findViewById(R.id.na_dot443));
        NA.get(18).add((View) findViewById(R.id.na_dot444));
        NA.get(18).add((View) findViewById(R.id.na_dot445));
        NA.get(18).add((View) findViewById(R.id.na_dot446));
        NA.get(18).add((View) findViewById(R.id.na_dot447));
        NA.get(18).add((View) findViewById(R.id.na_dot448));
        NA.get(18).add((View) findViewById(R.id.na_dot449));

        NA.get(19).add((View) findViewById(R.id.na_dot450minus1));
        NA.get(19).add((View) findViewById(R.id.na_dot450));
        NA.get(19).add((View) findViewById(R.id.na_dot451));
        NA.get(19).add((View) findViewById(R.id.na_dot452));
        NA.get(19).add((View) findViewById(R.id.na_dot453));
        NA.get(19).add((View) findViewById(R.id.na_dot454));
        NA.get(19).add((View) findViewById(R.id.na_dot455));
        NA.get(19).add((View) findViewById(R.id.na_dot456));
        NA.get(19).add((View) findViewById(R.id.na_dot457));
        NA.get(19).add((View) findViewById(R.id.na_dot458));
        NA.get(19).add((View) findViewById(R.id.na_dot459));
        NA.get(19).add((View) findViewById(R.id.na_dot460));
        NA.get(19).add((View) findViewById(R.id.na_dot461));
        NA.get(19).add((View) findViewById(R.id.na_dot462));
        NA.get(19).add((View) findViewById(R.id.na_dot463));
        NA.get(19).add((View) findViewById(R.id.na_dot464));
        NA.get(19).add((View) findViewById(R.id.na_dot465));
        NA.get(19).add((View) findViewById(R.id.na_dot466));
        NA.get(19).add((View) findViewById(R.id.na_dot467));
        NA.get(19).add((View) findViewById(R.id.na_dot468));
        NA.get(19).add((View) findViewById(R.id.na_dot469));
        NA.get(19).add((View) findViewById(R.id.na_dot470));
        NA.get(19).add((View) findViewById(R.id.na_dot471));

        NA.get(20).add((View) findViewById(R.id.na_dot472minus2));
        NA.get(20).add((View) findViewById(R.id.na_dot472minus1));
        NA.get(20).add((View) findViewById(R.id.na_dot472));
        NA.get(20).add((View) findViewById(R.id.na_dot473));
        NA.get(20).add((View) findViewById(R.id.na_dot474));
        NA.get(20).add((View) findViewById(R.id.na_dot475));
        NA.get(20).add((View) findViewById(R.id.na_dot476));
        NA.get(20).add((View) findViewById(R.id.na_dot477));
        NA.get(20).add((View) findViewById(R.id.na_dot478));
        NA.get(20).add((View) findViewById(R.id.na_dot479));
        NA.get(20).add((View) findViewById(R.id.na_dot480));
        NA.get(20).add((View) findViewById(R.id.na_dot481));
        NA.get(20).add((View) findViewById(R.id.na_dot482));
        NA.get(20).add((View) findViewById(R.id.na_dot483));
        NA.get(20).add((View) findViewById(R.id.na_dot484));
        NA.get(20).add((View) findViewById(R.id.na_dot485));
        NA.get(20).add((View) findViewById(R.id.na_dot486));
        NA.get(20).add((View) findViewById(R.id.na_dot487));
        NA.get(20).add((View) findViewById(R.id.na_dot488));
        NA.get(20).add((View) findViewById(R.id.na_dot489));
        NA.get(20).add((View) findViewById(R.id.na_dot490));

        NA.get(21).add((View) findViewById(R.id.na_dot491minus1));
        NA.get(21).add((View) findViewById(R.id.na_dot491));
        NA.get(21).add((View) findViewById(R.id.na_dot492));
        NA.get(21).add((View) findViewById(R.id.na_dot493));
        NA.get(21).add((View) findViewById(R.id.na_dot494));
        NA.get(21).add((View) findViewById(R.id.na_dot495));
        NA.get(21).add((View) findViewById(R.id.na_dot496));
        NA.get(21).add((View) findViewById(R.id.na_dot497));
        NA.get(21).add((View) findViewById(R.id.na_dot498));
        NA.get(21).add((View) findViewById(R.id.na_dot499));
        NA.get(21).add((View) findViewById(R.id.na_dot500));
        NA.get(21).add((View) findViewById(R.id.na_dot501));
        NA.get(21).add((View) findViewById(R.id.na_dot502));
        NA.get(21).add((View) findViewById(R.id.na_dot503));
        NA.get(21).add((View) findViewById(R.id.na_dot504));
        NA.get(21).add((View) findViewById(R.id.na_dot505));
        NA.get(21).add((View) findViewById(R.id.na_dot506));
        NA.get(21).add((View) findViewById(R.id.na_dot507));
        NA.get(21).add((View) findViewById(R.id.na_dot508));
        NA.get(21).add((View) findViewById(R.id.na_dot509));

        NA.get(22).add((View) findViewById(R.id.na_dot510));
        NA.get(22).add((View) findViewById(R.id.na_dot511));
        NA.get(22).add((View) findViewById(R.id.na_dot512));
        NA.get(22).add((View) findViewById(R.id.na_dot513));
        NA.get(22).add((View) findViewById(R.id.na_dot514));
        NA.get(22).add((View) findViewById(R.id.na_dot515));
        NA.get(22).add((View) findViewById(R.id.na_dot516));
        NA.get(22).add((View) findViewById(R.id.na_dot517));
        NA.get(22).add((View) findViewById(R.id.na_dot518));
        NA.get(22).add((View) findViewById(R.id.na_dot519));
        NA.get(22).add((View) findViewById(R.id.na_dot520));
        NA.get(22).add((View) findViewById(R.id.na_dot521));
        NA.get(22).add((View) findViewById(R.id.na_dot522));
        NA.get(22).add((View) findViewById(R.id.na_dot523));
        NA.get(22).add((View) findViewById(R.id.na_dot524));
        NA.get(22).add((View) findViewById(R.id.na_dot525));
        NA.get(22).add((View) findViewById(R.id.na_dot526));
        NA.get(22).add((View) findViewById(R.id.na_dot527));

        NA.get(23).add((View) findViewById(R.id.na_dot528));
        NA.get(23).add((View) findViewById(R.id.na_dot529));
        NA.get(23).add((View) findViewById(R.id.na_dot530));
        NA.get(23).add((View) findViewById(R.id.na_dot531));
        NA.get(23).add((View) findViewById(R.id.na_dot532));
        NA.get(23).add((View) findViewById(R.id.na_dot533));
        NA.get(23).add((View) findViewById(R.id.na_dot534));
        NA.get(23).add((View) findViewById(R.id.na_dot535));
        NA.get(23).add((View) findViewById(R.id.na_dot536));
        NA.get(23).add((View) findViewById(R.id.na_dot537));
        NA.get(23).add((View) findViewById(R.id.na_dot538));
        NA.get(23).add((View) findViewById(R.id.na_dot539));
        NA.get(23).add((View) findViewById(R.id.na_dot540));

        NA.get(24).add((View) findViewById(R.id.na_dot541));
        NA.get(24).add((View) findViewById(R.id.na_dot542));
        NA.get(24).add((View) findViewById(R.id.na_dot543));
        NA.get(24).add((View) findViewById(R.id.na_dot544));
        NA.get(24).add((View) findViewById(R.id.na_dot545));
        NA.get(24).add((View) findViewById(R.id.na_dot546));

        NA.get(25).add((View) findViewById(R.id.na_dot547));
        NA.get(25).add((View) findViewById(R.id.na_dot548));




    }
    public void buildCA(){
        for(int i = 0; i<26;i++) {
            CA.add(new ArrayList<View>());
        }

        CA.get(0).add((View) findViewById(R.id.ca_dot0));
        CA.get(0).add((View) findViewById(R.id.ca_dot1));
        CA.get(0).add((View) findViewById(R.id.ca_dot2));
        CA.get(1).add((View) findViewById(R.id.ca_dot3));
        CA.get(1).add((View) findViewById(R.id.ca_dot4));
        CA.get(1).add((View) findViewById(R.id.ca_dot5));
        CA.get(1).add((View) findViewById(R.id.ca_dot6));
        CA.get(1).add((View) findViewById(R.id.ca_dot7));
        CA.get(1).add((View) findViewById(R.id.ca_dot8));
        CA.get(1).add((View) findViewById(R.id.ca_dot9));
        CA.get(2).add((View) findViewById(R.id.ca_dot10));
        CA.get(2).add((View) findViewById(R.id.ca_dot11));
        CA.get(2).add((View) findViewById(R.id.ca_dot12));
        CA.get(2).add((View) findViewById(R.id.ca_dot13));
        CA.get(2).add((View) findViewById(R.id.ca_dot14));
        CA.get(2).add((View) findViewById(R.id.ca_dot15));
        CA.get(3).add((View) findViewById(R.id.ca_dot16));
        CA.get(3).add((View) findViewById(R.id.ca_dot17));
        CA.get(3).add((View) findViewById(R.id.ca_dot18));
        CA.get(3).add((View) findViewById(R.id.ca_dot19));
        CA.get(3).add((View) findViewById(R.id.ca_dot20));
        CA.get(4).add((View) findViewById(R.id.ca_dot21));
        CA.get(4).add((View) findViewById(R.id.ca_dot22));
        CA.get(4).add((View) findViewById(R.id.ca_dot23));
        CA.get(4).add((View) findViewById(R.id.ca_dot24));
        CA.get(4).add((View) findViewById(R.id.ca_dot25));
        CA.get(5).add((View) findViewById(R.id.ca_dot26));
        CA.get(5).add((View) findViewById(R.id.ca_dot27));
        CA.get(5).add((View) findViewById(R.id.ca_dot28));
        CA.get(5).add((View) findViewById(R.id.ca_dot29));
        CA.get(5).add((View) findViewById(R.id.ca_dot30));
        CA.get(5).add((View) findViewById(R.id.ca_dot31));
        CA.get(5).add((View) findViewById(R.id.ca_dot32));
        CA.get(6).add((View) findViewById(R.id.ca_dot33));
        CA.get(6).add((View) findViewById(R.id.ca_dot34));
        CA.get(6).add((View) findViewById(R.id.ca_dot35));
        CA.get(6).add((View) findViewById(R.id.ca_dot36));
        CA.get(6).add((View) findViewById(R.id.ca_dot37));
        CA.get(6).add((View) findViewById(R.id.ca_dot38));
        CA.get(6).add((View) findViewById(R.id.ca_dot39));
        CA.get(6).add((View) findViewById(R.id.ca_dot40));
        CA.get(7).add((View) findViewById(R.id.ca_dot41));
        CA.get(7).add((View) findViewById(R.id.ca_dot42));
        CA.get(7).add((View) findViewById(R.id.ca_dot43));
        CA.get(7).add((View) findViewById(R.id.ca_dot44));
        CA.get(7).add((View) findViewById(R.id.ca_dot45));
        CA.get(7).add((View) findViewById(R.id.ca_dot46));
        CA.get(8).add((View) findViewById(R.id.ca_dot47));
        CA.get(8).add((View) findViewById(R.id.ca_dot48));
        CA.get(8).add((View) findViewById(R.id.ca_dot49));
        CA.get(8).add((View) findViewById(R.id.ca_dot50));
        CA.get(8).add((View) findViewById(R.id.ca_dot51));
        CA.get(9).add((View) findViewById(R.id.ca_dot52));
        CA.get(9).add((View) findViewById(R.id.ca_dot53));
        CA.get(9).add((View) findViewById(R.id.ca_dot54));
        CA.get(9).add((View) findViewById(R.id.ca_dot55));
        CA.get(10).add((View) findViewById(R.id.ca_dot56));
        CA.get(10).add((View) findViewById(R.id.ca_dot57));
        CA.get(11).add((View) findViewById(R.id.ca_dot58));
        CA.get(12).add((View) findViewById(R.id.ca_dot59));
        CA.get(12).add((View) findViewById(R.id.ca_dot60));


    }
    public void buildSA(){
        for(int i = 0; i<34;i++) {
            SA.add(new ArrayList<View>());
        }

        SA.get(0).add(findViewById(R.id.sa_dot0));
        SA.get(0).add(findViewById(R.id.sa_dot1));
        SA.get(1).add(findViewById(R.id.sa_dot2));
        SA.get(1).add(findViewById(R.id.sa_dot3));
        SA.get(1).add(findViewById(R.id.sa_dot4));
        SA.get(1).add(findViewById(R.id.sa_dot5));
        SA.get(1).add(findViewById(R.id.sa_dot6));
        SA.get(1).add(findViewById(R.id.sa_dot7));
        SA.get(1).add(findViewById(R.id.sa_dot8));
        SA.get(2).add(findViewById(R.id.sa_dot9));
        SA.get(2).add(findViewById(R.id.sa_dot10));
        SA.get(2).add(findViewById(R.id.sa_dot11));
        SA.get(2).add(findViewById(R.id.sa_dot12));
        SA.get(2).add(findViewById(R.id.sa_dot13));
        SA.get(2).add(findViewById(R.id.sa_dot14));
        SA.get(2).add(findViewById(R.id.sa_dot15));
        SA.get(2).add(findViewById(R.id.sa_dot16));
        SA.get(2).add(findViewById(R.id.sa_dot17));
        SA.get(3).add(findViewById(R.id.sa_dot18));
        SA.get(3).add(findViewById(R.id.sa_dot19));
        SA.get(3).add(findViewById(R.id.sa_dot20));
        SA.get(3).add(findViewById(R.id.sa_dot21));
        SA.get(3).add(findViewById(R.id.sa_dot22));
        SA.get(3).add(findViewById(R.id.sa_dot23));
        SA.get(3).add(findViewById(R.id.sa_dot24));
        SA.get(3).add(findViewById(R.id.sa_dot25));
        SA.get(3).add(findViewById(R.id.sa_dot26));
        SA.get(3).add(findViewById(R.id.sa_dot27));
        SA.get(3).add(findViewById(R.id.sa_dot28));
        SA.get(3).add(findViewById(R.id.sa_dot29));
        SA.get(4).add(findViewById(R.id.sa_dot30));
        SA.get(4).add(findViewById(R.id.sa_dot31));
        SA.get(4).add(findViewById(R.id.sa_dot32));
        SA.get(4).add(findViewById(R.id.sa_dot33));
        SA.get(4).add(findViewById(R.id.sa_dot34));
        SA.get(4).add(findViewById(R.id.sa_dot35));
        SA.get(4).add(findViewById(R.id.sa_dot36));
        SA.get(4).add(findViewById(R.id.sa_dot37));
        SA.get(4).add(findViewById(R.id.sa_dot38));
        SA.get(4).add(findViewById(R.id.sa_dot39));
        SA.get(4).add(findViewById(R.id.sa_dot40));
        SA.get(4).add(findViewById(R.id.sa_dot41));
        SA.get(4).add(findViewById(R.id.sa_dot42));
        SA.get(5).add(findViewById(R.id.sa_dot43));
        SA.get(5).add(findViewById(R.id.sa_dot44));
        SA.get(5).add(findViewById(R.id.sa_dot45));
        SA.get(5).add(findViewById(R.id.sa_dot46));
        SA.get(5).add(findViewById(R.id.sa_dot47));
        SA.get(5).add(findViewById(R.id.sa_dot48));
        SA.get(5).add(findViewById(R.id.sa_dot49));
        SA.get(5).add(findViewById(R.id.sa_dot50));
        SA.get(5).add(findViewById(R.id.sa_dot51));
        SA.get(5).add(findViewById(R.id.sa_dot52));
        SA.get(5).add(findViewById(R.id.sa_dot53));
        SA.get(5).add(findViewById(R.id.sa_dot54));
        SA.get(5).add(findViewById(R.id.sa_dot55));
        SA.get(5).add(findViewById(R.id.sa_dot56));
        SA.get(6).add(findViewById(R.id.sa_dot57));
        SA.get(6).add(findViewById(R.id.sa_dot58));
        SA.get(6).add(findViewById(R.id.sa_dot59));
        SA.get(6).add(findViewById(R.id.sa_dot60));
        SA.get(6).add(findViewById(R.id.sa_dot61));
        SA.get(6).add(findViewById(R.id.sa_dot62));
        SA.get(6).add(findViewById(R.id.sa_dot63));
        SA.get(6).add(findViewById(R.id.sa_dot64));
        SA.get(6).add(findViewById(R.id.sa_dot65));
        SA.get(6).add(findViewById(R.id.sa_dot66));
        SA.get(6).add(findViewById(R.id.sa_dot67));
        SA.get(6).add(findViewById(R.id.sa_dot68));
        SA.get(6).add(findViewById(R.id.sa_dot69));
        SA.get(6).add(findViewById(R.id.sa_dot70));
        SA.get(6).add(findViewById(R.id.sa_dot71));
        SA.get(7).add(findViewById(R.id.sa_dot72));
        SA.get(7).add(findViewById(R.id.sa_dot73));
        SA.get(7).add(findViewById(R.id.sa_dot74));
        SA.get(7).add(findViewById(R.id.sa_dot75));
        SA.get(7).add(findViewById(R.id.sa_dot76));
        SA.get(7).add(findViewById(R.id.sa_dot77));
        SA.get(7).add(findViewById(R.id.sa_dot78));
        SA.get(7).add(findViewById(R.id.sa_dot79));
        SA.get(7).add(findViewById(R.id.sa_dot80));
        SA.get(7).add(findViewById(R.id.sa_dot81));
        SA.get(7).add(findViewById(R.id.sa_dot82));
        SA.get(7).add(findViewById(R.id.sa_dot83));
        SA.get(7).add(findViewById(R.id.sa_dot84));
        SA.get(7).add(findViewById(R.id.sa_dot85));
        SA.get(7).add(findViewById(R.id.sa_dot86));
        SA.get(7).add(findViewById(R.id.sa_dot87));
        SA.get(7).add(findViewById(R.id.sa_dot88));
        SA.get(8).add(findViewById(R.id.sa_dot89));
        SA.get(8).add(findViewById(R.id.sa_dot90));
        SA.get(8).add(findViewById(R.id.sa_dot91));
        SA.get(8).add(findViewById(R.id.sa_dot92));
        SA.get(8).add(findViewById(R.id.sa_dot93));
        SA.get(8).add(findViewById(R.id.sa_dot94));
        SA.get(8).add(findViewById(R.id.sa_dot95));
        SA.get(8).add(findViewById(R.id.sa_dot96));
        SA.get(8).add(findViewById(R.id.sa_dot97));
        SA.get(8).add(findViewById(R.id.sa_dot98));
        SA.get(8).add(findViewById(R.id.sa_dot99));
        SA.get(8).add(findViewById(R.id.sa_dot100));
        SA.get(8).add(findViewById(R.id.sa_dot101));
        SA.get(8).add(findViewById(R.id.sa_dot102));
        SA.get(8).add(findViewById(R.id.sa_dot103));
        SA.get(8).add(findViewById(R.id.sa_dot104));
        SA.get(8).add(findViewById(R.id.sa_dot105));
        SA.get(8).add(findViewById(R.id.sa_dot106));
        SA.get(8).add(findViewById(R.id.sa_dot107));
        SA.get(8).add(findViewById(R.id.sa_dot108));
        SA.get(9).add(findViewById(R.id.sa_dot109));
        SA.get(9).add(findViewById(R.id.sa_dot110));
        SA.get(9).add(findViewById(R.id.sa_dot111));
        SA.get(9).add(findViewById(R.id.sa_dot112));
        SA.get(9).add(findViewById(R.id.sa_dot113));
        SA.get(9).add(findViewById(R.id.sa_dot114));
        SA.get(9).add(findViewById(R.id.sa_dot115));
        SA.get(9).add(findViewById(R.id.sa_dot116));
        SA.get(9).add(findViewById(R.id.sa_dot117));
        SA.get(9).add(findViewById(R.id.sa_dot118));
        SA.get(9).add(findViewById(R.id.sa_dot119));
        SA.get(9).add(findViewById(R.id.sa_dot120));
        SA.get(9).add(findViewById(R.id.sa_dot121));
        SA.get(9).add(findViewById(R.id.sa_dot122));
        SA.get(9).add(findViewById(R.id.sa_dot123));
        SA.get(9).add(findViewById(R.id.sa_dot124));
        SA.get(9).add(findViewById(R.id.sa_dot125));
        SA.get(9).add(findViewById(R.id.sa_dot126));
        SA.get(9).add(findViewById(R.id.sa_dot127));
        SA.get(9).add(findViewById(R.id.sa_dot128));
        SA.get(9).add(findViewById(R.id.sa_dot129));
        SA.get(10).add(findViewById(R.id.sa_dot130));
        SA.get(10).add(findViewById(R.id.sa_dot131));
        SA.get(10).add(findViewById(R.id.sa_dot132));
        SA.get(10).add(findViewById(R.id.sa_dot133));
        SA.get(10).add(findViewById(R.id.sa_dot134));
        SA.get(10).add(findViewById(R.id.sa_dot135));
        SA.get(10).add(findViewById(R.id.sa_dot136));
        SA.get(10).add(findViewById(R.id.sa_dot137));
        SA.get(10).add(findViewById(R.id.sa_dot138));
        SA.get(10).add(findViewById(R.id.sa_dot139));
        SA.get(10).add(findViewById(R.id.sa_dot140));
        SA.get(10).add(findViewById(R.id.sa_dot141));
        SA.get(10).add(findViewById(R.id.sa_dot142));
        SA.get(10).add(findViewById(R.id.sa_dot143));
        SA.get(10).add(findViewById(R.id.sa_dot144));
        SA.get(10).add(findViewById(R.id.sa_dot145));
        SA.get(10).add(findViewById(R.id.sa_dot146));
        SA.get(10).add(findViewById(R.id.sa_dot147));
        SA.get(10).add(findViewById(R.id.sa_dot148));
        SA.get(10).add(findViewById(R.id.sa_dot149));
        SA.get(11).add(findViewById(R.id.sa_dot150));
        SA.get(11).add(findViewById(R.id.sa_dot151));
        SA.get(11).add(findViewById(R.id.sa_dot152));
        SA.get(11).add(findViewById(R.id.sa_dot153));
        SA.get(11).add(findViewById(R.id.sa_dot154));
        SA.get(11).add(findViewById(R.id.sa_dot155));
        SA.get(11).add(findViewById(R.id.sa_dot156));
        SA.get(11).add(findViewById(R.id.sa_dot157));
        SA.get(11).add(findViewById(R.id.sa_dot158));
        SA.get(11).add(findViewById(R.id.sa_dot159));
        SA.get(11).add(findViewById(R.id.sa_dot160));
        SA.get(11).add(findViewById(R.id.sa_dot161));
        SA.get(11).add(findViewById(R.id.sa_dot162));
        SA.get(11).add(findViewById(R.id.sa_dot163));
        SA.get(11).add(findViewById(R.id.sa_dot164));
        SA.get(11).add(findViewById(R.id.sa_dot165));
        SA.get(11).add(findViewById(R.id.sa_dot166));
        SA.get(11).add(findViewById(R.id.sa_dot167));
        SA.get(11).add(findViewById(R.id.sa_dot168));
        SA.get(11).add(findViewById(R.id.sa_dot169));
        SA.get(12).add(findViewById(R.id.sa_dot170));
        SA.get(12).add(findViewById(R.id.sa_dot171));
        SA.get(12).add(findViewById(R.id.sa_dot172));
        SA.get(12).add(findViewById(R.id.sa_dot173));
        SA.get(12).add(findViewById(R.id.sa_dot174));
        SA.get(12).add(findViewById(R.id.sa_dot175));
        SA.get(12).add(findViewById(R.id.sa_dot176));
        SA.get(12).add(findViewById(R.id.sa_dot177));
        SA.get(12).add(findViewById(R.id.sa_dot178));
        SA.get(12).add(findViewById(R.id.sa_dot179));
        SA.get(12).add(findViewById(R.id.sa_dot180));
        SA.get(12).add(findViewById(R.id.sa_dot181));
        SA.get(12).add(findViewById(R.id.sa_dot182));
        SA.get(12).add(findViewById(R.id.sa_dot183));
        SA.get(12).add(findViewById(R.id.sa_dot184));
        SA.get(12).add(findViewById(R.id.sa_dot185));
        SA.get(12).add(findViewById(R.id.sa_dot186));
        SA.get(12).add(findViewById(R.id.sa_dot187));
        SA.get(13).add(findViewById(R.id.sa_dot188));
        SA.get(13).add(findViewById(R.id.sa_dot189));
        SA.get(13).add(findViewById(R.id.sa_dot190));
        SA.get(13).add(findViewById(R.id.sa_dot191));
        SA.get(13).add(findViewById(R.id.sa_dot192));
        SA.get(13).add(findViewById(R.id.sa_dot193));
        SA.get(13).add(findViewById(R.id.sa_dot194));
        SA.get(13).add(findViewById(R.id.sa_dot195));
        SA.get(13).add(findViewById(R.id.sa_dot196));
        SA.get(13).add(findViewById(R.id.sa_dot197));
        SA.get(13).add(findViewById(R.id.sa_dot198));
        SA.get(13).add(findViewById(R.id.sa_dot199));
        SA.get(13).add(findViewById(R.id.sa_dot200));
        SA.get(13).add(findViewById(R.id.sa_dot201));
        SA.get(13).add(findViewById(R.id.sa_dot202));
        SA.get(13).add(findViewById(R.id.sa_dot203));
        SA.get(13).add(findViewById(R.id.sa_dot204));
        SA.get(14).add(findViewById(R.id.sa_dot205));
        SA.get(14).add(findViewById(R.id.sa_dot206));
        SA.get(14).add(findViewById(R.id.sa_dot207));
        SA.get(14).add(findViewById(R.id.sa_dot208));
        SA.get(14).add(findViewById(R.id.sa_dot209));
        SA.get(14).add(findViewById(R.id.sa_dot210));
        SA.get(14).add(findViewById(R.id.sa_dot211));
        SA.get(14).add(findViewById(R.id.sa_dot212));
        SA.get(14).add(findViewById(R.id.sa_dot213));
        SA.get(14).add(findViewById(R.id.sa_dot214));
        SA.get(14).add(findViewById(R.id.sa_dot215));
        SA.get(14).add(findViewById(R.id.sa_dot216));
        SA.get(14).add(findViewById(R.id.sa_dot217));
        SA.get(14).add(findViewById(R.id.sa_dot218));
        SA.get(14).add(findViewById(R.id.sa_dot219));
        SA.get(14).add(findViewById(R.id.sa_dot220));
        SA.get(15).add(findViewById(R.id.sa_dot221));
        SA.get(15).add(findViewById(R.id.sa_dot222));
        SA.get(15).add(findViewById(R.id.sa_dot223));
        SA.get(15).add(findViewById(R.id.sa_dot224));
        SA.get(15).add(findViewById(R.id.sa_dot225));
        SA.get(15).add(findViewById(R.id.sa_dot226));
        SA.get(15).add(findViewById(R.id.sa_dot227));
        SA.get(15).add(findViewById(R.id.sa_dot228));
        SA.get(15).add(findViewById(R.id.sa_dot229));
        SA.get(15).add(findViewById(R.id.sa_dot230));
        SA.get(15).add(findViewById(R.id.sa_dot231));
        SA.get(15).add(findViewById(R.id.sa_dot232));
        SA.get(15).add(findViewById(R.id.sa_dot233));
        SA.get(15).add(findViewById(R.id.sa_dot234));
        SA.get(16).add(findViewById(R.id.sa_dot235));
        SA.get(16).add(findViewById(R.id.sa_dot236));
        SA.get(16).add(findViewById(R.id.sa_dot237));
        SA.get(16).add(findViewById(R.id.sa_dot238));
        SA.get(16).add(findViewById(R.id.sa_dot239));
        SA.get(16).add(findViewById(R.id.sa_dot240));
        SA.get(16).add(findViewById(R.id.sa_dot241));
        SA.get(16).add(findViewById(R.id.sa_dot242));
        SA.get(16).add(findViewById(R.id.sa_dot243));
        SA.get(16).add(findViewById(R.id.sa_dot244));
        SA.get(16).add(findViewById(R.id.sa_dot245));
        SA.get(16).add(findViewById(R.id.sa_dot246));
        SA.get(16).add(findViewById(R.id.sa_dot247));
        SA.get(16).add(findViewById(R.id.sa_dot248));
        SA.get(17).add(findViewById(R.id.sa_dot249));
        SA.get(17).add(findViewById(R.id.sa_dot250));
        SA.get(17).add(findViewById(R.id.sa_dot251));
        SA.get(17).add(findViewById(R.id.sa_dot252));
        SA.get(17).add(findViewById(R.id.sa_dot253));
        SA.get(17).add(findViewById(R.id.sa_dot254));
        SA.get(17).add(findViewById(R.id.sa_dot255));
        SA.get(17).add(findViewById(R.id.sa_dot256));
        SA.get(17).add(findViewById(R.id.sa_dot257));
        SA.get(17).add(findViewById(R.id.sa_dot258));
        SA.get(17).add(findViewById(R.id.sa_dot259));
        SA.get(17).add(findViewById(R.id.sa_dot260));
        SA.get(17).add(findViewById(R.id.sa_dot261));
        SA.get(17).add(findViewById(R.id.sa_dot262));
        SA.get(18).add(findViewById(R.id.sa_dot263));
        SA.get(18).add(findViewById(R.id.sa_dot264));
        SA.get(18).add(findViewById(R.id.sa_dot265));
        SA.get(18).add(findViewById(R.id.sa_dot266));
        SA.get(18).add(findViewById(R.id.sa_dot267));
        SA.get(18).add(findViewById(R.id.sa_dot268));
        SA.get(18).add(findViewById(R.id.sa_dot269));
        SA.get(18).add(findViewById(R.id.sa_dot270));
        SA.get(18).add(findViewById(R.id.sa_dot271));
        SA.get(18).add(findViewById(R.id.sa_dot272));
        SA.get(18).add(findViewById(R.id.sa_dot273));
        SA.get(19).add(findViewById(R.id.sa_dot274));
        SA.get(19).add(findViewById(R.id.sa_dot275));
        SA.get(19).add(findViewById(R.id.sa_dot276));
        SA.get(19).add(findViewById(R.id.sa_dot277));
        SA.get(19).add(findViewById(R.id.sa_dot278));
        SA.get(19).add(findViewById(R.id.sa_dot279));
        SA.get(19).add(findViewById(R.id.sa_dot280));
        SA.get(19).add(findViewById(R.id.sa_dot281));
        SA.get(19).add(findViewById(R.id.sa_dot282));
        SA.get(19).add(findViewById(R.id.sa_dot283));
        SA.get(19).add(findViewById(R.id.sa_dot284));
        SA.get(20).add(findViewById(R.id.sa_dot285));
        SA.get(20).add(findViewById(R.id.sa_dot286));
        SA.get(20).add(findViewById(R.id.sa_dot287));
        SA.get(20).add(findViewById(R.id.sa_dot288));
        SA.get(20).add(findViewById(R.id.sa_dot289));
        SA.get(20).add(findViewById(R.id.sa_dot290));
        SA.get(20).add(findViewById(R.id.sa_dot291));
        SA.get(20).add(findViewById(R.id.sa_dot292));
        SA.get(20).add(findViewById(R.id.sa_dot293));
        SA.get(20).add(findViewById(R.id.sa_dot294));
        SA.get(20).add(findViewById(R.id.sa_dot295));
        SA.get(21).add(findViewById(R.id.sa_dot296));
        SA.get(21).add(findViewById(R.id.sa_dot297));
        SA.get(21).add(findViewById(R.id.sa_dot298));
        SA.get(21).add(findViewById(R.id.sa_dot299));
        SA.get(21).add(findViewById(R.id.sa_dot300));
        SA.get(21).add(findViewById(R.id.sa_dot301));
        SA.get(21).add(findViewById(R.id.sa_dot302));
        SA.get(21).add(findViewById(R.id.sa_dot303));
        SA.get(21).add(findViewById(R.id.sa_dot304));
        SA.get(21).add(findViewById(R.id.sa_dot305));
        SA.get(22).add(findViewById(R.id.sa_dot306));
        SA.get(22).add(findViewById(R.id.sa_dot307));
        SA.get(22).add(findViewById(R.id.sa_dot308));
        SA.get(22).add(findViewById(R.id.sa_dot309));
        SA.get(22).add(findViewById(R.id.sa_dot310));
        SA.get(22).add(findViewById(R.id.sa_dot311));
        SA.get(22).add(findViewById(R.id.sa_dot312));
        SA.get(22).add(findViewById(R.id.sa_dot313));
        SA.get(22).add(findViewById(R.id.sa_dot314));
        SA.get(23).add(findViewById(R.id.sa_dot315));
        SA.get(23).add(findViewById(R.id.sa_dot316));
        SA.get(23).add(findViewById(R.id.sa_dot317));
        SA.get(23).add(findViewById(R.id.sa_dot318));
        SA.get(23).add(findViewById(R.id.sa_dot319));
        SA.get(23).add(findViewById(R.id.sa_dot320));
        SA.get(23).add(findViewById(R.id.sa_dot321));
        SA.get(23).add(findViewById(R.id.sa_dot322));
        SA.get(24).add(findViewById(R.id.sa_dot323));
        SA.get(24).add(findViewById(R.id.sa_dot324));
        SA.get(24).add(findViewById(R.id.sa_dot325));
        SA.get(24).add(findViewById(R.id.sa_dot326));
        SA.get(24).add(findViewById(R.id.sa_dot327));
        SA.get(24).add(findViewById(R.id.sa_dot328));
        SA.get(24).add(findViewById(R.id.sa_dot329));
        SA.get(25).add(findViewById(R.id.sa_dot330));
        SA.get(25).add(findViewById(R.id.sa_dot331));
        SA.get(25).add(findViewById(R.id.sa_dot332));
        SA.get(25).add(findViewById(R.id.sa_dot333));
        SA.get(25).add(findViewById(R.id.sa_dot334));
        SA.get(25).add(findViewById(R.id.sa_dot335));
        SA.get(25).add(findViewById(R.id.sa_dot336));
        SA.get(26).add(findViewById(R.id.sa_dot337));
        SA.get(26).add(findViewById(R.id.sa_dot338));
        SA.get(26).add(findViewById(R.id.sa_dot339));
        SA.get(26).add(findViewById(R.id.sa_dot340));
        SA.get(26).add(findViewById(R.id.sa_dot341));
        SA.get(27).add(findViewById(R.id.sa_dot342));
        SA.get(27).add(findViewById(R.id.sa_dot343));
        SA.get(27).add(findViewById(R.id.sa_dot344));
        SA.get(28).add(findViewById(R.id.sa_dot345));
        SA.get(28).add(findViewById(R.id.sa_dot346));
        SA.get(28).add(findViewById(R.id.sa_dot347));
        SA.get(29).add(findViewById(R.id.sa_dot348));
        SA.get(29).add(findViewById(R.id.sa_dot349));
        SA.get(29).add(findViewById(R.id.sa_dot350));
        SA.get(30).add(findViewById(R.id.sa_dot351));
        SA.get(30).add(findViewById(R.id.sa_dot352));
        SA.get(30).add(findViewById(R.id.sa_dot353));
        SA.get(30).add(findViewById(R.id.sa_dot354));
        SA.get(31).add(findViewById(R.id.sa_dot355));
        SA.get(31).add(findViewById(R.id.sa_dot356));
        SA.get(32).add(findViewById(R.id.sa_dot357));
        SA.get(32).add(findViewById(R.id.sa_dot358));
        SA.get(33).add(findViewById(R.id.sa_dot359));
        SA.get(33).add(findViewById(R.id.sa_dot360));

    }
    public void buildEU(){
        for(int i = 0; i<19;i++) {
            EU.add(new ArrayList<View>());
        }

        EU.get(0).add(findViewById(R.id.eu_dot0));
        EU.get(0).add(findViewById(R.id.eu_dot1));
        EU.get(1).add(findViewById(R.id.eu_dot2));
        EU.get(1).add(findViewById(R.id.eu_dot3));
        EU.get(1).add(findViewById(R.id.eu_dot4));
        EU.get(1).add(findViewById(R.id.eu_dot5));
        EU.get(2).add(findViewById(R.id.eu_dot6));
        EU.get(2).add(findViewById(R.id.eu_dot7));
        EU.get(2).add(findViewById(R.id.eu_dot8));
        EU.get(2).add(findViewById(R.id.eu_dot9));
        EU.get(2).add(findViewById(R.id.eu_dot10));
        EU.get(2).add(findViewById(R.id.eu_dot11));
        EU.get(3).add(findViewById(R.id.eu_dot12));
        EU.get(3).add(findViewById(R.id.eu_dot13));
        EU.get(3).add(findViewById(R.id.eu_dot14));
        EU.get(3).add(findViewById(R.id.eu_dot15));
        EU.get(3).add(findViewById(R.id.eu_dot16));
        EU.get(3).add(findViewById(R.id.eu_dot17));
        EU.get(3).add(findViewById(R.id.eu_dot18));
        EU.get(4).add(findViewById(R.id.eu_dot19));
        EU.get(4).add(findViewById(R.id.eu_dot20));
        EU.get(4).add(findViewById(R.id.eu_dot21));
        EU.get(4).add(findViewById(R.id.eu_dot22));
        EU.get(4).add(findViewById(R.id.eu_dot23));
        EU.get(4).add(findViewById(R.id.eu_dot24));
        EU.get(4).add(findViewById(R.id.eu_dot25));
        EU.get(4).add(findViewById(R.id.eu_dot26));
        EU.get(4).add(findViewById(R.id.eu_dot27));
        EU.get(4).add(findViewById(R.id.eu_dot28));
        EU.get(4).add(findViewById(R.id.eu_dot29));
        EU.get(5).add(findViewById(R.id.eu_dot30));
        EU.get(5).add(findViewById(R.id.eu_dot31));
        EU.get(5).add(findViewById(R.id.eu_dot32));
        EU.get(5).add(findViewById(R.id.eu_dot33));
        EU.get(5).add(findViewById(R.id.eu_dot34));
        EU.get(5).add(findViewById(R.id.eu_dot35));
        EU.get(5).add(findViewById(R.id.eu_dot36));
        EU.get(5).add(findViewById(R.id.eu_dot37));
        EU.get(5).add(findViewById(R.id.eu_dot38));
        EU.get(6).add(findViewById(R.id.eu_dot39));
        EU.get(6).add(findViewById(R.id.eu_dot40));
        EU.get(6).add(findViewById(R.id.eu_dot41));
        EU.get(6).add(findViewById(R.id.eu_dot42));
        EU.get(6).add(findViewById(R.id.eu_dot43));
        EU.get(6).add(findViewById(R.id.eu_dot44));
        EU.get(6).add(findViewById(R.id.eu_dot45));
        EU.get(6).add(findViewById(R.id.eu_dot46));
        EU.get(7).add(findViewById(R.id.eu_dot47));
        EU.get(7).add(findViewById(R.id.eu_dot48));
        EU.get(7).add(findViewById(R.id.eu_dot49));
        EU.get(7).add(findViewById(R.id.eu_dot50));
        EU.get(8).add(findViewById(R.id.eu_dot51));
        EU.get(8).add(findViewById(R.id.eu_dot52));
        EU.get(8).add(findViewById(R.id.eu_dot53));
        EU.get(8).add(findViewById(R.id.eu_dot54));
        EU.get(8).add(findViewById(R.id.eu_dot55));
        EU.get(8).add(findViewById(R.id.eu_dot56));
        EU.get(8).add(findViewById(R.id.eu_dot57));
        EU.get(8).add(findViewById(R.id.eu_dot58));
        EU.get(9).add(findViewById(R.id.eu_dot59));
        EU.get(9).add(findViewById(R.id.eu_dot60));
        EU.get(9).add(findViewById(R.id.eu_dot61));
        EU.get(9).add(findViewById(R.id.eu_dot62));
        EU.get(9).add(findViewById(R.id.eu_dot63));
        EU.get(9).add(findViewById(R.id.eu_dot64));
        EU.get(9).add(findViewById(R.id.eu_dot65));
        EU.get(9).add(findViewById(R.id.eu_dot66));
        EU.get(9).add(findViewById(R.id.eu_dot67));
        EU.get(9).add(findViewById(R.id.eu_dot68));
        EU.get(9).add(findViewById(R.id.eu_dot69));
        EU.get(9).add(findViewById(R.id.eu_dot70));
        EU.get(9).add(findViewById(R.id.eu_dot71));
        EU.get(10).add(findViewById(R.id.eu_dot72));
        EU.get(10).add(findViewById(R.id.eu_dot73));
        EU.get(10).add(findViewById(R.id.eu_dot74));
        EU.get(10).add(findViewById(R.id.eu_dot75));
        EU.get(10).add(findViewById(R.id.eu_dot76));
        EU.get(10).add(findViewById(R.id.eu_dot77));
        EU.get(10).add(findViewById(R.id.eu_dot78));
        EU.get(10).add(findViewById(R.id.eu_dot79));
        EU.get(10).add(findViewById(R.id.eu_dot80));
        EU.get(10).add(findViewById(R.id.eu_dot81));
        EU.get(10).add(findViewById(R.id.eu_dot82));
        EU.get(10).add(findViewById(R.id.eu_dot83));
        EU.get(10).add(findViewById(R.id.eu_dot84));
        EU.get(10).add(findViewById(R.id.eu_dot85));
        EU.get(10).add(findViewById(R.id.eu_dot86));
        EU.get(10).add(findViewById(R.id.eu_dot87));
        EU.get(10).add(findViewById(R.id.eu_dot88));
        EU.get(11).add(findViewById(R.id.eu_dot89));
        EU.get(11).add(findViewById(R.id.eu_dot90));
        EU.get(11).add(findViewById(R.id.eu_dot91));
        EU.get(11).add(findViewById(R.id.eu_dot92));
        EU.get(11).add(findViewById(R.id.eu_dot93));
        EU.get(11).add(findViewById(R.id.eu_dot94));
        EU.get(11).add(findViewById(R.id.eu_dot95));
        EU.get(11).add(findViewById(R.id.eu_dot96));
        EU.get(11).add(findViewById(R.id.eu_dot97));
        EU.get(11).add(findViewById(R.id.eu_dot98));
        EU.get(11).add(findViewById(R.id.eu_dot99));
        EU.get(11).add(findViewById(R.id.eu_dot100));
        EU.get(11).add(findViewById(R.id.eu_dot101));
        EU.get(11).add(findViewById(R.id.eu_dot102));
        EU.get(11).add(findViewById(R.id.eu_dot103));
        EU.get(12).add(findViewById(R.id.eu_dot104));
        EU.get(12).add(findViewById(R.id.eu_dot105));
        EU.get(12).add(findViewById(R.id.eu_dot106));
        EU.get(12).add(findViewById(R.id.eu_dot107));
        EU.get(12).add(findViewById(R.id.eu_dot108));
        EU.get(12).add(findViewById(R.id.eu_dot109));
        EU.get(12).add(findViewById(R.id.eu_dot110));
        EU.get(12).add(findViewById(R.id.eu_dot111));
        EU.get(12).add(findViewById(R.id.eu_dot112));
        EU.get(12).add(findViewById(R.id.eu_dot113));
        EU.get(12).add(findViewById(R.id.eu_dot114));
        EU.get(12).add(findViewById(R.id.eu_dot115));
        EU.get(12).add(findViewById(R.id.eu_dot116));
        EU.get(12).add(findViewById(R.id.eu_dot117));
        EU.get(12).add(findViewById(R.id.eu_dot118));
        EU.get(12).add(findViewById(R.id.eu_dot119));
        EU.get(12).add(findViewById(R.id.eu_dot120));
        EU.get(12).add(findViewById(R.id.eu_dot121));
        EU.get(12).add(findViewById(R.id.eu_dot122));
        EU.get(13).add(findViewById(R.id.eu_dot123));
        EU.get(13).add(findViewById(R.id.eu_dot124));
        EU.get(13).add(findViewById(R.id.eu_dot125));
        EU.get(13).add(findViewById(R.id.eu_dot126));
        EU.get(13).add(findViewById(R.id.eu_dot127));
        EU.get(13).add(findViewById(R.id.eu_dot128));
        EU.get(13).add(findViewById(R.id.eu_dot129));
        EU.get(13).add(findViewById(R.id.eu_dot130));
        EU.get(13).add(findViewById(R.id.eu_dot131));
        EU.get(13).add(findViewById(R.id.eu_dot132));
        EU.get(13).add(findViewById(R.id.eu_dot133));
        EU.get(13).add(findViewById(R.id.eu_dot134));
        EU.get(13).add(findViewById(R.id.eu_dot135));
        EU.get(13).add(findViewById(R.id.eu_dot136));
        EU.get(14).add(findViewById(R.id.eu_dot137));
        EU.get(14).add(findViewById(R.id.eu_dot138));
        EU.get(14).add(findViewById(R.id.eu_dot139));
        EU.get(14).add(findViewById(R.id.eu_dot140));
        EU.get(14).add(findViewById(R.id.eu_dot141));
        EU.get(14).add(findViewById(R.id.eu_dot142));
        EU.get(14).add(findViewById(R.id.eu_dot143));
        EU.get(14).add(findViewById(R.id.eu_dot144));
        EU.get(14).add(findViewById(R.id.eu_dot145));
        EU.get(14).add(findViewById(R.id.eu_dot146));
        EU.get(14).add(findViewById(R.id.eu_dot147));
        EU.get(14).add(findViewById(R.id.eu_dot148));
        EU.get(14).add(findViewById(R.id.eu_dot149));
        EU.get(15).add(findViewById(R.id.eu_dot150));
        EU.get(15).add(findViewById(R.id.eu_dot151));
        EU.get(15).add(findViewById(R.id.eu_dot152));
        EU.get(15).add(findViewById(R.id.eu_dot153));
        EU.get(15).add(findViewById(R.id.eu_dot154));
        EU.get(15).add(findViewById(R.id.eu_dot155));
        EU.get(15).add(findViewById(R.id.eu_dot156));
        EU.get(15).add(findViewById(R.id.eu_dot157));
        EU.get(15).add(findViewById(R.id.eu_dot158));
        EU.get(15).add(findViewById(R.id.eu_dot159));
        EU.get(16).add(findViewById(R.id.eu_dot160));
        EU.get(16).add(findViewById(R.id.eu_dot161));
        EU.get(16).add(findViewById(R.id.eu_dot162));
        EU.get(16).add(findViewById(R.id.eu_dot163));
        EU.get(16).add(findViewById(R.id.eu_dot164));
        EU.get(16).add(findViewById(R.id.eu_dot165));
        EU.get(16).add(findViewById(R.id.eu_dot166));
        EU.get(16).add(findViewById(R.id.eu_dot167));
        EU.get(17).add(findViewById(R.id.eu_dot168));
        EU.get(17).add(findViewById(R.id.eu_dot169));
        EU.get(17).add(findViewById(R.id.eu_dot170));
        EU.get(17).add(findViewById(R.id.eu_dot171));
        EU.get(17).add(findViewById(R.id.eu_dot172));
        EU.get(18).add(findViewById(R.id.eu_dot173));

    }
    public void buildRS(){
        for(int i = 0; i<19;i++) {
            RS.add(new ArrayList<View>());
        }
        
        RS.get(0).add(findViewById(R.id.rs_dot0));
        RS.get(0).add(findViewById(R.id.rs_dot1));
        RS.get(0).add(findViewById(R.id.rs_dot2));
        RS.get(1).add(findViewById(R.id.rs_dot3));
        RS.get(1).add(findViewById(R.id.rs_dot4));
        RS.get(1).add(findViewById(R.id.rs_dot5));
        RS.get(1).add(findViewById(R.id.rs_dot6));
        RS.get(2).add(findViewById(R.id.rs_dot7));
        RS.get(2).add(findViewById(R.id.rs_dot8));
        RS.get(2).add(findViewById(R.id.rs_dot9));
        RS.get(2).add(findViewById(R.id.rs_dot10));
        RS.get(2).add(findViewById(R.id.rs_dot11));
        RS.get(2).add(findViewById(R.id.rs_dot12));
        RS.get(2).add(findViewById(R.id.rs_dot13));
        RS.get(2).add(findViewById(R.id.rs_dot14));
        RS.get(2).add(findViewById(R.id.rs_dot15));
        RS.get(2).add(findViewById(R.id.rs_dot16));
        RS.get(3).add(findViewById(R.id.rs_dot17));
        RS.get(3).add(findViewById(R.id.rs_dot18));
        RS.get(3).add(findViewById(R.id.rs_dot19));
        RS.get(3).add(findViewById(R.id.rs_dot20));
        RS.get(3).add(findViewById(R.id.rs_dot21));
        RS.get(3).add(findViewById(R.id.rs_dot22));
        RS.get(3).add(findViewById(R.id.rs_dot23));
        RS.get(3).add(findViewById(R.id.rs_dot24));
        RS.get(3).add(findViewById(R.id.rs_dot25));
        RS.get(3).add(findViewById(R.id.rs_dot26));
        RS.get(3).add(findViewById(R.id.rs_dot27));
        RS.get(3).add(findViewById(R.id.rs_dot28));
        RS.get(3).add(findViewById(R.id.rs_dot29));
        RS.get(3).add(findViewById(R.id.rs_dot30));
        RS.get(3).add(findViewById(R.id.rs_dot31));
        RS.get(3).add(findViewById(R.id.rs_dot32));
        RS.get(3).add(findViewById(R.id.rs_dot33));
        RS.get(3).add(findViewById(R.id.rs_dot34));
        RS.get(3).add(findViewById(R.id.rs_dot35));
        RS.get(3).add(findViewById(R.id.rs_dot36));
        RS.get(3).add(findViewById(R.id.rs_dot37));
        RS.get(3).add(findViewById(R.id.rs_dot38));
        RS.get(3).add(findViewById(R.id.rs_dot39));
        RS.get(4).add(findViewById(R.id.rs_dot40));
        RS.get(4).add(findViewById(R.id.rs_dot41));
        RS.get(4).add(findViewById(R.id.rs_dot42));
        RS.get(4).add(findViewById(R.id.rs_dot43));
        RS.get(4).add(findViewById(R.id.rs_dot44));
        RS.get(4).add(findViewById(R.id.rs_dot45));
        RS.get(4).add(findViewById(R.id.rs_dot46));
        RS.get(4).add(findViewById(R.id.rs_dot47));
        RS.get(4).add(findViewById(R.id.rs_dot48));
        RS.get(4).add(findViewById(R.id.rs_dot49));
        RS.get(4).add(findViewById(R.id.rs_dot50));
        RS.get(4).add(findViewById(R.id.rs_dot51));
        RS.get(4).add(findViewById(R.id.rs_dot52));
        RS.get(4).add(findViewById(R.id.rs_dot53));
        RS.get(4).add(findViewById(R.id.rs_dot54));
        RS.get(4).add(findViewById(R.id.rs_dot55));
        RS.get(4).add(findViewById(R.id.rs_dot56));
        RS.get(4).add(findViewById(R.id.rs_dot57));
        RS.get(4).add(findViewById(R.id.rs_dot58));
        RS.get(4).add(findViewById(R.id.rs_dot59));
        RS.get(4).add(findViewById(R.id.rs_dot60));
        RS.get(4).add(findViewById(R.id.rs_dot61));
        RS.get(4).add(findViewById(R.id.rs_dot62));
        RS.get(4).add(findViewById(R.id.rs_dot63));
        RS.get(4).add(findViewById(R.id.rs_dot64));
        RS.get(4).add(findViewById(R.id.rs_dot65));
        RS.get(4).add(findViewById(R.id.rs_dot66));
        RS.get(4).add(findViewById(R.id.rs_dot67));
        RS.get(4).add(findViewById(R.id.rs_dot68));
        RS.get(4).add(findViewById(R.id.rs_dot69));
        RS.get(4).add(findViewById(R.id.rs_dot70));
        RS.get(4).add(findViewById(R.id.rs_dot71));
        RS.get(4).add(findViewById(R.id.rs_dot72));
        RS.get(4).add(findViewById(R.id.rs_dot73));
        RS.get(4).add(findViewById(R.id.rs_dot74));
        RS.get(4).add(findViewById(R.id.rs_dot75));
        RS.get(4).add(findViewById(R.id.rs_dot76));
        RS.get(4).add(findViewById(R.id.rs_dot77));
        RS.get(4).add(findViewById(R.id.rs_dot78));
        RS.get(4).add(findViewById(R.id.rs_dot79));
        RS.get(4).add(findViewById(R.id.rs_dot80));
        RS.get(5).add(findViewById(R.id.rs_dot81));
        RS.get(5).add(findViewById(R.id.rs_dot82));
        RS.get(5).add(findViewById(R.id.rs_dot83));
        RS.get(5).add(findViewById(R.id.rs_dot84));
        RS.get(5).add(findViewById(R.id.rs_dot85));
        RS.get(5).add(findViewById(R.id.rs_dot86));
        RS.get(5).add(findViewById(R.id.rs_dot87));
        RS.get(5).add(findViewById(R.id.rs_dot88));
        RS.get(5).add(findViewById(R.id.rs_dot89));
        RS.get(5).add(findViewById(R.id.rs_dot90));
        RS.get(5).add(findViewById(R.id.rs_dot91));
        RS.get(5).add(findViewById(R.id.rs_dot92));
        RS.get(5).add(findViewById(R.id.rs_dot93));
        RS.get(5).add(findViewById(R.id.rs_dot94));
        RS.get(5).add(findViewById(R.id.rs_dot95));
        RS.get(5).add(findViewById(R.id.rs_dot96));
        RS.get(5).add(findViewById(R.id.rs_dot97));
        RS.get(5).add(findViewById(R.id.rs_dot98));
        RS.get(5).add(findViewById(R.id.rs_dot99));
        RS.get(5).add(findViewById(R.id.rs_dot100));
        RS.get(5).add(findViewById(R.id.rs_dot101));
        RS.get(5).add(findViewById(R.id.rs_dot102));
        RS.get(5).add(findViewById(R.id.rs_dot103));
        RS.get(5).add(findViewById(R.id.rs_dot104));
        RS.get(5).add(findViewById(R.id.rs_dot105));
        RS.get(5).add(findViewById(R.id.rs_dot106));
        RS.get(5).add(findViewById(R.id.rs_dot107));
        RS.get(5).add(findViewById(R.id.rs_dot108));
        RS.get(5).add(findViewById(R.id.rs_dot109));
        RS.get(5).add(findViewById(R.id.rs_dot110));
        RS.get(5).add(findViewById(R.id.rs_dot111));
        RS.get(5).add(findViewById(R.id.rs_dot112));
        RS.get(5).add(findViewById(R.id.rs_dot113));
        RS.get(5).add(findViewById(R.id.rs_dot114));
        RS.get(5).add(findViewById(R.id.rs_dot115));
        RS.get(5).add(findViewById(R.id.rs_dot116));
        RS.get(5).add(findViewById(R.id.rs_dot117));
        RS.get(5).add(findViewById(R.id.rs_dot118));
        RS.get(5).add(findViewById(R.id.rs_dot119));
        RS.get(5).add(findViewById(R.id.rs_dot120));
        RS.get(5).add(findViewById(R.id.rs_dot121));
        RS.get(5).add(findViewById(R.id.rs_dot122));
        RS.get(5).add(findViewById(R.id.rs_dot123));
        RS.get(5).add(findViewById(R.id.rs_dot124));
        RS.get(5).add(findViewById(R.id.rs_dot125));
        RS.get(5).add(findViewById(R.id.rs_dot126));
        RS.get(5).add(findViewById(R.id.rs_dot127));
        RS.get(5).add(findViewById(R.id.rs_dot128));
        RS.get(5).add(findViewById(R.id.rs_dot129));
        RS.get(5).add(findViewById(R.id.rs_dot130));
        RS.get(5).add(findViewById(R.id.rs_dot131));
        RS.get(6).add(findViewById(R.id.rs_dot132));
        RS.get(6).add(findViewById(R.id.rs_dot133));
        RS.get(6).add(findViewById(R.id.rs_dot134));
        RS.get(6).add(findViewById(R.id.rs_dot135));
        RS.get(6).add(findViewById(R.id.rs_dot136));
        RS.get(6).add(findViewById(R.id.rs_dot137));
        RS.get(6).add(findViewById(R.id.rs_dot138));
        RS.get(6).add(findViewById(R.id.rs_dot139));
        RS.get(6).add(findViewById(R.id.rs_dot140));
        RS.get(6).add(findViewById(R.id.rs_dot141));
        RS.get(6).add(findViewById(R.id.rs_dot142));
        RS.get(6).add(findViewById(R.id.rs_dot143));
        RS.get(6).add(findViewById(R.id.rs_dot144));
        RS.get(6).add(findViewById(R.id.rs_dot145));
        RS.get(6).add(findViewById(R.id.rs_dot146));
        RS.get(6).add(findViewById(R.id.rs_dot147));
        RS.get(6).add(findViewById(R.id.rs_dot148));
        RS.get(6).add(findViewById(R.id.rs_dot149));
        RS.get(6).add(findViewById(R.id.rs_dot150));
        RS.get(6).add(findViewById(R.id.rs_dot151));
        RS.get(6).add(findViewById(R.id.rs_dot152));
        RS.get(6).add(findViewById(R.id.rs_dot153));
        RS.get(6).add(findViewById(R.id.rs_dot154));
        RS.get(6).add(findViewById(R.id.rs_dot155));
        RS.get(6).add(findViewById(R.id.rs_dot156));
        RS.get(6).add(findViewById(R.id.rs_dot157));
        RS.get(6).add(findViewById(R.id.rs_dot158));
        RS.get(6).add(findViewById(R.id.rs_dot159));
        RS.get(6).add(findViewById(R.id.rs_dot160));
        RS.get(6).add(findViewById(R.id.rs_dot161));
        RS.get(6).add(findViewById(R.id.rs_dot162));
        RS.get(6).add(findViewById(R.id.rs_dot163));
        RS.get(6).add(findViewById(R.id.rs_dot164));
        RS.get(6).add(findViewById(R.id.rs_dot165));
        RS.get(6).add(findViewById(R.id.rs_dot166));
        RS.get(6).add(findViewById(R.id.rs_dot167));
        RS.get(6).add(findViewById(R.id.rs_dot168));
        RS.get(6).add(findViewById(R.id.rs_dot169));
        RS.get(6).add(findViewById(R.id.rs_dot170));
        RS.get(6).add(findViewById(R.id.rs_dot171));
        RS.get(6).add(findViewById(R.id.rs_dot172));
        RS.get(6).add(findViewById(R.id.rs_dot173));
        RS.get(6).add(findViewById(R.id.rs_dot174));
        RS.get(6).add(findViewById(R.id.rs_dot175));
        RS.get(6).add(findViewById(R.id.rs_dot176));
        RS.get(6).add(findViewById(R.id.rs_dot177));
        RS.get(6).add(findViewById(R.id.rs_dot178));
        RS.get(6).add(findViewById(R.id.rs_dot179));
        RS.get(6).add(findViewById(R.id.rs_dot180));
        RS.get(6).add(findViewById(R.id.rs_dot181));
        RS.get(6).add(findViewById(R.id.rs_dot182));
        RS.get(7).add(findViewById(R.id.rs_dot183));
        RS.get(7).add(findViewById(R.id.rs_dot184));
        RS.get(7).add(findViewById(R.id.rs_dot185));
        RS.get(7).add(findViewById(R.id.rs_dot186));
        RS.get(7).add(findViewById(R.id.rs_dot187));
        RS.get(7).add(findViewById(R.id.rs_dot188));
        RS.get(7).add(findViewById(R.id.rs_dot189));
        RS.get(7).add(findViewById(R.id.rs_dot190));
        RS.get(7).add(findViewById(R.id.rs_dot191));
        RS.get(7).add(findViewById(R.id.rs_dot192));
        RS.get(7).add(findViewById(R.id.rs_dot193));
        RS.get(7).add(findViewById(R.id.rs_dot194));
        RS.get(7).add(findViewById(R.id.rs_dot195));
        RS.get(7).add(findViewById(R.id.rs_dot196));
        RS.get(7).add(findViewById(R.id.rs_dot197));
        RS.get(7).add(findViewById(R.id.rs_dot198));
        RS.get(7).add(findViewById(R.id.rs_dot199));
        RS.get(7).add(findViewById(R.id.rs_dot200));
        RS.get(7).add(findViewById(R.id.rs_dot201));
        RS.get(7).add(findViewById(R.id.rs_dot202));
        RS.get(7).add(findViewById(R.id.rs_dot203));
        RS.get(7).add(findViewById(R.id.rs_dot204));
        RS.get(7).add(findViewById(R.id.rs_dot205));
        RS.get(7).add(findViewById(R.id.rs_dot206));
        RS.get(7).add(findViewById(R.id.rs_dot207));
        RS.get(7).add(findViewById(R.id.rs_dot208));
        RS.get(7).add(findViewById(R.id.rs_dot209));
        RS.get(7).add(findViewById(R.id.rs_dot210));
        RS.get(7).add(findViewById(R.id.rs_dot211));
        RS.get(7).add(findViewById(R.id.rs_dot212));
        RS.get(7).add(findViewById(R.id.rs_dot213));
        RS.get(7).add(findViewById(R.id.rs_dot214));
        RS.get(7).add(findViewById(R.id.rs_dot215));
        RS.get(7).add(findViewById(R.id.rs_dot216));
        RS.get(7).add(findViewById(R.id.rs_dot217));
        RS.get(7).add(findViewById(R.id.rs_dot218));
        RS.get(7).add(findViewById(R.id.rs_dot219));
        RS.get(7).add(findViewById(R.id.rs_dot220));
        RS.get(7).add(findViewById(R.id.rs_dot221));
        RS.get(7).add(findViewById(R.id.rs_dot222));
        RS.get(7).add(findViewById(R.id.rs_dot223));
        RS.get(7).add(findViewById(R.id.rs_dot224));
        RS.get(7).add(findViewById(R.id.rs_dot225));
        RS.get(7).add(findViewById(R.id.rs_dot226));
        RS.get(7).add(findViewById(R.id.rs_dot227));
        RS.get(7).add(findViewById(R.id.rs_dot228));
        RS.get(7).add(findViewById(R.id.rs_dot229));
        RS.get(7).add(findViewById(R.id.rs_dot230));
        RS.get(7).add(findViewById(R.id.rs_dot231));
        RS.get(7).add(findViewById(R.id.rs_dot232));
        RS.get(7).add(findViewById(R.id.rs_dot233));
        RS.get(7).add(findViewById(R.id.rs_dot234));
        RS.get(7).add(findViewById(R.id.rs_dot235));
        RS.get(8).add(findViewById(R.id.rs_dot236));
        RS.get(8).add(findViewById(R.id.rs_dot237));
        RS.get(8).add(findViewById(R.id.rs_dot238));
        RS.get(8).add(findViewById(R.id.rs_dot239));
        RS.get(8).add(findViewById(R.id.rs_dot240));
        RS.get(8).add(findViewById(R.id.rs_dot241));
        RS.get(8).add(findViewById(R.id.rs_dot242));
        RS.get(8).add(findViewById(R.id.rs_dot243));
        RS.get(8).add(findViewById(R.id.rs_dot244));
        RS.get(8).add(findViewById(R.id.rs_dot245));
        RS.get(8).add(findViewById(R.id.rs_dot246));
        RS.get(8).add(findViewById(R.id.rs_dot247));
        RS.get(8).add(findViewById(R.id.rs_dot248));
        RS.get(8).add(findViewById(R.id.rs_dot249));
        RS.get(8).add(findViewById(R.id.rs_dot250));
        RS.get(8).add(findViewById(R.id.rs_dot251));
        RS.get(8).add(findViewById(R.id.rs_dot252));
        RS.get(8).add(findViewById(R.id.rs_dot253));
        RS.get(8).add(findViewById(R.id.rs_dot254));
        RS.get(8).add(findViewById(R.id.rs_dot255));
        RS.get(8).add(findViewById(R.id.rs_dot256));
        RS.get(8).add(findViewById(R.id.rs_dot257));
        RS.get(8).add(findViewById(R.id.rs_dot258));
        RS.get(8).add(findViewById(R.id.rs_dot259));
        RS.get(8).add(findViewById(R.id.rs_dot260));
        RS.get(8).add(findViewById(R.id.rs_dot261));
        RS.get(8).add(findViewById(R.id.rs_dot262));
        RS.get(8).add(findViewById(R.id.rs_dot263));
        RS.get(8).add(findViewById(R.id.rs_dot264));
        RS.get(8).add(findViewById(R.id.rs_dot265));
        RS.get(8).add(findViewById(R.id.rs_dot266));
        RS.get(8).add(findViewById(R.id.rs_dot267));
        RS.get(8).add(findViewById(R.id.rs_dot268));
        RS.get(8).add(findViewById(R.id.rs_dot269));
        RS.get(8).add(findViewById(R.id.rs_dot270));
        RS.get(8).add(findViewById(R.id.rs_dot271));
        RS.get(8).add(findViewById(R.id.rs_dot272));
        RS.get(8).add(findViewById(R.id.rs_dot273));
        RS.get(8).add(findViewById(R.id.rs_dot274));
        RS.get(8).add(findViewById(R.id.rs_dot275));
        RS.get(8).add(findViewById(R.id.rs_dot276));
        RS.get(8).add(findViewById(R.id.rs_dot277));
        RS.get(8).add(findViewById(R.id.rs_dot278));
        RS.get(8).add(findViewById(R.id.rs_dot279));
        RS.get(8).add(findViewById(R.id.rs_dot280));
        RS.get(8).add(findViewById(R.id.rs_dot281));
        RS.get(8).add(findViewById(R.id.rs_dot282));
        RS.get(8).add(findViewById(R.id.rs_dot283));
        RS.get(8).add(findViewById(R.id.rs_dot284));
        RS.get(9).add(findViewById(R.id.rs_dot285));
        RS.get(9).add(findViewById(R.id.rs_dot286));
        RS.get(9).add(findViewById(R.id.rs_dot287));
        RS.get(9).add(findViewById(R.id.rs_dot288));
        RS.get(9).add(findViewById(R.id.rs_dot289));
        RS.get(9).add(findViewById(R.id.rs_dot290));
        RS.get(9).add(findViewById(R.id.rs_dot291));
        RS.get(9).add(findViewById(R.id.rs_dot292));
        RS.get(9).add(findViewById(R.id.rs_dot293));
        RS.get(9).add(findViewById(R.id.rs_dot294));
        RS.get(9).add(findViewById(R.id.rs_dot295));
        RS.get(9).add(findViewById(R.id.rs_dot296));
        RS.get(9).add(findViewById(R.id.rs_dot297));
        RS.get(9).add(findViewById(R.id.rs_dot298));
        RS.get(9).add(findViewById(R.id.rs_dot299));
        RS.get(9).add(findViewById(R.id.rs_dot300));
        RS.get(9).add(findViewById(R.id.rs_dot301));
        RS.get(9).add(findViewById(R.id.rs_dot302));
        RS.get(9).add(findViewById(R.id.rs_dot303));
        RS.get(9).add(findViewById(R.id.rs_dot304));
        RS.get(9).add(findViewById(R.id.rs_dot305));
        RS.get(9).add(findViewById(R.id.rs_dot306));
        RS.get(9).add(findViewById(R.id.rs_dot307));
        RS.get(9).add(findViewById(R.id.rs_dot308));
        RS.get(9).add(findViewById(R.id.rs_dot309));
        RS.get(9).add(findViewById(R.id.rs_dot310));
        RS.get(9).add(findViewById(R.id.rs_dot311));
        RS.get(9).add(findViewById(R.id.rs_dot312));
        RS.get(9).add(findViewById(R.id.rs_dot313));
        RS.get(9).add(findViewById(R.id.rs_dot314));
        RS.get(9).add(findViewById(R.id.rs_dot315));
        RS.get(9).add(findViewById(R.id.rs_dot316));
        RS.get(9).add(findViewById(R.id.rs_dot317));
        RS.get(9).add(findViewById(R.id.rs_dot318));
        RS.get(9).add(findViewById(R.id.rs_dot319));
        RS.get(9).add(findViewById(R.id.rs_dot320));
        RS.get(9).add(findViewById(R.id.rs_dot321));
        RS.get(9).add(findViewById(R.id.rs_dot322));
        RS.get(9).add(findViewById(R.id.rs_dot323));
        RS.get(9).add(findViewById(R.id.rs_dot324));
        RS.get(9).add(findViewById(R.id.rs_dot325));
        RS.get(9).add(findViewById(R.id.rs_dot326));
        RS.get(9).add(findViewById(R.id.rs_dot327));
        RS.get(9).add(findViewById(R.id.rs_dot328));
        RS.get(10).add(findViewById(R.id.rs_dot329));
        RS.get(10).add(findViewById(R.id.rs_dot330));
        RS.get(10).add(findViewById(R.id.rs_dot331));
        RS.get(10).add(findViewById(R.id.rs_dot332));
        RS.get(10).add(findViewById(R.id.rs_dot333));
        RS.get(10).add(findViewById(R.id.rs_dot334));
        RS.get(10).add(findViewById(R.id.rs_dot335));
        RS.get(10).add(findViewById(R.id.rs_dot336));
        RS.get(10).add(findViewById(R.id.rs_dot337));
        RS.get(10).add(findViewById(R.id.rs_dot338));
        RS.get(10).add(findViewById(R.id.rs_dot339));
        RS.get(10).add(findViewById(R.id.rs_dot340));
        RS.get(10).add(findViewById(R.id.rs_dot341));
        RS.get(10).add(findViewById(R.id.rs_dot342));
        RS.get(10).add(findViewById(R.id.rs_dot343));
        RS.get(10).add(findViewById(R.id.rs_dot344));
        RS.get(10).add(findViewById(R.id.rs_dot345));
        RS.get(10).add(findViewById(R.id.rs_dot346));
        RS.get(10).add(findViewById(R.id.rs_dot347));
        RS.get(10).add(findViewById(R.id.rs_dot348));
        RS.get(10).add(findViewById(R.id.rs_dot349));
        RS.get(10).add(findViewById(R.id.rs_dot350));
        RS.get(10).add(findViewById(R.id.rs_dot351));
        RS.get(10).add(findViewById(R.id.rs_dot352));
        RS.get(10).add(findViewById(R.id.rs_dot353));
        RS.get(10).add(findViewById(R.id.rs_dot354));
        RS.get(10).add(findViewById(R.id.rs_dot355));
        RS.get(10).add(findViewById(R.id.rs_dot356));
        RS.get(10).add(findViewById(R.id.rs_dot357));
        RS.get(10).add(findViewById(R.id.rs_dot358));
        RS.get(10).add(findViewById(R.id.rs_dot359));
        RS.get(10).add(findViewById(R.id.rs_dot360));
        RS.get(10).add(findViewById(R.id.rs_dot361));
        RS.get(10).add(findViewById(R.id.rs_dot362));
        RS.get(10).add(findViewById(R.id.rs_dot363));
        RS.get(10).add(findViewById(R.id.rs_dot364));
        RS.get(10).add(findViewById(R.id.rs_dot365));
        RS.get(10).add(findViewById(R.id.rs_dot366));
        RS.get(10).add(findViewById(R.id.rs_dot367));
        RS.get(10).add(findViewById(R.id.rs_dot368));
        RS.get(10).add(findViewById(R.id.rs_dot369));
        RS.get(10).add(findViewById(R.id.rs_dot370));
        RS.get(11).add(findViewById(R.id.rs_dot371));
        RS.get(11).add(findViewById(R.id.rs_dot372));
        RS.get(11).add(findViewById(R.id.rs_dot373));
        RS.get(11).add(findViewById(R.id.rs_dot374));
        RS.get(11).add(findViewById(R.id.rs_dot375));
        RS.get(11).add(findViewById(R.id.rs_dot376));
        RS.get(11).add(findViewById(R.id.rs_dot377));
        RS.get(11).add(findViewById(R.id.rs_dot378));
        RS.get(11).add(findViewById(R.id.rs_dot379));
        RS.get(11).add(findViewById(R.id.rs_dot380));
        RS.get(11).add(findViewById(R.id.rs_dot381));
        RS.get(11).add(findViewById(R.id.rs_dot382));
        RS.get(11).add(findViewById(R.id.rs_dot383));
        RS.get(11).add(findViewById(R.id.rs_dot384));
        RS.get(11).add(findViewById(R.id.rs_dot385));
        RS.get(11).add(findViewById(R.id.rs_dot386));
        RS.get(11).add(findViewById(R.id.rs_dot387));
        RS.get(11).add(findViewById(R.id.rs_dot388));
        RS.get(11).add(findViewById(R.id.rs_dot389));
        RS.get(11).add(findViewById(R.id.rs_dot390));
        RS.get(11).add(findViewById(R.id.rs_dot391));
        RS.get(11).add(findViewById(R.id.rs_dot392));
        RS.get(11).add(findViewById(R.id.rs_dot393));
        RS.get(11).add(findViewById(R.id.rs_dot394));
        RS.get(11).add(findViewById(R.id.rs_dot395));
        RS.get(11).add(findViewById(R.id.rs_dot396));
        RS.get(11).add(findViewById(R.id.rs_dot397));
        RS.get(11).add(findViewById(R.id.rs_dot398));
        RS.get(11).add(findViewById(R.id.rs_dot399));
        RS.get(11).add(findViewById(R.id.rs_dot400));
        RS.get(11).add(findViewById(R.id.rs_dot401));
        RS.get(11).add(findViewById(R.id.rs_dot402));
        RS.get(11).add(findViewById(R.id.rs_dot403));
        RS.get(11).add(findViewById(R.id.rs_dot404));
        RS.get(11).add(findViewById(R.id.rs_dot405));
        RS.get(11).add(findViewById(R.id.rs_dot406));
        RS.get(11).add(findViewById(R.id.rs_dot407));
        RS.get(12).add(findViewById(R.id.rs_dot408));
        RS.get(12).add(findViewById(R.id.rs_dot409));
        RS.get(12).add(findViewById(R.id.rs_dot410));
        RS.get(12).add(findViewById(R.id.rs_dot411));
        RS.get(12).add(findViewById(R.id.rs_dot412));
        RS.get(12).add(findViewById(R.id.rs_dot413));
        RS.get(12).add(findViewById(R.id.rs_dot414));
        RS.get(12).add(findViewById(R.id.rs_dot415));
        RS.get(12).add(findViewById(R.id.rs_dot416));
        RS.get(12).add(findViewById(R.id.rs_dot417));
        RS.get(12).add(findViewById(R.id.rs_dot418));
        RS.get(12).add(findViewById(R.id.rs_dot419));
        RS.get(12).add(findViewById(R.id.rs_dot420));
        RS.get(12).add(findViewById(R.id.rs_dot421));
        RS.get(12).add(findViewById(R.id.rs_dot422));
        RS.get(12).add(findViewById(R.id.rs_dot423));
        RS.get(12).add(findViewById(R.id.rs_dot424));
        RS.get(12).add(findViewById(R.id.rs_dot425));
        RS.get(12).add(findViewById(R.id.rs_dot426));
        RS.get(12).add(findViewById(R.id.rs_dot427));
        RS.get(12).add(findViewById(R.id.rs_dot428));
        RS.get(12).add(findViewById(R.id.rs_dot429));
        RS.get(12).add(findViewById(R.id.rs_dot430));
        RS.get(12).add(findViewById(R.id.rs_dot431));
        RS.get(12).add(findViewById(R.id.rs_dot432));
        RS.get(12).add(findViewById(R.id.rs_dot433));
        RS.get(13).add(findViewById(R.id.rs_dot434));
        RS.get(13).add(findViewById(R.id.rs_dot435));
        RS.get(13).add(findViewById(R.id.rs_dot436));
        RS.get(13).add(findViewById(R.id.rs_dot437));
        RS.get(13).add(findViewById(R.id.rs_dot438));
        RS.get(13).add(findViewById(R.id.rs_dot439));
        RS.get(13).add(findViewById(R.id.rs_dot440));
        RS.get(13).add(findViewById(R.id.rs_dot441));
        RS.get(13).add(findViewById(R.id.rs_dot442));
        RS.get(13).add(findViewById(R.id.rs_dot443));
        RS.get(13).add(findViewById(R.id.rs_dot444));
        RS.get(13).add(findViewById(R.id.rs_dot445));
        RS.get(13).add(findViewById(R.id.rs_dot446));
        RS.get(13).add(findViewById(R.id.rs_dot447));
        RS.get(14).add(findViewById(R.id.rs_dot448));
        RS.get(14).add(findViewById(R.id.rs_dot449));
        RS.get(14).add(findViewById(R.id.rs_dot450));
        RS.get(14).add(findViewById(R.id.rs_dot451));
        RS.get(14).add(findViewById(R.id.rs_dot452));
        RS.get(14).add(findViewById(R.id.rs_dot453));
        RS.get(14).add(findViewById(R.id.rs_dot454));
        RS.get(14).add(findViewById(R.id.rs_dot455));
        RS.get(14).add(findViewById(R.id.rs_dot456));
        RS.get(15).add(findViewById(R.id.rs_dot457));
        RS.get(15).add(findViewById(R.id.rs_dot458));
        RS.get(15).add(findViewById(R.id.rs_dot459));
        RS.get(15).add(findViewById(R.id.rs_dot460));
        RS.get(15).add(findViewById(R.id.rs_dot461));
        RS.get(15).add(findViewById(R.id.rs_dot462));
        RS.get(15).add(findViewById(R.id.rs_dot463));
        RS.get(15).add(findViewById(R.id.rs_dot464));
        RS.get(16).add(findViewById(R.id.rs_dot465));
        RS.get(16).add(findViewById(R.id.rs_dot466));
        RS.get(16).add(findViewById(R.id.rs_dot467));
        RS.get(16).add(findViewById(R.id.rs_dot468));
        RS.get(16).add(findViewById(R.id.rs_dot469));
        RS.get(16).add(findViewById(R.id.rs_dot470));
        RS.get(16).add(findViewById(R.id.rs_dot471));
        RS.get(17).add(findViewById(R.id.rs_dot472));
        RS.get(17).add(findViewById(R.id.rs_dot473));
        RS.get(18).add(findViewById(R.id.rs_dot474));

    }
    public void buildME(){
        for(int i = 0; i<15;i++) {
            ME.add(new ArrayList<View>());
        }

        ME.get(0).add(findViewById(R.id.me_dot0));
        ME.get(0).add(findViewById(R.id.me_dot1));
        ME.get(1).add(findViewById(R.id.me_dot2));
        ME.get(1).add(findViewById(R.id.me_dot3));
        ME.get(1).add(findViewById(R.id.me_dot4));
        ME.get(1).add(findViewById(R.id.me_dot5));
        ME.get(1).add(findViewById(R.id.me_dot6));
        ME.get(1).add(findViewById(R.id.me_dot7));
        ME.get(1).add(findViewById(R.id.me_dot8));
        ME.get(1).add(findViewById(R.id.me_dot9));
        ME.get(1).add(findViewById(R.id.me_dot10));
        ME.get(1).add(findViewById(R.id.me_dot11));
        ME.get(2).add(findViewById(R.id.me_dot12));
        ME.get(2).add(findViewById(R.id.me_dot13));
        ME.get(2).add(findViewById(R.id.me_dot14));
        ME.get(2).add(findViewById(R.id.me_dot15));
        ME.get(2).add(findViewById(R.id.me_dot16));
        ME.get(2).add(findViewById(R.id.me_dot17));
        ME.get(2).add(findViewById(R.id.me_dot18));
        ME.get(2).add(findViewById(R.id.me_dot19));
        ME.get(2).add(findViewById(R.id.me_dot20));
        ME.get(2).add(findViewById(R.id.me_dot21));
        ME.get(2).add(findViewById(R.id.me_dot22));
        ME.get(2).add(findViewById(R.id.me_dot23));
        ME.get(2).add(findViewById(R.id.me_dot24));
        ME.get(2).add(findViewById(R.id.me_dot25));
        ME.get(2).add(findViewById(R.id.me_dot26));
        ME.get(3).add(findViewById(R.id.me_dot27));
        ME.get(3).add(findViewById(R.id.me_dot28));
        ME.get(3).add(findViewById(R.id.me_dot29));
        ME.get(3).add(findViewById(R.id.me_dot30));
        ME.get(3).add(findViewById(R.id.me_dot31));
        ME.get(3).add(findViewById(R.id.me_dot32));
        ME.get(3).add(findViewById(R.id.me_dot33));
        ME.get(3).add(findViewById(R.id.me_dot34));
        ME.get(3).add(findViewById(R.id.me_dot35));
        ME.get(3).add(findViewById(R.id.me_dot36));
        ME.get(3).add(findViewById(R.id.me_dot37));
        ME.get(3).add(findViewById(R.id.me_dot38));
        ME.get(4).add(findViewById(R.id.me_dot39));
        ME.get(4).add(findViewById(R.id.me_dot40));
        ME.get(4).add(findViewById(R.id.me_dot41));
        ME.get(4).add(findViewById(R.id.me_dot42));
        ME.get(4).add(findViewById(R.id.me_dot43));
        ME.get(4).add(findViewById(R.id.me_dot44));
        ME.get(4).add(findViewById(R.id.me_dot45));
        ME.get(4).add(findViewById(R.id.me_dot46));
        ME.get(4).add(findViewById(R.id.me_dot47));
        ME.get(4).add(findViewById(R.id.me_dot48));
        ME.get(4).add(findViewById(R.id.me_dot49));
        ME.get(4).add(findViewById(R.id.me_dot50));
        ME.get(5).add(findViewById(R.id.me_dot51));
        ME.get(5).add(findViewById(R.id.me_dot52));
        ME.get(5).add(findViewById(R.id.me_dot53));
        ME.get(5).add(findViewById(R.id.me_dot54));
        ME.get(5).add(findViewById(R.id.me_dot55));
        ME.get(5).add(findViewById(R.id.me_dot56));
        ME.get(5).add(findViewById(R.id.me_dot57));
        ME.get(5).add(findViewById(R.id.me_dot58));
        ME.get(5).add(findViewById(R.id.me_dot59));
        ME.get(5).add(findViewById(R.id.me_dot60));
        ME.get(5).add(findViewById(R.id.me_dot61));
        ME.get(5).add(findViewById(R.id.me_dot62));
        ME.get(6).add(findViewById(R.id.me_dot63));
        ME.get(6).add(findViewById(R.id.me_dot64));
        ME.get(6).add(findViewById(R.id.me_dot65));
        ME.get(6).add(findViewById(R.id.me_dot66));
        ME.get(6).add(findViewById(R.id.me_dot67));
        ME.get(6).add(findViewById(R.id.me_dot68));
        ME.get(6).add(findViewById(R.id.me_dot69));
        ME.get(6).add(findViewById(R.id.me_dot70));
        ME.get(6).add(findViewById(R.id.me_dot71));
        ME.get(6).add(findViewById(R.id.me_dot72));
        ME.get(6).add(findViewById(R.id.me_dot73));
        ME.get(6).add(findViewById(R.id.me_dot74));
        ME.get(6).add(findViewById(R.id.me_dot75));
        ME.get(6).add(findViewById(R.id.me_dot76));
        ME.get(6).add(findViewById(R.id.me_dot77));
        ME.get(6).add(findViewById(R.id.me_dot78));
        ME.get(6).add(findViewById(R.id.me_dot79));
        ME.get(7).add(findViewById(R.id.me_dot80));
        ME.get(7).add(findViewById(R.id.me_dot81));
        ME.get(7).add(findViewById(R.id.me_dot82));
        ME.get(7).add(findViewById(R.id.me_dot83));
        ME.get(7).add(findViewById(R.id.me_dot84));
        ME.get(7).add(findViewById(R.id.me_dot85));
        ME.get(7).add(findViewById(R.id.me_dot86));
        ME.get(7).add(findViewById(R.id.me_dot87));
        ME.get(7).add(findViewById(R.id.me_dot88));
        ME.get(7).add(findViewById(R.id.me_dot89));
        ME.get(7).add(findViewById(R.id.me_dot90));
        ME.get(7).add(findViewById(R.id.me_dot91));
        ME.get(7).add(findViewById(R.id.me_dot92));
        ME.get(7).add(findViewById(R.id.me_dot93));
        ME.get(7).add(findViewById(R.id.me_dot94));
        ME.get(7).add(findViewById(R.id.me_dot95));
        ME.get(8).add(findViewById(R.id.me_dot96));
        ME.get(8).add(findViewById(R.id.me_dot97));
        ME.get(8).add(findViewById(R.id.me_dot98));
        ME.get(8).add(findViewById(R.id.me_dot99));
        ME.get(8).add(findViewById(R.id.me_dot100));
        ME.get(8).add(findViewById(R.id.me_dot101));
        ME.get(8).add(findViewById(R.id.me_dot102));
        ME.get(8).add(findViewById(R.id.me_dot103));
        ME.get(8).add(findViewById(R.id.me_dot104));
        ME.get(8).add(findViewById(R.id.me_dot105));
        ME.get(8).add(findViewById(R.id.me_dot106));
        ME.get(8).add(findViewById(R.id.me_dot107));
        ME.get(8).add(findViewById(R.id.me_dot108));
        ME.get(8).add(findViewById(R.id.me_dot109));
        ME.get(8).add(findViewById(R.id.me_dot110));
        ME.get(9).add(findViewById(R.id.me_dot111));
        ME.get(9).add(findViewById(R.id.me_dot112));
        ME.get(9).add(findViewById(R.id.me_dot113));
        ME.get(9).add(findViewById(R.id.me_dot114));
        ME.get(9).add(findViewById(R.id.me_dot115));
        ME.get(9).add(findViewById(R.id.me_dot116));
        ME.get(9).add(findViewById(R.id.me_dot117));
        ME.get(9).add(findViewById(R.id.me_dot118));
        ME.get(9).add(findViewById(R.id.me_dot119));
        ME.get(9).add(findViewById(R.id.me_dot120));
        ME.get(9).add(findViewById(R.id.me_dot121));
        ME.get(9).add(findViewById(R.id.me_dot122));
        ME.get(9).add(findViewById(R.id.me_dot123));
        ME.get(9).add(findViewById(R.id.me_dot124));
        ME.get(9).add(findViewById(R.id.me_dot125));
        ME.get(10).add(findViewById(R.id.me_dot126));
        ME.get(10).add(findViewById(R.id.me_dot127));
        ME.get(10).add(findViewById(R.id.me_dot128));
        ME.get(10).add(findViewById(R.id.me_dot129));
        ME.get(10).add(findViewById(R.id.me_dot130));
        ME.get(10).add(findViewById(R.id.me_dot131));
        ME.get(10).add(findViewById(R.id.me_dot132));
        ME.get(10).add(findViewById(R.id.me_dot133));
        ME.get(10).add(findViewById(R.id.me_dot134));
        ME.get(10).add(findViewById(R.id.me_dot135));
        ME.get(10).add(findViewById(R.id.me_dot136));
        ME.get(10).add(findViewById(R.id.me_dot137));
        ME.get(10).add(findViewById(R.id.me_dot138));
        ME.get(10).add(findViewById(R.id.me_dot139));
        ME.get(10).add(findViewById(R.id.me_dot140));
        ME.get(11).add(findViewById(R.id.me_dot141));
        ME.get(11).add(findViewById(R.id.me_dot142));
        ME.get(11).add(findViewById(R.id.me_dot143));
        ME.get(11).add(findViewById(R.id.me_dot144));
        ME.get(11).add(findViewById(R.id.me_dot145));
        ME.get(11).add(findViewById(R.id.me_dot146));
        ME.get(11).add(findViewById(R.id.me_dot147));
        ME.get(11).add(findViewById(R.id.me_dot148));
        ME.get(11).add(findViewById(R.id.me_dot149));
        ME.get(12).add(findViewById(R.id.me_dot150));
        ME.get(12).add(findViewById(R.id.me_dot151));
        ME.get(12).add(findViewById(R.id.me_dot152));
        ME.get(12).add(findViewById(R.id.me_dot153));
        ME.get(12).add(findViewById(R.id.me_dot154));
        ME.get(12).add(findViewById(R.id.me_dot155));
        ME.get(12).add(findViewById(R.id.me_dot156));
        ME.get(13).add(findViewById(R.id.me_dot157));
        ME.get(13).add(findViewById(R.id.me_dot158));
        ME.get(13).add(findViewById(R.id.me_dot159));
        ME.get(13).add(findViewById(R.id.me_dot160));
        ME.get(13).add(findViewById(R.id.me_dot161));
        ME.get(14).add(findViewById(R.id.me_dot162));
        ME.get(14).add(findViewById(R.id.me_dot163));

    }
    public void buildAS(){
        for(int i = 0; i<34;i++) {
            AS.add(new ArrayList<View>());
        }

        AS.get(0).add(findViewById(R.id.as_dot0));
        AS.get(1).add(findViewById(R.id.as_dot1));
        AS.get(1).add(findViewById(R.id.as_dot2));
        AS.get(1).add(findViewById(R.id.as_dot3));
        AS.get(1).add(findViewById(R.id.as_dot4));
        AS.get(1).add(findViewById(R.id.as_dot5));
        AS.get(1).add(findViewById(R.id.as_dot6));
        AS.get(1).add(findViewById(R.id.as_dot7));
        AS.get(2).add(findViewById(R.id.as_dot8));
        AS.get(2).add(findViewById(R.id.as_dot9));
        AS.get(2).add(findViewById(R.id.as_dot10));
        AS.get(2).add(findViewById(R.id.as_dot11));
        AS.get(2).add(findViewById(R.id.as_dot12));
        AS.get(2).add(findViewById(R.id.as_dot13));
        AS.get(2).add(findViewById(R.id.as_dot14));
        AS.get(2).add(findViewById(R.id.as_dot15));
        AS.get(2).add(findViewById(R.id.as_dot16));
        AS.get(2).add(findViewById(R.id.as_dot17));
        AS.get(2).add(findViewById(R.id.as_dot18));
        AS.get(2).add(findViewById(R.id.as_dot19));
        AS.get(2).add(findViewById(R.id.as_dot20));
        AS.get(2).add(findViewById(R.id.as_dot21));
        AS.get(2).add(findViewById(R.id.as_dot22));
        AS.get(2).add(findViewById(R.id.as_dot23));
        AS.get(3).add(findViewById(R.id.as_dot24));
        AS.get(3).add(findViewById(R.id.as_dot25));
        AS.get(3).add(findViewById(R.id.as_dot26));
        AS.get(3).add(findViewById(R.id.as_dot27));
        AS.get(3).add(findViewById(R.id.as_dot28));
        AS.get(3).add(findViewById(R.id.as_dot29));
        AS.get(3).add(findViewById(R.id.as_dot30));
        AS.get(3).add(findViewById(R.id.as_dot31));
        AS.get(3).add(findViewById(R.id.as_dot32));
        AS.get(3).add(findViewById(R.id.as_dot33));
        AS.get(3).add(findViewById(R.id.as_dot34));
        AS.get(3).add(findViewById(R.id.as_dot35));
        AS.get(3).add(findViewById(R.id.as_dot36));
        AS.get(3).add(findViewById(R.id.as_dot37));
        AS.get(3).add(findViewById(R.id.as_dot38));
        AS.get(3).add(findViewById(R.id.as_dot39));
        AS.get(3).add(findViewById(R.id.as_dot40));
        AS.get(3).add(findViewById(R.id.as_dot41));
        AS.get(3).add(findViewById(R.id.as_dot42));
        AS.get(3).add(findViewById(R.id.as_dot43));
        AS.get(3).add(findViewById(R.id.as_dot44));
        AS.get(3).add(findViewById(R.id.as_dot45));
        AS.get(3).add(findViewById(R.id.as_dot46));
        AS.get(3).add(findViewById(R.id.as_dot47));
        AS.get(3).add(findViewById(R.id.as_dot48));
        AS.get(3).add(findViewById(R.id.as_dot49));
        AS.get(3).add(findViewById(R.id.as_dot50));
        AS.get(3).add(findViewById(R.id.as_dot51));
        AS.get(4).add(findViewById(R.id.as_dot52));
        AS.get(4).add(findViewById(R.id.as_dot53));
        AS.get(4).add(findViewById(R.id.as_dot54));
        AS.get(4).add(findViewById(R.id.as_dot55));
        AS.get(4).add(findViewById(R.id.as_dot56));
        AS.get(4).add(findViewById(R.id.as_dot57));
        AS.get(4).add(findViewById(R.id.as_dot58));
        AS.get(4).add(findViewById(R.id.as_dot59));
        AS.get(4).add(findViewById(R.id.as_dot60));
        AS.get(4).add(findViewById(R.id.as_dot61));
        AS.get(4).add(findViewById(R.id.as_dot62));
        AS.get(4).add(findViewById(R.id.as_dot63));
        AS.get(4).add(findViewById(R.id.as_dot64));
        AS.get(4).add(findViewById(R.id.as_dot65));
        AS.get(4).add(findViewById(R.id.as_dot66));
        AS.get(4).add(findViewById(R.id.as_dot67));
        AS.get(4).add(findViewById(R.id.as_dot68));
        AS.get(4).add(findViewById(R.id.as_dot69));
        AS.get(4).add(findViewById(R.id.as_dot70));
        AS.get(4).add(findViewById(R.id.as_dot71));
        AS.get(4).add(findViewById(R.id.as_dot72));
        AS.get(4).add(findViewById(R.id.as_dot73));
        AS.get(4).add(findViewById(R.id.as_dot74));
        AS.get(4).add(findViewById(R.id.as_dot75));
        AS.get(4).add(findViewById(R.id.as_dot76));
        AS.get(4).add(findViewById(R.id.as_dot77));
        AS.get(4).add(findViewById(R.id.as_dot78));
        AS.get(4).add(findViewById(R.id.as_dot79));
        AS.get(4).add(findViewById(R.id.as_dot80));
        AS.get(4).add(findViewById(R.id.as_dot81));
        AS.get(4).add(findViewById(R.id.as_dot82));
        AS.get(4).add(findViewById(R.id.as_dot83));
        AS.get(5).add(findViewById(R.id.as_dot84));
        AS.get(5).add(findViewById(R.id.as_dot85));
        AS.get(5).add(findViewById(R.id.as_dot86));
        AS.get(5).add(findViewById(R.id.as_dot87));
        AS.get(5).add(findViewById(R.id.as_dot88));
        AS.get(5).add(findViewById(R.id.as_dot89));
        AS.get(5).add(findViewById(R.id.as_dot90));
        AS.get(5).add(findViewById(R.id.as_dot91));
        AS.get(5).add(findViewById(R.id.as_dot92));
        AS.get(5).add(findViewById(R.id.as_dot93));
        AS.get(5).add(findViewById(R.id.as_dot94));
        AS.get(5).add(findViewById(R.id.as_dot95));
        AS.get(5).add(findViewById(R.id.as_dot96));
        AS.get(5).add(findViewById(R.id.as_dot97));
        AS.get(5).add(findViewById(R.id.as_dot98));
        AS.get(5).add(findViewById(R.id.as_dot99));
        AS.get(5).add(findViewById(R.id.as_dot100));
        AS.get(5).add(findViewById(R.id.as_dot101));
        AS.get(5).add(findViewById(R.id.as_dot102));
        AS.get(5).add(findViewById(R.id.as_dot103));
        AS.get(5).add(findViewById(R.id.as_dot104));
        AS.get(5).add(findViewById(R.id.as_dot105));
        AS.get(5).add(findViewById(R.id.as_dot106));
        AS.get(5).add(findViewById(R.id.as_dot107));
        AS.get(5).add(findViewById(R.id.as_dot108));
        AS.get(5).add(findViewById(R.id.as_dot109));
        AS.get(5).add(findViewById(R.id.as_dot110));
        AS.get(5).add(findViewById(R.id.as_dot111));
        AS.get(5).add(findViewById(R.id.as_dot112));
        AS.get(5).add(findViewById(R.id.as_dot113));
        AS.get(5).add(findViewById(R.id.as_dot114));
        AS.get(5).add(findViewById(R.id.as_dot115));
        AS.get(5).add(findViewById(R.id.as_dot116));
        AS.get(6).add(findViewById(R.id.as_dot117));
        AS.get(6).add(findViewById(R.id.as_dot118));
        AS.get(6).add(findViewById(R.id.as_dot119));
        AS.get(6).add(findViewById(R.id.as_dot120));
        AS.get(6).add(findViewById(R.id.as_dot121));
        AS.get(6).add(findViewById(R.id.as_dot122));
        AS.get(6).add(findViewById(R.id.as_dot123));
        AS.get(6).add(findViewById(R.id.as_dot124));
        AS.get(6).add(findViewById(R.id.as_dot125));
        AS.get(6).add(findViewById(R.id.as_dot126));
        AS.get(6).add(findViewById(R.id.as_dot127));
        AS.get(6).add(findViewById(R.id.as_dot128));
        AS.get(6).add(findViewById(R.id.as_dot129));
        AS.get(6).add(findViewById(R.id.as_dot130));
        AS.get(6).add(findViewById(R.id.as_dot131));
        AS.get(6).add(findViewById(R.id.as_dot132));
        AS.get(6).add(findViewById(R.id.as_dot133));
        AS.get(6).add(findViewById(R.id.as_dot134));
        AS.get(6).add(findViewById(R.id.as_dot135));
        AS.get(6).add(findViewById(R.id.as_dot136));
        AS.get(6).add(findViewById(R.id.as_dot137));
        AS.get(6).add(findViewById(R.id.as_dot138));
        AS.get(6).add(findViewById(R.id.as_dot139));
        AS.get(6).add(findViewById(R.id.as_dot140));
        AS.get(6).add(findViewById(R.id.as_dot141));
        AS.get(6).add(findViewById(R.id.as_dot142));
        AS.get(6).add(findViewById(R.id.as_dot143));
        AS.get(6).add(findViewById(R.id.as_dot144));
        AS.get(6).add(findViewById(R.id.as_dot145));
        AS.get(6).add(findViewById(R.id.as_dot146));
        AS.get(6).add(findViewById(R.id.as_dot147));
        AS.get(6).add(findViewById(R.id.as_dot148));
        AS.get(6).add(findViewById(R.id.as_dot149));
        AS.get(6).add(findViewById(R.id.as_dot150));
        AS.get(6).add(findViewById(R.id.as_dot151));
        AS.get(7).add(findViewById(R.id.as_dot152));
        AS.get(7).add(findViewById(R.id.as_dot153));
        AS.get(7).add(findViewById(R.id.as_dot154));
        AS.get(7).add(findViewById(R.id.as_dot155));
        AS.get(7).add(findViewById(R.id.as_dot156));
        AS.get(7).add(findViewById(R.id.as_dot157));
        AS.get(7).add(findViewById(R.id.as_dot158));
        AS.get(7).add(findViewById(R.id.as_dot159));
        AS.get(7).add(findViewById(R.id.as_dot160));
        AS.get(7).add(findViewById(R.id.as_dot161));
        AS.get(7).add(findViewById(R.id.as_dot162));
        AS.get(7).add(findViewById(R.id.as_dot163));
        AS.get(7).add(findViewById(R.id.as_dot164));
        AS.get(7).add(findViewById(R.id.as_dot165));
        AS.get(7).add(findViewById(R.id.as_dot166));
        AS.get(7).add(findViewById(R.id.as_dot167));
        AS.get(7).add(findViewById(R.id.as_dot168));
        AS.get(7).add(findViewById(R.id.as_dot169));
        AS.get(7).add(findViewById(R.id.as_dot170));
        AS.get(7).add(findViewById(R.id.as_dot171));
        AS.get(7).add(findViewById(R.id.as_dot172));
        AS.get(7).add(findViewById(R.id.as_dot173));
        AS.get(7).add(findViewById(R.id.as_dot174));
        AS.get(7).add(findViewById(R.id.as_dot175));
        AS.get(7).add(findViewById(R.id.as_dot176));
        AS.get(7).add(findViewById(R.id.as_dot177));
        AS.get(7).add(findViewById(R.id.as_dot178));
        AS.get(7).add(findViewById(R.id.as_dot179));
        AS.get(7).add(findViewById(R.id.as_dot180));
        AS.get(7).add(findViewById(R.id.as_dot181));
        AS.get(7).add(findViewById(R.id.as_dot182));
        AS.get(7).add(findViewById(R.id.as_dot183));
        AS.get(7).add(findViewById(R.id.as_dot184));
        AS.get(7).add(findViewById(R.id.as_dot185));
        AS.get(7).add(findViewById(R.id.as_dot186));
        AS.get(7).add(findViewById(R.id.as_dot187));
        AS.get(8).add(findViewById(R.id.as_dot188));
        AS.get(8).add(findViewById(R.id.as_dot189));
        AS.get(8).add(findViewById(R.id.as_dot190));
        AS.get(8).add(findViewById(R.id.as_dot191));
        AS.get(8).add(findViewById(R.id.as_dot192));
        AS.get(8).add(findViewById(R.id.as_dot193));
        AS.get(8).add(findViewById(R.id.as_dot194));
        AS.get(8).add(findViewById(R.id.as_dot195));
        AS.get(8).add(findViewById(R.id.as_dot196));
        AS.get(8).add(findViewById(R.id.as_dot197));
        AS.get(8).add(findViewById(R.id.as_dot198));
        AS.get(8).add(findViewById(R.id.as_dot199));
        AS.get(8).add(findViewById(R.id.as_dot200));
        AS.get(8).add(findViewById(R.id.as_dot201));
        AS.get(8).add(findViewById(R.id.as_dot202));
        AS.get(8).add(findViewById(R.id.as_dot203));
        AS.get(8).add(findViewById(R.id.as_dot204));
        AS.get(8).add(findViewById(R.id.as_dot205));
        AS.get(8).add(findViewById(R.id.as_dot206));
        AS.get(8).add(findViewById(R.id.as_dot207));
        AS.get(8).add(findViewById(R.id.as_dot208));
        AS.get(8).add(findViewById(R.id.as_dot209));
        AS.get(8).add(findViewById(R.id.as_dot210));
        AS.get(8).add(findViewById(R.id.as_dot211));
        AS.get(8).add(findViewById(R.id.as_dot212));
        AS.get(8).add(findViewById(R.id.as_dot213));
        AS.get(8).add(findViewById(R.id.as_dot214));
        AS.get(8).add(findViewById(R.id.as_dot215));
        AS.get(8).add(findViewById(R.id.as_dot216));
        AS.get(8).add(findViewById(R.id.as_dot217));
        AS.get(8).add(findViewById(R.id.as_dot218));
        AS.get(8).add(findViewById(R.id.as_dot219));
        AS.get(9).add(findViewById(R.id.as_dot220));
        AS.get(9).add(findViewById(R.id.as_dot221));
        AS.get(9).add(findViewById(R.id.as_dot222));
        AS.get(9).add(findViewById(R.id.as_dot223));
        AS.get(9).add(findViewById(R.id.as_dot224));
        AS.get(9).add(findViewById(R.id.as_dot225));
        AS.get(9).add(findViewById(R.id.as_dot226));
        AS.get(9).add(findViewById(R.id.as_dot227));
        AS.get(9).add(findViewById(R.id.as_dot228));
        AS.get(9).add(findViewById(R.id.as_dot229));
        AS.get(9).add(findViewById(R.id.as_dot230));
        AS.get(9).add(findViewById(R.id.as_dot231));
        AS.get(9).add(findViewById(R.id.as_dot232));
        AS.get(9).add(findViewById(R.id.as_dot233));
        AS.get(9).add(findViewById(R.id.as_dot234));
        AS.get(9).add(findViewById(R.id.as_dot235));
        AS.get(9).add(findViewById(R.id.as_dot236));
        AS.get(9).add(findViewById(R.id.as_dot237));
        AS.get(9).add(findViewById(R.id.as_dot238));
        AS.get(9).add(findViewById(R.id.as_dot239));
        AS.get(9).add(findViewById(R.id.as_dot240));
        AS.get(9).add(findViewById(R.id.as_dot241));
        AS.get(9).add(findViewById(R.id.as_dot242));
        AS.get(9).add(findViewById(R.id.as_dot243));
        AS.get(9).add(findViewById(R.id.as_dot244));
        AS.get(9).add(findViewById(R.id.as_dot245));
        AS.get(9).add(findViewById(R.id.as_dot246));
        AS.get(9).add(findViewById(R.id.as_dot247));
        AS.get(9).add(findViewById(R.id.as_dot248));
        AS.get(10).add(findViewById(R.id.as_dot249));
        AS.get(10).add(findViewById(R.id.as_dot250));
        AS.get(10).add(findViewById(R.id.as_dot251));
        AS.get(10).add(findViewById(R.id.as_dot252));
        AS.get(10).add(findViewById(R.id.as_dot253));
        AS.get(10).add(findViewById(R.id.as_dot254));
        AS.get(10).add(findViewById(R.id.as_dot255));
        AS.get(10).add(findViewById(R.id.as_dot256));
        AS.get(10).add(findViewById(R.id.as_dot257));
        AS.get(10).add(findViewById(R.id.as_dot258));
        AS.get(10).add(findViewById(R.id.as_dot259));
        AS.get(10).add(findViewById(R.id.as_dot260));
        AS.get(10).add(findViewById(R.id.as_dot261));
        AS.get(10).add(findViewById(R.id.as_dot262));
        AS.get(10).add(findViewById(R.id.as_dot263));
        AS.get(10).add(findViewById(R.id.as_dot264));
        AS.get(10).add(findViewById(R.id.as_dot265));
        AS.get(10).add(findViewById(R.id.as_dot266));
        AS.get(10).add(findViewById(R.id.as_dot267));
        AS.get(10).add(findViewById(R.id.as_dot268));
        AS.get(10).add(findViewById(R.id.as_dot269));
        AS.get(10).add(findViewById(R.id.as_dot270));
        AS.get(10).add(findViewById(R.id.as_dot271));
        AS.get(10).add(findViewById(R.id.as_dot272));
        AS.get(10).add(findViewById(R.id.as_dot273));
        AS.get(10).add(findViewById(R.id.as_dot274));
        AS.get(10).add(findViewById(R.id.as_dot275));
        AS.get(10).add(findViewById(R.id.as_dot276));
        AS.get(10).add(findViewById(R.id.as_dot277));
        AS.get(10).add(findViewById(R.id.as_dot278));
        AS.get(11).add(findViewById(R.id.as_dot279));
        AS.get(11).add(findViewById(R.id.as_dot280));
        AS.get(11).add(findViewById(R.id.as_dot281));
        AS.get(11).add(findViewById(R.id.as_dot282));
        AS.get(11).add(findViewById(R.id.as_dot283));
        AS.get(11).add(findViewById(R.id.as_dot284));
        AS.get(11).add(findViewById(R.id.as_dot285));
        AS.get(11).add(findViewById(R.id.as_dot286));
        AS.get(11).add(findViewById(R.id.as_dot287));
        AS.get(11).add(findViewById(R.id.as_dot288));
        AS.get(11).add(findViewById(R.id.as_dot289));
        AS.get(11).add(findViewById(R.id.as_dot290));
        AS.get(11).add(findViewById(R.id.as_dot291));
        AS.get(11).add(findViewById(R.id.as_dot292));
        AS.get(11).add(findViewById(R.id.as_dot293));
        AS.get(11).add(findViewById(R.id.as_dot294));
        AS.get(11).add(findViewById(R.id.as_dot295));
        AS.get(11).add(findViewById(R.id.as_dot296));
        AS.get(11).add(findViewById(R.id.as_dot297));
        AS.get(11).add(findViewById(R.id.as_dot298));
        AS.get(11).add(findViewById(R.id.as_dot299));
        AS.get(11).add(findViewById(R.id.as_dot300));
        AS.get(11).add(findViewById(R.id.as_dot301));
        AS.get(11).add(findViewById(R.id.as_dot302));
        AS.get(11).add(findViewById(R.id.as_dot303));
        AS.get(12).add(findViewById(R.id.as_dot304));
        AS.get(12).add(findViewById(R.id.as_dot305));
        AS.get(12).add(findViewById(R.id.as_dot306));
        AS.get(12).add(findViewById(R.id.as_dot307));
        AS.get(12).add(findViewById(R.id.as_dot308));
        AS.get(12).add(findViewById(R.id.as_dot309));
        AS.get(12).add(findViewById(R.id.as_dot310));
        AS.get(12).add(findViewById(R.id.as_dot311));
        AS.get(12).add(findViewById(R.id.as_dot312));
        AS.get(12).add(findViewById(R.id.as_dot313));
        AS.get(12).add(findViewById(R.id.as_dot314));
        AS.get(12).add(findViewById(R.id.as_dot315));
        AS.get(12).add(findViewById(R.id.as_dot316));
        AS.get(12).add(findViewById(R.id.as_dot317));
        AS.get(12).add(findViewById(R.id.as_dot318));
        AS.get(12).add(findViewById(R.id.as_dot319));
        AS.get(12).add(findViewById(R.id.as_dot320));
        AS.get(12).add(findViewById(R.id.as_dot321));
        AS.get(12).add(findViewById(R.id.as_dot322));
        AS.get(12).add(findViewById(R.id.as_dot323));
        AS.get(12).add(findViewById(R.id.as_dot324));
        AS.get(12).add(findViewById(R.id.as_dot325));
        AS.get(12).add(findViewById(R.id.as_dot326));
        AS.get(12).add(findViewById(R.id.as_dot327));
        AS.get(12).add(findViewById(R.id.as_dot328));
        AS.get(12).add(findViewById(R.id.as_dot329));
        AS.get(13).add(findViewById(R.id.as_dot330));
        AS.get(13).add(findViewById(R.id.as_dot331));
        AS.get(13).add(findViewById(R.id.as_dot332));
        AS.get(13).add(findViewById(R.id.as_dot333));
        AS.get(13).add(findViewById(R.id.as_dot334));
        AS.get(13).add(findViewById(R.id.as_dot335));
        AS.get(13).add(findViewById(R.id.as_dot336));
        AS.get(13).add(findViewById(R.id.as_dot337));
        AS.get(13).add(findViewById(R.id.as_dot338));
        AS.get(13).add(findViewById(R.id.as_dot339));
        AS.get(13).add(findViewById(R.id.as_dot340));
        AS.get(13).add(findViewById(R.id.as_dot341));
        AS.get(13).add(findViewById(R.id.as_dot342));
        AS.get(13).add(findViewById(R.id.as_dot343));
        AS.get(13).add(findViewById(R.id.as_dot344));
        AS.get(13).add(findViewById(R.id.as_dot345));
        AS.get(13).add(findViewById(R.id.as_dot346));
        AS.get(13).add(findViewById(R.id.as_dot347));
        AS.get(13).add(findViewById(R.id.as_dot348));
        AS.get(13).add(findViewById(R.id.as_dot349));
        AS.get(13).add(findViewById(R.id.as_dot350));
        AS.get(13).add(findViewById(R.id.as_dot351));
        AS.get(13).add(findViewById(R.id.as_dot352));
        AS.get(13).add(findViewById(R.id.as_dot353));
        AS.get(13).add(findViewById(R.id.as_dot354));
        AS.get(13).add(findViewById(R.id.as_dot355));
        AS.get(14).add(findViewById(R.id.as_dot356));
        AS.get(14).add(findViewById(R.id.as_dot357));
        AS.get(14).add(findViewById(R.id.as_dot358));
        AS.get(14).add(findViewById(R.id.as_dot359));
        AS.get(14).add(findViewById(R.id.as_dot360));
        AS.get(14).add(findViewById(R.id.as_dot361));
        AS.get(14).add(findViewById(R.id.as_dot362));
        AS.get(14).add(findViewById(R.id.as_dot363));
        AS.get(14).add(findViewById(R.id.as_dot364));
        AS.get(14).add(findViewById(R.id.as_dot365));
        AS.get(14).add(findViewById(R.id.as_dot366));
        AS.get(14).add(findViewById(R.id.as_dot367));
        AS.get(14).add(findViewById(R.id.as_dot368));
        AS.get(14).add(findViewById(R.id.as_dot369));
        AS.get(14).add(findViewById(R.id.as_dot370));
        AS.get(14).add(findViewById(R.id.as_dot371));
        AS.get(14).add(findViewById(R.id.as_dot372));
        AS.get(14).add(findViewById(R.id.as_dot373));
        AS.get(14).add(findViewById(R.id.as_dot374));
        AS.get(14).add(findViewById(R.id.as_dot375));
        AS.get(14).add(findViewById(R.id.as_dot376));
        AS.get(14).add(findViewById(R.id.as_dot377));
        AS.get(14).add(findViewById(R.id.as_dot378));
        AS.get(14).add(findViewById(R.id.as_dot379));
        AS.get(14).add(findViewById(R.id.as_dot380));
        AS.get(15).add(findViewById(R.id.as_dot381));
        AS.get(15).add(findViewById(R.id.as_dot382));
        AS.get(15).add(findViewById(R.id.as_dot383));
        AS.get(15).add(findViewById(R.id.as_dot384));
        AS.get(15).add(findViewById(R.id.as_dot385));
        AS.get(15).add(findViewById(R.id.as_dot386));
        AS.get(15).add(findViewById(R.id.as_dot387));
        AS.get(15).add(findViewById(R.id.as_dot388));
        AS.get(15).add(findViewById(R.id.as_dot389));
        AS.get(15).add(findViewById(R.id.as_dot390));
        AS.get(15).add(findViewById(R.id.as_dot391));
        AS.get(15).add(findViewById(R.id.as_dot392));
        AS.get(15).add(findViewById(R.id.as_dot393));
        AS.get(15).add(findViewById(R.id.as_dot394));
        AS.get(15).add(findViewById(R.id.as_dot395));
        AS.get(15).add(findViewById(R.id.as_dot396));
        AS.get(15).add(findViewById(R.id.as_dot397));
        AS.get(15).add(findViewById(R.id.as_dot398));
        AS.get(15).add(findViewById(R.id.as_dot399));
        AS.get(15).add(findViewById(R.id.as_dot400));
        AS.get(15).add(findViewById(R.id.as_dot401));
        AS.get(15).add(findViewById(R.id.as_dot402));
        AS.get(15).add(findViewById(R.id.as_dot403));
        AS.get(15).add(findViewById(R.id.as_dot404));
        AS.get(15).add(findViewById(R.id.as_dot405));
        AS.get(15).add(findViewById(R.id.as_dot406));
        AS.get(16).add(findViewById(R.id.as_dot407));
        AS.get(16).add(findViewById(R.id.as_dot408));
        AS.get(16).add(findViewById(R.id.as_dot409));
        AS.get(16).add(findViewById(R.id.as_dot410));
        AS.get(16).add(findViewById(R.id.as_dot411));
        AS.get(16).add(findViewById(R.id.as_dot412));
        AS.get(16).add(findViewById(R.id.as_dot413));
        AS.get(16).add(findViewById(R.id.as_dot414));
        AS.get(16).add(findViewById(R.id.as_dot415));
        AS.get(16).add(findViewById(R.id.as_dot416));
        AS.get(16).add(findViewById(R.id.as_dot417));
        AS.get(16).add(findViewById(R.id.as_dot418));
        AS.get(16).add(findViewById(R.id.as_dot419));
        AS.get(16).add(findViewById(R.id.as_dot420));
        AS.get(16).add(findViewById(R.id.as_dot421));
        AS.get(16).add(findViewById(R.id.as_dot422));
        AS.get(16).add(findViewById(R.id.as_dot423));
        AS.get(16).add(findViewById(R.id.as_dot424));
        AS.get(16).add(findViewById(R.id.as_dot425));
        AS.get(16).add(findViewById(R.id.as_dot426));
        AS.get(16).add(findViewById(R.id.as_dot427));
        AS.get(16).add(findViewById(R.id.as_dot428));
        AS.get(16).add(findViewById(R.id.as_dot429));
        AS.get(17).add(findViewById(R.id.as_dot430));
        AS.get(17).add(findViewById(R.id.as_dot431));
        AS.get(17).add(findViewById(R.id.as_dot432));
        AS.get(17).add(findViewById(R.id.as_dot433));
        AS.get(17).add(findViewById(R.id.as_dot434));
        AS.get(17).add(findViewById(R.id.as_dot435));
        AS.get(17).add(findViewById(R.id.as_dot436));
        AS.get(17).add(findViewById(R.id.as_dot437));
        AS.get(17).add(findViewById(R.id.as_dot438));
        AS.get(17).add(findViewById(R.id.as_dot439));
        AS.get(17).add(findViewById(R.id.as_dot440));
        AS.get(17).add(findViewById(R.id.as_dot441));
        AS.get(17).add(findViewById(R.id.as_dot442));
        AS.get(17).add(findViewById(R.id.as_dot443));
        AS.get(17).add(findViewById(R.id.as_dot444));
        AS.get(17).add(findViewById(R.id.as_dot445));
        AS.get(17).add(findViewById(R.id.as_dot446));
        AS.get(18).add(findViewById(R.id.as_dot447));
        AS.get(18).add(findViewById(R.id.as_dot448));
        AS.get(18).add(findViewById(R.id.as_dot449));
        AS.get(18).add(findViewById(R.id.as_dot450));
        AS.get(18).add(findViewById(R.id.as_dot451));
        AS.get(18).add(findViewById(R.id.as_dot452));
        AS.get(18).add(findViewById(R.id.as_dot453));
        AS.get(18).add(findViewById(R.id.as_dot454));
        AS.get(18).add(findViewById(R.id.as_dot455));
        AS.get(18).add(findViewById(R.id.as_dot456));
        AS.get(18).add(findViewById(R.id.as_dot457));
        AS.get(19).add(findViewById(R.id.as_dot458));
        AS.get(19).add(findViewById(R.id.as_dot459));
        AS.get(19).add(findViewById(R.id.as_dot460));
        AS.get(19).add(findViewById(R.id.as_dot461));
        AS.get(19).add(findViewById(R.id.as_dot462));
        AS.get(19).add(findViewById(R.id.as_dot463));
        AS.get(19).add(findViewById(R.id.as_dot464));
        AS.get(19).add(findViewById(R.id.as_dot465));
        AS.get(19).add(findViewById(R.id.as_dot466));
        AS.get(19).add(findViewById(R.id.as_dot467));
        AS.get(19).add(findViewById(R.id.as_dot468));
        AS.get(19).add(findViewById(R.id.as_dot469));
        AS.get(20).add(findViewById(R.id.as_dot470));
        AS.get(20).add(findViewById(R.id.as_dot471));
        AS.get(20).add(findViewById(R.id.as_dot472));
        AS.get(20).add(findViewById(R.id.as_dot473));
        AS.get(20).add(findViewById(R.id.as_dot474));
        AS.get(20).add(findViewById(R.id.as_dot475));
        AS.get(20).add(findViewById(R.id.as_dot476));
        AS.get(20).add(findViewById(R.id.as_dot477));
        AS.get(20).add(findViewById(R.id.as_dot478));
        AS.get(21).add(findViewById(R.id.as_dot479));
        AS.get(21).add(findViewById(R.id.as_dot480));
        AS.get(21).add(findViewById(R.id.as_dot481));
        AS.get(21).add(findViewById(R.id.as_dot482));
        AS.get(21).add(findViewById(R.id.as_dot483));
        AS.get(21).add(findViewById(R.id.as_dot484));
        AS.get(21).add(findViewById(R.id.as_dot485));
        AS.get(21).add(findViewById(R.id.as_dot486));
        AS.get(21).add(findViewById(R.id.as_dot487));
        AS.get(22).add(findViewById(R.id.as_dot488));
        AS.get(22).add(findViewById(R.id.as_dot489));
        AS.get(22).add(findViewById(R.id.as_dot490));
        AS.get(22).add(findViewById(R.id.as_dot491));
        AS.get(22).add(findViewById(R.id.as_dot492));
        AS.get(22).add(findViewById(R.id.as_dot493));
        AS.get(22).add(findViewById(R.id.as_dot494));
        AS.get(22).add(findViewById(R.id.as_dot495));
        AS.get(23).add(findViewById(R.id.as_dot496));
        AS.get(23).add(findViewById(R.id.as_dot497));
        AS.get(23).add(findViewById(R.id.as_dot498));
        AS.get(23).add(findViewById(R.id.as_dot499));
        AS.get(23).add(findViewById(R.id.as_dot500));
        AS.get(23).add(findViewById(R.id.as_dot501));
        AS.get(24).add(findViewById(R.id.as_dot502));
        AS.get(24).add(findViewById(R.id.as_dot503));
        AS.get(24).add(findViewById(R.id.as_dot504));
        AS.get(24).add(findViewById(R.id.as_dot505));
        AS.get(25).add(findViewById(R.id.as_dot506));
        AS.get(25).add(findViewById(R.id.as_dot507));
        AS.get(25).add(findViewById(R.id.as_dot508));
        AS.get(26).add(findViewById(R.id.as_dot509));
        AS.get(26).add(findViewById(R.id.as_dot510));
        AS.get(26).add(findViewById(R.id.as_dot511));
        AS.get(26).add(findViewById(R.id.as_dot512));
        AS.get(26).add(findViewById(R.id.as_dot513));
        AS.get(27).add(findViewById(R.id.as_dot514));
        AS.get(27).add(findViewById(R.id.as_dot515));
        AS.get(27).add(findViewById(R.id.as_dot516));
        AS.get(27).add(findViewById(R.id.as_dot517));
        AS.get(27).add(findViewById(R.id.as_dot518));
        AS.get(27).add(findViewById(R.id.as_dot519));
        AS.get(27).add(findViewById(R.id.as_dot520));
        AS.get(27).add(findViewById(R.id.as_dot521));
        AS.get(28).add(findViewById(R.id.as_dot522));
        AS.get(28).add(findViewById(R.id.as_dot523));
        AS.get(28).add(findViewById(R.id.as_dot524));
        AS.get(28).add(findViewById(R.id.as_dot525));
        AS.get(28).add(findViewById(R.id.as_dot526));
        AS.get(28).add(findViewById(R.id.as_dot527));
        AS.get(28).add(findViewById(R.id.as_dot528));
        AS.get(28).add(findViewById(R.id.as_dot529));
        AS.get(29).add(findViewById(R.id.as_dot530));
        AS.get(29).add(findViewById(R.id.as_dot531));
        AS.get(29).add(findViewById(R.id.as_dot532));
        AS.get(29).add(findViewById(R.id.as_dot533));
        AS.get(29).add(findViewById(R.id.as_dot534));
        AS.get(29).add(findViewById(R.id.as_dot535));
        AS.get(29).add(findViewById(R.id.as_dot536));
        AS.get(29).add(findViewById(R.id.as_dot537));
        AS.get(29).add(findViewById(R.id.as_dot538));
        AS.get(29).add(findViewById(R.id.as_dot539));
        AS.get(30).add(findViewById(R.id.as_dot540));
        AS.get(30).add(findViewById(R.id.as_dot541));
        AS.get(30).add(findViewById(R.id.as_dot542));
        AS.get(30).add(findViewById(R.id.as_dot543));
        AS.get(30).add(findViewById(R.id.as_dot544));
        AS.get(30).add(findViewById(R.id.as_dot545));
        AS.get(30).add(findViewById(R.id.as_dot546));
        AS.get(30).add(findViewById(R.id.as_dot547));
        AS.get(30).add(findViewById(R.id.as_dot548));
        AS.get(30).add(findViewById(R.id.as_dot549));
        AS.get(31).add(findViewById(R.id.as_dot550));
        AS.get(31).add(findViewById(R.id.as_dot551));
        AS.get(31).add(findViewById(R.id.as_dot552));
        AS.get(31).add(findViewById(R.id.as_dot553));
        AS.get(31).add(findViewById(R.id.as_dot554));
        AS.get(31).add(findViewById(R.id.as_dot555));
        AS.get(31).add(findViewById(R.id.as_dot556));
        AS.get(32).add(findViewById(R.id.as_dot557));
        AS.get(32).add(findViewById(R.id.as_dot558));
        AS.get(32).add(findViewById(R.id.as_dot559));
        AS.get(32).add(findViewById(R.id.as_dot560));
        AS.get(32).add(findViewById(R.id.as_dot561));
        AS.get(32).add(findViewById(R.id.as_dot562));
        AS.get(32).add(findViewById(R.id.as_dot563));
        AS.get(33).add(findViewById(R.id.as_dot564));
        AS.get(33).add(findViewById(R.id.as_dot565));
        AS.get(33).add(findViewById(R.id.as_dot566));

    }
    public void buildNF(){
        for(int i = 0; i<19;i++) {
            NF.add(new ArrayList<View>());
        }

        NF.get(0).add(findViewById(R.id.nf_dot0));
        NF.get(0).add(findViewById(R.id.nf_dot1));
        NF.get(0).add(findViewById(R.id.nf_dot2));
        NF.get(0).add(findViewById(R.id.nf_dot3));
        NF.get(0).add(findViewById(R.id.nf_dot4));
        NF.get(1).add(findViewById(R.id.nf_dot5));
        NF.get(1).add(findViewById(R.id.nf_dot6));
        NF.get(1).add(findViewById(R.id.nf_dot7));
        NF.get(1).add(findViewById(R.id.nf_dot8));
        NF.get(1).add(findViewById(R.id.nf_dot9));
        NF.get(1).add(findViewById(R.id.nf_dot10));
        NF.get(1).add(findViewById(R.id.nf_dot11));
        NF.get(1).add(findViewById(R.id.nf_dot12));
        NF.get(2).add(findViewById(R.id.nf_dot13));
        NF.get(2).add(findViewById(R.id.nf_dot14));
        NF.get(2).add(findViewById(R.id.nf_dot15));
        NF.get(2).add(findViewById(R.id.nf_dot16));
        NF.get(2).add(findViewById(R.id.nf_dot17));
        NF.get(2).add(findViewById(R.id.nf_dot18));
        NF.get(2).add(findViewById(R.id.nf_dot19));
        NF.get(2).add(findViewById(R.id.nf_dot20));
        NF.get(2).add(findViewById(R.id.nf_dot21));
        NF.get(2).add(findViewById(R.id.nf_dot22));
        NF.get(2).add(findViewById(R.id.nf_dot23));
        NF.get(2).add(findViewById(R.id.nf_dot24));
        NF.get(2).add(findViewById(R.id.nf_dot25));
        NF.get(3).add(findViewById(R.id.nf_dot26));
        NF.get(3).add(findViewById(R.id.nf_dot27));
        NF.get(3).add(findViewById(R.id.nf_dot28));
        NF.get(3).add(findViewById(R.id.nf_dot29));
        NF.get(3).add(findViewById(R.id.nf_dot30));
        NF.get(3).add(findViewById(R.id.nf_dot31));
        NF.get(3).add(findViewById(R.id.nf_dot32));
        NF.get(3).add(findViewById(R.id.nf_dot33));
        NF.get(3).add(findViewById(R.id.nf_dot34));
        NF.get(3).add(findViewById(R.id.nf_dot35));
        NF.get(3).add(findViewById(R.id.nf_dot36));
        NF.get(3).add(findViewById(R.id.nf_dot37));
        NF.get(3).add(findViewById(R.id.nf_dot38));
        NF.get(3).add(findViewById(R.id.nf_dot39));
        NF.get(3).add(findViewById(R.id.nf_dot40));
        NF.get(4).add(findViewById(R.id.nf_dot41));
        NF.get(4).add(findViewById(R.id.nf_dot42));
        NF.get(4).add(findViewById(R.id.nf_dot43));
        NF.get(4).add(findViewById(R.id.nf_dot44));
        NF.get(4).add(findViewById(R.id.nf_dot45));
        NF.get(4).add(findViewById(R.id.nf_dot46));
        NF.get(4).add(findViewById(R.id.nf_dot47));
        NF.get(4).add(findViewById(R.id.nf_dot48));
        NF.get(4).add(findViewById(R.id.nf_dot49));
        NF.get(4).add(findViewById(R.id.nf_dot50));
        NF.get(4).add(findViewById(R.id.nf_dot51));
        NF.get(4).add(findViewById(R.id.nf_dot52));
        NF.get(4).add(findViewById(R.id.nf_dot53));
        NF.get(4).add(findViewById(R.id.nf_dot54));
        NF.get(4).add(findViewById(R.id.nf_dot55));
        NF.get(4).add(findViewById(R.id.nf_dot56));
        NF.get(4).add(findViewById(R.id.nf_dot57));
        NF.get(5).add(findViewById(R.id.nf_dot58));
        NF.get(5).add(findViewById(R.id.nf_dot59));
        NF.get(5).add(findViewById(R.id.nf_dot60));
        NF.get(5).add(findViewById(R.id.nf_dot61));
        NF.get(5).add(findViewById(R.id.nf_dot62));
        NF.get(5).add(findViewById(R.id.nf_dot63));
        NF.get(5).add(findViewById(R.id.nf_dot64));
        NF.get(5).add(findViewById(R.id.nf_dot65));
        NF.get(5).add(findViewById(R.id.nf_dot66));
        NF.get(5).add(findViewById(R.id.nf_dot67));
        NF.get(5).add(findViewById(R.id.nf_dot68));
        NF.get(5).add(findViewById(R.id.nf_dot69));
        NF.get(5).add(findViewById(R.id.nf_dot70));
        NF.get(5).add(findViewById(R.id.nf_dot71));
        NF.get(5).add(findViewById(R.id.nf_dot72));
        NF.get(5).add(findViewById(R.id.nf_dot73));
        NF.get(5).add(findViewById(R.id.nf_dot74));
        NF.get(6).add(findViewById(R.id.nf_dot75));
        NF.get(6).add(findViewById(R.id.nf_dot76));
        NF.get(6).add(findViewById(R.id.nf_dot77));
        NF.get(6).add(findViewById(R.id.nf_dot78));
        NF.get(6).add(findViewById(R.id.nf_dot79));
        NF.get(6).add(findViewById(R.id.nf_dot80));
        NF.get(6).add(findViewById(R.id.nf_dot81));
        NF.get(6).add(findViewById(R.id.nf_dot82));
        NF.get(6).add(findViewById(R.id.nf_dot83));
        NF.get(6).add(findViewById(R.id.nf_dot84));
        NF.get(6).add(findViewById(R.id.nf_dot85));
        NF.get(6).add(findViewById(R.id.nf_dot86));
        NF.get(6).add(findViewById(R.id.nf_dot87));
        NF.get(6).add(findViewById(R.id.nf_dot88));
        NF.get(6).add(findViewById(R.id.nf_dot89));
        NF.get(6).add(findViewById(R.id.nf_dot90));
        NF.get(6).add(findViewById(R.id.nf_dot91));
        NF.get(6).add(findViewById(R.id.nf_dot92));
        NF.get(7).add(findViewById(R.id.nf_dot93));
        NF.get(7).add(findViewById(R.id.nf_dot94));
        NF.get(7).add(findViewById(R.id.nf_dot95));
        NF.get(7).add(findViewById(R.id.nf_dot96));
        NF.get(7).add(findViewById(R.id.nf_dot97));
        NF.get(7).add(findViewById(R.id.nf_dot98));
        NF.get(7).add(findViewById(R.id.nf_dot99));
        NF.get(7).add(findViewById(R.id.nf_dot100));
        NF.get(7).add(findViewById(R.id.nf_dot101));
        NF.get(7).add(findViewById(R.id.nf_dot102));
        NF.get(7).add(findViewById(R.id.nf_dot103));
        NF.get(7).add(findViewById(R.id.nf_dot104));
        NF.get(7).add(findViewById(R.id.nf_dot105));
        NF.get(7).add(findViewById(R.id.nf_dot106));
        NF.get(7).add(findViewById(R.id.nf_dot107));
        NF.get(7).add(findViewById(R.id.nf_dot108));
        NF.get(7).add(findViewById(R.id.nf_dot109));
        NF.get(7).add(findViewById(R.id.nf_dot110));
        NF.get(8).add(findViewById(R.id.nf_dot111));
        NF.get(8).add(findViewById(R.id.nf_dot112));
        NF.get(8).add(findViewById(R.id.nf_dot113));
        NF.get(8).add(findViewById(R.id.nf_dot114));
        NF.get(8).add(findViewById(R.id.nf_dot115));
        NF.get(8).add(findViewById(R.id.nf_dot116));
        NF.get(8).add(findViewById(R.id.nf_dot117));
        NF.get(8).add(findViewById(R.id.nf_dot118));
        NF.get(8).add(findViewById(R.id.nf_dot119));
        NF.get(8).add(findViewById(R.id.nf_dot120));
        NF.get(8).add(findViewById(R.id.nf_dot121));
        NF.get(8).add(findViewById(R.id.nf_dot122));
        NF.get(8).add(findViewById(R.id.nf_dot123));
        NF.get(8).add(findViewById(R.id.nf_dot124));
        NF.get(8).add(findViewById(R.id.nf_dot125));
        NF.get(8).add(findViewById(R.id.nf_dot126));
        NF.get(8).add(findViewById(R.id.nf_dot127));
        NF.get(8).add(findViewById(R.id.nf_dot128));
        NF.get(8).add(findViewById(R.id.nf_dot129));
        NF.get(8).add(findViewById(R.id.nf_dot130));
        NF.get(8).add(findViewById(R.id.nf_dot131));
        NF.get(8).add(findViewById(R.id.nf_dot132));
        NF.get(8).add(findViewById(R.id.nf_dot133));
        NF.get(8).add(findViewById(R.id.nf_dot134));
        NF.get(9).add(findViewById(R.id.nf_dot135));
        NF.get(9).add(findViewById(R.id.nf_dot136));
        NF.get(9).add(findViewById(R.id.nf_dot137));
        NF.get(9).add(findViewById(R.id.nf_dot138));
        NF.get(9).add(findViewById(R.id.nf_dot139));
        NF.get(9).add(findViewById(R.id.nf_dot140));
        NF.get(9).add(findViewById(R.id.nf_dot141));
        NF.get(9).add(findViewById(R.id.nf_dot142));
        NF.get(9).add(findViewById(R.id.nf_dot143));
        NF.get(9).add(findViewById(R.id.nf_dot144));
        NF.get(9).add(findViewById(R.id.nf_dot145));
        NF.get(9).add(findViewById(R.id.nf_dot146));
        NF.get(9).add(findViewById(R.id.nf_dot147));
        NF.get(9).add(findViewById(R.id.nf_dot148));
        NF.get(9).add(findViewById(R.id.nf_dot149));
        NF.get(9).add(findViewById(R.id.nf_dot150));
        NF.get(9).add(findViewById(R.id.nf_dot151));
        NF.get(9).add(findViewById(R.id.nf_dot152));
        NF.get(9).add(findViewById(R.id.nf_dot153));
        NF.get(9).add(findViewById(R.id.nf_dot154));
        NF.get(9).add(findViewById(R.id.nf_dot155));
        NF.get(9).add(findViewById(R.id.nf_dot156));
        NF.get(9).add(findViewById(R.id.nf_dot157));
        NF.get(9).add(findViewById(R.id.nf_dot158));
        NF.get(9).add(findViewById(R.id.nf_dot159));
        NF.get(10).add(findViewById(R.id.nf_dot160));
        NF.get(10).add(findViewById(R.id.nf_dot161));
        NF.get(10).add(findViewById(R.id.nf_dot162));
        NF.get(10).add(findViewById(R.id.nf_dot163));
        NF.get(10).add(findViewById(R.id.nf_dot164));
        NF.get(10).add(findViewById(R.id.nf_dot165));
        NF.get(10).add(findViewById(R.id.nf_dot166));
        NF.get(10).add(findViewById(R.id.nf_dot167));
        NF.get(10).add(findViewById(R.id.nf_dot168));
        NF.get(10).add(findViewById(R.id.nf_dot169));
        NF.get(10).add(findViewById(R.id.nf_dot170));
        NF.get(10).add(findViewById(R.id.nf_dot171));
        NF.get(10).add(findViewById(R.id.nf_dot172));
        NF.get(10).add(findViewById(R.id.nf_dot173));
        NF.get(10).add(findViewById(R.id.nf_dot174));
        NF.get(10).add(findViewById(R.id.nf_dot175));
        NF.get(10).add(findViewById(R.id.nf_dot176));
        NF.get(10).add(findViewById(R.id.nf_dot177));
        NF.get(10).add(findViewById(R.id.nf_dot178));
        NF.get(10).add(findViewById(R.id.nf_dot179));
        NF.get(10).add(findViewById(R.id.nf_dot180));
        NF.get(10).add(findViewById(R.id.nf_dot181));
        NF.get(10).add(findViewById(R.id.nf_dot182));
        NF.get(10).add(findViewById(R.id.nf_dot183));
        NF.get(10).add(findViewById(R.id.nf_dot184));
        NF.get(10).add(findViewById(R.id.nf_dot185));
        NF.get(11).add(findViewById(R.id.nf_dot186));
        NF.get(11).add(findViewById(R.id.nf_dot187));
        NF.get(11).add(findViewById(R.id.nf_dot188));
        NF.get(11).add(findViewById(R.id.nf_dot189));
        NF.get(11).add(findViewById(R.id.nf_dot190));
        NF.get(11).add(findViewById(R.id.nf_dot191));
        NF.get(11).add(findViewById(R.id.nf_dot192));
        NF.get(11).add(findViewById(R.id.nf_dot193));
        NF.get(11).add(findViewById(R.id.nf_dot194));
        NF.get(11).add(findViewById(R.id.nf_dot195));
        NF.get(11).add(findViewById(R.id.nf_dot196));
        NF.get(11).add(findViewById(R.id.nf_dot197));
        NF.get(11).add(findViewById(R.id.nf_dot198));
        NF.get(11).add(findViewById(R.id.nf_dot199));
        NF.get(11).add(findViewById(R.id.nf_dot200));
        NF.get(11).add(findViewById(R.id.nf_dot201));
        NF.get(11).add(findViewById(R.id.nf_dot202));
        NF.get(11).add(findViewById(R.id.nf_dot203));
        NF.get(11).add(findViewById(R.id.nf_dot204));
        NF.get(11).add(findViewById(R.id.nf_dot205));
        NF.get(11).add(findViewById(R.id.nf_dot206));
        NF.get(11).add(findViewById(R.id.nf_dot207));
        NF.get(11).add(findViewById(R.id.nf_dot208));
        NF.get(11).add(findViewById(R.id.nf_dot209));
        NF.get(11).add(findViewById(R.id.nf_dot210));
        NF.get(11).add(findViewById(R.id.nf_dot211));
        NF.get(11).add(findViewById(R.id.nf_dot212));
        NF.get(12).add(findViewById(R.id.nf_dot213));
        NF.get(12).add(findViewById(R.id.nf_dot214));
        NF.get(12).add(findViewById(R.id.nf_dot215));
        NF.get(12).add(findViewById(R.id.nf_dot216));
        NF.get(12).add(findViewById(R.id.nf_dot217));
        NF.get(12).add(findViewById(R.id.nf_dot218));
        NF.get(12).add(findViewById(R.id.nf_dot219));
        NF.get(12).add(findViewById(R.id.nf_dot220));
        NF.get(12).add(findViewById(R.id.nf_dot221));
        NF.get(12).add(findViewById(R.id.nf_dot222));
        NF.get(12).add(findViewById(R.id.nf_dot223));
        NF.get(12).add(findViewById(R.id.nf_dot224));
        NF.get(12).add(findViewById(R.id.nf_dot225));
        NF.get(12).add(findViewById(R.id.nf_dot226));
        NF.get(12).add(findViewById(R.id.nf_dot227));
        NF.get(12).add(findViewById(R.id.nf_dot228));
        NF.get(12).add(findViewById(R.id.nf_dot229));
        NF.get(12).add(findViewById(R.id.nf_dot230));
        NF.get(12).add(findViewById(R.id.nf_dot231));
        NF.get(12).add(findViewById(R.id.nf_dot232));
        NF.get(12).add(findViewById(R.id.nf_dot233));
        NF.get(12).add(findViewById(R.id.nf_dot234));
        NF.get(12).add(findViewById(R.id.nf_dot235));
        NF.get(12).add(findViewById(R.id.nf_dot236));
        NF.get(12).add(findViewById(R.id.nf_dot237));
        NF.get(12).add(findViewById(R.id.nf_dot238));
        NF.get(12).add(findViewById(R.id.nf_dot239));
        NF.get(13).add(findViewById(R.id.nf_dot240));
        NF.get(13).add(findViewById(R.id.nf_dot241));
        NF.get(13).add(findViewById(R.id.nf_dot242));
        NF.get(13).add(findViewById(R.id.nf_dot243));
        NF.get(13).add(findViewById(R.id.nf_dot244));
        NF.get(13).add(findViewById(R.id.nf_dot245));
        NF.get(13).add(findViewById(R.id.nf_dot246));
        NF.get(13).add(findViewById(R.id.nf_dot247));
        NF.get(13).add(findViewById(R.id.nf_dot248));
        NF.get(13).add(findViewById(R.id.nf_dot249));
        NF.get(13).add(findViewById(R.id.nf_dot250));
        NF.get(13).add(findViewById(R.id.nf_dot251));
        NF.get(13).add(findViewById(R.id.nf_dot252));
        NF.get(13).add(findViewById(R.id.nf_dot253));
        NF.get(13).add(findViewById(R.id.nf_dot254));
        NF.get(13).add(findViewById(R.id.nf_dot255));
        NF.get(13).add(findViewById(R.id.nf_dot256));
        NF.get(13).add(findViewById(R.id.nf_dot257));
        NF.get(13).add(findViewById(R.id.nf_dot258));
        NF.get(13).add(findViewById(R.id.nf_dot259));
        NF.get(13).add(findViewById(R.id.nf_dot260));
        NF.get(13).add(findViewById(R.id.nf_dot261));
        NF.get(13).add(findViewById(R.id.nf_dot262));
        NF.get(13).add(findViewById(R.id.nf_dot263));
        NF.get(13).add(findViewById(R.id.nf_dot264));
        NF.get(13).add(findViewById(R.id.nf_dot265));
        NF.get(13).add(findViewById(R.id.nf_dot266));
        NF.get(13).add(findViewById(R.id.nf_dot267));
        NF.get(13).add(findViewById(R.id.nf_dot268));
        NF.get(13).add(findViewById(R.id.nf_dot269));
        NF.get(14).add(findViewById(R.id.nf_dot270));
        NF.get(14).add(findViewById(R.id.nf_dot271));
        NF.get(14).add(findViewById(R.id.nf_dot272));
        NF.get(14).add(findViewById(R.id.nf_dot273));
        NF.get(14).add(findViewById(R.id.nf_dot274));
        NF.get(14).add(findViewById(R.id.nf_dot275));
        NF.get(14).add(findViewById(R.id.nf_dot276));
        NF.get(14).add(findViewById(R.id.nf_dot277));
        NF.get(14).add(findViewById(R.id.nf_dot278));
        NF.get(14).add(findViewById(R.id.nf_dot279));
        NF.get(14).add(findViewById(R.id.nf_dot280));
        NF.get(14).add(findViewById(R.id.nf_dot281));
        NF.get(14).add(findViewById(R.id.nf_dot282));
        NF.get(14).add(findViewById(R.id.nf_dot283));
        NF.get(14).add(findViewById(R.id.nf_dot284));
        NF.get(14).add(findViewById(R.id.nf_dot285));
        NF.get(14).add(findViewById(R.id.nf_dot286));
        NF.get(14).add(findViewById(R.id.nf_dot287));
        NF.get(14).add(findViewById(R.id.nf_dot288));
        NF.get(14).add(findViewById(R.id.nf_dot289));
        NF.get(14).add(findViewById(R.id.nf_dot290));
        NF.get(14).add(findViewById(R.id.nf_dot291));
        NF.get(14).add(findViewById(R.id.nf_dot292));
        NF.get(14).add(findViewById(R.id.nf_dot293));
        NF.get(14).add(findViewById(R.id.nf_dot294));
        NF.get(14).add(findViewById(R.id.nf_dot295));
        NF.get(14).add(findViewById(R.id.nf_dot296));
        NF.get(14).add(findViewById(R.id.nf_dot297));
        NF.get(14).add(findViewById(R.id.nf_dot298));
        NF.get(15).add(findViewById(R.id.nf_dot299));
        NF.get(15).add(findViewById(R.id.nf_dot300));
        NF.get(15).add(findViewById(R.id.nf_dot301));
        NF.get(15).add(findViewById(R.id.nf_dot302));
        NF.get(15).add(findViewById(R.id.nf_dot303));
        NF.get(15).add(findViewById(R.id.nf_dot304));
        NF.get(15).add(findViewById(R.id.nf_dot305));
        NF.get(15).add(findViewById(R.id.nf_dot306));
        NF.get(15).add(findViewById(R.id.nf_dot307));
        NF.get(15).add(findViewById(R.id.nf_dot308));
        NF.get(15).add(findViewById(R.id.nf_dot309));
        NF.get(15).add(findViewById(R.id.nf_dot310));
        NF.get(15).add(findViewById(R.id.nf_dot311));
        NF.get(15).add(findViewById(R.id.nf_dot312));
        NF.get(15).add(findViewById(R.id.nf_dot313));
        NF.get(15).add(findViewById(R.id.nf_dot314));
        NF.get(15).add(findViewById(R.id.nf_dot315));
        NF.get(15).add(findViewById(R.id.nf_dot316));
        NF.get(15).add(findViewById(R.id.nf_dot317));
        NF.get(15).add(findViewById(R.id.nf_dot318));
        NF.get(15).add(findViewById(R.id.nf_dot319));
        NF.get(15).add(findViewById(R.id.nf_dot320));
        NF.get(15).add(findViewById(R.id.nf_dot321));
        NF.get(15).add(findViewById(R.id.nf_dot322));
        NF.get(15).add(findViewById(R.id.nf_dot323));
        NF.get(16).add(findViewById(R.id.nf_dot324));
        NF.get(16).add(findViewById(R.id.nf_dot325));
        NF.get(16).add(findViewById(R.id.nf_dot326));
        NF.get(16).add(findViewById(R.id.nf_dot327));
        NF.get(16).add(findViewById(R.id.nf_dot328));
        NF.get(16).add(findViewById(R.id.nf_dot329));
        NF.get(16).add(findViewById(R.id.nf_dot330));
        NF.get(16).add(findViewById(R.id.nf_dot331));
        NF.get(16).add(findViewById(R.id.nf_dot332));
        NF.get(16).add(findViewById(R.id.nf_dot333));
        NF.get(16).add(findViewById(R.id.nf_dot334));
        NF.get(16).add(findViewById(R.id.nf_dot335));
        NF.get(16).add(findViewById(R.id.nf_dot336));
        NF.get(17).add(findViewById(R.id.nf_dot337));
        NF.get(17).add(findViewById(R.id.nf_dot338));
        NF.get(17).add(findViewById(R.id.nf_dot339));
        NF.get(17).add(findViewById(R.id.nf_dot340));
        NF.get(17).add(findViewById(R.id.nf_dot341));
        NF.get(17).add(findViewById(R.id.nf_dot342));
        NF.get(17).add(findViewById(R.id.nf_dot343));
        NF.get(18).add(findViewById(R.id.nf_dot344));

    }
    public void buildSF(){
        for(int i = 0; i<20;i++) {
            SF.add(new ArrayList<View>());
        }

        SF.get(0).add(findViewById(R.id.sf_dot0));
        SF.get(0).add(findViewById(R.id.sf_dot1));
        SF.get(0).add(findViewById(R.id.sf_dot2));
        SF.get(0).add(findViewById(R.id.sf_dot3));
        SF.get(0).add(findViewById(R.id.sf_dot4));
        SF.get(1).add(findViewById(R.id.sf_dot5));
        SF.get(1).add(findViewById(R.id.sf_dot6));
        SF.get(1).add(findViewById(R.id.sf_dot7));
        SF.get(1).add(findViewById(R.id.sf_dot8));
        SF.get(1).add(findViewById(R.id.sf_dot9));
        SF.get(1).add(findViewById(R.id.sf_dot10));
        SF.get(1).add(findViewById(R.id.sf_dot11));
        SF.get(1).add(findViewById(R.id.sf_dot12));
        SF.get(1).add(findViewById(R.id.sf_dot13));
        SF.get(2).add(findViewById(R.id.sf_dot14));
        SF.get(2).add(findViewById(R.id.sf_dot15));
        SF.get(2).add(findViewById(R.id.sf_dot16));
        SF.get(2).add(findViewById(R.id.sf_dot17));
        SF.get(2).add(findViewById(R.id.sf_dot18));
        SF.get(2).add(findViewById(R.id.sf_dot19));
        SF.get(2).add(findViewById(R.id.sf_dot20));
        SF.get(2).add(findViewById(R.id.sf_dot21));
        SF.get(2).add(findViewById(R.id.sf_dot22));
        SF.get(2).add(findViewById(R.id.sf_dot23));
        SF.get(2).add(findViewById(R.id.sf_dot24));
        SF.get(2).add(findViewById(R.id.sf_dot25));
        SF.get(2).add(findViewById(R.id.sf_dot26));
        SF.get(2).add(findViewById(R.id.sf_dot27));
        SF.get(2).add(findViewById(R.id.sf_dot28));
        SF.get(3).add(findViewById(R.id.sf_dot29));
        SF.get(3).add(findViewById(R.id.sf_dot30));
        SF.get(3).add(findViewById(R.id.sf_dot31));
        SF.get(3).add(findViewById(R.id.sf_dot32));
        SF.get(3).add(findViewById(R.id.sf_dot33));
        SF.get(3).add(findViewById(R.id.sf_dot34));
        SF.get(3).add(findViewById(R.id.sf_dot35));
        SF.get(3).add(findViewById(R.id.sf_dot36));
        SF.get(3).add(findViewById(R.id.sf_dot37));
        SF.get(3).add(findViewById(R.id.sf_dot38));
        SF.get(3).add(findViewById(R.id.sf_dot39));
        SF.get(3).add(findViewById(R.id.sf_dot40));
        SF.get(3).add(findViewById(R.id.sf_dot41));
        SF.get(3).add(findViewById(R.id.sf_dot42));
        SF.get(3).add(findViewById(R.id.sf_dot43));
        SF.get(4).add(findViewById(R.id.sf_dot44));
        SF.get(4).add(findViewById(R.id.sf_dot45));
        SF.get(4).add(findViewById(R.id.sf_dot46));
        SF.get(4).add(findViewById(R.id.sf_dot47));
        SF.get(4).add(findViewById(R.id.sf_dot48));
        SF.get(4).add(findViewById(R.id.sf_dot49));
        SF.get(4).add(findViewById(R.id.sf_dot50));
        SF.get(4).add(findViewById(R.id.sf_dot51));
        SF.get(4).add(findViewById(R.id.sf_dot52));
        SF.get(4).add(findViewById(R.id.sf_dot53));
        SF.get(4).add(findViewById(R.id.sf_dot54));
        SF.get(4).add(findViewById(R.id.sf_dot55));
        SF.get(4).add(findViewById(R.id.sf_dot56));
        SF.get(5).add(findViewById(R.id.sf_dot57));
        SF.get(5).add(findViewById(R.id.sf_dot58));
        SF.get(5).add(findViewById(R.id.sf_dot59));
        SF.get(5).add(findViewById(R.id.sf_dot60));
        SF.get(5).add(findViewById(R.id.sf_dot61));
        SF.get(5).add(findViewById(R.id.sf_dot62));
        SF.get(5).add(findViewById(R.id.sf_dot63));
        SF.get(5).add(findViewById(R.id.sf_dot64));
        SF.get(5).add(findViewById(R.id.sf_dot65));
        SF.get(5).add(findViewById(R.id.sf_dot66));
        SF.get(5).add(findViewById(R.id.sf_dot67));
        SF.get(5).add(findViewById(R.id.sf_dot68));
        SF.get(6).add(findViewById(R.id.sf_dot69));
        SF.get(6).add(findViewById(R.id.sf_dot70));
        SF.get(6).add(findViewById(R.id.sf_dot71));
        SF.get(6).add(findViewById(R.id.sf_dot72));
        SF.get(6).add(findViewById(R.id.sf_dot73));
        SF.get(6).add(findViewById(R.id.sf_dot74));
        SF.get(6).add(findViewById(R.id.sf_dot75));
        SF.get(6).add(findViewById(R.id.sf_dot76));
        SF.get(6).add(findViewById(R.id.sf_dot77));
        SF.get(6).add(findViewById(R.id.sf_dot78));
        SF.get(6).add(findViewById(R.id.sf_dot79));
        SF.get(6).add(findViewById(R.id.sf_dot80));
        SF.get(7).add(findViewById(R.id.sf_dot81));
        SF.get(7).add(findViewById(R.id.sf_dot82));
        SF.get(7).add(findViewById(R.id.sf_dot83));
        SF.get(7).add(findViewById(R.id.sf_dot84));
        SF.get(7).add(findViewById(R.id.sf_dot85));
        SF.get(7).add(findViewById(R.id.sf_dot86));
        SF.get(7).add(findViewById(R.id.sf_dot87));
        SF.get(7).add(findViewById(R.id.sf_dot88));
        SF.get(7).add(findViewById(R.id.sf_dot89));
        SF.get(7).add(findViewById(R.id.sf_dot90));
        SF.get(7).add(findViewById(R.id.sf_dot91));
        SF.get(7).add(findViewById(R.id.sf_dot92));
        SF.get(7).add(findViewById(R.id.sf_dot93));
        SF.get(8).add(findViewById(R.id.sf_dot94));
        SF.get(8).add(findViewById(R.id.sf_dot95));
        SF.get(8).add(findViewById(R.id.sf_dot96));
        SF.get(8).add(findViewById(R.id.sf_dot97));
        SF.get(8).add(findViewById(R.id.sf_dot98));
        SF.get(8).add(findViewById(R.id.sf_dot99));
        SF.get(8).add(findViewById(R.id.sf_dot100));
        SF.get(8).add(findViewById(R.id.sf_dot101));
        SF.get(8).add(findViewById(R.id.sf_dot102));
        SF.get(8).add(findViewById(R.id.sf_dot103));
        SF.get(8).add(findViewById(R.id.sf_dot104));
        SF.get(8).add(findViewById(R.id.sf_dot105));
        SF.get(8).add(findViewById(R.id.sf_dot106));
        SF.get(9).add(findViewById(R.id.sf_dot107));
        SF.get(9).add(findViewById(R.id.sf_dot108));
        SF.get(9).add(findViewById(R.id.sf_dot109));
        SF.get(9).add(findViewById(R.id.sf_dot110));
        SF.get(9).add(findViewById(R.id.sf_dot111));
        SF.get(9).add(findViewById(R.id.sf_dot112));
        SF.get(9).add(findViewById(R.id.sf_dot113));
        SF.get(9).add(findViewById(R.id.sf_dot114));
        SF.get(9).add(findViewById(R.id.sf_dot115));
        SF.get(9).add(findViewById(R.id.sf_dot116));
        SF.get(9).add(findViewById(R.id.sf_dot117));
        SF.get(9).add(findViewById(R.id.sf_dot118));
        SF.get(9).add(findViewById(R.id.sf_dot119));
        SF.get(9).add(findViewById(R.id.sf_dot120));
        SF.get(10).add(findViewById(R.id.sf_dot121));
        SF.get(10).add(findViewById(R.id.sf_dot122));
        SF.get(10).add(findViewById(R.id.sf_dot123));
        SF.get(10).add(findViewById(R.id.sf_dot124));
        SF.get(10).add(findViewById(R.id.sf_dot125));
        SF.get(10).add(findViewById(R.id.sf_dot126));
        SF.get(10).add(findViewById(R.id.sf_dot127));
        SF.get(10).add(findViewById(R.id.sf_dot128));
        SF.get(10).add(findViewById(R.id.sf_dot129));
        SF.get(10).add(findViewById(R.id.sf_dot130));
        SF.get(10).add(findViewById(R.id.sf_dot131));
        SF.get(10).add(findViewById(R.id.sf_dot132));
        SF.get(10).add(findViewById(R.id.sf_dot133));
        SF.get(10).add(findViewById(R.id.sf_dot134));
        SF.get(10).add(findViewById(R.id.sf_dot135));
        SF.get(10).add(findViewById(R.id.sf_dot136));
        SF.get(11).add(findViewById(R.id.sf_dot137));
        SF.get(11).add(findViewById(R.id.sf_dot138));
        SF.get(11).add(findViewById(R.id.sf_dot139));
        SF.get(11).add(findViewById(R.id.sf_dot140));
        SF.get(11).add(findViewById(R.id.sf_dot141));
        SF.get(11).add(findViewById(R.id.sf_dot142));
        SF.get(11).add(findViewById(R.id.sf_dot143));
        SF.get(11).add(findViewById(R.id.sf_dot144));
        SF.get(11).add(findViewById(R.id.sf_dot145));
        SF.get(11).add(findViewById(R.id.sf_dot146));
        SF.get(11).add(findViewById(R.id.sf_dot147));
        SF.get(11).add(findViewById(R.id.sf_dot148));
        SF.get(11).add(findViewById(R.id.sf_dot149));
        SF.get(11).add(findViewById(R.id.sf_dot150));
        SF.get(12).add(findViewById(R.id.sf_dot151));
        SF.get(12).add(findViewById(R.id.sf_dot152));
        SF.get(12).add(findViewById(R.id.sf_dot153));
        SF.get(12).add(findViewById(R.id.sf_dot154));
        SF.get(12).add(findViewById(R.id.sf_dot155));
        SF.get(12).add(findViewById(R.id.sf_dot156));
        SF.get(12).add(findViewById(R.id.sf_dot157));
        SF.get(12).add(findViewById(R.id.sf_dot158));
        SF.get(12).add(findViewById(R.id.sf_dot159));
        SF.get(12).add(findViewById(R.id.sf_dot160));
        SF.get(12).add(findViewById(R.id.sf_dot161));
        SF.get(12).add(findViewById(R.id.sf_dot162));
        SF.get(13).add(findViewById(R.id.sf_dot163));
        SF.get(13).add(findViewById(R.id.sf_dot164));
        SF.get(13).add(findViewById(R.id.sf_dot165));
        SF.get(13).add(findViewById(R.id.sf_dot166));
        SF.get(13).add(findViewById(R.id.sf_dot167));
        SF.get(13).add(findViewById(R.id.sf_dot168));
        SF.get(13).add(findViewById(R.id.sf_dot169));
        SF.get(13).add(findViewById(R.id.sf_dot170));
        SF.get(13).add(findViewById(R.id.sf_dot171));
        SF.get(13).add(findViewById(R.id.sf_dot172));
        SF.get(13).add(findViewById(R.id.sf_dot173));
        SF.get(14).add(findViewById(R.id.sf_dot174));
        SF.get(14).add(findViewById(R.id.sf_dot175));
        SF.get(14).add(findViewById(R.id.sf_dot176));
        SF.get(14).add(findViewById(R.id.sf_dot177));
        SF.get(14).add(findViewById(R.id.sf_dot178));
        SF.get(14).add(findViewById(R.id.sf_dot179));
        SF.get(14).add(findViewById(R.id.sf_dot180));
        SF.get(14).add(findViewById(R.id.sf_dot181));
        SF.get(14).add(findViewById(R.id.sf_dot182));
        SF.get(14).add(findViewById(R.id.sf_dot183));
        SF.get(14).add(findViewById(R.id.sf_dot184));
        SF.get(15).add(findViewById(R.id.sf_dot185));
        SF.get(15).add(findViewById(R.id.sf_dot186));
        SF.get(15).add(findViewById(R.id.sf_dot187));
        SF.get(15).add(findViewById(R.id.sf_dot188));
        SF.get(15).add(findViewById(R.id.sf_dot189));
        SF.get(15).add(findViewById(R.id.sf_dot190));
        SF.get(15).add(findViewById(R.id.sf_dot191));
        SF.get(15).add(findViewById(R.id.sf_dot192));
        SF.get(16).add(findViewById(R.id.sf_dot193));
        SF.get(16).add(findViewById(R.id.sf_dot194));
        SF.get(16).add(findViewById(R.id.sf_dot195));
        SF.get(16).add(findViewById(R.id.sf_dot196));
        SF.get(16).add(findViewById(R.id.sf_dot197));
        SF.get(16).add(findViewById(R.id.sf_dot198));
        SF.get(16).add(findViewById(R.id.sf_dot199));
        SF.get(16).add(findViewById(R.id.sf_dot200));
        SF.get(17).add(findViewById(R.id.sf_dot201));
        SF.get(17).add(findViewById(R.id.sf_dot202));
        SF.get(17).add(findViewById(R.id.sf_dot203));
        SF.get(17).add(findViewById(R.id.sf_dot204));
        SF.get(17).add(findViewById(R.id.sf_dot205));
        SF.get(17).add(findViewById(R.id.sf_dot206));
        SF.get(18).add(findViewById(R.id.sf_dot207));
        SF.get(18).add(findViewById(R.id.sf_dot208));
        SF.get(18).add(findViewById(R.id.sf_dot209));
        SF.get(18).add(findViewById(R.id.sf_dot210));
        SF.get(18).add(findViewById(R.id.sf_dot211));
        SF.get(19).add(findViewById(R.id.sf_dot212));
        SF.get(19).add(findViewById(R.id.sf_dot213));
        SF.get(19).add(findViewById(R.id.sf_dot214));
        SF.get(19).add(findViewById(R.id.sf_dot215));

    }
    public void buildOC(){
        for(int i = 0; i<18;i++) {
            OC.add(new ArrayList<View>());
        }

        OC.get(0).add(findViewById(R.id.oc_dot0));
        OC.get(0).add(findViewById(R.id.oc_dot1));
        OC.get(0).add(findViewById(R.id.oc_dot2));
        OC.get(1).add(findViewById(R.id.oc_dot3));
        OC.get(1).add(findViewById(R.id.oc_dot4));
        OC.get(1).add(findViewById(R.id.oc_dot5));
        OC.get(1).add(findViewById(R.id.oc_dot6));
        OC.get(1).add(findViewById(R.id.oc_dot7));
        OC.get(2).add(findViewById(R.id.oc_dot8));
        OC.get(2).add(findViewById(R.id.oc_dot9));
        OC.get(2).add(findViewById(R.id.oc_dot10));
        OC.get(2).add(findViewById(R.id.oc_dot11));
        OC.get(2).add(findViewById(R.id.oc_dot12));
        OC.get(2).add(findViewById(R.id.oc_dot13));
        OC.get(2).add(findViewById(R.id.oc_dot14));
        OC.get(2).add(findViewById(R.id.oc_dot15));
        OC.get(3).add(findViewById(R.id.oc_dot16));
        OC.get(3).add(findViewById(R.id.oc_dot17));
        OC.get(3).add(findViewById(R.id.oc_dot18));
        OC.get(3).add(findViewById(R.id.oc_dot19));
        OC.get(3).add(findViewById(R.id.oc_dot20));
        OC.get(3).add(findViewById(R.id.oc_dot21));
        OC.get(3).add(findViewById(R.id.oc_dot22));
        OC.get(3).add(findViewById(R.id.oc_dot23));
        OC.get(3).add(findViewById(R.id.oc_dot24));
        OC.get(3).add(findViewById(R.id.oc_dot25));
        OC.get(3).add(findViewById(R.id.oc_dot26));
        OC.get(3).add(findViewById(R.id.oc_dot27));
        OC.get(4).add(findViewById(R.id.oc_dot28));
        OC.get(4).add(findViewById(R.id.oc_dot29));
        OC.get(4).add(findViewById(R.id.oc_dot30));
        OC.get(4).add(findViewById(R.id.oc_dot31));
        OC.get(4).add(findViewById(R.id.oc_dot32));
        OC.get(4).add(findViewById(R.id.oc_dot33));
        OC.get(4).add(findViewById(R.id.oc_dot34));
        OC.get(4).add(findViewById(R.id.oc_dot35));
        OC.get(4).add(findViewById(R.id.oc_dot36));
        OC.get(4).add(findViewById(R.id.oc_dot37));
        OC.get(4).add(findViewById(R.id.oc_dot38));
        OC.get(4).add(findViewById(R.id.oc_dot39));
        OC.get(4).add(findViewById(R.id.oc_dot40));
        OC.get(4).add(findViewById(R.id.oc_dot41));
        OC.get(4).add(findViewById(R.id.oc_dot42));
        OC.get(5).add(findViewById(R.id.oc_dot43));
        OC.get(5).add(findViewById(R.id.oc_dot44));
        OC.get(5).add(findViewById(R.id.oc_dot45));
        OC.get(5).add(findViewById(R.id.oc_dot46));
        OC.get(5).add(findViewById(R.id.oc_dot47));
        OC.get(5).add(findViewById(R.id.oc_dot48));
        OC.get(5).add(findViewById(R.id.oc_dot49));
        OC.get(5).add(findViewById(R.id.oc_dot50));
        OC.get(5).add(findViewById(R.id.oc_dot51));
        OC.get(5).add(findViewById(R.id.oc_dot52));
        OC.get(5).add(findViewById(R.id.oc_dot53));
        OC.get(5).add(findViewById(R.id.oc_dot54));
        OC.get(5).add(findViewById(R.id.oc_dot55));
        OC.get(5).add(findViewById(R.id.oc_dot56));
        OC.get(5).add(findViewById(R.id.oc_dot57));
        OC.get(5).add(findViewById(R.id.oc_dot58));
        OC.get(5).add(findViewById(R.id.oc_dot59));
        OC.get(6).add(findViewById(R.id.oc_dot60));
        OC.get(6).add(findViewById(R.id.oc_dot61));
        OC.get(6).add(findViewById(R.id.oc_dot62));
        OC.get(6).add(findViewById(R.id.oc_dot63));
        OC.get(6).add(findViewById(R.id.oc_dot64));
        OC.get(6).add(findViewById(R.id.oc_dot65));
        OC.get(6).add(findViewById(R.id.oc_dot66));
        OC.get(6).add(findViewById(R.id.oc_dot67));
        OC.get(6).add(findViewById(R.id.oc_dot68));
        OC.get(6).add(findViewById(R.id.oc_dot69));
        OC.get(6).add(findViewById(R.id.oc_dot70));
        OC.get(6).add(findViewById(R.id.oc_dot71));
        OC.get(6).add(findViewById(R.id.oc_dot72));
        OC.get(6).add(findViewById(R.id.oc_dot73));
        OC.get(6).add(findViewById(R.id.oc_dot74));
        OC.get(6).add(findViewById(R.id.oc_dot75));
        OC.get(6).add(findViewById(R.id.oc_dot76));
        OC.get(6).add(findViewById(R.id.oc_dot77));
        OC.get(7).add(findViewById(R.id.oc_dot78));
        OC.get(7).add(findViewById(R.id.oc_dot79));
        OC.get(7).add(findViewById(R.id.oc_dot80));
        OC.get(7).add(findViewById(R.id.oc_dot81));
        OC.get(7).add(findViewById(R.id.oc_dot82));
        OC.get(7).add(findViewById(R.id.oc_dot83));
        OC.get(7).add(findViewById(R.id.oc_dot84));
        OC.get(7).add(findViewById(R.id.oc_dot85));
        OC.get(7).add(findViewById(R.id.oc_dot86));
        OC.get(7).add(findViewById(R.id.oc_dot87));
        OC.get(7).add(findViewById(R.id.oc_dot88));
        OC.get(7).add(findViewById(R.id.oc_dot89));
        OC.get(7).add(findViewById(R.id.oc_dot90));
        OC.get(7).add(findViewById(R.id.oc_dot91));
        OC.get(7).add(findViewById(R.id.oc_dot92));
        OC.get(7).add(findViewById(R.id.oc_dot93));
        OC.get(7).add(findViewById(R.id.oc_dot94));
        OC.get(7).add(findViewById(R.id.oc_dot95));
        OC.get(8).add(findViewById(R.id.oc_dot96));
        OC.get(8).add(findViewById(R.id.oc_dot97));
        OC.get(8).add(findViewById(R.id.oc_dot98));
        OC.get(8).add(findViewById(R.id.oc_dot99));
        OC.get(8).add(findViewById(R.id.oc_dot100));
        OC.get(8).add(findViewById(R.id.oc_dot101));
        OC.get(8).add(findViewById(R.id.oc_dot102));
        OC.get(8).add(findViewById(R.id.oc_dot103));
        OC.get(8).add(findViewById(R.id.oc_dot104));
        OC.get(8).add(findViewById(R.id.oc_dot105));
        OC.get(8).add(findViewById(R.id.oc_dot106));
        OC.get(8).add(findViewById(R.id.oc_dot107));
        OC.get(8).add(findViewById(R.id.oc_dot108));
        OC.get(8).add(findViewById(R.id.oc_dot109));
        OC.get(8).add(findViewById(R.id.oc_dot110));
        OC.get(8).add(findViewById(R.id.oc_dot111));
        OC.get(8).add(findViewById(R.id.oc_dot112));
        OC.get(8).add(findViewById(R.id.oc_dot113));
        OC.get(9).add(findViewById(R.id.oc_dot114));
        OC.get(9).add(findViewById(R.id.oc_dot115));
        OC.get(9).add(findViewById(R.id.oc_dot116));
        OC.get(9).add(findViewById(R.id.oc_dot117));
        OC.get(9).add(findViewById(R.id.oc_dot118));
        OC.get(9).add(findViewById(R.id.oc_dot119));
        OC.get(9).add(findViewById(R.id.oc_dot120));
        OC.get(9).add(findViewById(R.id.oc_dot121));
        OC.get(9).add(findViewById(R.id.oc_dot122));
        OC.get(9).add(findViewById(R.id.oc_dot123));
        OC.get(9).add(findViewById(R.id.oc_dot124));
        OC.get(9).add(findViewById(R.id.oc_dot125));
        OC.get(9).add(findViewById(R.id.oc_dot126));
        OC.get(9).add(findViewById(R.id.oc_dot127));
        OC.get(9).add(findViewById(R.id.oc_dot128));
        OC.get(9).add(findViewById(R.id.oc_dot129));
        OC.get(9).add(findViewById(R.id.oc_dot130));
        OC.get(9).add(findViewById(R.id.oc_dot131));
        OC.get(10).add(findViewById(R.id.oc_dot132));
        OC.get(10).add(findViewById(R.id.oc_dot133));
        OC.get(10).add(findViewById(R.id.oc_dot134));
        OC.get(10).add(findViewById(R.id.oc_dot135));
        OC.get(10).add(findViewById(R.id.oc_dot136));
        OC.get(10).add(findViewById(R.id.oc_dot137));
        OC.get(10).add(findViewById(R.id.oc_dot138));
        OC.get(10).add(findViewById(R.id.oc_dot139));
        OC.get(10).add(findViewById(R.id.oc_dot140));
        OC.get(10).add(findViewById(R.id.oc_dot141));
        OC.get(10).add(findViewById(R.id.oc_dot142));
        OC.get(10).add(findViewById(R.id.oc_dot143));
        OC.get(10).add(findViewById(R.id.oc_dot144));
        OC.get(10).add(findViewById(R.id.oc_dot145));
        OC.get(10).add(findViewById(R.id.oc_dot146));
        OC.get(10).add(findViewById(R.id.oc_dot147));
        OC.get(10).add(findViewById(R.id.oc_dot148));
        OC.get(11).add(findViewById(R.id.oc_dot149));
        OC.get(11).add(findViewById(R.id.oc_dot150));
        OC.get(11).add(findViewById(R.id.oc_dot151));
        OC.get(11).add(findViewById(R.id.oc_dot152));
        OC.get(11).add(findViewById(R.id.oc_dot153));
        OC.get(11).add(findViewById(R.id.oc_dot154));
        OC.get(11).add(findViewById(R.id.oc_dot155));
        OC.get(11).add(findViewById(R.id.oc_dot156));
        OC.get(11).add(findViewById(R.id.oc_dot157));
        OC.get(11).add(findViewById(R.id.oc_dot158));
        OC.get(12).add(findViewById(R.id.oc_dot159));
        OC.get(12).add(findViewById(R.id.oc_dot160));
        OC.get(12).add(findViewById(R.id.oc_dot161));
        OC.get(12).add(findViewById(R.id.oc_dot162));
        OC.get(12).add(findViewById(R.id.oc_dot163));
        OC.get(13).add(findViewById(R.id.oc_dot164));
        OC.get(13).add(findViewById(R.id.oc_dot165));
        OC.get(13).add(findViewById(R.id.oc_dot166));
        OC.get(13).add(findViewById(R.id.oc_dot167));
        OC.get(13).add(findViewById(R.id.oc_dot168));
        OC.get(14).add(findViewById(R.id.oc_dot169));
        OC.get(15).add(findViewById(R.id.oc_dot170));
        OC.get(16).add(findViewById(R.id.oc_dot171));
        OC.get(16).add(findViewById(R.id.oc_dot172));
        OC.get(17).add(findViewById(R.id.oc_dot173));
        OC.get(17).add(findViewById(R.id.oc_dot174));


    }
    
    public void infection(Infection inf, ArrayList<ArrayList<View>> region, int id) {

        //Log.d("infection", "id:  " + id);

        boolean infected = inf.isInfected();

            if (infected) {
                greenLoop(region,id);
            } else if (!pulsing[id]) {
                pulse(region,inf,id);
                /*switch (id) {
                    case 0:
                        pulseAS(region,inf,id);
                        break;
                    case 1:
                        pulseOC(region,inf,id);
                        break;
                    case 2:
                        pulseCA(region,inf,id);
                        break;
                    case 3:
                        pulseEU(region,inf,id);
                        break;
                    case 4:
                        pulseME(region,inf,id);
                        break;
                    case 5:
                        pulseNF(region,inf,id);
                    case 6:
                        pulse(region, inf, id);
                        break;
                    case 7:
                        pulse(region, inf, id);
                        break;
                    case 8:
                        pulseSF(region,inf,id);
                        break;
                    case 9:
                        pulseSA(region, inf, id);
                        break;
                }*/
                pulsing[id] = true;
            }

    }
    public Infection checkRegion(long t, int id) {
        return infections[id];
    }

    private void greenLoop(ArrayList<ArrayList<View>> region,int id){
        for (int i = 0; i < rows[id]; i++) {
            for (int p = 0; p < region.get(i).size(); p++) {
                region.get(i).get(p).setBackgroundResource(R.drawable.green_dot);
            }
        }
    }
    final Counter[] counters = {new Counter(34),new Counter(18),new Counter(12),new Counter(19),new Counter(15),new Counter(19),new Counter(26),new Counter(19),new Counter(20),new Counter(34)};
    /*final Counter NAcounter = new Counter(26);
    final Counter CAcounter = new Counter(12);
    final Counter SAcounter = new Counter(34);
    final Counter EUcounter = new Counter(19);
    final Counter RScounter = new Counter(19);
    final Counter MEcounter = new Counter(15);
    final Counter AScounter = new Counter(34);
    final Counter NFcounter = new Counter(19);
    final Counter SFcounter = new Counter(20);
    final Counter OCcounter = new Counter(18);*/
    
    boolean[] pulsing;

    public void pulse(ArrayList<ArrayList<View>> c, final Infection infection, final int id) {
        final ArrayList<ArrayList<View>> dotty = c;

        Log.d("pulse", "id:  " + id);
        final int idNum=id;
        int max = 0;

        for (int i = 0; i < rows[id]; i++) {
            if (dotty.get(i).size() > dotty.get(max).size()) {
                max = i;
            }
        }
        final int maxindex = max;

        final int[] middles = new int[rows[id]];

        for (int i = 0; i < rows[id]; i++) {
            middles[i] = (dotty.get(i).size() / 4);
            //Log.d("size", "      " + dotty.get(i).size());
        }

        counters[id].setHcount(middles);

        Thread p = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        while (!counters[id].isDone()) {
                            Thread.sleep((long) (interval * .4));
                            runOnUiThread(new Runnable() {


                                @Override
                                public void run() {
                                    if (!suspended && !paused[idNum]) {

                                        //Log.d("test", "        " + counters[id].getHcount()[maxindex]);

                                        if(infections[id].isInfected()) {
                                            greenLoop(dotty,id);
                                        }

                                        else if (counters[id].getHcount()[maxindex] >= maxes[idNum]*infections[idNum].getPerc()/4+maxes[idNum]/2) {
                                            for (int i = 0; i < rows[idNum]; i++) {
                                                for (int p = 0; p < dotty.get(i).size(); p++) {
                                                    dotty.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                                }
                                            }
                                            //Log.d("blue loop", "ran");
                                            counters[id].setHcount(middles);
                                        } else {
                                            for (int i = 0; i < rows[idNum]; i++) {

                                                //Log.d("i,counters[id]", i + "," + counters[id].getHcount()[i]);

                                                if ((counters[id].getHcount()[i] < dotty.get(i).size())) {
                                                    dotty.get(i).get(counters[id].getHcount()[i]).setBackgroundResource(R.drawable.green_dot);
                                                }
                                                if (dotty.get(i).size() - counters[id].getHcount()[i] - 1 > 0) {
                                                    dotty.get(i).get(dotty.get(i).size() - counters[id].getHcount()[i] - 1).setBackgroundResource(R.drawable.green_dot);
                                                }

                                                counters[id].hrise(i);

                                            }
                                        }
                                    }
                                }
                            });

                        }

                    }
                } catch (InterruptedException e) {
                }
            }
        };

        p.start();

    }

   /* public void pulseCA(ArrayList<ArrayList<View>> c, final Infection infection, final int id) {
        final ArrayList<ArrayList<View>> dotty = c;

        Log.d("pulse", "id:  " + id);
        final int idNum=id;
        int max = 0;

        for (int i = 0; i < rows[id]; i++) {
            if (dotty.get(i).size() > dotty.get(max).size()) {
                max = i;
            }
        }
        final int maxindex = max;

        final int[] middles = new int[rows[id]];

        for (int i = 0; i < rows[id]; i++) {
            middles[i] = (dotty.get(i).size() / 4);
            //Log.d("size", "      " + dotty.get(i).size());
        }

        CAcounter.setHcount(middles);

        Thread ca = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        while (!CAcounter.isDone()) {
                            Thread.sleep((long) (interval * .4));
                            runOnUiThread(new Runnable() {


                                @Override
                                public void run() {
                                    if (!suspended && !paused[idNum]) {

                                        //Log.d("thread", "        " + infections[6].getPerc());

                                        if(infections[id].isInfected()) {
                                            greenLoop(CA,id);
                                        }

                                        else if (CAcounter.getHcount()[maxindex] >= maxes[idNum]*infections[idNum].getPerc()/4+maxes[idNum]/2) {
                                            for (int i = 0; i < rows[idNum]; i++) {
                                                for (int p = 0; p < dotty.get(i).size(); p++) {
                                                    dotty.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                                }
                                            }
                                            //Log.d("blue loop", "ran");
                                            CAcounter.setHcount(middles);
                                        } else {
                                            for (int i = 0; i < rows[idNum]; i++) {

                                                //Log.d("i,NAcounter", i + "," + NAcounter.getHcount()[i]);

                                                if ((CAcounter.getHcount()[i] < dotty.get(i).size())) {
                                                    dotty.get(i).get(CAcounter.getHcount()[i]).setBackgroundResource(R.drawable.green_dot);
                                                }
                                                if (dotty.get(i).size() - CAcounter.getHcount()[i] - 1 > 0) {
                                                    dotty.get(i).get(dotty.get(i).size() - CAcounter.getHcount()[i] - 1).setBackgroundResource(R.drawable.green_dot);
                                                }

                                                CAcounter.hrise(i);

                                            }
                                        }
                                    }
                                }
                            });

                        }

                    }
                } catch (InterruptedException e) {
                }
            }
        };

        ca.start();

    }

    public void pulseSA(ArrayList<ArrayList<View>> c, final Infection infection, final int id) {
        final ArrayList<ArrayList<View>> dotty = c;

        Log.d("pulse", "id:  " + id);
        final int idNum=id;
        int max = 0;

        for (int i = 0; i < rows[id]; i++) {
            if (dotty.get(i).size() > dotty.get(max).size()) {
                max = i;
            }
        }
        final int maxindex = max;

        final int[] middles = new int[rows[id]];

        for (int i = 0; i < rows[id]; i++) {
            middles[i] = (dotty.get(i).size() / 4);
            //Log.d("size", "      " + dotty.get(i).size());
        }

        SAcounter.setHcount(middles);

        Thread sa = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        while (!SAcounter.isDone()) {
                            Thread.sleep((long) (interval * .4));
                            runOnUiThread(new Runnable() {


                                @Override
                                public void run() {
                                    if (!suspended && !paused[idNum]) {

                                        //Log.d("thread", "        " + infections[6].getPerc());

                                        if(infections[id].isInfected()) {
                                            greenLoop(SA,id);
                                        }

                                        else if (SAcounter.getHcount()[maxindex] >= maxes[idNum]*infections[idNum].getPerc()/4+maxes[idNum]/2) {
                                            for (int i = 0; i < rows[idNum]; i++) {
                                                for (int p = 0; p < dotty.get(i).size(); p++) {
                                                    dotty.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                                }
                                            }
                                            //Log.d("blue loop", "ran");
                                            SAcounter.setHcount(middles);
                                        } else {
                                            for (int i = 0; i < rows[idNum]; i++) {

                                                //Log.d("i,NAcounter", i + "," + NAcounter.getHcount()[i]);

                                                if ((SAcounter.getHcount()[i] < dotty.get(i).size())) {
                                                    dotty.get(i).get(SAcounter.getHcount()[i]).setBackgroundResource(R.drawable.green_dot);
                                                }
                                                if (dotty.get(i).size() - SAcounter.getHcount()[i] - 1 > 0) {
                                                    dotty.get(i).get(dotty.get(i).size() - SAcounter.getHcount()[i] - 1).setBackgroundResource(R.drawable.green_dot);
                                                }

                                                SAcounter.hrise(i);

                                            }
                                        }
                                    }
                                }
                            });

                        }

                    }
                } catch (InterruptedException e) {
                }
            }
        };

        sa.start();

    }

    public void pulseEU(ArrayList<ArrayList<View>> c, final Infection infection, final int id) {
        final ArrayList<ArrayList<View>> dotty = c;

        Log.d("pulse", "id:  " + id);
        final int idNum=id;
        int max = 0;

        for (int i = 0; i < rows[id]; i++) {
            if (dotty.get(i).size() > dotty.get(max).size()) {
                max = i;
            }
        }
        final int maxindex = max;

        final int[] middles = new int[rows[id]];

        for (int i = 0; i < rows[id]; i++) {
            middles[i] = (dotty.get(i).size() / 4);
            //Log.d("size", "      " + dotty.get(i).size());
        }

        EUcounter.setHcount(middles);

        Thread eu = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        while (!EUcounter.isDone()) {
                            Thread.sleep((long) (interval * .4));
                            runOnUiThread(new Runnable() {


                                @Override
                                public void run() {
                                    if (!suspended && !paused[idNum]) {

                                        //Log.d("thread", "        " + infections[6].getPerc());

                                        if(infections[id].isInfected()) {
                                            greenLoop(EU,id);
                                        }

                                        else if (EUcounter.getHcount()[maxindex] >= maxes[idNum]*infections[idNum].getPerc()/4+maxes[idNum]/2) {
                                            for (int i = 0; i < rows[idNum]; i++) {
                                                for (int p = 0; p < dotty.get(i).size(); p++) {
                                                    dotty.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                                }
                                            }
                                            //Log.d("blue loop", "ran");
                                            EUcounter.setHcount(middles);
                                        } else {
                                            for (int i = 0; i < rows[idNum]; i++) {

                                                //Log.d("i,NAcounter", i + "," + NAcounter.getHcount()[i]);

                                                if ((EUcounter.getHcount()[i] < dotty.get(i).size())) {
                                                    dotty.get(i).get(EUcounter.getHcount()[i]).setBackgroundResource(R.drawable.green_dot);
                                                }
                                                if (dotty.get(i).size() - EUcounter.getHcount()[i] - 1 > 0) {
                                                    dotty.get(i).get(dotty.get(i).size() - EUcounter.getHcount()[i] - 1).setBackgroundResource(R.drawable.green_dot);
                                                }

                                                EUcounter.hrise(i);

                                            }
                                        }
                                    }
                                }
                            });

                        }

                    }
                } catch (InterruptedException e) {
                }
            }
        };

        eu.start();

    }

    public void pulseRS(ArrayList<ArrayList<View>> c, final Infection infection, final int id) {
        final ArrayList<ArrayList<View>> dotty = c;

        Log.d("pulse", "id:  " + id);
        final int idNum=id;
        int max = 0;

        for (int i = 0; i < rows[id]; i++) {
            if (dotty.get(i).size() > dotty.get(max).size()) {
                max = i;
            }
        }
        final int maxindex = max;

        final int[] middles = new int[rows[id]];

        for (int i = 0; i < rows[id]; i++) {
            middles[i] = (dotty.get(i).size() / 4);
            //Log.d("size", "      " + dotty.get(i).size());
        }

        RScounter.setHcount(middles);

        Thread rs = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        while (!RScounter.isDone()) {
                            Thread.sleep((long) (interval * .4));
                            runOnUiThread(new Runnable() {


                                @Override
                                public void run() {
                                    if (!suspended && !paused[idNum]) {

                                        //Log.d("thread", "        " + infections[6].getPerc());

                                        if(infections[id].isInfected()) {
                                            greenLoop(RS,id);
                                        }

                                        else if (RScounter.getHcount()[maxindex] >= maxes[idNum]*infections[idNum].getPerc()/4+maxes[idNum]/2) {
                                            for (int i = 0; i < rows[idNum]; i++) {
                                                for (int p = 0; p < dotty.get(i).size(); p++) {
                                                    dotty.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                                }
                                            }
                                            //Log.d("blue loop", "ran");
                                            RScounter.setHcount(middles);
                                        } else {
                                            for (int i = 0; i < rows[idNum]; i++) {

                                                //Log.d("i,NAcounter", i + "," + NAcounter.getHcount()[i]);

                                                if ((RScounter.getHcount()[i] < dotty.get(i).size())) {
                                                    dotty.get(i).get(RScounter.getHcount()[i]).setBackgroundResource(R.drawable.green_dot);
                                                }
                                                if (dotty.get(i).size() - RScounter.getHcount()[i] - 1 > 0) {
                                                    dotty.get(i).get(dotty.get(i).size() - RScounter.getHcount()[i] - 1).setBackgroundResource(R.drawable.green_dot);
                                                }

                                                RScounter.hrise(i);

                                            }
                                        }
                                    }
                                }
                            });

                        }

                    }
                } catch (InterruptedException e) {
                }
            }
        };

        rs.start();

    }

    public void pulseME(ArrayList<ArrayList<View>> c, final Infection infection, final int id) {
        final ArrayList<ArrayList<View>> dotty = c;

        Log.d("pulse", "id:  " + id);
        final int idNum=id;
        int max = 0;

        for (int i = 0; i < rows[id]; i++) {
            if (dotty.get(i).size() > dotty.get(max).size()) {
                max = i;
            }
        }
        final int maxindex = max;

        final int[] middles = new int[rows[id]];

        for (int i = 0; i < rows[id]; i++) {
            middles[i] = (dotty.get(i).size() / 4);
            //Log.d("size", "      " + dotty.get(i).size());
        }

        MEcounter.setHcount(middles);

        Thread me = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        while (!MEcounter.isDone()) {
                            Thread.sleep((long) (interval * .4));
                            runOnUiThread(new Runnable() {


                                @Override
                                public void run() {
                                    if (!suspended && !paused[idNum]) {

                                        //Log.d("thread", "        " + infections[6].getPerc());

                                        if(infections[id].isInfected()) {
                                            greenLoop(ME,id);
                                        }

                                        else if (MEcounter.getHcount()[maxindex] >= maxes[idNum]*infections[idNum].getPerc()/4+maxes[idNum]/2) {
                                            for (int i = 0; i < rows[idNum]; i++) {
                                                for (int p = 0; p < dotty.get(i).size(); p++) {
                                                    dotty.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                                }
                                            }
                                            //Log.d("blue loop", "ran");
                                            MEcounter.setHcount(middles);
                                        } else {
                                            for (int i = 0; i < rows[idNum]; i++) {

                                                //Log.d("i,NAcounter", i + "," + NAcounter.getHcount()[i]);

                                                if ((MEcounter.getHcount()[i] < dotty.get(i).size())) {
                                                    dotty.get(i).get(MEcounter.getHcount()[i]).setBackgroundResource(R.drawable.green_dot);
                                                }
                                                if (dotty.get(i).size() - MEcounter.getHcount()[i] - 1 > 0) {
                                                    dotty.get(i).get(dotty.get(i).size() - MEcounter.getHcount()[i] - 1).setBackgroundResource(R.drawable.green_dot);
                                                }

                                                MEcounter.hrise(i);

                                            }
                                        }
                                    }
                                }
                            });

                        }

                    }
                } catch (InterruptedException e) {
                }
            }
        };

        me.start();

    }

    public void pulseAS(ArrayList<ArrayList<View>> c, final Infection infection, final int id) {
        final ArrayList<ArrayList<View>> dotty = c;

        Log.d("pulse", "id:  " + id);
        final int idNum=id;
        int max = 0;

        for (int i = 0; i < rows[id]; i++) {
            if (dotty.get(i).size() > dotty.get(max).size()) {
                max = i;
            }
        }
        final int maxindex = max;

        final int[] middles = new int[rows[id]];

        for (int i = 0; i < rows[id]; i++) {
            middles[i] = (dotty.get(i).size() / 4);
            //Log.d("size", "      " + dotty.get(i).size());
        }

        AScounter.setHcount(middles);

        Thread me = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        while (!AScounter.isDone()) {
                            Thread.sleep((long) (interval * .4));
                            runOnUiThread(new Runnable() {


                                @Override
                                public void run() {
                                    if (!suspended && !paused[idNum]) {

                                        //Log.d("thread", "        " + infections[6].getPerc());

                                        if(infections[id].isInfected()) {
                                            greenLoop(AS,id);
                                        }

                                        else if (AScounter.getHcount()[maxindex] >= maxes[idNum]*infections[idNum].getPerc()/4+maxes[idNum]/2) {
                                            for (int i = 0; i < rows[idNum]; i++) {
                                                for (int p = 0; p < dotty.get(i).size(); p++) {
                                                    dotty.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                                }
                                            }
                                            //Log.d("blue loop", "ran");
                                            AScounter.setHcount(middles);
                                        } else {
                                            for (int i = 0; i < rows[idNum]; i++) {

                                                //Log.d("i,NAcounter", i + "," + NAcounter.getHcount()[i]);

                                                if ((AScounter.getHcount()[i] < dotty.get(i).size())) {
                                                    dotty.get(i).get(AScounter.getHcount()[i]).setBackgroundResource(R.drawable.green_dot);
                                                }
                                                if (dotty.get(i).size() - AScounter.getHcount()[i] - 1 > 0) {
                                                    dotty.get(i).get(dotty.get(i).size() - AScounter.getHcount()[i] - 1).setBackgroundResource(R.drawable.green_dot);
                                                }

                                                AScounter.hrise(i);

                                            }
                                        }
                                    }
                                }
                            });

                        }

                    }
                } catch (InterruptedException e) {
                }
            }
        };

        me.start();

    }

    public void pulseNF(ArrayList<ArrayList<View>> c, final Infection infection, final int id) {
        final ArrayList<ArrayList<View>> dotty = c;

        Log.d("pulse", "id:  " + id);
        final int idNum=id;
        int max = 0;

        for (int i = 0; i < rows[id]; i++) {
            if (dotty.get(i).size() > dotty.get(max).size()) {
                max = i;
            }
        }
        final int maxindex = max;

        final int[] middles = new int[rows[id]];

        for (int i = 0; i < rows[id]; i++) {
            middles[i] = (dotty.get(i).size() / 4);
            //Log.d("size", "      " + dotty.get(i).size());
        }

        NFcounter.setHcount(middles);

        Thread me = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        while (!NFcounter.isDone()) {
                            Thread.sleep((long) (interval * .4));
                            runOnUiThread(new Runnable() {


                                @Override
                                public void run() {
                                    if (!suspended && !paused[idNum]) {

                                        //Log.d("thread", "        " + infections[6].getPerc());

                                        if(infections[id].isInfected()) {
                                            greenLoop(NF,id);
                                        }

                                        else if (NFcounter.getHcount()[maxindex] >= maxes[idNum]*infections[idNum].getPerc()/4+maxes[idNum]/2) {
                                            for (int i = 0; i < rows[idNum]; i++) {
                                                for (int p = 0; p < dotty.get(i).size(); p++) {
                                                    dotty.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                                }
                                            }
                                            //Log.d("blue loop", "ran");
                                            NFcounter.setHcount(middles);
                                        } else {
                                            for (int i = 0; i < rows[idNum]; i++) {

                                                //Log.d("i,NAcounter", i + "," + NAcounter.getHcount()[i]);

                                                if ((NFcounter.getHcount()[i] < dotty.get(i).size())) {
                                                    dotty.get(i).get(NFcounter.getHcount()[i]).setBackgroundResource(R.drawable.green_dot);
                                                }
                                                if (dotty.get(i).size() - NFcounter.getHcount()[i] - 1 > 0) {
                                                    dotty.get(i).get(dotty.get(i).size() - NFcounter.getHcount()[i] - 1).setBackgroundResource(R.drawable.green_dot);
                                                }

                                                NFcounter.hrise(i);

                                            }
                                        }
                                    }
                                }
                            });

                        }

                    }
                } catch (InterruptedException e) {
                }
            }
        };

        me.start();

    }

    public void pulseSF(ArrayList<ArrayList<View>> c, final Infection infection, final int id) {
        final ArrayList<ArrayList<View>> dotty = c;

        Log.d("pulse", "id:  " + id);
        final int idNum=id;
        int max = 0;

        for (int i = 0; i < rows[id]; i++) {
            if (dotty.get(i).size() > dotty.get(max).size()) {
                max = i;
            }
        }
        final int maxindex = max;

        final int[] middles = new int[rows[id]];

        for (int i = 0; i < rows[id]; i++) {
            middles[i] = (dotty.get(i).size() / 4);
            //Log.d("size", "      " + dotty.get(i).size());
        }

        SFcounter.setHcount(middles);

        Thread me = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        while (!SFcounter.isDone()) {
                            Thread.sleep((long) (interval * .4));
                            runOnUiThread(new Runnable() {


                                @Override
                                public void run() {
                                    if (!suspended && !paused[idNum]) {

                                        //Log.d("thread", "        " + infections[6].getPerc());
                                        Log.d("test" , "     " +SFcounter.getHcount()[maxindex]);
                                        if(infections[id].isInfected()) {
                                            greenLoop(SF, id);
                                        }

                                        else if (SFcounter.getHcount()[maxindex] >= maxes[idNum]*infections[idNum].getPerc()/4+maxes[idNum]/2) {
                                            for (int i = 0; i < rows[idNum]; i++) {
                                                for (int p = 0; p < dotty.get(i).size(); p++) {
                                                    dotty.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                                }
                                            }
                                            //Log.d("blue loop", "ran");
                                            SFcounter.setHcount(middles);
                                        } else {
                                            for (int i = 0; i < rows[idNum]; i++) {

                                                //Log.d("i,NAcounter", i + "," + NAcounter.getHcount()[i]);

                                                if ((SFcounter.getHcount()[i] < dotty.get(i).size())) {
                                                    dotty.get(i).get(SFcounter.getHcount()[i]).setBackgroundResource(R.drawable.green_dot);
                                                }
                                                if (dotty.get(i).size() - SFcounter.getHcount()[i] - 1 > 0) {
                                                    dotty.get(i).get(dotty.get(i).size() - SFcounter.getHcount()[i] - 1).setBackgroundResource(R.drawable.green_dot);
                                                }

                                                SFcounter.hrise(i);

                                            }
                                        }
                                    }
                                }
                            });

                        }

                    }
                } catch (InterruptedException e) {
                }
            }
        };

        me.start();

    }

    public void pulseOC(ArrayList<ArrayList<View>> c, final Infection infection, final int id) {
        final ArrayList<ArrayList<View>> dotty = c;

        Log.d("pulse", "id:  " + id);
        final int idNum=id;
        int max = 0;

        for (int i = 0; i < rows[id]; i++) {
            if (dotty.get(i).size() > dotty.get(max).size()) {
                max = i;
            }
        }
        final int maxindex = max;

        final int[] middles = new int[rows[id]];

        for (int i = 0; i < rows[id]; i++) {
            middles[i] = (dotty.get(i).size() / 4);
            //Log.d("size", "      " + dotty.get(i).size());
        }

        OCcounter.setHcount(middles);

        Thread me = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        while (!OCcounter.isDone()) {
                            Thread.sleep((long) (interval * .4));
                            runOnUiThread(new Runnable() {


                                @Override
                                public void run() {
                                    if (!suspended && !paused[idNum]) {

                                        //Log.d("thread", "        " + infections[6].getPerc());

                                        if(infections[id].isInfected()) {
                                            greenLoop(OC,id);
                                        }

                                        else if (OCcounter.getHcount()[maxindex] >= maxes[idNum]*infections[idNum].getPerc()/4+maxes[idNum]/2) {
                                            for (int i = 0; i < rows[idNum]; i++) {
                                                for (int p = 0; p < dotty.get(i).size(); p++) {
                                                    dotty.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                                }
                                            }
                                            //Log.d("blue loop", "ran");
                                            OCcounter.setHcount(middles);
                                        } else {
                                            for (int i = 0; i < rows[idNum]; i++) {

                                                //Log.d("i,NAcounter", i + "," + NAcounter.getHcount()[i]);

                                                if ((OCcounter.getHcount()[i] < dotty.get(i).size())) {
                                                    dotty.get(i).get(OCcounter.getHcount()[i]).setBackgroundResource(R.drawable.green_dot);
                                                }
                                                if (dotty.get(i).size() - OCcounter.getHcount()[i] - 1 > 0) {
                                                    dotty.get(i).get(dotty.get(i).size() - OCcounter.getHcount()[i] - 1).setBackgroundResource(R.drawable.green_dot);
                                                }

                                                OCcounter.hrise(i);

                                            }
                                        }
                                    }
                                }
                            });

                        }

                    }
                } catch (InterruptedException e) {
                }
            }
        };

        me.start();

    }*/

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);



        atDawn();



    }


    @Override
    protected void onPause(){
        super.onPause();
        if(!isPaused())
            pause();
        //save to database here

    }

    @Override
    protected void onResume(){
        //get from database
        super.onResume();
        if(isPaused())
            pause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_map_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    boolean speedState = false;//normal speed

    public void speedClick(View view) {
        ImageButton speedButton = (ImageButton) view;
        if (!suspended) {
            if (!speedState) {
                speedButton.setBackgroundResource(R.color.ff_green);
                speedState = true;
                interval = 750;
            } else {
                speedButton.setImageResource(R.drawable.fast_forward);
                speedButton.setBackgroundResource(R.color.speed_grey);
                speedState = false;
                interval = 1000;
            }
        }
    }

    public void pauseClick(View view) {
        pause();
    }

    public void pause() {
        if (interval == 750) {
            speedClick(findViewById(R.id.speed_button));
        }
        ImageButton pauseButton = (ImageButton) findViewById(R.id.pause_button);
        if (!suspended) {
            pauseButton.setImageResource(R.drawable.play);
            suspended = true;

        } else {
            pauseButton.setImageResource(R.drawable.pause);
            suspended = false;
            interval = 1000;
        }
    }

    ImageView[] regionBacks;

    public void NAClick(View view) {

        regionClick(6);

        TextView countryText = (TextView) findViewById(R.id.country_text);
        countryText.setText("North America");
        progressRegion=6;
        setProgressBar(6);
    }
    public void caClick(View view) {
        regionClick(2);

        TextView countryText = (TextView) findViewById(R.id.country_text);
        countryText.setText("Central America");
        progressRegion=2;
        setProgressBar(2);
    }
    public void backClick(View view) {
        regionClick(10);

        TextView countryText = (TextView) findViewById(R.id.country_text);
        countryText.setText("World");
        progressRegion=10;
        setProgressBar(10);
    }
    public void saClick(View view){
        regionClick(9);

        TextView countryText = (TextView) findViewById(R.id.country_text);
        countryText.setText("South America");
        progressRegion=3;
        setProgressBar(3);
    }
    public void euClick(View view){
        regionClick(3);

        TextView countryText = (TextView) findViewById(R.id.country_text);
        countryText.setText("Europe");
        progressRegion=9;
        setProgressBar(9);
    }
    public void rsClick(View view){
        regionClick(7);

        TextView countryText = (TextView) findViewById(R.id.country_text);
        countryText.setText("Russia");
        progressRegion=7;
        setProgressBar(7);
    }
    public void meClick(View view){
        regionClick(4);

        TextView countryText = (TextView) findViewById(R.id.country_text);
        countryText.setText("Middle East");
        progressRegion=4;
        setProgressBar(4);
    }
    public void asClick(View view){
        regionClick(0);

        TextView countryText = (TextView) findViewById(R.id.country_text);
        countryText.setText("Asia");
        progressRegion=0;
        setProgressBar(0);
    }
    public void nfClick(View view){
        regionClick(5);

        TextView countryText = (TextView) findViewById(R.id.country_text);
        countryText.setText("Northern Africa");
        progressRegion=5;
        setProgressBar(5);
    }
    public void sfClick(View view){
        regionClick(8);

        TextView countryText = (TextView) findViewById(R.id.country_text);
        countryText.setText("Southern Africa");
        progressRegion=8;
        setProgressBar(8);
    }
    public void ocClick(View view){
        regionClick(1);

        TextView countryText = (TextView) findViewById(R.id.country_text);
        countryText.setText("Australia and Oceania");
        progressRegion=1;
        setProgressBar(1);
    }
    public void regionClick(int id){
        for(ImageView view: regionBacks)
            if(view!= null)
                view.setImageResource(R.drawable.clear_round_rect);

        if(id!=10)
            regionBacks[id].setImageResource(R.drawable.red_round_rect);
    }
    public boolean isPaused(){
        return suspended;
    }

    public void click80(View view) {
        for(int i = 0; i< infections.length; i++)
            infections[i].setPerc(.8);
    }
    public void click50(View view) {
        for(int i = 0; i< infections.length; i++)
            infections[i].setPerc(.5);
    }
    public void click20(View view) {
        for(int i = 0; i< infections.length; i++)
            infections[i].setPerc(.2);
    }
    public void click100(View view) {
        for(int i = 0; i< infections.length; i++) {
            infections[i].setPerc(1.0);
            infections[i].setInfected(true);
        }
    }

    final double[] regionPercs = {0.4109173251, .01647451496, 0.01865028259, 0.2105428825, 0.02960426752, 0.02028537098, 0.1958554289, 0.02028537098, 0.05969140798, 0.05969140798};
    public double calcWorldPerc() {
        double count=0.0;
        for(int i = 0; i< infections.length;i++) {
            count =count + infections[i].getPerc() * regionPercs[i];
            //Log.d("region" + i, "    "+ infections[i].getPerc());
        }
        return count;
    }
    public void setProgressBar(int id) {

        double perc = 0;

        if(id!=10)
            perc = infections[id].getPerc();
        else if(id == 10)
            perc = worldPerc;


        int prog = ((int)(perc*100)-1);
        if(prog>=100)
            prog=99;

        //Log.d("prog","         "+ prog);
        for(int i = 0; i<=prog; i++)
            progress[i].setBackgroundResource(R.color.virusGreen);

        if(prog<99)
            progress[prog+1].setBackgroundResource(R.color.pbgreen1);
        if(prog<98)
            progress[prog+2].setBackgroundResource(R.color.pbgreen2);
        if(prog<97)
            progress[prog+3].setBackgroundResource(R.color.pbgreen3);
        if(prog<96)
            progress[prog+4].setBackgroundResource(R.color.pbgreen4);
        if(prog<95)
            progress[prog+5].setBackgroundResource(R.color.pbgreen5);
        if(prog<94)
            progress[prog+6].setBackgroundResource(R.color.pbgreen6);
        if(prog<93)
            progress[prog+7].setBackgroundResource(R.color.pbgreen7);
        if(prog<92)
            progress[prog+8].setBackgroundResource(R.color.pbgreen8);
        if(prog<91) {
            progress[prog + 9].setBackgroundResource(R.color.pbgreen9);

            for (int i = prog + 10; i <= 99; i++)
                progress[i].setBackgroundResource(R.color.black);
        }
    }
    View[] progress;
    public void buildProgress(){
        progress = new View[100];

        progress[0] = findViewById(R.id.pb1);
        progress[1] = findViewById(R.id.pb2);
        progress[2] = findViewById(R.id.pb3);
        progress[3] = findViewById(R.id.pb4);
        progress[4] = findViewById(R.id.pb5);
        progress[5] = findViewById(R.id.pb6);
        progress[6] = findViewById(R.id.pb7);
        progress[7] = findViewById(R.id.pb8);
        progress[8] = findViewById(R.id.pb9);
        progress[9] = findViewById(R.id.pb10);
        progress[10] = findViewById(R.id.pb11);
        progress[11] = findViewById(R.id.pb12);
        progress[12] = findViewById(R.id.pb13);
        progress[13] = findViewById(R.id.pb14);
        progress[14] = findViewById(R.id.pb15);
        progress[15] = findViewById(R.id.pb16);
        progress[16] = findViewById(R.id.pb17);
        progress[17] = findViewById(R.id.pb18);
        progress[18] = findViewById(R.id.pb19);
        progress[19] = findViewById(R.id.pb20);
        progress[20] = findViewById(R.id.pb21);
        progress[21] = findViewById(R.id.pb22);
        progress[22] = findViewById(R.id.pb23);
        progress[23] = findViewById(R.id.pb24);
        progress[24] = findViewById(R.id.pb25);
        progress[25] = findViewById(R.id.pb26);
        progress[26] = findViewById(R.id.pb27);
        progress[27] = findViewById(R.id.pb28);
        progress[28] = findViewById(R.id.pb29);
        progress[29] = findViewById(R.id.pb30);
        progress[30] = findViewById(R.id.pb31);
        progress[31] = findViewById(R.id.pb32);
        progress[32] = findViewById(R.id.pb33);
        progress[33] = findViewById(R.id.pb34);
        progress[34] = findViewById(R.id.pb35);
        progress[35] = findViewById(R.id.pb36);
        progress[36] = findViewById(R.id.pb37);
        progress[37] = findViewById(R.id.pb38);
        progress[38] = findViewById(R.id.pb39);
        progress[39] = findViewById(R.id.pb40);
        progress[40] = findViewById(R.id.pb41);
        progress[41] = findViewById(R.id.pb42);
        progress[42] = findViewById(R.id.pb43);
        progress[43] = findViewById(R.id.pb44);
        progress[44] = findViewById(R.id.pb45);
        progress[45] = findViewById(R.id.pb46);
        progress[46] = findViewById(R.id.pb47);
        progress[47] = findViewById(R.id.pb48);
        progress[48] = findViewById(R.id.pb49);
        progress[49] = findViewById(R.id.pb50);
        progress[50] = findViewById(R.id.pb51);
        progress[51] = findViewById(R.id.pb52);
        progress[52] = findViewById(R.id.pb53);
        progress[53] = findViewById(R.id.pb54);
        progress[54] = findViewById(R.id.pb55);
        progress[55] = findViewById(R.id.pb56);
        progress[56] = findViewById(R.id.pb57);
        progress[57] = findViewById(R.id.pb58);
        progress[58] = findViewById(R.id.pb59);
        progress[59] = findViewById(R.id.pb60);
        progress[60] = findViewById(R.id.pb61);
        progress[61] = findViewById(R.id.pb62);
        progress[62] = findViewById(R.id.pb63);
        progress[63] = findViewById(R.id.pb64);
        progress[64] = findViewById(R.id.pb65);
        progress[65] = findViewById(R.id.pb66);
        progress[66] = findViewById(R.id.pb67);
        progress[67] = findViewById(R.id.pb68);
        progress[68] = findViewById(R.id.pb69);
        progress[69] = findViewById(R.id.pb70);
        progress[70] = findViewById(R.id.pb71);
        progress[71] = findViewById(R.id.pb72);
        progress[72] = findViewById(R.id.pb73);
        progress[73] = findViewById(R.id.pb74);
        progress[74] = findViewById(R.id.pb75);
        progress[75] = findViewById(R.id.pb76);
        progress[76] = findViewById(R.id.pb77);
        progress[77] = findViewById(R.id.pb78);
        progress[78] = findViewById(R.id.pb79);
        progress[79] = findViewById(R.id.pb80);
        progress[80] = findViewById(R.id.pb81);
        progress[81] = findViewById(R.id.pb82);
        progress[82] = findViewById(R.id.pb83);
        progress[83] = findViewById(R.id.pb84);
        progress[84] = findViewById(R.id.pb85);
        progress[85] = findViewById(R.id.pb86);
        progress[86] = findViewById(R.id.pb87);
        progress[87] = findViewById(R.id.pb88);
        progress[88] = findViewById(R.id.pb89);
        progress[89] = findViewById(R.id.pb90);
        progress[90] = findViewById(R.id.pb91);
        progress[91] = findViewById(R.id.pb92);
        progress[92] = findViewById(R.id.pb93);
        progress[93] = findViewById(R.id.pb94);
        progress[94] = findViewById(R.id.pb95);
        progress[95] = findViewById(R.id.pb96);
        progress[96] = findViewById(R.id.pb97);
        progress[97] = findViewById(R.id.pb98);
        progress[98] = findViewById(R.id.pb99);
        progress[99] = findViewById(R.id.pb100);
    }
    public boolean checkCure(long t){
        if(Math.abs(curePerc(t)-1.0)<.0000000001)
            return true;
        return false;
    }
    public  double thePercentageCured;
    public double curePerc(double t){
        return thePercentageCured;
    }


    //public Counter cureCount,cureCountCA,cureCountSA,cureCountEU,cureCountRS,cureCountME,cureCountAS,cureCountNF,cureCountSF,cureCountOC;

    public Counter[] cureCount = new Counter[10];

    boolean[] cured;

    public void allCured(int n){
        cured[n]=true;

        boolean lose = true;

        for(boolean done: cured)
            if(!done)
                lose = false;

        if(lose)
            loseScreen();
    }

    public void cureSpread(ArrayList<ArrayList<View>> region, int[] regionSizes, int idNum){
        final ArrayList<ArrayList<View>> country = region;

        final int[] sizes = regionSizes;

        final int id = idNum;

        Thread g = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                            Thread.sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int step = (int)(24/cureCount[id].getRegCount());
                                    //Log.d("cure", "      " + step);

                                    for (int i = 0; i < rows[id]; i++) {
                                        for (int p = 0; p < sizes[i]; p+=step) {
                                            //Log.d("cure SA","   " + p);
                                            country.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                        }
                                    }


                                    if(cureCount[id].getRegCount()==9) {
                                        allCured(id);
                                    }
                                    cureCount[id].regCountRise(1);


                                }
                            });
                        }

                } catch (InterruptedException e) {
                }
            }
        };

        g.start();





    }

    public void cureSpreadCA(ArrayList<ArrayList<View>> region, int[] regionSizes, int idNum){
        final ArrayList<ArrayList<View>> country = region;

        final int[] sizes = regionSizes;

        final int id = idNum;


        Thread gca = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int step = (int)(24/cureCount[id].getRegCount());

                                for (int i = 0; i <= rows[id]; i++) {
                                    for (int p = 0; p < sizes[i]; p+=step) {
                                        country.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                    }
                                }


                                if(cureCount[id].getRegCount()==9) {
                                    allCured(id);
                                }
                                cureCount[id].regCountRise(1);


                            }
                        });
                    }

                } catch (InterruptedException e) {
                }
            }
        };

        gca.start();





    }

    public void cureSpreadSA(ArrayList<ArrayList<View>> region, int[] regionSizes, int idNum){
        final ArrayList<ArrayList<View>> country = region;

        final int[] sizes = regionSizes;

        final int id = idNum;


        Thread gsa = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int step = (int)(24/cureCount[id].getRegCount());

                                for (int i = 0; i < rows[9]; i++) {
                                    for (int p = 0; p < sizes[i]; p+=step) {
                                        country.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                    }
                                }


                                if(cureCount[id].getRegCount()==9) {
                                    allCured(9);
                                }
                                cureCount[id].regCountRise(1);


                            }
                        });
                    }

                } catch (InterruptedException e) {
                }
            }
        };

        gsa.start();





    }

    public void cureSpreadEU(ArrayList<ArrayList<View>> region, int[] regionSizes, int idNum){
        final ArrayList<ArrayList<View>> country = region;

        final int[] sizes = regionSizes;

        final int id = idNum;


        Thread geu = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int step = (int)(24/cureCount[id].getRegCount());

                                for (int i = 0; i < rows[id]; i++) {
                                    for (int p = 0; p < sizes[i]; p+=step) {
                                        country.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                    }
                                }


                                if(cureCount[id].getRegCount()==9) {
                                    allCured(id);
                                }
                                cureCount[id].regCountRise(1);


                            }
                        });
                    }

                } catch (InterruptedException e) {
                }
            }
        };

        geu.start();





    }

    public void cureSpreadRS(ArrayList<ArrayList<View>> region, int[] regionSizes, int idNum){
        final ArrayList<ArrayList<View>> country = region;

        final int[] sizes = regionSizes;

        final int id = idNum;


        Thread grs = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int step = (int)(24/cureCount[id].getRegCount());

                                for (int i = 0; i < rows[id]; i++) {
                                    for (int p = 0; p < sizes[i]; p+=step) {
                                        country.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                    }
                                }


                                if(cureCount[id].getRegCount()==9) {
                                    allCured(id);
                                }
                                cureCount[id].regCountRise(1);


                            }
                        });
                    }

                } catch (InterruptedException e) {
                }
            }
        };

        grs.start();





    }

    public void cureSpreadME(ArrayList<ArrayList<View>> region, int[] regionSizes, int idNum){
        final ArrayList<ArrayList<View>> country = region;

        final int[] sizes = regionSizes;

        final int id = idNum;


        Thread gme = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int step = (int)(24/cureCount[id].getRegCount());

                                for (int i = 0; i < rows[id]; i++) {
                                    for (int p = 0; p < sizes[i]; p+=step) {
                                        country.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                    }
                                }


                                if(cureCount[id].getRegCount()==9) {
                                    allCured(id);
                                }
                                cureCount[id].regCountRise(1);


                            }
                        });
                    }

                } catch (InterruptedException e) {
                }
            }
        };

        gme.start();





    }

    public void cureSpreadAS(ArrayList<ArrayList<View>> region, int[] regionSizes, int idNum) {
        final ArrayList<ArrayList<View>> country = region;

        final int[] sizes = regionSizes;

        final int id = idNum;


        Thread gas = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int step = (int) (24 / cureCount[id].getRegCount());

                                for (int i = 0; i < rows[id]; i++) {
                                    for (int p = 0; p < sizes[i]; p += step) {
                                        country.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                    }
                                }


                                if (cureCount[id].getRegCount() == 9) {
                                    allCured(id);
                                }
                                cureCount[id].regCountRise(1);


                            }
                        });
                    }

                } catch (InterruptedException e) {
                }
            }
        };

        gas.start();
    }

    public void cureSpreadNF(ArrayList<ArrayList<View>> region, int[] regionSizes, int idNum){
        final ArrayList<ArrayList<View>> country = region;

        final int[] sizes = regionSizes;

        final int id = idNum;


        Thread gas = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int step = (int)(24/cureCount[id].getRegCount());

                                for (int i = 0; i < rows[id]; i++) {
                                    for (int p = 0; p < sizes[i]; p+=step) {
                                        country.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                    }
                                }

                                if(cureCount[id].getRegCount()==18) {
                                    allCured(id);
                                }
                                cureCount[id].regCountRise(1);


                            }
                        });
                    }

                } catch (InterruptedException e) {
                }
            }
        };

        gas.start();
    }


    public void cureSpreadSF(ArrayList<ArrayList<View>> region, int[] regionSizes, int idNum) {
        final ArrayList<ArrayList<View>> country = region;

        final int[] sizes = regionSizes;

        final int id = idNum;


        Thread gas = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int step = (int) (24 / cureCount[id].getRegCount());

                                for (int i = 0; i < rows[id]; i++) {
                                    for (int p = 0; p < sizes[i]; p += step) {
                                        country.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                    }
                                }


                                if (cureCount[id].getRegCount() == 9) {
                                    allCured(id);
                                }
                                cureCount[id].regCountRise(1);


                            }
                        });
                    }

                } catch (InterruptedException e) {
                }
            }
        };

        gas.start();
    }

    public void cureSpreadOC(ArrayList<ArrayList<View>> region, int[] regionSizes, int idNum) {
        final ArrayList<ArrayList<View>> country = region;

        final int[] sizes = regionSizes;

        final int id = idNum;


        Thread gas = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int step = (int) (24 / cureCount[id].getRegCount());

                                for (int i = 0; i < rows[id]; i++) {
                                    for (int p = 0; p < sizes[i]; p += step) {
                                        country.get(i).get(p).setBackgroundResource(R.drawable.blue_dot);
                                    }
                                }


                                if (cureCount[id].getRegCount() == 9) {
                                    allCured(id);
                                }
                                cureCount[id].regCountRise(1);


                            }
                        });
                    }

                } catch (InterruptedException e) {
                }
            }
        };

        gas.start();
    }

    public void disable(){
        regionClick(10);

        View[] disabledObjects= new View[28];
        disabledObjects[0] = findViewById(R.id.time_text_view);
        disabledObjects[1]=findViewById(R.id.pause_button);
        disabledObjects[2]=findViewById(R.id.speed_button);
        disabledObjects[3]=findViewById(R.id.percent100);
        disabledObjects[4]=findViewById(R.id.percent20);
        disabledObjects[5]=findViewById(R.id.percent50);
        disabledObjects[6]=findViewById(R.id.percent80);
        disabledObjects[7]=findViewById(R.id.cure100);
        disabledObjects[8]=findViewById(R.id.upgrade_button);
        disabledObjects[9]=findViewById(R.id.country_text);
        disabledObjects[10]=findViewById(R.id.progress_bar);
        disabledObjects[11]=findViewById(R.id.settings_button);
        disabledObjects[12]=findViewById(R.id.cure_text);
        disabledObjects[13]=findViewById(R.id.upgrade_points);
        disabledObjects[14]=findViewById(R.id.cure80);
        disabledObjects[15]=findViewById(R.id.cure50);
        disabledObjects[16]=findViewById(R.id.cure20);
        disabledObjects[17]=findViewById(R.id.cure_progress);
        disabledObjects[18]=findViewById(R.id.na_upgrade);
        disabledObjects[19]=findViewById(R.id.ca_upgrade);
        disabledObjects[20]=findViewById(R.id.sa_upgrade);
        disabledObjects[21]=findViewById(R.id.eu_upgrade);
        disabledObjects[22]=findViewById(R.id.rs_upgrade);
        disabledObjects[23]=findViewById(R.id.me_upgrade);
        disabledObjects[24]=findViewById(R.id.oc_upgrade);
        disabledObjects[25]=findViewById(R.id.nf_upgrade);
        disabledObjects[26]=findViewById(R.id.sf_upgrade);
        disabledObjects[27]=findViewById(R.id.as_upgrade);

        for (int i =0;i<disabledObjects.length;i++){
            disabledObjects[i].setVisibility(View.GONE);
        }

        RelativeLayout northA = (RelativeLayout) findViewById(R.id.northAmerica);
        northA.setClickable(false);
        RelativeLayout centA = (RelativeLayout) findViewById(R.id.centralAmerica);
        centA.setClickable(false);
        RelativeLayout southA = (RelativeLayout) findViewById(R.id.southAmerica);
        southA.setClickable(false);
        RelativeLayout europe = (RelativeLayout) findViewById(R.id.europe);
        europe.setClickable(false);
        RelativeLayout russia = (RelativeLayout) findViewById(R.id.russia);
        russia.setClickable(false);
        RelativeLayout mEast = (RelativeLayout) findViewById(R.id.middleEast);
        mEast.setClickable(false);
        RelativeLayout asia = (RelativeLayout) findViewById(R.id.asia);
        asia.setClickable(false);
        RelativeLayout nAfrica = (RelativeLayout) findViewById(R.id.northAfrica);
        nAfrica.setClickable(false);
        RelativeLayout sAfrica = (RelativeLayout) findViewById(R.id.southAfrica);
        sAfrica.setClickable(false);
        RelativeLayout asOC = (RelativeLayout) findViewById(R.id.oceania);
        asOC.setClickable(false);
    }
    public void gameOver(){
        disable();

        cured=new boolean[10];

        TextView info = (TextView) findViewById(R.id.info_text);
        info.setClickable(false);
        info.setText("GAME OVER");
        

        




       /* cureSpread(CA,caSizes,2);
        cureSpread(AS,asSizes,0);
        cureSpread(OC,ocSizes,1);
        cureSpread(EU,euSizes,3);
        cureSpread(ME,meSizes,4);
        cureSpread(NF,nfSizes,5);
        cureSpread(RS,rsSizes,7);
        cureSpread(SF,sfSizes,8);
        cureSpread(SA,saSizes,9);*/
        cureSpread(NA,naSizes, 6);
        cureSpreadCA(CA, caSizes, 2);
        cureSpreadSA(SA, saSizes, 9);
        cureSpreadEU(EU, euSizes, 3);
        cureSpreadRS(RS, rsSizes, 7);
        cureSpreadME(ME, meSizes, 4);
        cureSpreadNF(NF, nfSizes, 5);
        cureSpreadSF(SF, sfSizes, 8);
        cureSpreadOC(OC, ocSizes, 1);
        cureSpreadAS(AS, asSizes,0);
    }
    public void loseScreen(){

        Log.d("lose screen", "lost");

        RelativeLayout na = (RelativeLayout) findViewById(R.id.northAmerica);
        na.setVisibility(View.GONE);

        RelativeLayout ca = (RelativeLayout) findViewById(R.id.centralAmerica);
        ca.setVisibility(View.GONE);

        RelativeLayout sa = (RelativeLayout) findViewById(R.id.southAmerica);
        sa.setVisibility(View.GONE);

        RelativeLayout eu = (RelativeLayout) findViewById(R.id.europe);
        eu.setVisibility(View.GONE);

        RelativeLayout rs = (RelativeLayout) findViewById(R.id.russia);
        rs.setVisibility(View.GONE);

        RelativeLayout me = (RelativeLayout) findViewById(R.id.middleEast);
        me.setVisibility(View.GONE);

        RelativeLayout as = (RelativeLayout) findViewById(R.id.asia);
        as.setVisibility(View.GONE);

        RelativeLayout nf = (RelativeLayout) findViewById(R.id.northAfrica);
        nf.setVisibility(View.GONE);

        RelativeLayout sf = (RelativeLayout) findViewById(R.id.southAfrica);
        sf.setVisibility(View.GONE);

        RelativeLayout oc = (RelativeLayout) findViewById(R.id.oceania);
        oc.setVisibility(View.GONE);

        TextView info = (TextView) findViewById(R.id.info_text);
        info.setText("YOU LOSE");
    }

    public void checkupgradePoints(){
        RelativeLayout[] poppers = {(RelativeLayout) findViewById(R.id.as_upgrade), (RelativeLayout) findViewById(R.id.oc_upgrade), (RelativeLayout) findViewById(R.id.ca_upgrade), (RelativeLayout) findViewById(R.id.eu_upgrade), (RelativeLayout) findViewById(R.id.me_upgrade), (RelativeLayout) findViewById(R.id.nf_upgrade), (RelativeLayout) findViewById(R.id.na_upgrade), (RelativeLayout) findViewById(R.id.rs_upgrade), (RelativeLayout) findViewById(R.id.sf_upgrade), (RelativeLayout) findViewById(R.id.sa_upgrade)};
        /*RelativeLayout napopper = (RelativeLayout) findViewById(R.id.na_upgrade);
        RelativeLayout capopper = (RelativeLayout) findViewById(R.id.ca_upgrade);
        RelativeLayout sapopper = (RelativeLayout) findViewById(R.id.sa_upgrade);
        RelativeLayout eupopper = (RelativeLayout) findViewById(R.id.eu_upgrade);
        RelativeLayout rspopper = (RelativeLayout) findViewById(R.id.rs_upgrade);
        RelativeLayout mepopper = (RelativeLayout) findViewById(R.id.me_upgrade);
        RelativeLayout aspopper = (RelativeLayout) findViewById(R.id.as_upgrade);*/

        for(int i = 0; i<poppers.length;i++){
            if(infections[i].getPerc()>(upCount[i]*.25) && !upgradePoints[i][upCount[i]]) {
                poppers[i].setClickable(true);
                poppers[i].setVisibility(View.VISIBLE);
            }
        }

        /*if(infections[6].getPerc()>(upCount[6]*.25) && !upgradePoints[6][upCount[6]]){
            napopper.setClickable(true);
            napopper.setVisibility(View.VISIBLE);
        }

        if(infections[2].getPerc()>(upCount[2]*.25) && !upgradePoints[2][upCount[2]]){
            capopper.setClickable(true);
            capopper.setVisibility(View.VISIBLE);
        }
        if(infections[9].getPerc()>(upCount[9]*.25) && !upgradePoints[9][upCount[9]]){
            sapopper.setClickable(true);
            sapopper.setVisibility(View.VISIBLE);
        }
        if(infections[3].getPerc()>(upCount[3]*.25) && !upgradePoints[3][upCount[3]]){
            eupopper.setClickable(true);
            eupopper.setVisibility(View.VISIBLE);
        }
        if(infections[7].getPerc()>(upCount[7]*.25) && !upgradePoints[7][upCount[7]]){
            rspopper.setClickable(true);
            rspopper.setVisibility(View.VISIBLE);
        }
        if(infections[4].getPerc()>(upCount[4]*.25) && !upgradePoints[4][upCount[4]]){
            mepopper.setClickable(true);
            mepopper.setVisibility(View.VISIBLE);
        }
        if(infections[0].getPerc()>(upCount[0]*.25) && !upgradePoints[0][upCount[0]]){
            aspopper.setClickable(true);
            aspopper.setVisibility(View.VISIBLE);
        }*/
    }
    public void upgradeClick(View view,int id){
        upgradePoints[id][upCount[id]++]=true;
        view.setClickable(false);
        view.setVisibility(View.INVISIBLE);
        updateUpgrade();
    }
    public void NAupgradeClick(View view){
        upgradeClick(view, 6);
    }
    public void CAupgradeClick(View view){
        upgradeClick(view, 2);
    }
    public void SAupgradeClick(View view){
        upgradeClick(view,9);
    }
    public void EUupgradeClick(View view){
        upgradeClick(view, 3);
    }
    public void RSupgradeClick(View view){
        upgradeClick(view, 7);
    }
    public void MEupgradeClick(View view){
        upgradeClick(view, 4);
    }
    public void ASupgradeClick(View view){
        upgradeClick(view,0);
    }
    public void NFupgradeClick(View view){
        upgradeClick(view,5);
    }
    public void SFupgradeClick(View view){
        upgradeClick(view,8);
    }
    public void OCupgradeClick(View view){
        upgradeClick(view,1);
    }
    public int upgrades;
    public void updateUpgrade(){
        TextView upgradePointsText = (TextView) findViewById(R.id.upgrade_points);
        int count=0;
        for(int num: upCount)
            count+=num;
        upgradePointsText.setText(" " + count + " ");
        upgrades=count;
    }
    View[] cureProgressBar;
    public void buildCureProg(){
        cureProgressBar = new View[100];

        cureProgressBar[0] = findViewById(R.id.cpb99);
        cureProgressBar[1] = findViewById(R.id.cpb98);
        cureProgressBar[2] = findViewById(R.id.cpb97);
        cureProgressBar[3] = findViewById(R.id.cpb96);
        cureProgressBar[4] = findViewById(R.id.cpb95);
        cureProgressBar[5] = findViewById(R.id.cpb94);
        cureProgressBar[6] = findViewById(R.id.cpb93);
        cureProgressBar[7] = findViewById(R.id.cpb92);
        cureProgressBar[8] = findViewById(R.id.cpb91);
        cureProgressBar[9] = findViewById(R.id.cpb90);
        cureProgressBar[10] = findViewById(R.id.cpb89);
        cureProgressBar[11] = findViewById(R.id.cpb88);
        cureProgressBar[12] = findViewById(R.id.cpb87);
        cureProgressBar[13] = findViewById(R.id.cpb86);
        cureProgressBar[14] = findViewById(R.id.cpb85);
        cureProgressBar[15] = findViewById(R.id.cpb84);
        cureProgressBar[16] = findViewById(R.id.cpb83);
        cureProgressBar[17] = findViewById(R.id.cpb82);
        cureProgressBar[18] = findViewById(R.id.cpb81);
        cureProgressBar[19] = findViewById(R.id.cpb80);
        cureProgressBar[20] = findViewById(R.id.cpb79);
        cureProgressBar[21] = findViewById(R.id.cpb78);
        cureProgressBar[22] = findViewById(R.id.cpb77);
        cureProgressBar[23] = findViewById(R.id.cpb76);
        cureProgressBar[24] = findViewById(R.id.cpb75);
        cureProgressBar[25] = findViewById(R.id.cpb74);
        cureProgressBar[26] = findViewById(R.id.cpb73);
        cureProgressBar[27] = findViewById(R.id.cpb72);
        cureProgressBar[28] = findViewById(R.id.cpb71);
        cureProgressBar[29] = findViewById(R.id.cpb70);
        cureProgressBar[30] = findViewById(R.id.cpb69);
        cureProgressBar[31] = findViewById(R.id.cpb68);
        cureProgressBar[32] = findViewById(R.id.cpb67);
        cureProgressBar[33] = findViewById(R.id.cpb66);
        cureProgressBar[34] = findViewById(R.id.cpb65);
        cureProgressBar[35] = findViewById(R.id.cpb64);
        cureProgressBar[36] = findViewById(R.id.cpb63);
        cureProgressBar[37] = findViewById(R.id.cpb62);
        cureProgressBar[38] = findViewById(R.id.cpb61);
        cureProgressBar[39] = findViewById(R.id.cpb60);
        cureProgressBar[40] = findViewById(R.id.cpb59);
        cureProgressBar[41] = findViewById(R.id.cpb58);
        cureProgressBar[42] = findViewById(R.id.cpb57);
        cureProgressBar[43] = findViewById(R.id.cpb56);
        cureProgressBar[44] = findViewById(R.id.cpb55);
        cureProgressBar[45] = findViewById(R.id.cpb54);
        cureProgressBar[46] = findViewById(R.id.cpb53);
        cureProgressBar[47] = findViewById(R.id.cpb52);
        cureProgressBar[48] = findViewById(R.id.cpb51);
        cureProgressBar[49] = findViewById(R.id.cpb50);
        cureProgressBar[50] = findViewById(R.id.cpb49);
        cureProgressBar[51] = findViewById(R.id.cpb48);
        cureProgressBar[52] = findViewById(R.id.cpb47);
        cureProgressBar[53] = findViewById(R.id.cpb46);
        cureProgressBar[54] = findViewById(R.id.cpb45);
        cureProgressBar[55] = findViewById(R.id.cpb44);
        cureProgressBar[56] = findViewById(R.id.cpb43);
        cureProgressBar[57] = findViewById(R.id.cpb42);
        cureProgressBar[58] = findViewById(R.id.cpb41);
        cureProgressBar[59] = findViewById(R.id.cpb40);
        cureProgressBar[60] = findViewById(R.id.cpb39);
        cureProgressBar[61] = findViewById(R.id.cpb38);
        cureProgressBar[62] = findViewById(R.id.cpb37);
        cureProgressBar[63] = findViewById(R.id.cpb36);
        cureProgressBar[64] = findViewById(R.id.cpb35);
        cureProgressBar[65] = findViewById(R.id.cpb34);
        cureProgressBar[66] = findViewById(R.id.cpb33);
        cureProgressBar[67] = findViewById(R.id.cpb32);
        cureProgressBar[68] = findViewById(R.id.cpb31);
        cureProgressBar[69] = findViewById(R.id.cpb30);
        cureProgressBar[70] = findViewById(R.id.cpb29);
        cureProgressBar[71] = findViewById(R.id.cpb28);
        cureProgressBar[72] = findViewById(R.id.cpb27);
        cureProgressBar[73] = findViewById(R.id.cpb26);
        cureProgressBar[74] = findViewById(R.id.cpb25);
        cureProgressBar[75] = findViewById(R.id.cpb24);
        cureProgressBar[76] = findViewById(R.id.cpb23);
        cureProgressBar[77] = findViewById(R.id.cpb22);
        cureProgressBar[78] = findViewById(R.id.cpb21);
        cureProgressBar[79] = findViewById(R.id.cpb20);
        cureProgressBar[80] = findViewById(R.id.cpb19);
        cureProgressBar[81] = findViewById(R.id.cpb18);
        cureProgressBar[82] = findViewById(R.id.cpb17);
        cureProgressBar[83] = findViewById(R.id.cpb16);
        cureProgressBar[84] = findViewById(R.id.cpb15);
        cureProgressBar[85] = findViewById(R.id.cpb14);
        cureProgressBar[86] = findViewById(R.id.cpb13);
        cureProgressBar[87] = findViewById(R.id.cpb12);
        cureProgressBar[88] = findViewById(R.id.cpb11);
        cureProgressBar[89] = findViewById(R.id.cpb10);
        cureProgressBar[90] = findViewById(R.id.cpb9);
        cureProgressBar[91] = findViewById(R.id.cpb8);
        cureProgressBar[92] = findViewById(R.id.cpb7);
        cureProgressBar[93] = findViewById(R.id.cpb6);
        cureProgressBar[94] = findViewById(R.id.cpb5);
        cureProgressBar[95] = findViewById(R.id.cpb4);
        cureProgressBar[96] = findViewById(R.id.cpb3);
        cureProgressBar[97] = findViewById(R.id.cpb2);
        cureProgressBar[98] = findViewById(R.id.cpb1);
        cureProgressBar[99] = findViewById(R.id.cpb0);
    }
    public void clickCured20(View view){thePercentageCured = .2;}
    public void clickCured50(View view){thePercentageCured = .5;}
    public void clickCured80(View view){thePercentageCured = .8;}
    public void clickCured100(View view){thePercentageCured = 1.0;}
    public void setCureProgressBar(double perc){
        int p =(int)(perc*100);

        for(int i = 0; i<= p;i++){
            cureProgressBar[i].setBackgroundResource(R.color.mapBlue);
        }


        if(p<99)
            cureProgressBar[p+1].setBackgroundResource(R.color.cureBlue1);
        if(p<98)
            cureProgressBar[p+2].setBackgroundResource(R.color.cureBlue2);
        if(p<97)
            cureProgressBar[p+3].setBackgroundResource(R.color.cureBlue3);
        if(p<96)
            cureProgressBar[p+4].setBackgroundResource(R.color.cureBlue4);
        if(p<95)
            cureProgressBar[p+5].setBackgroundResource(R.color.cureBlue5);
        if(p<94)
            cureProgressBar[p+6].setBackgroundResource(R.color.cureBlue6);
        if(p<93)
            cureProgressBar[p+7].setBackgroundResource(R.color.cureBlue7);
        if(p<92)
            cureProgressBar[p+8].setBackgroundResource(R.color.cureBlue8);
        if(p<91) {
            cureProgressBar[p + 9].setBackgroundResource(R.color.cureBlue9);

            for (int i = p+8; i < 99; i++) {
                cureProgressBar[i].setBackgroundResource(R.color.black);
            }
        }
    }

    int missileInterval;

    public void missileLaunch(View view){
        if(upgrades==40) {
            upgrades=0;
            disable();
            final ImageView[][] paths = new ImageView[2][13];

            paths[0][0] = (ImageView) findViewById(R.id.na_missile_1);
            paths[0][1] = (ImageView) findViewById(R.id.na_missile_2);
            paths[0][2] = (ImageView) findViewById(R.id.na_missile_3);
            paths[0][3] = (ImageView) findViewById(R.id.na_missile_4);
            paths[0][4] = (ImageView) findViewById(R.id.na_missile_5);
            paths[0][5] = (ImageView) findViewById(R.id.na_missile_6);
            paths[0][6] = (ImageView) findViewById(R.id.na_missile_7);
            paths[0][7] = (ImageView) findViewById(R.id.na_missile_8);
            paths[0][8] = (ImageView) findViewById(R.id.na_missile_9);
            paths[0][9] = (ImageView) findViewById(R.id.na_explosion_1);
            paths[0][10] = (ImageView) findViewById(R.id.na_explosion_2);
            paths[0][11] = (ImageView) findViewById(R.id.na_explosion_3);
            paths[0][12] = (ImageView) findViewById(R.id.na_explosion_4);

            paths[1][0] = (ImageView) findViewById(R.id.ca_missile_1);
            paths[1][1] = (ImageView) findViewById(R.id.ca_missile_2);
            paths[1][2] = (ImageView) findViewById(R.id.ca_missile_3);
            paths[1][3] = (ImageView) findViewById(R.id.ca_missile_4);
            paths[1][4] = (ImageView) findViewById(R.id.ca_missile_5);
            paths[1][5] = (ImageView) findViewById(R.id.ca_missile_6);
            paths[1][6] = (ImageView) findViewById(R.id.ca_missile_7);
            paths[1][7] = (ImageView) findViewById(R.id.ca_missile_8);
            paths[1][8] = (ImageView) findViewById(R.id.ca_missile_9);
            paths[1][9] = (ImageView) findViewById(R.id.ca_explosion_1);
            paths[1][10] = (ImageView) findViewById(R.id.ca_explosion_2);
            paths[1][11] = (ImageView) findViewById(R.id.ca_explosion_3);
            paths[1][12] = (ImageView) findViewById(R.id.ca_explosion_4);

            missileInterval = 500;


            final Counter missileCounter = new Counter();
            missileCounter.setCount(0);

            Thread m = new Thread() {

                @Override
                public void run() {
                    try {
                        while (!isInterrupted()) {
                            while (!missileCounter.isDone()) {
                                Thread.sleep(missileInterval);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.d("missiles", "   " + missileCounter.getRegCount());
                                        if (missileCounter.getRegCount() < 12) {

                                            for (int i = 0; i < paths.length; i++)
                                                paths[i][missileCounter.getRegCount()].setVisibility(View.VISIBLE);


                                            if (missileCounter.getRegCount() == 7) {
                                                setInt(1500);
                                            }
                                            if (missileCounter.getRegCount() == 8) {
                                                setInt(100);
                                            }
                                        } else if (missileCounter.getRegCount() == 27) {
                                            winScreen();
                                            missileCounter.stop();
                                        }
                                        missileCounter.regCountRise(1);

                                    }
                                });
                            }
                        }
                    } catch (InterruptedException e) {
                    }
                }
            };

            m.start();
        }
    }
    public void winScreen(){
        RelativeLayout win = (RelativeLayout)findViewById(R.id.winLayout);
        win.setVisibility(View.VISIBLE);
    }
    public void setInt(int num){
        missileInterval=num;
    }
}


