package android.realmtest.com.realmtest;

import io.realm.RealmObject;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;


public class Virus extends RealmObject{


    private String name;
    private String category;
    private double networkingValue;
    private double lethalityValue;
    private double securityValue;

    private boolean purchased = false;

    private boolean lethalityG = false;
    private boolean lethalityC = false;

    private long id;

    //  Other objects in a one-to-one relation must also subclass RealmObject

//  private Dog dog;
//
//  One-to-many relations is simply a RealmList of the objects which also subclass RealmObject

//  private RealmList<Cat> cats;

//  You can instruct Realm to ignore a field and not persist it.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcategory() {
        return category;
    }

    public void setCategory(String cat) {
        this.category = cat;
    }

    public double getNetworkingValue(){
        return networkingValue;
    }

    public void setNetworkingValue(double val) {
        this.networkingValue = val;
    }

    public double getLethalityValue(){
        return lethalityValue;
    }

    public void setLethalityValue(double lethal){
        this.lethalityValue = lethal;
    }

    public double getSecurityValue(){
        return securityValue;
    }

    public void setSecurityValue(double secure) {
        this.securityValue = secure;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getPurchased(){
        return purchased;
    }

    public void setPurchased(boolean boughtIt) {
        purchased = boughtIt;
    }

    public boolean getLethalityG(){
        return lethalityG;
    }

    public void setLethalityG(boolean G_or_Nah){
        lethalityG = G_or_Nah;
    }

    public boolean getLethalityC() {
        return lethalityC;
    }

    public void setLethalityC(boolean C_or_Nah){
        lethalityC = C_or_Nah;
    }





}
