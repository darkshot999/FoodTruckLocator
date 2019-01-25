package com.example.dustin.finalappproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Dustin on 10/21/2016.
 */

public class gradingPage extends AppCompatActivity {
    dbOpenHelper datasource;
    dbManager dbm;
    Button addGrade;
    Button goToCalc;
    Button viewStudents;
    EditText columnID;
    EditText grade;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grading_page);
        datasource = new dbOpenHelper(this);
        goToCalc = (Button) findViewById(R.id.goToFinal);
        columnID = (EditText) findViewById(R.id.ColumnID);
        grade = (EditText) findViewById(R.id.Grade);
        addGrade = (Button) findViewById(R.id.updateGrade);
        viewStudentData();
        addGrade();
    }
    public void addGrade(){
        addGrade.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbManager.open();
                        int tGrade = grade.getId();
                        int tColumnID = columnID.getId();
                        datasource.addGrade(tColumnID, tGrade);
                        dbManager.close();
                    }
                }
        );
    }
    public void viewStudentData(){
        viewStudents.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor results = datasource.viewStudents();
                        if(results.getCount() == 0){
                            Toast.makeText(gradingPage.this, "No data to show", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        StringBuilder buffer = new StringBuilder();
                        while (results.moveToNext()){
                            buffer.append("Student ID: ").append(results.getInt(0)).append("\n");
                            buffer.append("First Name: ").append(results.getString(1)).append("\n");
                            buffer.append("Last Name: ").append(results.getString(2)).append("\n");
                            buffer.append("Class ID: ").append(results.getInt(3)).append("\n");
                            buffer.append("Class Name: ").append(results.getString(4)).append("\n\n");
                            showMessage("Students", buffer.toString());
                        }
                    }
                }
        );
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void goToCalc(){
        goToCalc.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(gradingPage.this, gradeCalc.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
