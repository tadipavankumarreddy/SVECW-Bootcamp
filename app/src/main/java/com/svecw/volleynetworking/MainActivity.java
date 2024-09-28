package com.svecw.volleynetworking;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    String news_url = "https://newsapi.org/v2/top-headlines?category=entertainment&pageSize=10&apiKey=6c6c30f263e34b19be74f12a17a5617c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ProgressBar pb = findViewById(R.id.progressBar);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                news_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // gson parsing

                        pb.setVisibility(View.GONE);
                        Gson g = new Gson();
                        Log.v("MAIN",response);
                        NewsArticle a = g.fromJson(response,NewsArticle.class);
                        RecyclerView rv = findViewById(R.id.recycler);
                        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        rv.setAdapter(new NewsAdapter(MainActivity.this,a.getArticles()));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pb.setVisibility(View.GONE);
                        Log.v("MAIN",error.getMessage());
                    }
                }
        );

        queue.add(request);
    }
}