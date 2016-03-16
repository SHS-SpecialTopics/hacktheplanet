package android.realmtest.com.realmtest;


import io.realm.RealmObject;

public class RealmTester extends RealmObject {

    private String name;
    private int value;
    private String category;

    public String getName (){return name;}

    public String getCategory (){return category;}

    public int getValue (){return value;}


    public void setName(String namer){
        name = namer;
    }

    public void setCategory(String cat)
    {
        category = cat;
    }


    public void setValue(int holdVal)
    {
        value = holdVal;
    }

}


