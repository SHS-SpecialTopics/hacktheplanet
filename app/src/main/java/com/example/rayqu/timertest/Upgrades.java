package com.example.rayqu.timertest;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
        pager.setOffscreenPageLimit(3);
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
