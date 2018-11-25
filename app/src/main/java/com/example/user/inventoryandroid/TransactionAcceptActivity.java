package com.example.user.inventoryandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TransactionAcceptActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_accept);

        Intent intent = getIntent();

        String id = intent.getStringExtra("Scan_id");

        TextView textView = findViewById(R.id.tv_take_date);
        textView.setText("id " + id);


    }
}
