package com.example.user.inventoryandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LibraryActivity extends AppCompatActivity {

    private String json_data;
    JSONObject jsonObject , emailJSON;
    JSONArray jsonArray, jsonEmailArray;
    LibraryListViewAdapter libraryListViewAdapter;
    ListView listView;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        email = getIntent().getExtras().getString("user_data1");


        libraryListViewAdapter = new LibraryListViewAdapter(this, R.layout.row_layout);
        listView = findViewById(R.id.lv_inventory);
        listView.setAdapter(libraryListViewAdapter);


        json_data = getIntent().getExtras().getString("json_string_data");

        try {

            emailJSON = new JSONObject(email);
            jsonEmailArray = emailJSON.getJSONArray("server_response1");
            JSONObject JOemail = jsonEmailArray.getJSONObject(0);

            String email, username, group;
            TextView tv_email = findViewById(R.id.tv_email);
            TextView tv_name = findViewById(R.id.tv_name);
            TextView tv_group = findViewById(R.id.tv_group);
            email = JOemail.getString("email");
            username = JOemail.getString("name");
            group = JOemail.getString("class_group");
            tv_email.setText(email);
            tv_name.setText(username);
            tv_group.setText(group);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            jsonObject = new JSONObject(json_data);
            jsonArray = jsonObject.getJSONArray("server_response");



            String name, author, year, gCount, left;

            int count=0;
            while (count<jsonArray.length()){

                JSONObject JO = jsonArray.getJSONObject(count);

                name = JO.getString("name");
                author = JO.getString("year");
                year = JO.getString("author");
                gCount = JO.getString("books_gcount");
                left = JO.getString("books_left");

                LibraryItems libraryItems = new LibraryItems(name, author, year, gCount, left);
                libraryListViewAdapter.add(libraryItems);

                count++;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
