package com.example.rayqu.timertest;

import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VirusDetail extends AppCompatActivity{ //implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose;
    public EditText editTextName;
    EditText editTextCategory;
    EditText editTextValue;
    private int _Virus_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fake_map);


        //DataTest maker = new DataTest();

        /* btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextName = (EditText) findViewById(R.id.editVirusName);
        editTextCategory = (EditText) findViewById(R.id.editTextCategory);
        editTextValue = (EditText) findViewById(R.id.editTextValue);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        */

        Intent intent = getIntent();

        //Find out what this does??
        _Virus_Id = intent.getIntExtra("virus_Id", 0);


        DbActions repo = new DbActions(this);

        DataOutline virusPrime = new DataOutline();

        virusPrime = repo.getVirusById(_Virus_Id);


        /*
        if (intent.getIntExtra("origin",0) != 101)

        {
            editTextValue.setText(Integer.toString(DataTest.value));
            editTextName.setText(DataTest.name);
            editTextCategory.setText(DataTest.category);

        }
        else
        {
            editTextValue.setText("");
            editTextName.setText("");
            editTextCategory.setText("");
        }

    }
        */


    /* public void onClick(View view) {


        if (view == findViewById(R.id.btnSave)){

            DbActions repo = new DbActions(this);

            DataOutline virus = new DataOutline();


            virus.value = Integer.parseInt(editTextValue.getText().toString());
            virus.category = editTextCategory.getText().toString();
            virus.name = editTextName.getText().toString();
            virus.virus_ID = _Virus_Id;

            if (_Virus_Id == 0){
                _Virus_Id = repo.insert(virus);

                Toast.makeText(this,"New Virus Insert",Toast.LENGTH_SHORT).show();
                finish();
            }else{

             repo.update(virus);
                Toast.makeText(this,"Virus Updated",Toast.LENGTH_SHORT).show();
                finish();
            }


        }else if (view == findViewById(R.id.btnDelete)){

            DbActions repo = new DbActions(this);

            //virus_Id = (TextView) view.findViewById(R.id.virus_Id);

            int virusId = DataTest.getID();

            repo.delete(virusId);

            Toast.makeText(this, "Virus Deleted", Toast.LENGTH_SHORT).show();
            finish();

        }else if (view == findViewById(R.id.btnClose)){
            finish();
        }

 */
    }

}

