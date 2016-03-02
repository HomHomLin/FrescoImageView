package com.lhh.frescoimageview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import lib.lhh.fiv.library.FrescoImageView;

/**
 * Created by Linhh on 16/2/19.
 */
public class FrescoImageViewActivity extends AppCompatActivity {

    private String mImgUrl = "https://avatars1.githubusercontent.com/u/8758713?v=3&s=460";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_imgeview);
        FrescoImageView frescoImageView = (FrescoImageView)findViewById(R.id.fiv);
        frescoImageView.asCircle();
        frescoImageView.loadView(mImgUrl, R.mipmap.ic_launcher);
    }
}
