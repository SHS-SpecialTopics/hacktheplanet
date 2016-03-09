package com.example.rayqu.timertest;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class myDatabase extends AppCompatActivity {


    private int _Virus_Id;
    private ListView lv;
    double value;
    int id;
    int count = 0;
    String category;
    String name;
    boolean matching = false;

    int[] testerRay = {1,2,3,4,5,6};

    int counter = 0;

    final String PREFS_NAME = "MyPresFile";
    final int FOOBAR = 0;
    final String GETIT = "yolo";

    BooleanActions placer;
    BooleanOutline boo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fake_map);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Virus Database Stuff

        Intent intentData = getIntent();

        _Virus_Id = intentData.getIntExtra("virus_Id", 0);

        DbActions repo = new DbActions(this);

        DataOutline virus = new DataOutline();

        // End of Virus Database Stuff


        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int countForMe = prefs.getInt(GETIT,FOOBAR);

        Toast.makeText(myDatabase.this, "" + countForMe, Toast.LENGTH_SHORT).show();


        Global global = (Global) getApplication();

        count = global.counter;

        //This is where I will get the numbers from the Upgrades Screen

        value = .05;
        category = "Networking";
        name = "CellTowers";
        id = 0;

        // Make and Get Boolean from Table

        Boolean tester;

        if(countForMe == 0)
        {
            placer = new BooleanActions(this);
            boo = new BooleanOutline();
            global.setYanksOut(placer);
            global.setYanksIn(boo);
            count++;
            global.counter = count;
            tester = false;
        }
        else
        {
            placer = global.getYanksOut();
            boo = global.getYanksIn();
            tester = true;
        }


        global.set_Opened(tester);

        Boolean openedYet = global.get_Opened();

        if(!openedYet) {

            for (int k = 0; k < 10; k++) {
                virus.value = value + k;
                virus.category = category;
                virus.name = name;
                virus.virus_ID = k;
                repo.insert(virus);
            }
            ((Global) this.getApplicationContext()).set_Opened(true);
            int myBooInt = (global.get_Opened()) ? 1 : 0;
            boo.value = myBooInt;
            global.setYanksOut(placer);
            global.setYanksIn(boo);

        }


        placer.update(boo);
        global.setYanksOut(placer);

        //This is my viruses virus ID

        int userInputedNum = 5;
        int userChangeNum = 10;

        //Save Global Variable in Database

        while(!matching){
            for(int i = 0; i < testerRay.length ; i ++){
                if(i == userInputedNum) {
                    DataOutline virusChange = repo.getVirusById(userInputedNum);
                    virusChange.value += userChangeNum;
                    repo.update(virusChange);
                    Toast.makeText(this, "Virus Number " + virusChange.virus_ID + "Updated", Toast.LENGTH_SHORT).show();
                    matching = true;
                }
            }

        }



        lv = (ListView) findViewById(R.id.stuff_view);

        ArrayList<HashMap<String, String>> studentList = repo.getVirusList();

        ArrayAdapter<HashMap<String, String>> arrayAdapter = new ArrayAdapter<HashMap<String, String>>(
                this,
                android.R.layout.simple_list_item_1,
                studentList);

        lv.setAdapter(arrayAdapter);


        global.counter++;


        counter++;


        prefs.edit().putInt(GETIT, counter).commit();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


}
