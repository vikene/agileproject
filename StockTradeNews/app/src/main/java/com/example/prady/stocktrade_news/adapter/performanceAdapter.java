package com.example.prady.stocktrade_news.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prady.stocktrade_news.Holdings_recyclerView;
import com.example.prady.stocktrade_news.R;
import com.example.prady.stocktrade_news.models.performancemodel;
import com.example.prady.stocktrade_news.models.transactionData;
import com.example.prady.stocktrade_news.models.watchlistModel;
import com.example.prady.stocktrade_news.viewholder.performanceHolder;
import com.example.prady.stocktrade_news.viewholder.watclist;

import java.util.List;

public class performanceAdapter  extends RecyclerView.Adapter<performanceHolder> {
    private List<String> tickerData;
    private  List<performancemodel> ticker_Data;
    private LayoutInflater mInflater;
    private performanceAdapter adapter;
    public performanceAdapter(List<performancemodel> mylist, Context context){
        ticker_Data = mylist;
        mInflater = LayoutInflater.from(context);

    }
    public void setAdapter(performanceAdapter madapter){
        adapter = madapter;
    }
    @NonNull
    @Override
    public performanceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.performance_item,parent,false);
        return new performanceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull performanceHolder holder, final int position) {
        performancemodel data = ticker_Data.get(position);
        holder.setCompanyname(data.companyname);
        holder.setBuyprice("Buy Price: "+data.buyprice);
        holder.setSellprice("Sell Price: "+data.sellprice);
        if(data.gainusd > 0){
            holder.setImageArrow("android.resource://com.example.prady.stocktrade_news/" + R.drawable.ic_keyboard_arrow_up_black_24dp);
            holder.setImageGreen();
        }else{
            holder.setImageArrow("android.resource://com.example.prady.stocktrade_news/" + R.drawable.ic_keyboard_arrow_down_black_24dp);
            holder.setImageRed();
        }
        holder.setGainUSD(Math.round(data.gainusd * 100D) / 100D+" USD");
        holder.setGainPercent(Math.round(data.gainpercent * 100D) / 100D+"%");

    }

    @Override
    public int getItemCount() {
        return ticker_Data.size();
    }
}

