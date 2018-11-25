package com.example.user.inventoryandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LibraryListViewAdapter extends ArrayAdapter {

    List list = new ArrayList();
    Context context;

    public LibraryListViewAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public void add(@Nullable LibraryItems object) {
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
            itemsHolder.item_name = row.findViewById(R.id.tv_book_name);
            itemsHolder.item_author = row.findViewById(R.id.tv_book_author);
            itemsHolder.item_year = row.findViewById(R.id.tv_book_year);
            itemsHolder.item_gcount = row.findViewById(R.id.tv_library_gcount);
            itemsHolder.item_left = row.findViewById(R.id.tv_library_left);
            row.setTag(itemsHolder);
        }else{
            itemsHolder = (ItemsHolder) row.getTag();
        }

        final LibraryItems libraryItems = (LibraryItems) this.getItem(position);

        itemsHolder.item_name.setText(libraryItems.getName());
        itemsHolder.item_author.setText(libraryItems.getAuthor());
        itemsHolder.item_year.setText(libraryItems.getYear());
        itemsHolder.item_gcount.setText("Всього: " + libraryItems.getgCount());
        itemsHolder.item_left.setText("Залишилось: " + libraryItems.getLeft());
        return row;
    }

    static class ItemsHolder{
        TextView item_name, item_author, item_year, item_gcount, item_left;
    }
}
