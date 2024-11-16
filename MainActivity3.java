package com.example.myapp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    public WebView webView3;
    ImageView imageView;
    TextView textView;

    public String TAG = "MainActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView = findViewById(R.id.query);
        imageView = findViewById(R.id.home);

        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, MainActivity.class);
            startActivity(intent);
        });

        webView3 = findViewById(R.id.web_view3);
        WebSettings webSettings = webView3.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String query = getIntent().getStringExtra("text_key");
        Log.e(TAG, "topic" + query);
        textView.setText(query);

        String videoUrl = "https://www.youtube.com/results?search_query=" + query;
        webView3.loadUrl(videoUrl);

        webView3.setWebViewClient(new WebViewClient());
    }
}

