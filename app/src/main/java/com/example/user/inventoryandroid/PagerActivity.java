package com.example.user.inventoryandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class PagerActivity extends FragmentActivity {
    static final String TAG = "myLogs";
    static final int PAGE_COUNT = 3;

    ViewPager pager;

    private String json_data;
    private String email;
    JSONObject jsonObject , emailJSON;
    JSONArray jsonArray, jsonEmailArray;
    LibraryListViewAdapter libraryListViewAdapter;

    PagerAdapter pagerAdapter;
    private int positionActual;
    Button button, buttonMain;
    TextView textView, textView2;
    ProgressBar progressBar;
    ConstraintLayout constraintLayout;

    private SurfaceView cameraView;
    private TextView barcodeInfo;
    private CameraSource cameraSource;
    EditText search;
    private ListView listView;
    Button btn_search;
    private int w =0;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 101;
    BarcodeDetector detector;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_activity);
        positionActual=0;
        pager=findViewById(R.id.pager);
        constraintLayout = findViewById(R.id.container_main);
        barcodeInfo = findViewById(R.id.textView4);
        search = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);
        btn_search = findViewById(R.id.button3);

        constraintLayout.removeView(search);


        libraryListViewAdapter = new LibraryListViewAdapter(this, R.layout.row_layout);
        listView.setAdapter(libraryListViewAdapter);


        json_data = getIntent().getExtras().getString("json_string_data");

        try {

            emailJSON = new JSONObject(email);
            jsonEmailArray = emailJSON.getJSONArray("server_response1");
            JSONObject JOemail = jsonEmailArray.getJSONObject(0);

            String email, username, group;
            TextView tv_email = findViewById(R.id.tv_email);
            TextView tv_name = findViewById(R.id.tv_name);
            TextView tv_group = findViewById(R.id.tv_group);
            email = JOemail.getString("email");
            username = JOemail.getString("name");
            group = JOemail.getString("class_group");
            tv_email.setText(email);
            tv_name.setText(username);
            tv_group.setText(group);
        } catch (JSONException e) {
            e.printStackTrace();
        }


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
                libraryListViewAdapter.add(libraryItems);

                count++;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
//        setPage();
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                positionActual = position;
                setPage();
                barcodeInfo.setText("pos " + positionActual);
                Log.d(TAG, "onPageSelected, position = " + position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }


    private void setPage(){
        if (w==0){
            constraintLayout.addView(search);
            w++;
        }
        switch (positionActual){
            case 0:
//                listView.setAlpha(0);
//                listView.setClickable(false);
                search.setAlpha(1);
                search.setClickable(true);

                barcodeInfo.setText("CERRRRRRRRRRRRRRr");
                break;
            case 1:
                listView.setAlpha(1);
                listView.setClickable(true);
                search.setAlpha(0);
                search.setClickable(false);
                barcodeInfo.setText("CERRRRgsfgsdRRRRRRRRRRr");
                break;
            case 2:
                Intent intent = new Intent(PagerActivity.this,ScanActivity.class);
                startActivity(intent);
                break;
        }

    }


    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }
}
