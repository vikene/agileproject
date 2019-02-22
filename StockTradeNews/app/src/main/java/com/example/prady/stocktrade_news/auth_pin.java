package com.example.prady.stocktrade_news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class auth_pin extends AppCompatActivity {
    EditText pin;
    Button authBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_pin);
        authBut = findViewById(R.id.authpin);
        pin = findViewById(R.id.pin23);
        authBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pin.getText().toString().equals("1234") ||pin.getText().toString().equals("4567") ){
                    Intent myintent = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(myintent);
                }else{
                    Toast.makeText(getApplicationContext(),"WRONG PIN", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
