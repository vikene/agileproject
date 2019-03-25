package com.example.prady.stocktrade_news.Utility;


import com.example.prady.stocktrade_news.models.MarketWatch;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Query;


public interface ApiInterface {

    // Intrinio
//    @GET("data_point")
//    Call<MarketWatch> getStockPrices(@Query(AppConstants.IDENTIFIER) String query, @Query(AppConstants.ITEM) String query2, @Query(AppConstants.API_ARGUMENT) String query3);

    // add another api here
    @GET("api/v1/stock?")
    Call<MarketWatch> getStockQuotes(@Query(AppConstants.SYMBOL) String query, @Query(AppConstants.API_TOKEN) String query2);

}