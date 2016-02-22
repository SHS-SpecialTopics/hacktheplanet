package com.example.rayqu.timertest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rayqu on 1/8/2016.*/
public class DbHelper extends SQLiteOpenHelper {


    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_DATA= "CREATE TABLE " + DataOutline.TABLE  + "("
                + DataOutline.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + DataOutline.KEY_name + " TEXT, "
                + DataOutline.KEY_category + " DOUBLE, "
                + DataOutline.KEY_value + " TEXT )";

        db.execSQL(CREATE_TABLE_DATA);



    }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed, all data will be gone!!!
            db.execSQL("DROP TABLE IF EXISTS " + DataOutline.TABLE);

            // Create tables again
            onCreate(db);

        }
}
