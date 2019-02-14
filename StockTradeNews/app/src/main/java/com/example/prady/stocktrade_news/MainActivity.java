package com.example.prady.stocktrade_news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.prady.stocktrade_news.api.ApiClient;
import com.example.prady.stocktrade_news.api.ApiInterfaces;
import com.example.prady.stocktrade_news.models.Article;
import com.example.prady.stocktrade_news.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "5f41c189047f4089955001d9acd91c8c";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private Adapter adapter;
    private String TAG = MainActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        LoadJson();
    }

    public void LoadJson(){
        ApiInterfaces apiInterface = ApiClient.getApiClient().create(ApiInterfaces.class);
        String country =  Utils.getCountry();
        Call<News> call;
        call = apiInterface.getNews(country,API_KEY);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful() && response.body().getArticle()!= null){
                    if(!articles.isEmpty()) {
                        articles.clear();
                    }
                    articles = response.body().getArticle();
                    adapter = new Adapter(articles,MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    initListener();
                }
                else{
                    Toast.makeText(MainActivity.this,"No Results!", Toast.LENGTH_SHORT).show();
                }
            }

            private void initListener(){
                adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
                        Article article = articles.get(position);
                        intent.putExtra("url",article.getUrl());
                        intent.putExtra("title",article.getTitle());
                        intent.putExtra("img",article.getUrlToImage());
                        intent.putExtra("date",article.getPublishedAt());
                        intent.putExtra("source",article.getSource().getName());
                        intent.putExtra("author",article.getAuthor());

                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });

    }


}
