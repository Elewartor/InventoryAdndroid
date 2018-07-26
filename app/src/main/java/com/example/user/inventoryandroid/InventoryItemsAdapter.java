package com.example.user.inventoryandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InventoryItemsAdapter extends ArrayAdapter {

    List list = new ArrayList();
    Context context;

    public InventoryItemsAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public void add(@Nullable InventoryItems object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        final ItemsHolder itemsHolder;


        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            itemsHolder = new ItemsHolder();
            itemsHolder.item_id = row.findViewById(R.id.item_id);
            itemsHolder.item_name = row.findViewById(R.id.item_name);
            itemsHolder.item_status = row.findViewById(R.id.item_status);
            itemsHolder.menu_button = row.findViewById(R.id.bt_popup);
            row.setTag(itemsHolder);
        }else{
            itemsHolder = (ItemsHolder) row.getTag();
        }

        final InventoryItems inventoryItems = (InventoryItems) this.getItem(position);

        itemsHolder.item_id.setText(inventoryItems.getId());
        itemsHolder.item_name.setText(inventoryItems.getName());
        itemsHolder.item_status.setText(inventoryItems.getStatus());
        itemsHolder.menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(context, itemsHolder.menu_button);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.delete:
                                String method;
                                method = "delete data item";
                                BackgroundTask backgroundTask = new BackgroundTask(context);
                                backgroundTask.execute(method, inventoryItems.getId());
                                return true;
                            case R.id.more:
                                CustomDialog customDialog = new CustomDialog(context, inventoryItems);
                                customDialog.show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });

        return row;
    }

    static class ItemsHolder{
        TextView item_id, item_name, item_status;
        ImageButton menu_button;
    }
}
