package com.example.dustin.finalappproject;

import android.database.Cursor;
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

public class gradeCalc extends AppCompatActivity {
    dbOpenHelper datasource;
    EditText studentID;
    Button viewStudents;
    Button viewSemester;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grade_calculator);
        datasource = new dbOpenHelper(this);
        studentID = (EditText) findViewById(R.id.studentIDGC);
        viewSemester = (Button) findViewById(R.id.viewSemesterGC);
        viewStudents = (Button) findViewById(R.id.viewStudentsGC);
    }
    public void viewStudentData(){
        viewStudents.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor results = datasource.viewStudents();
                        if(results.getCount() == 0){
                            Toast.makeText(gradeCalc.this, "No data to show", Toast.LENGTH_SHORT).show();
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
    public void viewSemester(){
        viewSemester.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor results = datasource.viewStudents();
                        String letterGrade;
                        if(results.getCount() == 0) {
                            Toast.makeText(gradeCalc.this, "No data to show", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int ID = studentID.getId();
                        int totalGrade = datasource.getGradeValues(ID);
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
}
