package com.example.prady.stocktrade_news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.prady.stocktrade_news.Utility.ApiClient2;
import com.example.prady.stocktrade_news.Utility.ApiInterface;
import com.example.prady.stocktrade_news.Utility.AppConstants;
import com.example.prady.stocktrade_news.models.MarketWatch;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketWatchActivity extends AppCompatActivity {

//    public static final String ALPHA_API_KEY = "Ywy8s2NN6hwjJRZztGMf";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
//    private RecyclerView.Adapter adapter;
    private String ticker;
    private String TAG = MainActivity.class.getSimpleName();
    private List<MarketWatch> marketwatch = new ArrayList<>();
    private AdapterMW adapterMW; // = new AdapterMW(marketwatch,MarketWatchActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_watch);

        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(MarketWatchActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        // Intrinio
//        callMarketAPI("AAPL", "last_price", AppConstants.API_KEY);

        callMarketAPI("AAPL,MSFT,TSLA,AMZN,MU", AppConstants.API_KEY);
        callMarketAPI("NFLX,PETQ,WIX,ERF,SWN", AppConstants.API_KEY);
    }

//    private void callMarketAPI(String identifier, String item, String apiKey) {
    private void callMarketAPI(String identifier, String apiKey) {

        ApiInterface apiService =
                ApiClient2.getClient().create(ApiInterface.class);

//        Intrinio
//        Call<MarketWatch> call = apiService.getStockPrices(identifier, item, apiKey);
        Call<MarketWatch> call = apiService.getStockQuotes(identifier, apiKey);
        call.enqueue(new Callback<MarketWatch>() {

            @Override
            public void onResponse(Call<MarketWatch> call, Response<MarketWatch> response) {

                MarketWatch marketWatch = response.body();
                Log.e("getSymbolsRequested",  response.body().getSymbolsRequested() +" ");
                Log.e("getSymbolsReturned ", response.body().getSymbolsReturned() + " ");

//                adapter = new adapter(response.body().getData(), MarketWatchActivity.this);
//                marketwatch = response.body().getData();
                marketwatch.addAll(response.body().getData());
                adapterMW = new AdapterMW(marketwatch,MarketWatchActivity.this);
                recyclerView.setAdapter(adapterMW);
                adapterMW.notifyDataSetChanged();

                /*if(marketWatch != null){
                    Log.e("Symbol", response.body().getSymbol());
                    Log.e("Name", response.body().getName());
                    Log.e("Price", response.body().getPrice()+" ");
                } else {
                    Log.e("Data is null", " NULL ");
                }*/
            }

//            @Override
//            public void onResponse(Call<MarketWatch> call, Response<MarketWatch> response) {
//
//                MarketWatch marketWatch = response.body();
//                ticker = marketWatch.getIdentifier();
//
//                if(marketWatch != null){
//                    Log.e("identifier", response.body().getIdentifier());
//                    Log.e("item", response.body().getItem());
//                    Log.e("value", response.body().getValue()+" ");
//                }
//            }

            @Override
            public void onFailure(Call<MarketWatch> call, Throwable t) {
                Log.e("API RESPONSE", t.toString());
            }
        });
    }



}
