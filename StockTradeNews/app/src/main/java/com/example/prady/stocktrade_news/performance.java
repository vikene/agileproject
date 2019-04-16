package com.example.prady.stocktrade_news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.prady.stocktrade_news.adapter.performanceAdapter;
import com.example.prady.stocktrade_news.models.performancemodel;
import com.example.prady.stocktrade_news.models.transactionData;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class performance extends AppCompatActivity {
    List<performancemodel> data;
    performanceAdapter adapter;
    HashMap<String, performancemodel> process;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);
        data = new ArrayList<>();
        adapter = new performanceAdapter(data, getApplicationContext());
        adapter.setAdapter(adapter);
        rv = findViewById(R.id.performancerv);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        process = new HashMap<>();
        db();

    }
    public  void db(){
        final String TAG = "Firebase";
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                transactionData trdata = dataSnapshot.getValue(transactionData.class);
                if(process.get(trdata.ticker) != null){
                    if(trdata.type.equals("Buy")){
                        performancemodel da = process.get(trdata.ticker);
                        da.buyprice = trdata.price+"";
                        da.gainusd = Double.parseDouble(da.sellprice) - Double.parseDouble(da.buyprice);
                        da.gainpercent = (da.gainusd / Double.parseDouble(da.buyprice))*100;
                    }
                    else{
                        performancemodel da = process.get(trdata.ticker);
                        da.sellprice = trdata.price+"";
                        da.gainusd = Double.parseDouble(da.sellprice) - Double.parseDouble(da.buyprice);
                        da.gainpercent = (da.gainusd / Double.parseDouble(da.buyprice))*100;
                    }
                }
                else{
                    performancemodel temp;
                    if(trdata.type.equals("Buy")){
                        temp = new performancemodel(trdata.ticker,trdata.price+"",0+"",0.0, 0.0);
                    }else{
                         temp = new performancemodel(trdata.ticker,0+"",trdata.price+"",0.0, 0.0);
                    }
                    data.add(temp);
                    process.put(trdata.ticker, temp);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref2 = database.getReference("/transaction");
        myref2.addChildEventListener(childEventListener);
    }
    public  void popluate(){
        data.add(new performancemodel("Boeing/BA","300","379",26.44,79.39));
        data.add(new performancemodel("Amazon/AMZN","1892.45","1843.06",10.0,30.0));
        adapter.notifyDataSetChanged();
    }
}
