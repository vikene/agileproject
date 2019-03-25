package com.example.prady.stocktrade_news;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class account_settings extends AppCompatActivity {
    EditText namee;
    EditText phonee;
    EditText emaile;
    Button save;
    public  static SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        sp = sharedPref;
        if (sharedPref.getString("Name","DEFAULT").equals("DEFAULT")) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("Name", "Vigneash Sundar");
            editor.putString("Phone", "4699999999");
            editor.putString("Email", "vigneashsundar@live.com");
            editor.commit();
        }

        final String name = sharedPref.getString("Name", "Vigneash ");
        String phone = sharedPref.getString("Phone","4692701938");
        String email = sharedPref.getString("Email","vigneashsundar@live.com");

        namee = findViewById(R.id.name);
        emaile = findViewById(R.id.eid);
        phonee = findViewById(R.id.pno);
        save = findViewById(R.id.button);
        namee.setText(name);
        phonee.setText(phone);
        emaile.setText(email);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Name", namee.getText().toString());
                editor.putString("Phone", phonee.getText().toString());
                editor.putString("Email", emaile.getText().toString());
                editor.commit();
                Toast.makeText(getApplicationContext(),"Saved successfully",Toast.LENGTH_LONG).show();
            }
        });



    }
}
