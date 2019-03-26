package com.example.prady.stocktrade_news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Stock_Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock__details);
        Intent data = getIntent();
        String ticker = data.getStringExtra("ticker");
        String company = data.getStringExtra("company");
        String price = data.getStringExtra("price");
        String change = data.getStringExtra("change");
        TextView tick = findViewById(R.id.ticker);
        TextView cname = findViewById(R.id.companyname);
        TextView priceT = findViewById(R.id.price);
        TextView changeT = findViewById(R.id.change);
        tick.setText(ticker);
        cname.setText(company);
        priceT.setText(price);
        changeT.setText(change);
    }
}
