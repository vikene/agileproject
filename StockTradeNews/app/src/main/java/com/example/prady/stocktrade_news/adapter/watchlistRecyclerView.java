package com.example.prady.stocktrade_news.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prady.stocktrade_news.Holdings_recyclerView;
import com.example.prady.stocktrade_news.R;
import com.example.prady.stocktrade_news.models.transactionData;
import com.example.prady.stocktrade_news.models.watchlistModel;
import com.example.prady.stocktrade_news.viewholder.watclist;

import java.util.List;

public class watchlistRecyclerView  extends RecyclerView.Adapter<watclist> {
    private List<String> tickerData;
    private  List<watchlistModel> ticker_Data;
    private LayoutInflater mInflater;
    public watchlistRecyclerView(List<watchlistModel> mylist, Context context){
        ticker_Data = mylist;
        mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public watclist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.watchlistrow,parent,false);
        return new watclist(view);
    }

    @Override
    public void onBindViewHolder(@NonNull watclist holder, int position) {
        watchlistModel data = ticker_Data.get(position);
        holder.setHoldingTicker(data.name+" , "+data.description);
        holder.setHolding_gain(data.price);

    }

    @Override
    public int getItemCount() {
        return ticker_Data.size();
    }
}

