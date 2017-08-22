package com.irononetech.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by RuchiraSSD on 7/18/17.
 */
@ContentView(R.layout.activity_settings)
public class SettingsActivity extends RoboActivity {

    String KEY_CHECK_FOR_UPDATES = "chk-for-update";
    String KEY_URL = "key-url";

    @InjectView(R.id.edit_url) private EditText txtURL;
    @InjectView(R.id.chk_updates) private CheckBox chkUpdates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("com.irononetech.persistence.settings", Context.MODE_PRIVATE);
        boolean shouldCheckForUpdates = prefs.getBoolean(KEY_CHECK_FOR_UPDATES, false);
        String url = prefs.getString(KEY_URL, "None");

        chkUpdates.setChecked(shouldCheckForUpdates);
        txtURL.setText(url);

    }

    public void saveSettings(View view){

        SharedPreferences prefs = getSharedPreferences("com.irononetech.persistence.settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean(KEY_CHECK_FOR_UPDATES, chkUpdates.isChecked());
        editor.putString(KEY_URL, txtURL.getText().toString());

        editor.commit();

        Toast.makeText(this, "Changes saved to prefs.", Toast.LENGTH_SHORT).show();

        finish();
    }
}
