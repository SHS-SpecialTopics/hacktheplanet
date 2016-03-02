package com.example.rayqu.timertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

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


    public void update(BooleanOutline boo) {

        SQLiteDatabase db = bHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BooleanOutline.KEY_value, boo.value);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(BooleanOutline.TABLE, values, BooleanOutline.KEY_ID + "= ?", new String[]{String.valueOf(boo.value)});
        db.close(); // Closing database connection
    }




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

