package com.example.user.inventoryandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrdersListViewAdapter extends ArrayAdapter {
    List list = new ArrayList();
    Context context;

    public OrdersListViewAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public void add(@Nullable OrderItems object) {
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
        final OrdersListViewAdapter.ItemsHolder itemsHolder;


        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.order_row_layout, parent, false);
            itemsHolder = new OrdersListViewAdapter.ItemsHolder();
            itemsHolder.item_name = row.findViewById(R.id.textView6);
            itemsHolder.item_author = row.findViewById(R.id.textView7);
            itemsHolder.item_year = row.findViewById(R.id.textView8);
            itemsHolder.item_order_date = row.findViewById(R.id.textView9);
            itemsHolder.item_days_ordered = row.findViewById(R.id.textView10);
            itemsHolder.item_order_end = row.findViewById(R.id.textView11);
            row.setTag(itemsHolder);
        }else{
            itemsHolder = (OrdersListViewAdapter.ItemsHolder) row.getTag();
        }

        final OrderItems orderItems = (OrderItems) this.getItem(position);

        itemsHolder.item_name.setText(orderItems.getName());
        itemsHolder.item_author.setText(orderItems.getAuthor());
        itemsHolder.item_year.setText(orderItems.getYear());
        itemsHolder.item_order_date.setText(orderItems.getDate());
        itemsHolder.item_days_ordered.setText(orderItems.getDays());
        itemsHolder.item_order_end.setText(orderItems.getEndDate());
        return row;
    }

    static class ItemsHolder{
        TextView item_name, item_author, item_year, item_order_date, item_days_ordered, item_order_end;
    }
}

