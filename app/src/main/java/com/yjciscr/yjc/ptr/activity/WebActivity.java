package com.yjciscr.yjc.ptr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yjciscr.yjc.ptr.R;


/**
 * Created by YJC on 2016/5/18.
 */
public class WebActivity extends AppCompatActivity {

    //声明WebView对象
    private WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mWebView = (WebView) findViewById(R.id.webview);
        //WebView默认使用三方浏览器加载网址，通过以下方式可以是自定义WebView加载网址
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();
        String url = intent.getStringExtra("urlweb");


        //使用WebView.loaUrl方法可以加载具体的网址
        mWebView.loadUrl(url);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_web);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else if (newProgress < 100) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /*
         * 判断点击的实体键是否是返回键，WebView是否有上一页
		 * 如果以上两个条件都符合，则调用WebView返回上一页，并return
		 */
        if (keyCode == KeyEvent.KEYCODE_BACK   //按下的实体键是否是返回键
                && mWebView.canGoBack())    //WebView是否有上一页
        {
            mWebView.goBack();  //返回到WebView的上一页
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
