package com.example.prady.stocktrade_news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PIN extends AppCompatActivity {
    Button setpin;
    EditText pin1;
    EditText pin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        pin1 = findViewById(R.id.pin);
        pin2 = findViewById(R.id.pin2);
        setpin = findViewById(R.id.setauth);
        setpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pin1.getText().length() != 4){
                    Toast.makeText(getApplicationContext(),"PIN size is expected to be 4",Toast.LENGTH_LONG).show();
                    return;
                }
                if(! pin2.getText().toString().equals(pin1.getText().toString())){
                    Toast.makeText(getApplicationContext(),"PIN Doesn't MATCH ",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent mainAct = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(mainAct);
            }
        });


    }
}
