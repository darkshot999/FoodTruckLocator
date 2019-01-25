package com.example.leaf.learningteamproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
    switch (id) {
        case R.id.action_favorites:
        Intent intent = new Intent(this, Favorites.class);
        startActivity(intent);
        return true;

        case R.id.action_nearMe:
        Intent intent2 = new Intent(this, NearMe.class);
        startActivity(intent2);
        return true;

        case R.id.action_settings:
            Intent intent3 = new Intent(this, Setting.class);
            startActivity(intent3);
            return true;

        case R.id.action_about:
            Intent intent4 = new Intent(this, About.class);
            startActivity(intent4);
            return true;

        case R.id.action_help:
            Intent intent5 = new Intent(this, Help.class);
            startActivity(intent5);
            return true;


        // noinspection SimplifiableIfStatement

    }
        return false;
    }}

