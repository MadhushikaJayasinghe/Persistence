package com.irononetech.persistence;

import android.accessibilityservice.GestureDescription;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by RuchiraSSD on 7/18/17.
 */

@ContentView(R.layout.activity_memory)
public class MemoryActivity extends RoboActivity{

    private String value;

    @InjectView(R.id.txt_memory_box) private EditText txtMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("com.irononetech.persistencedemo", 0);
        String userAlreadyEnteredText = preferences.getString("user-input1", "NONE");
        txtMemory.setText(userAlreadyEnteredText);
    }

    public void onClick(View aView){

        SharedPreferences preferences = getSharedPreferences("com.irononetech.persistencedemo", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString( "user-input1",txtMemory.getText().toString());
        editor.commit();
    }
}
