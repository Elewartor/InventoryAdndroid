package com.example.user.inventoryandroid;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrdersActivity extends AppCompatActivity {

    private String json_data;
    JSONObject jsonObject , emailJSON;
    JSONArray jsonArray, jsonEmailArray;
    OrdersListViewAdapter OrderListViewAdapter;
    ListView listView;
    private String email;

    ListView ordersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        ordersList = findViewById(R.id.ordersList);
        OrderListViewAdapter = new OrdersListViewAdapter(this, R.layout.row_layout);
        listView.setAdapter(OrderListViewAdapter);

        json_data = getIntent().getExtras().getString("json_string_data");

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
                OrderListViewAdapter.add(libraryItems);

                count++;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
