package com.example.prady.stocktrade_news;

import android.content.Intent;
import android.renderscript.Type;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.prady.stocktrade_news.models.transactionData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;

import org.w3c.dom.TypeInfo;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.*;

public class add_transaction extends AppCompatActivity {
    private  String itemBuySell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        final EditText mTicker = (EditText) findViewById(R.id.enter_ticker_text_input);
        final EditText dot = (EditText) findViewById(R.id.dt) ;
        final EditText quantityField = (EditText) findViewById(R.id.enter_quantity_text_input);
        final EditText priceField = (EditText) findViewById(R.id.enter_price_text_input);
        final EditText transactionFeeField =(EditText) findViewById(R.id.enter_transaction_fee_text_input);
        dot.setInputType(InputType.TYPE_CLASS_TEXT);
        String tickerText = mTicker.getText().toString();
        String dateOfTransaction = "";
        int quantity;
        int price;
        int transactionfee;
        final Button saveButton = findViewById(R.id.save_button_id);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int radioButtonClicked = radioGroup.getCheckedRadioButtonId();
        itemBuySell = "Buy";
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_buy :
                        itemBuySell = "Buy";
                        break;
                    case R.id.radio_sell :
                        itemBuySell = "Sell";
                        break;
                }
            }
        });



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(mTicker)|| isEmpty(dot) || isEmpty(quantityField)||isEmpty(priceField)||isEmpty(transactionFeeField))
                {
                    Toast t = Toast.makeText(v.getContext(),"Enter all Fields!", LENGTH_SHORT);
                    t.show();
                }
                else
                    {
                    final String ticker_text = mTicker.getText().toString();
                    final Double quant = Double.parseDouble(quantityField.getText().toString());
                    final Double pric = Double.parseDouble(priceField.getText().toString());
                    final Double tr = Double.parseDouble(transactionFeeField.getText().toString());
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("/");
                    String key = myRef.child("/").push().getKey();
                    transactionData post = new transactionData(ticker_text,  pric,itemBuySell,dot.getText().toString(),quant,tr);
                    Map<String, Object> postValues = post.toMap();

                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/transaction/" + key, postValues);
                    myRef.updateChildren(childUpdates);
                    Intent saveback = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(saveback);
                }
            }
        });



    }
    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }



}

