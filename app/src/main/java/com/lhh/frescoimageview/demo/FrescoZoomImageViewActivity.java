package com.lhh.frescoimageview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import lib.lhh.fiv.library.FrescoZoomImageView;

/**
 * Created by Linhh on 16/2/19.
 */
public class FrescoZoomImageViewActivity extends AppCompatActivity {

    private String mImgUrl = "https://avatars1.githubusercontent.com/u/8758713?v=3&s=460";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_zoom_imageview);
        final FrescoZoomImageView frescoImageView = (FrescoZoomImageView)findViewById(R.id.fiv);
        frescoImageView.loadView(mImgUrl,R.mipmap.ic_launcher);
        frescoImageView.setOnDraweeClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FrescoZoomImageViewActivity.this,"OnClick",Toast.LENGTH_SHORT).show();
                frescoImageView.asCircle();
            }
        });
    }
}

