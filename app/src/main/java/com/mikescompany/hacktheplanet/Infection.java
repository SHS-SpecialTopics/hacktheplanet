package com.mikescompany.hacktheplanet;

/**
 * Created by Mike on 1/29/2016.
 */
public class Infection {
    private  double perc;
    private boolean infected;

    public Infection(){
        perc = 0;
        infected = false;
    }
    public double getPerc(){
        return perc;
    }
    public boolean isInfected(){
        return infected;
    }
    public void setInfected(boolean b){
        infected=b;
    }
    public void setPerc(double d){
        perc=d;
    }
}
