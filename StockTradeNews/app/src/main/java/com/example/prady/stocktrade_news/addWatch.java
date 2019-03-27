package com.example.prady.stocktrade_news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.prady.stocktrade_news.models.transactionData;
import com.example.prady.stocktrade_news.models.watchlistModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class addWatch extends AppCompatActivity {
    private ListView lv;
    private ArrayList<String> list;
    private List<watchlistModel> wlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_watch);
        Intent myintent = new Intent(addWatch.this, MarketWatchActivity.class);
        startActivity(myintent);
        finish();
    }
}
