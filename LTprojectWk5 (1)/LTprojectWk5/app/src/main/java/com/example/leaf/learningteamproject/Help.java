package com.example.leaf.learningteamproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ExpandableTextView expandableTextView1 = (ExpandableTextView) findViewById(R.id.expandableTV1);
        expandableTextView1.setText(R.string.expandable_text_1);

        ExpandableTextView expandableTextView2 = (ExpandableTextView) findViewById(R.id.expandableTV2);
        expandableTextView2.setText(R.string.expandable_text_2);

        ExpandableTextView expandableTextView3 = (ExpandableTextView) findViewById(R.id.expandableTV3);
        expandableTextView3.setText(R.string.expandable_text_3);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // call to object method here

    }

}
