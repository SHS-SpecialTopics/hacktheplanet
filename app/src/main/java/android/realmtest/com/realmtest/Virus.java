package android.realmtest.com.realmtest;

import io.realm.RealmObject;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;


public class Virus extends RealmObject{


    private String name;
    private String category;
    private double value;
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

    public double getValue(){
        return value;
    }

    public void setValue(double val) {
        this.value = val;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public String toString(){
//        return "I AM HERE! ";
//    }


    //Gonna need this for a GAME object
//    public RealmList<Cat> getCats() {
//        return cats;
//    }
//
//    public void setCats(RealmList<Cat> cats) {
//        this.cats = cats;
//    }

}
