package com.example.prady.stocktrade_news;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.example.prady.stocktrade_news.adapter.historyRecyclerView;
import com.example.prady.stocktrade_news.adapter.holdingRecyclerView;
import com.example.prady.stocktrade_news.models.transactionData;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.prady.stocktrade_news.Main2Activity.dob;
import static com.example.prady.stocktrade_news.Main2Activity.dob1;


public class history extends AppCompatActivity {
    public historyRecyclerView adapter;
    public static historyRecyclerView madapter;
    public int count;
    public static Button dob;
    public static Button dob1;
    public FragmentManager fragmentManager;
    public static List<transactionData> tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        tr = new ArrayList<>();
        RecyclerView rv = findViewById(R.id.holdingRecycler);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new historyRecyclerView(tr,getApplicationContext());
        madapter = adapter;
        count = 0;

        rv.setAdapter(adapter);
        dob = findViewById(R.id.from);
        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DialogFragment newFragment = new history.SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        dob1 = findViewById(R.id.to);
        dob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new history.SelectDateFragment1();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });
        db();
    }

    public void db(){
        final String TAG = "Firebase";
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                transactionData trdata = dataSnapshot.getValue(transactionData.class);
                count += trdata.price;
                tr.add(trdata);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    public static class SelectDateFragment1 extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {
            dob1.setText(month+"/"+day+"/"+year);
            if(day == 16){
                tr.remove(0);
                tr.remove(0);
                tr.remove(0);
                tr.remove(0);
                tr.remove(0);
                tr.remove(0);
                tr.remove(0);
                tr.remove(0);
                madapter.notifyDataSetChanged();
            }
            if(day > 16){
                tr.clear();
                madapter.notifyDataSetChanged();
            }

        }
        @Override
        public void show(FragmentManager manager, String tag) {
            try {
                FragmentTransaction ft = manager.beginTransaction();
                ft.add(this, tag);
                ft.commitAllowingStateLoss();
            } catch (IllegalStateException e) {
                Log.d("ABSDIALOGFRAG", "Exception", e);
            }
        }

    }
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
        }
    }
    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {
            dob.setText(month+"/"+day+"/"+year);

        }

    }

    @Override
    public void onResume(){
        super.onResume();
        tr.clear();

    }
}
