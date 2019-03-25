package com.example.prady.stocktrade_news.viewholder;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prady.stocktrade_news.R;

public class watclist extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView watchTicker;
    TextView watchDescription;
    ImageView watchPrice;
    public void setHoldingTicker(String name){
        watchTicker.setText(name);
        watchTicker.setAllCaps(true);
    }
    public void setHolding_gain(double prices){
        watchDescription.setText("$ "+prices+"");
    }
    public watclist(View itemView){
        super(itemView);
        watchTicker = itemView.findViewById(R.id.watch_list_name);
        watchDescription = itemView.findViewById(R.id.watch_list_price);
        watchPrice = itemView.findViewById(R.id.watch_image);
        watchPrice.setImageURI(Uri.parse("android.resource://com.example.prady.stocktrade_news/" + R.drawable.ic_expand_less_black_24dp));
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
