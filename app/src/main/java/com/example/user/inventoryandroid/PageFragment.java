package com.example.user.inventoryandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class PageFragment extends Fragment {

    private int [] imageArr = {R.drawable.ic_find, R.drawable.ic_notification,R.drawable.ic_scan};

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    public int pageNumber;
    int backColor;

//    ConstraintLayout container;

    static PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);

        TextView tvPage = (TextView) view.findViewById(R.id.textView3);
//        ImageView imageView = view.findViewById(R.id.imageView);
//        imageView.setImageResource(imageArr[pageNumber]);

        container = view.findViewById(R.id.fragment);
        container.setBackgroundResource(imageArr[pageNumber]);
        tvPage.setText("Page " + pageNumber);
        tvPage.setBackgroundColor(backColor);

        return view;
    }
}
