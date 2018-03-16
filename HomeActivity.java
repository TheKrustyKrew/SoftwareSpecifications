package com.example.android.softwarespecifications;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void createEntry(View view){
        Intent intent = new Intent(this, CreateEntry.class);
        startActivity(intent);
    }

    public void editEntry(View view){
        Intent intent = new Intent(this, EditEntry.class);
        startActivity(intent);
    }

    public void tournamentInfo(View view){
        Intent intent = new Intent(this, Tournament.class);
        startActivity(intent);
    }
}
