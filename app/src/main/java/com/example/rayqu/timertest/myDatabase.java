package com.example.rayqu.timertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rayqu on 2/19/2016.
 */
public class myDatabase extends AppCompatActivity {


    private int _Virus_Id = 0;
    private ListView lv;
    double value;
    String category;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fake_map);

        Intent intent = getIntent();

        _Virus_Id = intent.getIntExtra("virus_Id", 0);


        DbActions repo = new DbActions(this);

        DataOutline virusPrime = new DataOutline();

        virusPrime = repo.getVirusById(_Virus_Id);


        //This is where I will get the numbers from the Upgrades screem

        value = .05;
        category = "Networking";
        name = "CellTowers";

        DataOutline virus = new DataOutline();


        virus.value = value;
        virus.category = category;
        virus.name = name;
        virus.virus_ID = _Virus_Id;


        if (_Virus_Id == 0){
            _Virus_Id = repo.insert(virus);

            Toast.makeText(this,"New Virus Insert",Toast.LENGTH_SHORT).show();
            //finish();
        }else{

            repo.update(virus);
            Toast.makeText(this,"Virus Updated",Toast.LENGTH_SHORT).show();
            //finish();
        }


        lv = (ListView) findViewById(R.id.stuff_view);


        ArrayList<HashMap<String, String>> studentList = repo.getVirusList();

        ArrayAdapter<HashMap<String, String>> arrayAdapter = new ArrayAdapter<HashMap<String, String>>(
                this,
                android.R.layout.simple_list_item_1,
                studentList);

        lv.setAdapter(arrayAdapter);



    }

    


}
