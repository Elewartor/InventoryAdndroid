package com.example.user.inventoryandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class ScanActivity extends Activity {

    private SurfaceView cameraView;
    private TextView barcodeInfo;
    private CameraSource cameraSource;

    private final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_qr_activity);

        final TextView textView = findViewById(R.id.textView5);

        Button button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

        }
        });
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // нема дозволу
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
        else {
            // надали доступ
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission. WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission. WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }
        else {

        }

        cameraView = (SurfaceView) findViewById(R.id.surfaceView);
        barcodeInfo = (TextView) findViewById(R.id.textView2);

        BarcodeDetector detector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(this, detector).setRequestedPreviewSize(640, 480).build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    //якщщо немає пра, тоді краш
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException e) {
                    Log.e("CAMERA SOURCE", e.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    barcodeInfo.post(new Runnable() {
                        @Override
                        public void run() {
                            barcodeInfo.setText(barcodes.valueAt(0).displayValue);
                            String id = barcodeInfo.getText().toString();
                            textView.setText(id);
                            //просто тестив чи перейде на потрыбне актівіті
                            Intent intent = new Intent(ScanActivity.this,TransactionAcceptActivity.class);
                            intent.putExtra("Scan_id", id );
                            startActivity(intent);

                        }
                    });
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                //якщо просто закрили форму, тоді масив пустий
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // надали доступ, можна ще щось зробити
                    //q
                } else {

                }

                return;
            }
        }
    }

    //fffffffffffffffffff

}
