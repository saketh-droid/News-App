package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    NewsVideoAdapter adapter1;
    NewsVideoAdapter adapter2;
    NewsVideoAdapter adapter3;
    ArrayList<YouTubeVideo> videos1 = new ArrayList<>();
    ArrayList<YouTubeVideo> videos2 = new ArrayList<>();
    ArrayList<YouTubeVideo> videos3 = new ArrayList<>();
    public String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView1 = findViewById(R.id.recycler_view1);
        recyclerView2 = findViewById(R.id.recycler_view2);
        recyclerView3 = findViewById(R.id.recycler_view3);
        adapter1 = new NewsVideoAdapter(MainActivity.this,videos1);
        adapter2 = new NewsVideoAdapter(MainActivity.this,videos2);
        adapter3 = new NewsVideoAdapter(MainActivity.this,videos3);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext());
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(adapter1);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(adapter2);

        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getApplicationContext());
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView3.setAdapter(adapter3);

        ImageView profile = findViewById(R.id.user_profile);
        Button newsarticles = findViewById(R.id.articles);
        ImageView imageView = findViewById(R.id.gif_image);
        ImageView auth = findViewById(R.id.login);
        EditText editText = findViewById(R.id.search_bar);

        Glide.with(this)
                .load(R.drawable.search)
                .into(imageView);

        Glide.with(this)
                .load(R.drawable.login)
                .into(auth);

        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = editText.getText().toString();
                if (topic.equals("")){
                    Toast.makeText(MainActivity.this, "\uD83D\uDE15 oops! Entered an empty topic damn!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                    intent.putExtra("text_key", topic);
                    startActivity(intent);
                    editText.setText("");
                }
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, profile.class);
                startActivity(intent);
            }
        });

        newsarticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, news_articles.class);
                startActivity(intent);
            }
        });

        recycle1();
        recycle2();
        recycle3();
    }

    private void recycle1(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, "https://www.googleapis.com/youtube/v3/search?part=snippet&q=top+latest+international+news&type=video&maxResults=10&key=AIzaSyCnJ7Gyyyah2RjN7t8xI5PexUhpA2UVdHg", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");
                    for (int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonobjid = jsonObject1.getJSONObject("id");
                        JSONObject jsonsnippet = jsonObject1.getJSONObject("snippet");
                        JSONObject jsonthumbnail = jsonsnippet.getJSONObject("thumbnails").getJSONObject("medium");

                        YouTubeVideo yv = new YouTubeVideo();

                        if(i!=1 && i!=2) {
                            yv.setTitle(jsonsnippet.getString("title"));
                            yv.setUrl(jsonthumbnail.getString("url"));
                            yv.setid(jsonobjid.getString("videoId"));
                            videos1.add(yv);
                        }

                    }
                    adapter1.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Check Your Internet Connection \uD83D\uDEDC", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest1);
    }

    private void recycle2(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, "https://www.googleapis.com/youtube/v3/search?part=snippet&q=top+latest+news+in+nellore&type=video&maxResults=15&key=AIzaSyCnJ7Gyyyah2RjN7t8xI5PexUhpA2UVdHg", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");
                    for (int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonobjid = jsonObject1.getJSONObject("id");
                        JSONObject jsonsnippet = jsonObject1.getJSONObject("snippet");
                        JSONObject jsonthumbnail = jsonsnippet.getJSONObject("thumbnails").getJSONObject("medium");

                        YouTubeVideo yv = new YouTubeVideo();

                        yv.setTitle(jsonsnippet.getString("title"));
                        yv.setUrl(jsonthumbnail.getString("url"));
                        yv.setid(jsonobjid.getString("videoId"));
                        videos2.add(yv);
                    }
                    adapter2.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Check Your Internet Connection \uD83D\uDEDC", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest2);
    }

    private void recycle3(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest3 = new StringRequest(Request.Method.GET, "https://www.googleapis.com/youtube/v3/search?part=snippet&q=top+latest+news+in+India&type=video&maxResults=20&key=AIzaSyCnJ7Gyyyah2RjN7t8xI5PexUhpA2UVdHg", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");
                    for (int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonobjid = jsonObject1.getJSONObject("id");
                        JSONObject jsonsnippet = jsonObject1.getJSONObject("snippet");
                        JSONObject jsonthumbnail = jsonsnippet.getJSONObject("thumbnails").getJSONObject("medium");

                        YouTubeVideo yv = new YouTubeVideo();

                        yv.setTitle(jsonsnippet.getString("title"));
                        yv.setid(jsonobjid.getString("videoId"));
                        yv.setUrl(jsonthumbnail.getString("url"));
                        videos3.add(yv);
                    }
                    adapter3.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Check Your Internet Connection \uD83D\uDEDC", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest3);
    }
}