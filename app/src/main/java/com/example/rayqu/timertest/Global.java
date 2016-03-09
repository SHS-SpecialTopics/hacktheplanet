package com.example.rayqu.timertest;

import android.app.Application;

public  class  Global extends Application {

    private Boolean openedYet = false;

    public int counter = 0;

    public Boolean get_Opened() {
        return openedYet;
    }

    public void set_Opened(Boolean openNow) {
        this.openedYet = openNow;
    }


    private BooleanActions yanksOut = new BooleanActions(this);

    private BooleanOutline yanksIn = new BooleanOutline();


    public BooleanActions getYanksOut() {return yanksOut;}

    public BooleanOutline getYanksIn() {return yanksIn;}

    public void setYanksOut(BooleanActions input) {yanksOut = input;}

    public void setYanksIn(BooleanOutline input) { yanksIn = input;}




}

