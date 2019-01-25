package com.example.dustin.finalappproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    dbOpenHelper datasource;
    EditText studentID, firstName, lastName, classID, className;
    Button addStudent;
    Button viewStudents;
    Button gradingPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datasource = new dbOpenHelper(this);
        studentID = (EditText) findViewById(R.id.studendID);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        classID = (EditText) findViewById(R.id.classID);
        className = (EditText) findViewById(R.id.className);
        addStudent = (Button) findViewById(R.id.addStudent);
        viewStudents = (Button) findViewById(R.id.viewStudentsGC);
        gradingPage = (Button) findViewById(R.id.gradingPage);
        addData();
        viewStudentData();
        goToGrading();
    }
    public void addData(){
        addStudent.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                       boolean isInserted = datasource.addStudent(studentID.getId(), firstName.getText().toString(), lastName.getText().toString(), classID.getId(), className.getText().toString());
                        if (isInserted)
                            Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(MainActivity.this, "No data to show", Toast.LENGTH_SHORT).show();
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
    public void goToGrading(){
        gradingPage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, gradingPage.class);
                        startActivity(intent);
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
