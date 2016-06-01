package com.mikescompany.hacktheplanet;

/**
 * Created by Mike on 12/16/2015.
 */
public class myDate {
    private long hr;
    private long day;
    private long min;
    private long sec;
    private long yr;
    private long time;

    public myDate(long l) {
        sec = l;
        set(sec);

        time=0;
    }

    public String toString() {
        return ("year_" + yr + "\nday_"  + day);
    }

    public void update() {

        set(time++);

    }

    private void set(double m) {
        day = time;

        yr = day/364;
        if (min > 59)//hours
            min -= hr * 60;
        if (hr > 23)//days
            hr -= day * 24;
        if(day>363)//years
            day-= yr*364;


    }

    public long getSec() {
        return sec;
    }

    public long getTime(){ return time;}
}
