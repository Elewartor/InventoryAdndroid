package com.example.user.inventoryandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword, etAcceptPassword;
    private Button btCreateAcc;

    private String email;
    private String password;
    private String passwordAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.ET_reg_email);
        etPassword = findViewById(R.id.ET_reg_pass);
        etAcceptPassword = findViewById(R.id.ET_reg_apass);
        btCreateAcc = findViewById(R.id.BT_reg_reg);
    }

    public void onCreateAccount(View view){

        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        passwordAccept = etAcceptPassword.getText().toString();

        if(password.equals(passwordAccept)){
            String method = "register";
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, email, password);
            finish();
        }else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }
}
