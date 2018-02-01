package com.junseo.barcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    public static final String TAG = "BARCODE";

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera(); //카메라 시작
        mScannerView.setAutoFocus(true); //오토포커스 허용

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera(); //카메라 정지
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.v(TAG, rawResult.getText()); //스캔 결과를 출력합니다.
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); //스캔한 후 포맷을 출력합니다.
        mScannerView.resumeCameraPreview(this);
    }
}