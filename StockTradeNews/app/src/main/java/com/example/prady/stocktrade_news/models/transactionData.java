package com.example.prady.stocktrade_news.models;

import android.util.Log;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class transactionData {
    public String ticker;
    public Double price;
    public String type;
    public String date;
    public Double quantity;
    public Double transactionfee;

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTransactionfee() {
        return transactionfee;
    }

    public void setTransactionfee(Double transactionfee) {
        this.transactionfee = transactionfee;
    }

    public transactionData(){

    }
    public transactionData(String tick, Double price,String type,String date, Double quantity, Double transactionfee){
        this.ticker = tick;
        this.price = price;
        this.type = type;
        this.date = date;
        this.quantity = quantity;
        this.transactionfee = transactionfee;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ticker", this.ticker);
        result.put("price", this.price);
        result.put("type", this.type);
        result.put("date", this.date);
        result.put("quantity", this.quantity);
        result.put("transactionfee", this.transactionfee);
        return result;
    }
}