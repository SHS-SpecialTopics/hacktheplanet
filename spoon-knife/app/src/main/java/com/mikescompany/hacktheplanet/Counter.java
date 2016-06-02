package com.mikescompany.hacktheplanet;

/**
 * Created by Mike on 1/22/2016.
 */
public class Counter {
    private int[] hcount;
    private int vcount;
    private int count;
    private boolean done = false;
    public Counter(int h[], int v){
        hcount = h;
        vcount = v;
    }
    public Counter(int rows){
        vcount=0;
        hcount = new int[rows];
    }
    public int[] getHcount(){
        return hcount;
    }
    public void hrise(int i){
            hcount[i]++;
    }
    public void setHcount(int[] c){
        hcount = c.clone();
    }
    public void stop(){
        done = true;
        count=0;
    }
    public Counter(){
        count = 1;
    }
    public void setCount(int n){
        count=n;
    }
    public void regCountRise(int i){
        count++;
    }
    public int getRegCount(){
        return count;
    }
    public boolean isDone(){
        return done;
    }
    public void prepare(){
        done=false;
    }
}
