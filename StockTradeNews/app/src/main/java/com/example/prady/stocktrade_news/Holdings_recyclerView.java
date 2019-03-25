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
    ImageView holding_gain_loss;
    public void setHoldingTicker(String name){
        holding_ticker.setText(name);
        holding_ticker.setAllCaps(true);
    }
    public void setHolding_gain(int prices){
        holding_gain.setText("$ "+prices+"");
    }
    public Holdings_recyclerView(View itemView){
        super(itemView);
        holding_ticker = itemView.findViewById(R.id.holding_ticker_row);
        holding_gain = itemView.findViewById(R.id.holding_ticker_profit);
        holding_gain_loss = itemView.findViewById(R.id.holding_image);
        holding_gain_loss.setImageURI(Uri.parse("android.resource://com.example.prady.stocktrade_news/" + R.drawable.ic_expand_less_black_24dp));
        itemView.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

    }
}
