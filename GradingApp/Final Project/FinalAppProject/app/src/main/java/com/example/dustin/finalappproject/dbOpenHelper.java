package com.example.dustin.finalappproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

/**
 * Created by Dustin on 10/19/2016.
 */

public class dbOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "STUDENT_DB";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "STUDENT_GRADES";
    public static final String COLUMN_ID = "COLUMN_ID";
    public static final String GRADE = "GRADE";
    public static final String LETTER_GRADE = "Letter Grade";
    public static final String STUDENT_ID = "STUDENT_ID";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LASTNAME = "LAST_NAME";
    public static final String CLASS_ID = "CLASS_ID";
    public static final String CLASS_NAME = "CLASS_NAME";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEHER PRIMARY KEY AUTOINCREMENT, " +
                    STUDENT_ID + " VARCHAR(8) , " +
                    FIRST_NAME + " VARCHAR(20), " + LASTNAME + " VARCHAR(20), " + CLASS_ID +
                    " INTEGER(3), " + CLASS_NAME + " VARCHAR(20), " + GRADE + " VARCHAR(2) " + LETTER_GRADE + "VARCHAR(2)" + ")";

    public dbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public boolean addStudent(int studentID, String firstName, String lastName, int classID, String className){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long result;
        values.put(STUDENT_ID, studentID);
        values.put(FIRST_NAME, firstName);
        values.put(LASTNAME, lastName);
        values.put(CLASS_ID, classID);
        values.put(CLASS_NAME, className);
        result = db.insert(TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean addGrade(int columnID, int grade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long result;
        values.put(GRADE, grade);
        String where = "WHERE COLUMN_ID = " + columnID;
        result = db.update(TABLE_NAME, values, where, null);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor viewStudents(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);
    }
    public  int getGradeValues(int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        int totalGrade = 0;
        int temp = 0;
        String query = "SELECT GRADES FROM STUDENT_DB WHERE STUDENT_ID = " + ID;
        Cursor cursor;
        cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            temp = cursor.getInt(0);
            totalGrade += temp;
        }
        cursor.close();
        return totalGrade;
    }
}
