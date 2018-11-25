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


public class PagerActivity extends FragmentActivity {
    static final String TAG = "myLogs";
    static final int PAGE_COUNT = 3;

    ViewPager pager;

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

//    private void scan(){
////        BarcodeDetector detector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
////        cameraSource = new CameraSource.Builder(this, detector).setRequestedPreviewSize(640, 480).build();
//
//        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                try {
//                    //якщщо немає пра, тоді краш
//                    cameraSource.start(cameraView.getHolder());
//                } catch (IOException e) {
//                    Log.e("CAMERA SOURCE", e.getMessage());
//                }
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                cameraSource.stop();
//                constraintLayout.removeView(cameraView);
//            }
//        });
//
//        detector.setProcessor(new Detector.Processor<Barcode>() {
//            @Override
//            public void release() {
//
//            }
//
//            @Override
//            public void receiveDetections(Detector.Detections<Barcode> detections) {
//                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
//                if (barcodes.size() != 0) {
//                    barcodeInfo.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            barcodeInfo.setText(barcodes.valueAt(0).displayValue);
////                            Intent intent = new Intent(PagerActivity.this,MainActivity.class);
////                            startActivity(intent);
//                        }
//                    });
//                }
//            }
//        });
//    }

    private void setPage(){
        if (w==0){
            constraintLayout.addView(search);
            w++;
        }
        switch (positionActual){
            case 0:
                listView.setAlpha(0);
                listView.setClickable(false);
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
