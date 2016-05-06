package com.lhh.frescoimageview.demo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import okhttp3.OkHttpClient;

/**
 * Created by Linhh on 16/2/18.
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initOKFresco();
    }

    private void initFresco(){
        Fresco.initialize(this);
    }

    private void initOKFresco(){
        ImagePipelineConfig frescoConfig = OkHttpImagePipelineConfigFactory
                .newBuilder(this, new OkHttpClient())
                .build();
        Fresco.initialize(this,frescoConfig);
    }
}
