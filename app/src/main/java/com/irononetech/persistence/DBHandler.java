package com.irononetech.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by RuchiraSSD on 7/17/17.
 */
public class DBHandler extends SQLiteOpenHelper {

    //Signifies the version of the database.
    //Incrementing this calls necessary methods
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "users.db";

    private Context context;
    //Override the constructor
    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //Called by the OS. We don't call this directly.
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE = "CREATE TABLE user(name TEXT, age TEXT, city TEXT)";

        db.execSQL(CREATE);

        Log.i("DB","Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Toast.makeText(context, "On Upgrade", Toast.LENGTH_SHORT).show();

        if(oldVersion ==1 && newVersion==2){
            //create new tables
            //delete old tables
        }
        else if(oldVersion==1 && newVersion ==2){

        }

        //Check version
        //Maybe copy data to a backup
        //recreate a new database
    }

    public void addUser(User user){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", user.name);
        values.put("age", user.age);
        values.put("city", user.city);

        //INSERT INTO user(name, age, city) VALUES(user.name, user.age, user.city)
        db.insert("user", null, values);
        db.close();
    }

    public User getFirstUserByName(String name){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE user.name=?",new String[]{name});
        cursor.moveToFirst();

        String age = cursor.getString(1);
        String city = cursor.getString(2);

        cursor.close();
        db.close();

        User user = new User(name, age, city);
        user.name = name;
        user.age = age;
        user.city = city;
        return user;
    }

    public User[] getUsers(){

        SQLiteDatabase db = getReadableDatabase();
        ArrayList<User> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM user",null);

        while (cursor.moveToNext()){

            String name = cursor.getString(0);
            String age = cursor.getString(1);
            String city = cursor.getString(2);

            User aUser = new User(name, age, city);
            list.add(aUser);
        }

        cursor.close();
        db.close();

        return list.toArray(new User[list.size()]);
    }

    public void deleteUser(User user){

        SQLiteDatabase db = getWritableDatabase();
        String sqlStatement = "DELETE FROM user WHERE name='"+user.name+"'";
        db.execSQL(sqlStatement);
    }
    public void updateUser(User user){
        SQLiteDatabase db=getWritableDatabase();
        String sqlStatement="UPDATE user [name,age,city] VALUES ['"+user.name+","+user.age+","+user.city+"] WHERE name=" +user.name+"'";
        db.execSQL(sqlStatement);
    }
}
