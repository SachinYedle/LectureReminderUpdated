package com.example.admin1.tablayoutrecyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewDemo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_demo);

        WebView webView = (WebView)findViewById(R.id.web_view);

        String urlData = getIntent().getExtras().getString("url");

        webView.loadDataWithBaseURL("", urlData, "text/html", "UTF-8", "");
    }
}
