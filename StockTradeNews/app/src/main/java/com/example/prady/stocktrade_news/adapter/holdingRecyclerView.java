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

import java.util.List;

public class holdingRecyclerView extends RecyclerView.Adapter<Holdings_recyclerView> {
    private List<String> tickerData;
    private  List<transactionData> ticker_Data;
    private LayoutInflater mInflater;
    public holdingRecyclerView(List<transactionData> mylist, Context context){
        ticker_Data = mylist;
        mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public Holdings_recyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.holdings_row,parent,false);
        return new Holdings_recyclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holdings_recyclerView holder, int position) {
        transactionData data = ticker_Data.get(position);
        Log.d("JAVUU", data.ticker);
        holder.setHoldingTicker(data.ticker);
        holder.setHolding_gain(data.price);

    }

    @Override
    public int getItemCount() {
        return ticker_Data.size();
    }
}
