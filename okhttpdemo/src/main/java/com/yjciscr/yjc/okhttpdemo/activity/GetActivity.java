package com.yjciscr.yjc.okhttpdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yjciscr.yjc.okhttpdemo.Constants;
import com.yjciscr.yjc.okhttpdemo.R;

import java.io.IOException;

/**
 * Created by YJC on 2016/5/28.
 */
public class GetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton_tongbu;
    private Button mButton_async;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        initView();
    }

    private void initView() {
        mButton_tongbu = (Button) findViewById(R.id.btn_tongbu);
        mButton_async = (Button) findViewById(R.id.btn_async);
        mButton_tongbu.setOnClickListener(this);
        mButton_async.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tongbu:
                londData();
                break;
            case R.id.btn_async:
                londAsyncData();
        }
    }

    private void londAsyncData() {
    }

    private void londData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(Constants.URL_GET)
                        .build();

                OkHttpClient client = new OkHttpClient();

                try {
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
