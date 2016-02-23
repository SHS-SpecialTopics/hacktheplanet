package com.example.rayqu.timertest;

import android.app.Application;

public  class  Global extends Application {

    private Boolean openedYet =false;

    public Boolean get_Opened() {
        return openedYet;
    }

    public void set_Opened(Boolean openNow) {
        this.openedYet = openNow;
    }
}

