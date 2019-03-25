package com.example.prady.stocktrade_news.Utility;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient2 {

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                  .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
