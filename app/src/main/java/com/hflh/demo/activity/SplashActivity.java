package com.hflh.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hflh.demo.R;
import com.hflh.demo.util.IntentUtils;


public class SplashActivity extends Activity {

    private Handler handler =  new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            IntentUtils.toActivity(SplashActivity.this,MainActivity.class,true);
            SplashActivity.this.finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }


    public void initView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    handler.sendEmptyMessage(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }


}
