package android.realmtest.com.realmtest;

/**
 * Created by rayqu on 2/3/2016.
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
        time = 0;
    }

    //changed following to time instead of day
    public String toString() {
        return ("year_" + yr + "\nday_"  + day + "\ntime_" + time);
    }

    public void update(/* char c, long t*/) {
//        if(c=='s')
//            sec = t;
//
//        set(sec);

        //time++;

        set(time++);
    }

    private void set(long m) {

//        min = sec / 60;
//        hr = min / 60;
        day = time; //hr / 24;
        yr = day/364;
//        if (min > 59)//hours
//            min -= hr * 60;
//        if (hr > 23)//days
//            hr -= day * 24;
        if(day>363) {//years
            day -= yr * 364;
            //time= time -1;
        }

    }

    public long getSec() {
        return sec;
    }

    public long getDay() {
        return day;
    }

    public long getTime() {
        return time;
    }
}

