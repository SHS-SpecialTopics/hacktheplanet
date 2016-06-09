 package com.mikescompany.hacktheplanet;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikescompany.hacktheplanet.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mikescompany.hacktheplanet.R;

import java.util.ArrayList;
import java.util.List;

 public class TimerTest extends AppCompatActivity {
     boolean openedYet = false;

     myDate time = new myDate(0);

     double security = .9;
     double panic = 1;
     int shuffle = 0;

     static double networking = -.5;
     //double midpoint = 5;
     boolean suspended = false;

    static double trueMid;

     int count = 0;

     //246.8403042

     private ListView lv;

     String thisDoesNothing = "This Does Nothing";


     MediaPlayer mySound;

     double equationVal;

     String[][] myRegionArray = new String[][]{
             {"Asia", "524974492.88", ""},
             {"Oceania", "21047299.81", ""},
             {"CentralAmerica", "23826989.14", ""},
             {"Europe", "268982679.59", ""},
             {"MiddleEast", "37821440.91", ""},
             {"NorthAfrica", "35365087.89", ""},
             {"NorthAmerica", "250218470.23", ""},
             {"Russia", "25915924.43", ""},
             {"SouthAfrica", "13155022.37", ""},
             {"SouthAmerica", "76259784.44", ""}

     };

     /**
      * ATTENTION: This was auto-generated to implement the App Indexing API.
      * See https://g.co/AppIndexing/AndroidStudio for more information.
      */
     private GoogleApiClient client;


     @Override
     protected void onCreate(Bundle savedInstanceState) {

         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_timer_test);
         getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

         mySound = MediaPlayer.create(this, R.raw.soundeffect);


         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

         FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Snackbar.make(view, "This Does Nothing But it Looks Cool", Snackbar.LENGTH_LONG)
                         .setAction("Action", null).show();
             }
         });


         final TextView time_view = (TextView) findViewById(R.id.time_view);
         final TextView equation_view = (TextView) findViewById(R.id.equation_view);
         final TextView logarithmic_view = (TextView) findViewById(R.id.logarithmic_view);


         for (int i = 0; i < 10; i++) {
             myRegionArray[i][2] = "" + (1 / Double.parseDouble(myRegionArray[i][1]));
         }

         //This is where the List Adapter is Made
         lv = (ListView) findViewById(R.id.numberList_view);

         // Instanciating an array list (you don't need to do this,
         // you already have yours).

         List<String> myNumbers = new ArrayList<String>();

         for (int i = 0; i < 10; i++){
             myNumbers.add("" + myRegionArray[i][0]);

         }

         // This is the array adapter, it takes the context of the activity as a
         // first parameter, the type of list view as a second parameter and your
         // array as a third parameter.
         ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                 this,
                 android.R.layout.simple_list_item_1,
                 myNumbers);

         lv.setAdapter(arrayAdapter);

         lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position,
                                     long id) {

                 String pickedCountry = ((TextView) view).getText().toString();

                 Toast.makeText(getBaseContext(), pickedCountry, Toast.LENGTH_LONG).show();

             }
         });

         trueMid = -(Math.log((100/Double.parseDouble(myRegionArray[8][2]))-1))/networking;

         Toast.makeText(TimerTest.this, "" + trueMid , Toast.LENGTH_SHORT).show();

 //End


         Thread t = new Thread()

         {


             @Override
             public void run() {

                 try {
                     while (!isInterrupted()) {
                         while (!suspended) {
                             Thread.sleep(500);
                             runOnUiThread(new Runnable() {
                                 @Override
                                 public void run() {
                                     time.update();
                                     time_view.setText(time.toString());
                                     equation_view.setText("Anti-Virus Progress = (Security) * (Panic)(Time) - Shuffle = " + " " + ((security * (panic / 4) * time.getDay()) - shuffle));
                                     logarithmic_view.setText("This is what the logartithimic will look like for Asia:\n" + (100 / (1 + Math.exp(networking * (time.getDay() - trueMid)))));
                                     equationVal = 100 / (1 + Math.exp(networking * (time.getDay() - trueMid)));


                                     if (equationVal >= 50) {
                                         suspended = true;
                                         //mySound.start();
                                     }

                                 }
                             });
                         }
                     }
                 } catch (InterruptedException e) {
                 }
             }
         };

         t.start();

         // ATTENTION: This was auto-generated to implement the App Indexing API.
         // See https://g.co/AppIndexing/AndroidStudio for more information.
         client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.menu_timer_test, menu);
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
         if (id == R.id.upgrades) {
             startActivity(new Intent(this, Upgrades.class));
             return true;
         }
         if (id == R.id.databaseTester) {

             Intent intentData = new Intent(this, MainActivity.class);
//             intentData.putExtra("virus_Id", 0);
//             intentData.putExtra("count", 0);
             startActivity(intentData);
             return true;
         }

         return super.onOptionsItemSelected(item);
     }

     @Override
     public void onStart() {
         super.onStart();

         // ATTENTION: This was auto-generated to implement the App Indexing API.
         // See https://g.co/AppIndexing/AndroidStudio for more information.
         client.connect();
         Action viewAction = Action.newAction(
                 Action.TYPE_VIEW, // TODO: choose an action type.
                 "TimerTest Page", // TODO: Define a title for the content shown.
                 // TODO: If you have web page content that matches this app activity's content,
                 // make sure this auto-generated web page URL is correct.
                 // Otherwise, set the URL to null.
                 Uri.parse("http://host/path"),
                 // TODO: Make sure this auto-generated app deep link URI is correct.
                 Uri.parse("android-app://com.mikescompany.hacktheplanet.timertest/http/host/path")
         );
         AppIndex.AppIndexApi.start(client, viewAction);
     }

     @Override
     public void onStop() {
         super.onStop();

         // ATTENTION: This was auto-generated to implement the App Indexing API.
         // See https://g.co/AppIndexing/AndroidStudio for more information.
         Action viewAction = Action.newAction(
                 Action.TYPE_VIEW, // TODO: choose an action type.
                 "TimerTest Page", // TODO: Define a title for the content shown.
                 // TODO: If you have web page content that matches this app activity's content,
                 // make sure this auto-generated web page URL is correct.
                 // Otherwise, set the URL to null.
                 Uri.parse("http://host/path"),
                 // TODO: Make sure this auto-generated app deep link URI is correct.
                 Uri.parse("android-app://com.mikescompany.hacktheplanet.timertest/http/host/path")
         );
         AppIndex.AppIndexApi.end(client, viewAction);
         client.disconnect();
     }
     @Override
     public void onResume(){
         super.onResume();
         trueMid = -(Math.log((100/Double.parseDouble(myRegionArray[8][2]))-1))/networking;

         Toast.makeText(TimerTest.this, "" + trueMid , Toast.LENGTH_SHORT).show();

     }

 }
