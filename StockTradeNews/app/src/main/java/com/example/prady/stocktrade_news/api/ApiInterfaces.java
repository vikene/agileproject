package com.example.prady.stocktrade_news.api;

import com.example.prady.stocktrade_news.Market;
import com.example.prady.stocktrade_news.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Prady on 2/11/2019.
 */

public interface ApiInterfaces {
    @GET("top-headlines?category=business")
    Call<News> getNews(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );
}
