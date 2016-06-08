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
//import io.realm.RealmResults;
//
//public class VirusTest extends AppCompatActivity {
//
//    public RealmConfiguration realmConfig;
//    public Realm realm;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.virus_tester);
//
//        realmConfig = new RealmConfiguration.Builder(this).build();
//
//        realm = Realm.getInstance(realmConfig);
//
//        realm.beginTransaction();
//
//        if (realm.where(Virus.class).findFirst() == null) {
//            final Virus virus1 = realm.createObject(Virus.class);
//            virus1.setName("Laptop");
//            virus1.setCategory("Leathality");
//            virus1.setValue(.10);
//            virus1.setId(1);
//        }
//
//        realm.commitTransaction();
//
//
//        RealmResults helper = realm.where(Virus.class).findAll();
//
//        String thisShouldBeAll = helper.toString();
//
//        Toast.makeText(VirusTest.this, thisShouldBeAll, Toast.LENGTH_LONG).show();
//
//    }
//
//}
