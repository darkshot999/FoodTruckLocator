package com.example.dustin.finalappproject;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dustin on 10/22/2016.
 */

public class dbManager {
    static SQLiteDatabase database;
    static SQLiteOpenHelper dbHelper;
    public static void open(){
        database = dbHelper.getWritableDatabase();
    }
    public static void close(){
        dbHelper.close();
    }
}
