package com.irononetech.persistence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onViewSettingsClick(View aView){

        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onCreateUserClick(View aView){

        Intent intent = new Intent(this, UsersActivity.class);
        startActivity(intent);
    }

    public void onViewUsersClick(View aView){

        Intent intent = new Intent(this, UserListActivity.class);
        startActivity(intent);
    }

    public void onFileSaveClick(View aView){

        Intent intent = new Intent(this, FileSaveActivity.class);
        startActivity(intent);
    }

    public void onDemoMemoryClick(View aView){

        Intent intent = new Intent(this, MemoryActivity.class);
        startActivity(intent);
    }
}
