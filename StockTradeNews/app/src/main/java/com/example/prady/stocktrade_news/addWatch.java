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
        lv = findViewById(R.id.addwatch);
        list = new ArrayList<>();
        wlist = new ArrayList<>();
        wlist.add(new watchlistModel("GOOGLE","Alphabet, INC", 1122.89));
        wlist.add(new watchlistModel("BA","The Boeing Company", 435.44));
        wlist.add(new watchlistModel("FFIDX","Fidelity", 44.44));
        wlist.add(new watchlistModel("AAPL","Apple, INC", 174.87));
        wlist.add(new watchlistModel("ISRG","Intuitive Surgical Inc", 549.41));
        wlist.add(new watchlistModel("NTAP","NetApp, Inc", 66.08));
        wlist.add(new watchlistModel("AMZN","Amazon, INC", 1641.09));
        wlist.add(new watchlistModel("ILMN","Illumina, INC", 309.16));
        wlist.add(new watchlistModel("BSX","Boston Scientific Corporation", 39.93));
        wlist.add(new watchlistModel("UA","Under Armour, Inc.", 22.52));
        wlist.add(new watchlistModel("HCA","HCA Healthcare", 138.81));
        wlist.add(new watchlistModel("NFLX","Netflix, Inc", 362.87));
        wlist.add(new watchlistModel("CMG","Chipotle Mexican Grill, Inc", 599.28));
        wlist.add(new watchlistModel("AAP","Advance Auto Parts, Inc", 158.30));
        wlist.add(new watchlistModel("FTNT","Fortinet, Inc", 87.90));
        wlist.add(new watchlistModel("TRIP","TripAdvisor, Inc", 54.13));
        wlist.add(new watchlistModel("AMD","Advanced Micro Devices", 23.48));
        wlist.add(new watchlistModel("ABMD","ABIOMED, Inc", 336.17));
        wlist.add(new watchlistModel("ORCL","oracle corporation", 52.38));
        wlist.add(new watchlistModel("NKE","NIKE, INC", 86.17));
        wlist.add(new watchlistModel("SBUX","Starbucks Corp", 70.15));
        wlist.add(new watchlistModel("ADBE","Adobe INC", 262.90));
        wlist.add(new watchlistModel("CRM","Salesforce", 163.12));
        wlist.add(new watchlistModel("T","AT&T, INC", 31.06));
        wlist.add(new watchlistModel("BABA","Alibaba group", 184.58));
        wlist.add(new watchlistModel("BSE","Bank of america", 25.90));
        for(int i = 0 ; i < wlist.size(); i ++){
            list.add(wlist.get(i).description+" , " +wlist.get(i).name);
        }


        lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("/");
                String key = myRef.child("/").push().getKey();

                Map<String, Object> postValues = wlist.get(position).toMap();

                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("/watchlist/" + key, postValues);
                myRef.updateChildren(childUpdates);
                Intent saveback = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(saveback);
            }
        });
    }
}
