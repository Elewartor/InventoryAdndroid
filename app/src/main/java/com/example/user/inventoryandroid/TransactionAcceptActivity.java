package com.example.user.inventoryandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class TransactionAcceptActivity extends AppCompatActivity {

    private JSONObject jsonObject;
    private JSONArray jsonArray;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_id = "id";
    SharedPreferences sharedPreferences;

    private String bookId;
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_accept);

        Intent intent = getIntent();

        String id = intent.getStringExtra("Scan_id");

        TextView textView = findViewById(R.id.tv_bookyear);
        textView.setText("id " + id);

        String method = "getBookByQRId";
        String result = null;

        try {
            result = new BackgroundTask(this).execute(method, id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            jsonObject = new JSONObject(result);
            jsonArray = jsonObject.getJSONArray("book_info");
            JSONObject JO = jsonArray.getJSONObject(0);

            String name, year, author;

            bookId = JO.getString("id");
            name = JO.getString("name");
            year = JO.getString("year");
            author = JO.getString("author");

            TextView tv_name  = findViewById(R.id.tv_bookname);
            TextView tv_year = findViewById(R.id.tv_bookyear);
            TextView tv_author = findViewById(R.id.tv_bookauthor);

            tv_name.setText(name);
            tv_author.setText(author);
            tv_year.setText(year);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void onAccept(View view){
        String method = "createOrder";
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString(APP_id, null);

        try {
            result = new BackgroundTask(this).execute(method, userId, bookId).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, OrdersActivity.class));
    }
    public void onDecline(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        // Dismissing 'Back' Button
    }
}
