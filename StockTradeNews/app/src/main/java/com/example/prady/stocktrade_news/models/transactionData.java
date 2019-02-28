package com.example.prady.stocktrade_news.models;

import android.util.Log;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class transactionData {
    public String ticker;
    public int price;

    public transactionData(){

    }
    public transactionData(String tick, int price){
        this.ticker = tick;
        this.price = price;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ticker", this.ticker);
        result.put("price", this.price);
        return result;
    }
}
