package com.example.prady.stocktrade_news.models;

public class performancemodel {
    public String companyname;
    public String buyprice;
    public String sellprice;
    public Double gainpercent;
    public Double gainusd;
    public performancemodel(String name, String buyprice, String sellprice, Double gainpercent, Double gainusd ){
        this.companyname = name;
        this.buyprice = buyprice;
        this.sellprice = sellprice;
        this.gainpercent = gainpercent;
        this.gainusd = gainusd;
    }
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(String buyprice) {
        this.buyprice = buyprice;
    }

    public String getSellprice() {
        return sellprice;
    }

    public void setSellprice(String sellprice) {
        this.sellprice = sellprice;
    }

    public Double getGainpercent() {
        return gainpercent;
    }

    public void setGainpercent(Double gainpercent) {
        this.gainpercent = gainpercent;
    }

    public Double getGainusd() {
        return gainusd;
    }

    public void setGainusd(Double gainusd) {
        this.gainusd = gainusd;
    }

}
