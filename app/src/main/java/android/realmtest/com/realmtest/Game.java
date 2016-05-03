package android.realmtest.com.realmtest;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;


public class Game extends RealmObject {

    private String name;
    private RealmList<Virus> virusList;
    private long id;

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


}
