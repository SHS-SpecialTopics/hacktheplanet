package android.realmtest.com.realmtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import junit.framework.Test;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    RealmConfiguration realmConfig;
    Realm realm;
    private ListView lv;
    String helperstring;

    RealmList<Virus> virusList;
    ArrayList<String> virusListView = new ArrayList<String>();

    int timerCount = 0;

    String[] securityStrings = { "Code Scrambler I", "Code Scrambler II", "Code Scrambler III",
            "Code Scrambler IV", "Code Scrambler V", "Cry Baby", "Toddler's Tantrum",
            "Teenage Angst", "Parent's Scorn", "Programmer's Rage!", "God's Wrath", "Macro Virus",
            "File Infector", "Overwrite Virus", "Resident Virus"};

    String[] lethalityStrings = {"Laptop", "Desktop", "Workstation", "Mobile Phones",
            "Smart Phones", "Tablets", "Ultimate Civilian Controller", "WiFi Scrambler",
            "Random Reboots", "Servers (Military)", "Mainframes (Military)",
            "Government Satellites", "Remotely Operated Vehicles", "Warsuits", "Drones",
            "Launch Codes"};

    String[] networkingStrings = {"Pop-Ups", "Routers (Home)", "USB Transmission",
            "Internet Provider", "Infected Programs", "Routers (Business)", "LAN Connections",
            "Cell Towers", "Data Servers (Business)", "Data Servers (Tech Conglomerates)",
            "Satellites", "Mainframes (Business)", "Trans-Atlantic Cables", "MAN Connections",
            "Upload to Cloud"};

    double[] securityVals = {3.00,5.00,7.00,9.00,11.00,-0.1,-0.15,-0.25,-0.4,-0.45,-0.5,-0.20,-0.30,-0.45,-0.49};
    double[] lethalityVals = {0.10, 0.15,0.20,0.35,0.40,0.45,0.50,0.10,0.15,0.20,0.25,0.30,0.40,0.45,0.50,100000000};
    double[] networkingVals = {0.025,0.025,0.025,0.025,0.050,0.050,0.050,0.050,0.100,0.100,0.125,0.125,0.200,0.200,0.450};

    boolean suspended = false;
    myDate time = new myDate(0);
    double equationVal;
    double AntiVirus;

    double security = 0;
    double panic = 0;
    //Need panic to change according to??
    int shuffle = 0;
    double networking = 0;
    double lethality = 0;
    double lethalCarry = 0;

    double trueMid = 0;

    boolean securityHelper = false;
    boolean networkingHelper = false;
    boolean lethalityHelper = false;

    boolean reCalcMidPoint = false;

    double carryCap = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        realmConfig = new RealmConfiguration.Builder(this).build();

        realm = Realm.getInstance(realmConfig);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Please Select A Realm So We Can Get On With The Show", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });



        View decorView = getWindow().getDecorView(); // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }


    public void openGameOne(View view){

        realmConfig = new RealmConfiguration.Builder(this).build();

        realm = Realm.getInstance(realmConfig);

        realm.beginTransaction();

        Game game1 = realm.where(Game.class).findFirst();

        lv = (ListView) findViewById(R.id.listViewVirus);

        realm.commitTransaction();


        if (game1 == null) {

        realm.beginTransaction();
            game1 = realm.createObject(Game.class);
            game1.setName("Game1Bitch");
            game1.setId(1);

            int bob = game1.getVirusList().size();

            virusList = game1.getVirusList();


            for(int i = 0; i < securityStrings.length; i++) {
                Virus looper = new Virus();
                looper.setName(securityStrings[i] + " ("  + i + ")");
                looper.setCategory("Security");
                looper.setSecurityValue(securityVals[i]);
                looper.setId(i);
                virusList.add(looper);
            }

            for(int i = 0; i < lethalityStrings.length; i++) {
                Virus looper = new Virus();
                looper.setName(lethalityStrings[i] + " ("  + (securityStrings.length +i) + ")");
                looper.setCategory("Lethality");
                looper.setLethalityValue(lethalityVals[i]);
                looper.setId(i);

                if(i < 7){
                    looper.setLethalityC(true);
                }

                if(i > 6){
                    looper.setLethalityG(true);
                }

                virusList.add(looper);

            }

            for(int i = 0; i < networkingStrings.length; i++) {
                Virus looper = new Virus();
                looper.setName(networkingStrings[i] + " ("  + (securityStrings.length + lethalityStrings.length + i)+ ")");
                looper.setCategory("Networking");
                looper.setNetworkingValue(networkingVals[i]);
                looper.setId(i);
                virusList.add(looper);
            }


            realm.commitTransaction();

            Toast.makeText(MainActivity.this, "Game1 Created", Toast.LENGTH_SHORT).show();

            //panic = 1;

            virusList = game1.getVirusList();

            for(int j = 0; j < securityStrings.length + networkingStrings.length + lethalityStrings.length; j++) {

                Virus helpMe;
                helpMe = virusList.get(j);
                helperstring = helpMe.getName();
                virusListView.add(helperstring);

            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    R.layout.customlay,
                    virusListView);

            lv.setAdapter(arrayAdapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Virus helpMeAgain = new Virus();
                    helpMeAgain = virusList.get(position);

                    String useThis = "";

                    realm.beginTransaction();

                    if(helpMeAgain.getNetworkingValue() != 0)
                    {
                        useThis = helpMeAgain.getNetworkingValue() + "";
                        Toast.makeText(MainActivity.this, "" + helpMeAgain.getcategory() + " : " + useThis, Toast.LENGTH_SHORT).show();

                        if(helpMeAgain.getPurchased() == false) {

                            networkingHelper = helpMeAgain.getPurchased();
                            networkingHelper = true;

                            networking = networking + helpMeAgain.getNetworkingValue();
                            helpMeAgain.setPurchased(networkingHelper);
                        }
                    }
                    else if(helpMeAgain.getSecurityValue() != 0)
                    {
                        useThis = helpMeAgain.getSecurityValue() + "";
                        Toast.makeText(MainActivity.this, "" + helpMeAgain.getcategory() + " : " + useThis, Toast.LENGTH_SHORT).show();

                        if(helpMeAgain.getPurchased() == false) {

                            securityHelper = helpMeAgain.getPurchased();
                            securityHelper = true;

                            security = security + helpMeAgain.getSecurityValue();
                            helpMeAgain.setPurchased(securityHelper);
                        }


                    }
                    else
                    {
                        useThis = helpMeAgain.getLethalityValue() + "";
                        Toast.makeText(MainActivity.this, "" + helpMeAgain.getcategory() + " : " + useThis + " C- " + helpMeAgain.getLethalityC() + " G- " + helpMeAgain.getLethalityG(), Toast.LENGTH_SHORT).show();

                        if(helpMeAgain.getPurchased() == false) {

                            lethalityHelper = helpMeAgain.getPurchased();
                            lethalityHelper = true;

                            if(helpMeAgain.getLethalityC()) {
                                lethalCarry = lethalCarry + helpMeAgain.getLethalityValue() * 2;
                            }
                            else
                            {
                                panic = lethality;
                                lethality = lethality + helpMeAgain.getLethalityValue();
                            }



                            helpMeAgain.setPurchased(lethalityHelper);



                        }
                    }

                    realm.commitTransaction();

                    Realm realmer = Realm.getInstance(realmConfig);

                    realmer.beginTransaction();


                    realmer.commitTransaction();

                    //Toast.makeText(MainActivity.this, "" + helpMeAgain.getcategory() + " : " + useThis, Toast.LENGTH_SHORT).show();

                }
            });
        }
        else{

            realm.beginTransaction();
            virusListView.clear();

            Toast.makeText(MainActivity.this, "There Is A Game1 Here Already", Toast.LENGTH_SHORT).show();

            Game game1Already = realm.where(Game.class).findFirst();

            final RealmList<Virus> virusListAlready = game1Already.getVirusList();

            for(int j = 0; j < securityStrings.length + networkingStrings.length + lethalityStrings.length; j++) {

                Virus helpMeAlready;
                helpMeAlready = virusListAlready.get(j);
                helperstring = helpMeAlready.getName();
                virusListView.add(helperstring);

            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    R.layout.customlay,
                    virusListView);

            lv.setAdapter(arrayAdapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Virus helpMeAgain = new Virus();
                    helpMeAgain = virusListAlready.get(position);

                    String useThis = "";

                    realm.beginTransaction();

                    if(helpMeAgain.getNetworkingValue() != 0)
                    {
                        useThis = helpMeAgain.getNetworkingValue() + "";
                        Toast.makeText(MainActivity.this, "" + helpMeAgain.getcategory() + " : " + useThis, Toast.LENGTH_SHORT).show();

                        if(helpMeAgain.getPurchased() == false) {

                            networkingHelper = helpMeAgain.getPurchased();
                            networkingHelper = true;

                            networking = networking + helpMeAgain.getNetworkingValue();
                            helpMeAgain.setPurchased(networkingHelper);
                        }

                    }
                    else if(helpMeAgain.getSecurityValue() != 0)
                    {
                        useThis = helpMeAgain.getSecurityValue() + "";
                        Toast.makeText(MainActivity.this, "" + helpMeAgain.getcategory() + " : " + useThis, Toast.LENGTH_SHORT).show();

                        if(helpMeAgain.getPurchased() == false) {

                            securityHelper = helpMeAgain.getPurchased();
                            securityHelper = true;

                            security = security + helpMeAgain.getSecurityValue();
                            helpMeAgain.setPurchased(securityHelper);
                        }


                    }
                    else
                    {
                        useThis = helpMeAgain.getLethalityValue() + "";
                        Toast.makeText(MainActivity.this, "" + helpMeAgain.getcategory() + " : " + useThis + " C- " + helpMeAgain.getLethalityC() + " G- " + helpMeAgain.getLethalityG(), Toast.LENGTH_SHORT).show();

                        if(helpMeAgain.getPurchased() == false) {

                            lethalityHelper = helpMeAgain.getPurchased();
                            lethalityHelper = true;

                            if(helpMeAgain.getLethalityC()) {
                                lethalCarry = lethalCarry + helpMeAgain.getLethalityValue() * 2;
                            }
                            else
                            {
                                lethality = lethality + helpMeAgain.getLethalityValue();
                            }



                            helpMeAgain.setPurchased(lethalityHelper);
                        }


                    }

                    realm.commitTransaction();

                }
            });

            realm.commitTransaction();
        }



    }


    public void changeNetworking(View view){

        if(realm.where(Game.class).equalTo("id", 1).findFirst() == null)
        {
            Toast.makeText(MainActivity.this, "Ouch No Game Yet", Toast.LENGTH_SHORT).show();
        }
        else {
            realm.beginTransaction();

            Integer test = 1;

            Game editGame = realm.where(Game.class).equalTo("id", 1).findFirst();

            final RealmList<Virus> editVirusList;

            virusListView.clear();

            editVirusList = editGame.getVirusList();
            final RealmList<Virus> looperList = new RealmList<Virus>();

            String helperString2 = "";

            for (int j = 0; j < 46; j++) {


                Virus helpMeAgain;

                helpMeAgain = editVirusList.get(j);

                if (helpMeAgain.getcategory().equals("Networking")) {
                    helperString2 = helpMeAgain.getName();
                    helpMeAgain.setNetworkingValue(helpMeAgain.getNetworkingValue() + 10);
                    looperList.add(helpMeAgain);
                    virusListView.add(helperString2);
                }


            }

            ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(
                    this,
                    R.layout.customlay,
                    virusListView);

            lv.setAdapter(arrayAdapter2);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Virus helpMeAgain3 = new Virus();
                    helpMeAgain3 = looperList.get(position);
                    Toast.makeText(MainActivity.this, "" + helpMeAgain3.getcategory() + " : " + helpMeAgain3.getNetworkingValue(), Toast.LENGTH_SHORT).show();

                }
            });


            realm.commitTransaction();
        }

    }


    public void equationChange(View view) {

        setContentView(R.layout.equation_list);


        if (timerCount == 0) {

            final TextView time_view = (TextView) findViewById(R.id.time_view);
            final EditText equation_view = (EditText) findViewById(R.id.SecurityEdit);
            final EditText logarithmic_view = (EditText) findViewById(R.id.LogGrowthEdit);

            time_view.setText(" ");
            equation_view.setText(" ");
            logarithmic_view.setText(" ");

            final TextView networkingCoeff = (TextView) findViewById(R.id.NetworkingCoeff);
            final TextView securityCoeff = (TextView) findViewById(R.id.SecurityCoeff);
            final TextView lethalityCoeff = (TextView) findViewById(R.id.LethalityCoeff);
            final TextView midPoint = (TextView) findViewById(R.id.TrueMid);
            final TextView carryingCapacity = (TextView) findViewById(R.id.CarryingCap);


            networkingCoeff.setText("Networking: " + networking);
            securityCoeff.setText("Security: " + security);
            lethalityCoeff.setText("Lethality: " + lethality);
            carryingCapacity.setText("CarryingCap: " + lethalCarry);



            trueMid = ((Math.log(((carryCap * lethalCarry)/(1.17691094*10e-32))-1))/(networking + lethality)) + 0;

            midPoint.setText("Mid Point: " + trueMid);

            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        while (!isInterrupted()) {
                            while (!suspended) {
                                Thread.sleep(250);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        time.update(/*'s', time.getSec() + (3600 * 12)*/);

                                        time_view.setText(time.toString());


                                        logarithmic_view.setText("" + ((carryCap * lethalCarry) / (1 + Math.exp((-(networking + lethality)) * (time.getTime() - trueMid)))));

                                        equationVal = ((carryCap * lethalCarry) / (1 + Math.exp(-((networking + lethality)) * (time.getTime() - trueMid))));


                                        //Get Rid of at Some Point
                                        //trueMid = ((Math.log((100/1)-1))/(networking + lethality)) + 0;

//                                        Log.d("Equation Value", "" + equationVal);
//                                        Log.d("True Middle", "" + trueMid);
//                                        Log.d("Seconds", "" + time.getTime());

                                        int randomness;

                                        if(lethality >= .30){

                                            randomness  = (int) (Math.random() * 1000);

                                            //Randomness here
                                            if (randomness > 995){

                                                Toast.makeText(MainActivity.this, "" + randomness + " It Is Spreading: " + lethality , Toast.LENGTH_SHORT).show();

                                            }
                                        }

                                        int randomness2;


                                        if (AntiVirus <= 100)
                                        {

                                            equation_view.setText("Antivirus Progress = (1 - Security) * (Panic)(Time) - Shuffle = " + " 100 - " + (((1 + security) * (panic / 4) * time.getTime()) - shuffle));
                                            AntiVirus = (((1 + security) * (panic / 4) * time.getTime()) - shuffle);

                                            if(lethalCarry >= .35)
                                            {

                                                randomness2 = (int) (Math.random() * 10000);

                                                Log.d("Panic", "" + panic);

                                                if (randomness2 > 9800)
                                                {

                                                    panic = panic + (lethalCarry/2);

                                                    Toast.makeText(MainActivity.this, "It Okay Don't Panic It Is Only: " + panic, Toast.LENGTH_SHORT).show();
                                                }

                                            }
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

            midPoint.setText("Mid Point: " + trueMid);


        } else {

            final TextView time_view = (TextView) findViewById(R.id.time_view);
            final EditText equation_view = (EditText) findViewById(R.id.SecurityEdit);
            final EditText logarithmic_view = (EditText) findViewById(R.id.LogGrowthEdit);

            final TextView networkingCoeff = (TextView) findViewById(R.id.NetworkingCoeff);
            final TextView securityCoeff = (TextView) findViewById(R.id.SecurityCoeff);
            final TextView lethalityCoeff = (TextView) findViewById(R.id.LethalityCoeff);
            final TextView midPoint = (TextView) findViewById(R.id.TrueMid);


            suspended = false;


                    time_view.setText(time.toString());
                    equation_view.setText("Antivirus Progress = (Security) * (Panic)(Time) - Shuffle = " + " " + (((1 + security) * (panic / 4) * time.getTime()) - shuffle));
                    logarithmic_view.setText("" + ((carryCap * lethalCarry) / (1 + Math.exp((networking + lethality) * (time.getTime() - trueMid)))));

                    networkingCoeff.setText("Networking: " + networking);
                    securityCoeff.setText("Security: " + security);
                    lethalityCoeff.setText("Lethality: " + lethality);
                    midPoint.setText("Mid Point: " + trueMid);

            }


        timerCount++;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    // Make the Screens Back Button go to the Main Screen
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");

//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);

        Log.d("Hit it", "Stop Fucking");

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Please Select A Realm So We Can Get On With The Show", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        suspended = true;
    }

    public void resetGame(View view) {

        realmConfig = new RealmConfiguration.Builder(this).build();

        realm = Realm.getInstance(realmConfig);

        realm.beginTransaction();

        realm.clear(Game.class);

        realm.commitTransaction();

        virusListView.clear();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.customlay,
                virusListView);


        if (lv != null)
            lv.setAdapter(arrayAdapter);

        Toast.makeText(MainActivity.this, "Reseted", Toast.LENGTH_SHORT).show();


    }

//    public void reCalcMid(View view)
//    {
//
//        final TextView midPoint = (TextView) findViewById(R.id.TrueMid);
//
//        trueMid = ((Math.log(((carryCap * lethalCarry) /(1.17691094*10e-32))-1))/(networking + lethality)) + 0;
//
//        equationVal = ((carryCap * lethalCarry)/ (1 + Math.exp(-((networking + lethality)) * (time.getTime() - trueMid))));
//
//        //                                                              Maybe change above to 0
//
//        midPoint.setText("Mid Point: " + trueMid);
//
//        double equationValCheck;
//
//        equationValCheck = ((carryCap * lethalCarry) / (1 + Math.exp(-((networking + lethality)) * (0 - trueMid))));
//
//        Toast.makeText(MainActivity.this, "" + equationValCheck, Toast.LENGTH_SHORT).show();
//
//
//        //Log.d("Checker", equationVal + "");
//        //Log.d("Checker", "Here I Should Be");
//
//
//        //Instead of Calculating the midpoint with the equationVal it needs to be the number of equationVal at 0 with the networking + lethality of .025 and the trueMid of 3125
//
//    }




    /*Commented Out Comments*/
    //
//            //Making of a Second Game to Test Straight Virus Query
//            game2 = realm.createObject(Game.class);
//            game2.setName("GameNumber2");
//            game2.setId(2);
//
//            RealmList<Virus> virusListPrime = game2.getVirusList();
//
//            Virus prime = new Virus();
//
//            prime.setId(100);
//            prime.setName("HelloWorld");
//            prime.setCategory("Networking");
//            prime.setSecurityValue(100000000);
//
//            virusListPrime.add(0,prime);
//
//            game2.setVirusList(virusListPrime);
//            //End of Making Second Game
//
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
//        Make a Lethality G and Lethality C that controls panic and carrying cap respectively
//        Now make the number arrays for the above
//        Remember that some of the security (scramblers) have different type of numbers for there values
//        Used For Thread Below
//
//        REMEMBER TO ACTUALLY SET THE VIRUSES TO HAVE THE CORRECT VALUE IN THE CORRECT VALUE-SET
//        LIKE PUTTING LETHALITY VALUES IN THE setLethalityValue METHOD (LOOK!!!!)
//
//        Put right here (above) that along with the toast it changes something in the equations!
//        Also have the xml file show the networking, security and lethality values,
//

    //Make a random number generator dependent on lethality that starts the panic!!!

    //if(lethality > .30)
    //{
    //Random Number Blah
    //}

    // IF IT GOES OVER A YEAR USING TIME WILL BE WRONG!!!!!
    //setContentView(R.layout.main);
}

