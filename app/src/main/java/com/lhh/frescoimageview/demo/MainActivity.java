package com.lhh.frescoimageview.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lib.lhh.fiv.library.FrescoImageView;
import lib.lhh.fiv.library.FrescoZoomImageView;

public class MainActivity extends AppCompatActivity {

    Button mBtnFrescoNormal;
    Button mBtnFrescoZoom;
    Button mBtnFrescoGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnFrescoNormal = (Button)findViewById(R.id.btn_fresco_normal);
        mBtnFrescoZoom = (Button)findViewById(R.id.btn_fresco_zoom);
        mBtnFrescoGif = (Button)findViewById(R.id.btn_fresco_gif);
        mBtnFrescoNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FrescoImageViewActivity.class));
            }
        });
        mBtnFrescoZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FrescoZoomImageViewActivity.class));
            }
        });
        mBtnFrescoGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FrescoGifImageViewActivity.class));
            }
        });
    }
}
