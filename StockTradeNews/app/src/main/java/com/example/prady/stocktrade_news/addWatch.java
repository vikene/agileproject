package com.example.prady.stocktrade_news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class addWatch extends AppCompatActivity {
    private ListView lv;
    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_watch);
        lv = findViewById(R.id.addwatch);
        list = new ArrayList<>();
        
        list.add("Alphabet, GOOGL");
        list.add("The Boeing Company, BA");
        list.add("Fidelity, FFIDX");
        list.add("Apple, AAPL");
        list.add("Intuitive Surgical Inc, ISRG");
        list.add("NetApp, Inc, NTAP");
        list.add("Amazon, AMZN");
        list.add("Illumina, INC, ILMN");
        list.add("Boston Scientific Corporation (BSX)");
        list.add("Under Armour, Inc. (UA)");
        list.add("HCA Healthcare (HCA)");
        list.add("Netflix, Inc. (NFLX)");
        list.add("Chipotle Mexican Grill, Inc. (CMG)");
        list.add("Advance Auto Parts, Inc. (AAP)");
        list.add("Fortinet, Inc. (FTNT)");
        list.add(" TripAdvisor, Inc. (TRIP)");
        list.add("Advanced Micro Devices (AMD)");
        list.add("ABIOMED, Inc. (ABMD)");
        list.add("oracle corporation, ORCL");
        list.add("NIKE, INC, NKE");
        list.add("Starbucks Corp, SBUX");
        list.add("Adobe INC, ADBE");
        list.add("Salesforce, CRM");
        list.add("AT&T, INC, T");
        list.add("Alibaba group, BABA");
        list.add("Bank of america, BSE");









        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
