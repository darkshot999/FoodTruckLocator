package com.example.leaf.learningteamproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        List<String> distance = new ArrayList<>();
        distance.add("5 miles");
        distance.add("10 miles");
        distance.add("15 miles");
        distance.add("20 miles");
        distance.add("25 miles");
        ArrayAdapter<String> distanceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, distance);
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(distanceAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}