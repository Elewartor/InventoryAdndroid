package com.example.user.inventoryandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
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

   private TextView name, lastname, email, group;
   Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.tp_name);
        lastname = findViewById(R.id.tp_lastname);
        group = findViewById(R.id.tp_group);
        email = findViewById(R.id.tp_email);
        logout = findViewById(R.id.logout);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = mSettings.edit();

        name.setText(mSettings.getString(APP_username, null));
        lastname.setText(mSettings.getString(APP_username, null));
        group.setText(mSettings.getString(APP_username, null));
        email.setText(mSettings.getString(APP_username, null));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt(APP_stage, 0).apply();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
