package com.lhh.frescoimageview.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lib.lhh.fiv.library.FrescoImageView;
import lib.lhh.fiv.library.FrescoZoomImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrescoZoomImageView frescoImageView = (FrescoZoomImageView)findViewById(R.id.fiv);
        frescoImageView.loadView("https://avatars1.githubusercontent.com/u/8758713?v=3&s=460",R.mipmap.ic_launcher);
    }
}
