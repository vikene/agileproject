package com.example.prady.stocktrade_news.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class History implements Serializable {
    String name;
    List<innerHist> history;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<innerHist> getHistory() {
        return history;
    }

    public void setHistory(List<innerHist> history) {
        history = history;
    }
}
