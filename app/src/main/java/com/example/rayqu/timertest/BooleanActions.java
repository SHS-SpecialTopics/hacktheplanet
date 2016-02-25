package com.example.rayqu.timertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rayqu on 2/25/2016.
 */
public class BooleanActions {
    private BooleanHelper bHelper;

    public BooleanActions(Context context) {
        bHelper= new BooleanHelper(context);
    }


    public int insert(BooleanOutline booleanVal) {

        //Open connection to write data
        SQLiteDatabase db = bHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BooleanOutline.KEY_value, booleanVal.value);

        // Inserting Row
        long boolean_Id = db.insert(BooleanOutline.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) boolean_Id ;
    }


    /* public void delete(int virus_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(DataOutline.TABLE, DataOutline.KEY_ID + "= ?", new String[]{String.valueOf(virus_Id)});

        Log.d("DeleteMethod", "**********************");

        db.close(); // Closing database connection
    }

    public void update(DataOutline virusValues) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DataOutline.KEY_value, virusValues.value);
        values.put(DataOutline.KEY_category,virusValues.category);
        values.put(DataOutline.KEY_name, virusValues.name);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(DataOutline.TABLE, values, DataOutline.KEY_ID + "= ?", new String[]{String.valueOf(virusValues.virus_ID)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getVirusList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                DataOutline.KEY_ID + "," +
                DataOutline.KEY_name + "," +
                DataOutline.KEY_category+ "," +
                DataOutline.KEY_value +
                " FROM " + DataOutline.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> virusList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> virus = new HashMap<String, String>();
                virus.put("id", cursor.getString(cursor.getColumnIndex(DataOutline.KEY_ID)));
                virus.put("name", cursor.getString(cursor.getColumnIndex(DataOutline.KEY_name)));
                virus.put("category", cursor.getString(cursor.getColumnIndex(DataOutline.KEY_category)));
                virus.put("value", cursor.getString(cursor.getColumnIndex(DataOutline.KEY_value)));
                virusList.add(virus);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return virusList;

    } */


    public BooleanOutline getBooleann(int Id){
        SQLiteDatabase db = bHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                DataOutline.KEY_ID + "," +
                DataOutline.KEY_value +
                " FROM " + BooleanOutline.TABLE
                + " WHERE " +
                BooleanOutline.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        BooleanOutline bool1 = new BooleanOutline();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        cursor.close();
        db.close();
        return bool1;
    }


}

