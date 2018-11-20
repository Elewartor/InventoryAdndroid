package com.example.user.inventoryandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword, etAcceptPassword, name, last_name, group;
    private Button btCreateAcc;

    private String email;
    private String password;
    private String passwordAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button button = findViewById(R.id.scan);

        etEmail = findViewById(R.id.ET_reg_email);
        etPassword = findViewById(R.id.ET_reg_pass);
        etAcceptPassword = findViewById(R.id.ET_reg_apass);
        btCreateAcc = findViewById(R.id.BT_reg_reg);
        name=findViewById(R.id.ET_reg_name);
        last_name=findViewById(R.id.ET_reg_last_name);
        group=findViewById(R.id.ET_reg_group);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,ScanActivity.class);
                startActivity(intent);
            }
        });
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
