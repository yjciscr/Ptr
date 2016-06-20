package com.yjciscr.yjc.okhttpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yjciscr.yjc.okhttpdemo.activity.GetActivity;
import com.yjciscr.yjc.okhttpdemo.activity.PostActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton_get;
    private Button mButton_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mButton_get = (Button) findViewById(R.id.btn_get);
        mButton_post = (Button) findViewById(R.id.btn_post);
        mButton_get.setOnClickListener(this);
        mButton_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get:
                startActivity(new Intent(this, GetActivity.class));
                break;
            case R.id.btn_post:
                startActivity(new Intent(this, PostActivity.class));
        }
    }
}
