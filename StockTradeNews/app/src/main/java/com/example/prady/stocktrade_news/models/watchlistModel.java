package com.example.prady.stocktrade_news.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class watchlistModel {
    public String name;
    public String description;
    public double price;
    public watchlistModel(){

    }
    public watchlistModel(String name, String desc, double pr){
        this.name= name;
        this.description = desc;
        this.price = pr ;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", this.name);
        result.put("description", this.description);
        result.put("price", this.price);
        return result;
    }
}
