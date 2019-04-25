package com.example.prady.stocktrade_news;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

public class Holdings_recyclerView extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView holding_ticker;
    TextView holding_gain;
    TextView holding_date;
    public void setHoldingTicker(String name){
        holding_ticker.setText(name);
        holding_ticker.setAllCaps(true);
    }
    public void setHolding_date(String date){
        holding_date.setText(date);
    }
    public void setHolding_gain(Double prices){

        holding_gain.setText("$ "+String.format("%.2f",prices)+"");
    }
    public Holdings_recyclerView(View itemView){
        super(itemView);
        holding_ticker = itemView.findViewById(R.id.holding_ticker_row);
        holding_gain = itemView.findViewById(R.id.holding_ticker_profit);
        holding_date = itemView.findViewById(R.id.holding_date);
        itemView.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

    }
}
