package com.example.prady.stocktrade_news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Market {

    @SerializedName("identifier")
    @Expose
    private String identifier;
    @SerializedName("item")
    @Expose
    private String item;
    @SerializedName("value")
    @Expose
    private Double value;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}