package android.realmtest.com.realmtest;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;


public class Game extends RealmObject {

    private String name;
    private RealmList<Virus> virusList;
    private long id;


    private ArrayList<Double> regionPercent;
    private ArrayList<Boolean> regionPoints;

    private double curePercent;
    private long time;
    private int notSpentPoints;


    public RealmList<Virus> getVirusList() {
        return virusList;
    }

    public void setVirusList(RealmList<Virus> bob)
    {
        this.virusList = bob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCurePercent() {
        return curePercent;
    }

    public void setCurePercent(double curePercent) {
        this.curePercent = curePercent;
    }

    public int getNotSpentPoints() {
        return notSpentPoints;
    }

    public void setNotSpentPoints(int notSpentPoints) {
        this.notSpentPoints = notSpentPoints;
    }

    public ArrayList<Boolean> getRegionPoints() {
        return regionPoints;
    }

    public void setRegionPoints(ArrayList<Boolean> regionPoints) {
        this.regionPoints = regionPoints;
    }

    public ArrayList<Double> getRegionPercent() {
        return regionPercent;
    }

    public void setRegionPercent(ArrayList<Double> regionPercent) {
        this.regionPercent = regionPercent;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


}
