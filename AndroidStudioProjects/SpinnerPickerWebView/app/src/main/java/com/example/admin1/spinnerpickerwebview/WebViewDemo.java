package com.example.admin1.spinnerpickerwebview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

public class WebViewDemo extends AppCompatActivity {
    private EditText editText;
    private WebView webView;
    final Activity activity= WebViewDemo.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_demo);
        editText = (EditText) findViewById(R.id.text);
        webView = (WebView) findViewById(R.id.webview);

    }
    public void showWebSite(View view){
        String url = editText.getText().toString();
        webView.loadUrl(url);

    }

}
