package com.example.prady.stocktrade_news.models;

import android.util.Log;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class transactionData {
    public String ticker;
    public int price;
    public String type;
    public String date;

    public transactionData(){

    }
    public transactionData(String tick, int price,String type,String date){
        this.ticker = tick;
        this.price = price;
        this.type = type;
        this.date = date;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ticker", this.ticker);
        result.put("price", this.price);
        result.put("type", this.type);
        result.put("date", this.date);
        return result;
    }
}
