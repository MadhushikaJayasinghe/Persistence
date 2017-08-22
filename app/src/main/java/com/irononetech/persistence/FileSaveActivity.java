package com.irononetech.persistence;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by RuchiraSSD on 7/18/17.
 */
@ContentView(R.layout.activity_file_save)

public class FileSaveActivity extends RoboActivity {

    @InjectView(R.id.edit_file_content) private EditText txtFileContent;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            String alreadyText = _readFromFile();
            txtFileContent.setText(alreadyText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String _readFromFile() throws IOException {

        String FILENAME = "hello_file";
        FileInputStream fis = openFileInput(FILENAME);

        StringBuffer fileContent = new StringBuffer("");

        byte[] buffer = new byte[1024];
        int n;

        while ((n = fis.read(buffer)) != -1)
        {
            fileContent.append(new String(buffer, 0, n));
        }

        return fileContent.toString();
    }

    private void _writeFile() throws IOException {

        String FILENAME = "hello_file";
        String string = "hello world!";

        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
        fos.write(string.getBytes());
        fos.close();
    }

    //On click methods

    public void writeToFile(View aView){
        try {
            _writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finish();
    }

    public void resetFile(View aView){

        try{
            String FILENAME = "hello_file";
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write("".getBytes());
            fos.close();
        }

        catch (Exception e){

        }

        finish();
    }
}
