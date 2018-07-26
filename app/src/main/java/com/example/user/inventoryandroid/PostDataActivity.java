package com.example.user.inventoryandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PostDataActivity extends AppCompatActivity {

    EditText put_name, put_description, put_status, put_resper, put_price, put_buy_date, put_wattime, put_category, put_location, put_image, put_comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_data);

        put_name = findViewById(R.id.put_name);
        put_description = findViewById(R.id.put_description);
        put_status = findViewById(R.id.put_status);
        put_resper = findViewById(R.id.put_resper);
        put_price = findViewById(R.id.put_price);
        put_buy_date = findViewById(R.id.put_buy_date);
        put_wattime = findViewById(R.id.put_wattime);
        put_category = findViewById(R.id.put_category);
        put_location = findViewById(R.id.put_location);
        put_image = findViewById(R.id.put_image);
        put_comment = findViewById(R.id.put_comment);


    }

    public void add_data(View view){
        String name, desc, stat, resp, price, buyd, watt, catg, locn, comm, imag;

        name = put_name.getText().toString();
        desc = put_description.getText().toString();
        stat = put_status.getText().toString();
        resp = put_resper.getText().toString();
        price = put_price.getText().toString();
        buyd = put_buy_date.getText().toString();
        watt = put_wattime.getText().toString();
        locn = put_location.getText().toString();
        catg = put_category.getText().toString();
        imag = put_image.getText().toString();
        comm = put_comment.getText().toString();

        String method = "post new data item";

        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method/*0*/, name/*1*/, desc/*2*/, stat/*3*/, resp/*4*/, price/*5*/, buyd/*6*/, watt/*7*/, locn/*8*/, catg/*9*/, imag/*10*/, comm/*11*/);

    }
}
