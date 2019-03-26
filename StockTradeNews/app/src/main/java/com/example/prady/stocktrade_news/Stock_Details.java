package com.example.prady.stocktrade_news;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        LineChart chart = (LineChart) findViewById(R.id.chart);
        changeT.setText(change);

        List<Entry> entries = new ArrayList<Entry>();
        for(int i = 1; i< 40;i++){
            entries.add(new Entry(i, (float)Math.random()*1000));
        }

        LineDataSet dataSet = new LineDataSet(entries, ticker);
        dataSet.setColor(Color.CYAN);
        dataSet.setLineWidth(3);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(Color.CYAN);
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.setBackgroundColor(Color.BLACK);
        chart.invalidate();
        getData();
    }

    public void getData(){

    }


}


