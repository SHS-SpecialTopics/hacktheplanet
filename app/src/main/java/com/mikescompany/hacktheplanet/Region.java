package com.mikescompany.hacktheplanet;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;


public class Region extends RealmObject {


    private double regionPercent;
    private double regionPoints;

    public double getRegionPoints() {
        return regionPoints;
    }

    public void setRegionPoints(double regionPoints) {
        this.regionPoints = regionPoints;
    }

    public double getRegionPercent() {
        return regionPercent;
    }

    public void setRegionPercent(double regionPercent) {
        this.regionPercent = regionPercent;
    }




}