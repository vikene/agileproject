package com.example.prady.stocktrade_news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.prady.stocktrade_news.models.transactionData;
import java.util.HashMap;
import java.util.Map;

public class edit_transaction extends AppCompatActivity {

    private static final String TAG = "edit_transaction";
    private String key;
    private String ticker;
    String iBuySell = "";

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction);
        Log.d(TAG, "onCreate: Started");
        ticker = getIntent().getStringExtra("ticker");

        EditText t = findViewById(R.id.enter_ticker_text_input_edit);
        t.setText(ticker);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/");
        myRef.child("transaction").orderByChild("ticker").equalTo(ticker).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                transactionData transaction = dataSnapshot.getValue(transactionData.class);

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    transaction = child.getValue(transactionData.class);
                    key = child.getKey();
                }

                EditText newTicker = findViewById(R.id.enter_ticker_text_input_edit);
                EditText date = findViewById(R.id.dt_edit);
                EditText price = findViewById(R.id.enter_price_text_input_edit);
                EditText qty = findViewById(R.id.enter_quantity_text_input_edit);
                EditText transationFee = findViewById(R.id.enter_transaction_fee_text_input_edit);
                newTicker.setText(transaction.ticker);
                date.setText(transaction.date);
                qty.setText(transaction.quantity.toString());
                transationFee.setText(transaction.transactionfee.toString());
                price.setText(String.valueOf(transaction.price));
                iBuySell = transaction.type;



                if (iBuySell.trim().equals("Buy")) {
                    RadioButton itemBuy = findViewById(R.id.radio_buy_edit);
                    itemBuy.setChecked(true);

                }
                else if (iBuySell.trim().equals("SELL")){
                    RadioButton itemSell = findViewById(R.id.radio_sell_edit);
                    itemSell.setChecked(true);

                }
//                if (iBuySell.equalsIgnoreCase("SELL")) {
//
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void btnUpdate(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/");
        EditText newTicker = findViewById(R.id.enter_ticker_text_input_edit);
        EditText date = findViewById(R.id.dt_edit);
        EditText price = findViewById(R.id.enter_price_text_input_edit);
        EditText qty = findViewById(R.id.enter_quantity_text_input_edit);
        EditText transationFee = findViewById(R.id.enter_transaction_fee_text_input_edit);
        iBuySell = "";
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        RadioButton checkedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        iBuySell = checkedRadioButton.getText().toString();

        transactionData post = new transactionData(newTicker.getText().toString(),  Double.parseDouble(price.getText().toString()),iBuySell,date.getText().toString(),Double.parseDouble(qty.getText().toString()),Double.parseDouble(transationFee.getText().toString()));

        myRef.child("/transaction/"+key).updateChildren(post.toMap());
        onBackPressed();
    }

    public void btnCancel(View view) {
        onBackPressed();
    }


}