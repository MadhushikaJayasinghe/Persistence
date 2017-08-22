package com.irononetech.persistence;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by RuchiraSSD on 7/17/17.
 */

@ContentView(R.layout.activity_user)
public class UsersActivity extends RoboActivity{

    @InjectView(R.id.edit_name) private EditText txtName;
    @InjectView(R.id.edit_age) private EditText txtAge;
    @InjectView(R.id.edit_city) private EditText txtCity;
    @InjectView(R.id.button_create) private Button btnCreate;
    @InjectView(R.id.button_update) private Button btnUpdate;
    @InjectView(R.id.button_delete) private Button btnDelete;

    DBHandler handler;

    private DBHandler getHandler(){
        if(handler==null){
            handler = new DBHandler(this);
        }
        return handler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void onClick(View view) {

        int id = view.getId();

        if(id == R.id.button_create){

            DBHandler handler = getHandler();

            handler.addUser(new User(txtName.getText().toString(),
                    txtAge.getText().toString(),
                    txtCity.getText().toString()) );

            Toast.makeText(this, "New user created", Toast.LENGTH_SHORT).show();
            finish();


        }
        else if(id == R.id.button_delete){
            DBHandler handler = getHandler();
            handler.deleteUser(new User(txtName.getText().toString(), null, null));
        }
        else if(id==R.id.button_update){
            DBHandler handler=getHandler();
            handler.updateUser(new User(txtName.getText().toString(),txtAge.getText().toString(),txtCity.getText().toString()));
        }
    }
}
