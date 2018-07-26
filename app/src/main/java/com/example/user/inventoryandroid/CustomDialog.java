package com.example.user.inventoryandroid;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class CustomDialog extends Dialog implements View.OnClickListener{

    private InventoryItems inventoryItems;
    private TextView comment, name, description, status, res_person, location, price;
    private Image image;


    public CustomDialog(@NonNull Context context, InventoryItems inventoryItems) {
        super(context);

        this.inventoryItems = inventoryItems;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_layout);

        comment = findViewById(R.id.comment);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        status = findViewById(R.id.status);
        res_person = findViewById(R.id.res_person);
        location = findViewById(R.id.location);
        price = findViewById(R.id.price);

        comment.setText(inventoryItems.getComment());
        name.setText(inventoryItems.getName());
        description.setText(inventoryItems.getDescription());
        status.setText(inventoryItems.getStatus());
        res_person.setText(inventoryItems.getRes_person());
        location.setText(inventoryItems.getLocation());
        price.setText(inventoryItems.getPrice());

    }

    @Override
    public void onClick(View view) {

    }
}
