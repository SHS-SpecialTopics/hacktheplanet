//package android.realmtest.com.realmtest;
//
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import io.realm.Realm;
//import io.realm.RealmConfiguration;
//
//public class TestingActivity extends AppCompatActivity {
//
//
//    public RealmConfiguration realmConfig;
//    public Realm realm;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_testing);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        final TextView Person_view = (TextView) findViewById(R.id.Displayer);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Snackbar.make(view, "Cleared", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                realm.beginTransaction();
//                realm.allObjects(Person.class).clear();
//                realm.commitTransaction();
//
//                if(realm.where(Person.class).findFirst() == null){
//                    Person_view.setText("Nothing Here Bro");
//                }
//
//            }
//        });
//
//
//        realmConfig = new RealmConfiguration.Builder(this).build();
//        realm = Realm.getInstance(realmConfig);
//
//
//        Person sally = realm.where(Person.class).findFirst();
//
//        Person_view.setText( "" + sally.getNamer() + ", " + sally.getAge());
//
//    }
//
//
//    public void changeName(View viewer){
//
//        realmConfig = new RealmConfiguration.Builder(this).build();
//        realm = Realm.getInstance(realmConfig);
//
//
//        TextView Person_view = (TextView) findViewById(R.id.Displayer);
//
//        realm.beginTransaction();
//
//        if (realm.where(Person.class).findFirst() != null){
//            Person sally = realm.where(Person.class).findFirst();
//
//            sally.setNamer(sally.getNamer() + " BillyBob");
//
//            Person_view.setText("" + sally.getNamer() + ", " + sally.getAge());
//        }
//        else
//        {
//            final Person person = realm.createObject(Person.class);
//            person.setId(1);
//            person.setNamer("James");
//            person.setAge(15);
//
//            Person_view.setText("" + person.getNamer() + ", " + person.getAge());
//        }
//
//        realm.commitTransaction();
//
//    }
//
//    public void changeAge(View v){
//
//        realmConfig = new RealmConfiguration.Builder(this).build();
//        realm = Realm.getInstance(realmConfig);
//
//
//        TextView Person_view = (TextView) findViewById(R.id.Displayer);
//
//        realm.beginTransaction();
//
//        if (realm.where(Person.class).findFirst() != null) {
//            Person sally = realm.where(Person.class).findFirst();
//
//            sally.setAge(sally.getAge() + 5);
//
//            Person_view.setText("" + sally.getNamer() + ", " + sally.getAge());
//        }
//        else
//        {
//            final Person person = realm.createObject(Person.class);
//            person.setId(1);
//            person.setNamer("James");
//            person.setAge(15);
//
//            Person_view.setText("" + person.getNamer() + ", " + person.getAge());
//        }
//
//        realm.commitTransaction();
//
//
//    }
//
//
//
//
//
//}
