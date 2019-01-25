package com.example.leaf.learningteamproject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.leaf.learningteamproject.R.id.editText_ID;

public class Favorites extends AppCompatActivity {
    Databasehelper myDB;
    EditText editName, editTextId;
    Button btnAddData;
    Button btnViewAll;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDB = new Databasehelper(this);

        editName = (EditText)findViewById(R.id.editText_insert);
        editTextId = (EditText)findViewById(R.id.editText_ID);
        btnAddData = (Button)findViewById(R.id.button_Add);
        btnViewAll = (Button)findViewById(R.id.button_View);
        btnDelete = (Button)findViewById(R.id.button_Delete);
        AddData();
        viewAll();
        DeleteData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public  void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDB.deleteData(editTextId.getText().toString());
                       if(deletedRows > 0)
                           Toast.makeText(Favorites.this, "Favorite Deleted", Toast.LENGTH_SHORT).show();
                        else
                        Toast.makeText(Favorites.this, "Unabale to Delete Favorite", Toast.LENGTH_LONG).show();
                    }
                });
    }
    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(editName.getText().toString());
                        if(isInserted = true)
                            Toast.makeText(Favorites.this, "Favorite Added", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Favorites.this, "Unabale to Add Favorite", Toast.LENGTH_LONG).show();
                    }
                }
        );

        };

    public void viewAll() {
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Cursor res = myDB.getAllData();
                        if(res.getCount() == 0) {
                            showMessage("Error", "No Favorites Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :"+ res.getString(0)+"\n");
                            buffer.append("NAME :"+ res.getString(1)+"\n\n");
                        }

                        //show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }
public void showMessage(String title, String Message) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(Message);
    builder.show();
}



    }


