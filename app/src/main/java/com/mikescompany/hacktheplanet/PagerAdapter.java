package com.mikescompany.hacktheplanet;

/**
 * Created by Owner on 3/4/2016.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by androidwarriors on 10/16/2015.
 */
public class PagerAdapter extends FragmentStatePagerAdapter{
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag=null;
        switch (position){
            case 0:
                frag=new networking_upgrade();
                break;
            case 1:
                frag=new lethality_upgrade();
                break;
            case 2:
                frag=new security_upgrade();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title="Networking";
                break;
            case 1:
                title="Lethality";
                break;
            case 2:
                title="Security";
                break;
        }

        return title;
    }
}
