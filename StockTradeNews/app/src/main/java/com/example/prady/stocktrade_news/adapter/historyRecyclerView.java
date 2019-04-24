package com.example.prady.stocktrade_news.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.prady.stocktrade_news.Holdings_recyclerView;
import com.example.prady.stocktrade_news.R;
import com.example.prady.stocktrade_news.models.transactionData;

import java.util.LinkedList;
import java.util.List;

public class historyRecyclerView extends RecyclerView.Adapter<Holdings_recyclerView> implements Filterable {
    private List<String> tickerData;
    private List<transactionData> ticker_Data;
    private List<transactionData> ticker_DataFull;
    private LayoutInflater mInflater;

    public historyRecyclerView(List<transactionData> mylist, Context context){
        ticker_Data = mylist;
        mInflater = LayoutInflater.from(context);
//        ticker_DataFull = new LinkedList<>();
        ticker_DataFull = mylist;
    }

    @NonNull
    @Override
    public Holdings_recyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.history_row,parent,false);
        return new Holdings_recyclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holdings_recyclerView holder, int position) {
        transactionData data = ticker_Data.get(position);
        holder.setHoldingTicker(data.ticker);
        holder.setHolding_gain(data.price);
        holder.setHolding_date(data.date);
    }

    @Override
    public int getItemCount() {
        return ticker_Data.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            LinkedList<transactionData> filteredList = new LinkedList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(ticker_DataFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                if(filterPattern.length() != 0){
                    for (transactionData item : ticker_DataFull) {
                        if (item.ticker.toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                else{
                    filteredList.addAll(ticker_DataFull);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ticker_Data.clear();
            ticker_Data.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
