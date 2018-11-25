package com.example.user.inventoryandroid;

import android.content.Context;
import android.content.SharedPreferences;
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

    public static final String APP_PREFERENCES = "mysettings";

    public static final String APP_id = "id";
    public static final String APP_email = "email";
    public static final String APP_pass = "pass";
    public static final String APP_lastname = "lastname";
    public static final String APP_username = "username";
    public static final String APP_group = "group";
    public static final String APP_stage = "stage";

    SharedPreferences mSettings;
    SharedPreferences.Editor editor;
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

            String id, email, pass, username, lastname, group;

            TextView tv_id = findViewById(R.id.tv_id);
            TextView tv_email = findViewById(R.id.tv_email);
            TextView tv_pass = findViewById(R.id.tv_pass);
            TextView tv_name = findViewById(R.id.tv_name);
            TextView tv_lastname = findViewById(R.id.tv_lastname);
            TextView tv_group = findViewById(R.id.tv_group);


            id = JOemail.getString("id");
            email = JOemail.getString("email");
            pass = JOemail.getString("pass");
            username = JOemail.getString("name");
            lastname = JOemail.getString("lastname");
            group = JOemail.getString("class_group");

            mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            editor = mSettings.edit();

            editor.putString(APP_id, id).apply();
            editor.putString(APP_email, email).apply();
            editor.putString(APP_pass, pass).apply();
            editor.putString(APP_username, username).apply();
            editor.putString(APP_lastname, lastname).apply();
            editor.putString(APP_group, group).apply();
            editor.putInt(APP_stage, 1).apply();

            tv_id.setText(id);
            tv_email.setText(email);
            tv_pass.setText(pass);
            tv_name.setText(username);
            tv_lastname.setText(lastname);
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
