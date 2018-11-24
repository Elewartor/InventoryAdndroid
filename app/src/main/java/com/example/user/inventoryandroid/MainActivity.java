package com.example.user.inventoryandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegister;
    private SharedPreferences userPref;

    private String log_email, log_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        etEmail = findViewById(R.id.ET_login_email);
        etPassword = findViewById(R.id.ET_login_pass);
        btLogin = findViewById(R.id.BT_login_login);
        btRegister = findViewById(R.id.BT_login_reg);

    }

    public void onRegister(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onLogin(View view){

        log_email = etEmail.getText().toString();
        log_password = etPassword.getText().toString();

        String method = "login";

        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, log_email, log_password);

    }

}
