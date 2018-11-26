package com.example.user.inventoryandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class OrdersActivity extends AppCompatActivity {

    private String json_data;
    JSONObject jsonObject;
    JSONArray jsonArray;
    OrdersListViewAdapter orderListViewAdapter;

    public static final String APP_PREFERENCES = "mysettings";

    public static final String APP_id = "id";

    SharedPreferences sharedPreferences;

    ListView ordersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        ordersList = findViewById(R.id.ordersList);
        orderListViewAdapter = new OrdersListViewAdapter(this, R.layout.order_row_layout);
        ordersList.setAdapter(orderListViewAdapter);

        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        String id = sharedPreferences.getString(APP_id, null);
        String method = "getOrdersInfo";

        try {
            json_data = new BackgroundTask(this).execute(method, id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            jsonObject = new JSONObject(json_data);
            jsonArray = jsonObject.getJSONArray("user_orders_info");



            String name, author, year, date, days, end;

            int count=0;
            while (count<jsonArray.length()){

                JSONObject JO = jsonArray.getJSONObject(count);

                name = JO.getString("name");
                author = JO.getString("author");
                year = JO.getString("year");
                date= JO.getString("time_order_date");
                days = JO.getString("time_order_days");
                end = JO.getString("time_order_end");

                OrderItems orderItems = new OrderItems(name, author, year, date, days, end);
                orderListViewAdapter.add(orderItems);

                count++;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
