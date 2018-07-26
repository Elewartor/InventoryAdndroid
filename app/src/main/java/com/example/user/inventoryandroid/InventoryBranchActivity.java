package com.example.user.inventoryandroid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InventoryBranchActivity extends AppCompatActivity{

    public String jsonMData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_branch);

        executeMData();
    }

    private void executeMData(){

        String method = "get_mData";
        BackgroundTask backgroundTask = new BackgroundTask(this, new Exporter() {
            @Override
            public void sendData(String data) {
                jsonMData = data;
                Intent intent = new Intent(getApplicationContext(), InventoryActivity.class);
                intent.putExtra("json_string_data", data);
                startActivity(intent);
                finish();

            }});
        backgroundTask.execute(method);
    }
}
