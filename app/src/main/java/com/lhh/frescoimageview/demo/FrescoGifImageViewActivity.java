package com.lhh.frescoimageview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import lib.lhh.fiv.library.FrescoImageView;

/**
 * Created by Linhh on 16/5/6.
 */
public class FrescoGifImageViewActivity extends AppCompatActivity {


    private String mImgUrl = "http://s1.dwstatic.com/group1/M00/32/EF/21871fbdd4ffa13d7cbf0249aea1d558.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_imgeview);
        FrescoImageView frescoImageView = (FrescoImageView)findViewById(R.id.fiv);
        frescoImageView.loadView(mImgUrl, R.mipmap.ic_launcher);
    }
}
