package com.example.user.inventoryandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InventoryActivity extends AppCompatActivity {

    private String json_data;
    JSONObject jsonObject;
    JSONArray jsonArray;
    InventoryItemsAdapter inventoryItemsAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        inventoryItemsAdapter = new InventoryItemsAdapter(this, R.layout.row_layout);
        listView = findViewById(R.id.lv_inventory);
        listView.setAdapter(inventoryItemsAdapter);


        json_data = getIntent().getExtras().getString("json_string_data");

        try {
            jsonObject = new JSONObject(json_data);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count=0;
            String id, name, description, status, res_person, price, buy_date, dep_time, location, category, image, comment;
            while (count<jsonArray.length()){

                JSONObject JO = jsonArray.getJSONObject(count);

                id = JO.getString("id");
                name = JO.getString("name");
                description = JO.getString("description");
                status = JO.getString("status");
                res_person = JO.getString("res_person");
                price = JO.getString("price");
                buy_date = JO.getString("buy_date");
                dep_time = JO.getString("dep_time");
                location = JO.getString("location");
                category = JO.getString("category");
                image = JO.getString("image");
                comment = JO.getString("comment");

                InventoryItems inventoryItems = new InventoryItems(id, name
                        , description, status
                        , res_person, price
                        , buy_date, dep_time
                        , location, category
                        ,image,comment);
                inventoryItemsAdapter.add(inventoryItems);
                count++;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.title_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.put_data:
                Intent intent = new Intent(this, PostDataActivity.class);
                startActivity(intent);
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
