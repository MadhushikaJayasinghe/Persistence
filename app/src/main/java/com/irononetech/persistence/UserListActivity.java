package com.irononetech.persistence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by RuchiraSSD on 7/18/17.
 */

@ContentView(R.layout.activity_user_list)

public class UserListActivity extends RoboActivity {

    public class Placeholder{
        public TextView nameTV;
        public TextView ageTV;
        public TextView cityTV;
    }

    @InjectView(R.id.listView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        DBHandler handler = new DBHandler(this);
        final User[] usersList = handler.getUsers();

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return usersList.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
             public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View convertView, ViewGroup viewGroup) {

                User user = usersList[i];
                View cellUser = null;

                if(convertView == null){
                    cellUser = LayoutInflater.from(UserListActivity.this).inflate(R.layout.cell_user, null);
                }
                else{
                    cellUser = convertView;
                }

                Placeholder ph = (Placeholder) cellUser.getTag();
                TextView nameTV;
                TextView ageTV;
                TextView cityTV;

                if(ph == null){
                    nameTV = (TextView) cellUser.findViewById(R.id.cell_name);
                    ageTV = (TextView) cellUser.findViewById(R.id.cell_age);
                    cityTV = (TextView) cellUser.findViewById(R.id.cell_city);

                    ph = new Placeholder();
                    ph.nameTV = nameTV;
                    ph.ageTV = ageTV;
                    ph.cityTV = cityTV;

                    cellUser.setTag(ph);
                }
                else {
                    nameTV = ph.nameTV;
                    ageTV = ph.ageTV;
                    cityTV = ph.cityTV;
                }

                nameTV.setText(user.name);
                ageTV.setText(user.age);
                cityTV.setText(user.city);


                return cellUser;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
