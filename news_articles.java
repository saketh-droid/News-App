package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.webkit.WebView;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class news_articles extends AppCompatActivity {
    public static String TAG="news_articles";
    public WebView myWebView;
    public String url;

    RecyclerView recyclerView;
    List<Article> articleList = new ArrayList<>();
    NewsArticleAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_articles);
        recyclerView = findViewById(R.id.recycler_article_view);
        setupRecyclerView();
        getNews();

        ImageView profile = findViewById(R.id.user_profile);
        ImageView home = findViewById(R.id.home);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(news_articles.this, profile.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(news_articles.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    void  setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsArticleAdapter(articleList);
        recyclerView.setAdapter(adapter);
    }

    void getNews(){
        NewsApiClient newsApiClient = new NewsApiClient("a3c34df6ff4f43d6bb28e597ca053fb5");
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder().language("en").build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        runOnUiThread(()->{
                            articleList = response.getArticles();
                            Log.d(TAG, "onSuccess: "+response);
                            adapter.updateData(articleList);
                            adapter.notifyDataSetChanged();
                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.i(TAG, "Got Failure: " + throwable.toString());
                    }
                }
        );
    }
}
