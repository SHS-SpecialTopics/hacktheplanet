package android.realmtest.com.realmtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import junit.framework.Test;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();

        Realm realm = Realm.getInstance(realmConfig);

        // Beginning of the Real Stuff that Should Work
        realm.beginTransaction();

        // Add a person every time the app runs
        final Person person = realm.createObject(Person.class);
        person.setId(1);
        person.setNamer("Jimmy");
        person.setAge(15);

        // When the transaction is committed, all changes a synced to disk.
        realm.commitTransaction();

        // Find the first person (no query conditions) and read a field
        //person = realm.where(Person.class).findFirst();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "" + person.getNamer() + ", " + person.getAge(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        /*Commented Out Comments*/
        //        realm.beginTransaction();
//
//        RealmTester bob = new RealmTester();
//
//        bob.setName("Goldilocks");
//        bob.setVal(101);
//        bob.setCategory("Faggot");
//
//        realm.copyToRealm(bob);
//        realm.commitTransaction();
//
//        Toast.makeText(MainActivity.this, bob.getName() + ", " + bob.getCategory() + ", " + bob.getVal(), Toast.LENGTH_SHORT).show();
//
//
//        Person sally = realm.where(Person.class).findFirst();
//
//        realm.beginTransaction();
//        sally.setNamer("Sally");
//        realm.commitTransaction();
//
//
//        Toast.makeText(MainActivity.this, "" + sally.getNamer() + ", " + sally.getAge(), Toast.LENGTH_SHORT).show();
//
//
//        showStatus(person.getName() + ":" + person.getAge());
//
//        // Update person in a transaction
//        realm.beginTransaction();
//        person.setName("Senior Person");
//        person.setAge(99);
//        //showStatus(person.getName() + " got older: " + person.getAge());
//        realm.commitTransaction();
//
//        // Delete all persons
//        realm.beginTransaction();
//        realm.allObjects(Person.class).clear();
//        realm.commitTransaction();
//
//        //End of Realm */
//

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.move_form){
            Intent testIntent = new Intent(this, VirusTest.class);
            startActivity(testIntent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
