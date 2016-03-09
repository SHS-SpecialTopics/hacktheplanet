package com.example.rayqu.timertest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BooleanHelper extends SQLiteOpenHelper {


    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StupidOne.db";

    public BooleanHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_DATA= "CREATE TABLE " + BooleanOutline.TABLE  + "("
                + BooleanOutline.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + DataOutline.KEY_value + " INTEGER )";

        db.execSQL(CREATE_TABLE_DATA);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + BooleanOutline.TABLE);

        // Create tables again
        onCreate(db);

    }





}
