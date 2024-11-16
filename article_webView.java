package com.example.myapp;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class article_webView extends AppCompatActivity {
    WebView webview;
    public String TAG = "article_webView";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_webview);

        String url = getIntent().getStringExtra("url");
        Log.d(TAG, "onCreate: "+url);
        webview = findViewById(R.id.article_webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient());
    }
}
