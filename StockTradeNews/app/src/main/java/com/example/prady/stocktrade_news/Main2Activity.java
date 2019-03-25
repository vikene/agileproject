package com.example.prady.stocktrade_news;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.prady.stocktrade_news.adapter.holdingRecyclerView;
import com.example.prady.stocktrade_news.adapter.watchlistRecyclerView;
import com.example.prady.stocktrade_news.api.ApiClient;
import com.example.prady.stocktrade_news.api.ApiInterfaces;
import com.example.prady.stocktrade_news.models.Article;
import com.example.prady.stocktrade_news.models.News;
import com.example.prady.stocktrade_news.models.transactionData;
import com.example.prady.stocktrade_news.models.watchlistModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    public static List<transactionData> tr;
    public  static  List<watchlistModel> wl;
    public static ActionBar actionBar;
    static Button dob;
    static Button dob1;
    static android.app.FragmentManager mFragementManager;
    static holdingRecyclerView madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dl = (DrawerLayout)findViewById(R.id.main_activity);
        t = new ActionBarDrawerToggle(this, dl,R.string.OPEN, R.string.CLOSE);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tr = new ArrayList<>();
        wl = new ArrayList<>();
        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.account:
                        Toast.makeText(getApplicationContext(), "Account details",Toast.LENGTH_SHORT).show();
                        Intent account =  new Intent(getApplicationContext(), account_settings.class);
                        startActivity(account);
                    case R.id.settings:
                        Toast.makeText(getApplicationContext(), "Watch list",Toast.LENGTH_SHORT).show();
                    case R.id.mycart:
                        Toast.makeText(getApplicationContext(), "Holdings",Toast.LENGTH_SHORT).show();
                    default:
                        return true;
                }
            }
        });


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mFragementManager = getFragmentManager();

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mViewPager.getCurrentItem() == 0){
                    Intent myint = new Intent(getApplicationContext(), add_transaction.class);
                    startActivity(myint);
                }
                else{
                    Intent myint = new Intent(getApplicationContext(), addWatch.class);
                    startActivity(myint);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public static class NewsView extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String API_KEY = "5f41c189047f4089955001d9acd91c8c";
        private RecyclerView recyclerView;
        private RecyclerView.LayoutManager layoutManager;
        private List<Article> articles = new ArrayList<>();
        private Adapter adapter;
        private String TAG = MainActivity.class.getSimpleName();
        private static final String ARG_SECTION_NUMBER = "section_number";
        private Context context;

        public NewsView() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static NewsView newInstance() {
            NewsView fragment = new NewsView();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_main, container, false);
            recyclerView = rootView.findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(this.getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(false);
            this.context = getContext();
            LoadJson();
            return rootView;
        }

        public void LoadJson(){
            ApiInterfaces apiInterface = ApiClient.getApiClient().create(ApiInterfaces.class);
            String country =  Utils.getCountry();
            Call<News> call;
            call = apiInterface.getNews(country,API_KEY);
            final Context appContext = getContext();

            call.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    if(response.isSuccessful() && response.body().getArticle()!= null){
                        if(!articles.isEmpty()) {
                            articles.clear();
                        }
                        articles = response.body().getArticle();
                        adapter = new Adapter(articles,appContext);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        initListener();
                    }
                    else{
                        Toast.makeText(appContext,"No Results!", Toast.LENGTH_SHORT).show();
                    }
                }

                private void initListener(){
                    adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(appContext, NewsDetailActivity.class);
                            Article article = articles.get(position);
                            intent.putExtra("url",article.getUrl());
                            intent.putExtra("title",article.getTitle());
                            intent.putExtra("img",article.getUrlToImage());
                            intent.putExtra("date",article.getPublishedAt());
                            intent.putExtra("source",article.getSource().getName());
                            intent.putExtra("author",article.getAuthor());

                            startActivity(intent);

                        }
                    });
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });

        }
    }


    public static class watchlist extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public watchlistRecyclerView wadapter;

        public watchlist() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.watchlist, container, false);
            RecyclerView rv = rootView.findViewById(R.id.rv);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            wadapter = new watchlistRecyclerView(wl,getContext());
            wadapter.setAdapter(wadapter);

            rv.setAdapter(wadapter);
            db();
            return rootView;
        }

        public void db(){
            final String TAG = "Firebase";
            ChildEventListener childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                    watchlistModel trdata = dataSnapshot.getValue(watchlistModel.class);
                    wl.add(trdata);
                    wadapter.notifyDataSetChanged();

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
            DatabaseReference myref2 = database.getReference("/watchlist");
            myref2.addChildEventListener(childEventListener);
        }


    }


    public static class holdings_fragment extends Fragment {
        public holdingRecyclerView adapter;
        public TextView HoldingPage_final;
        public int count;

        public holdings_fragment(){

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.holdings_page, container, false);
            RecyclerView rv = rootView.findViewById(R.id.holdingRecycler);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            ImageView im = rootView.findViewById(R.id.ImageBack);
            Glide.with(this).load(R.mipmap.line_graph_dribbbble).into(im);
            HoldingPage_final = rootView.findViewById(R.id.HoldingPage_final);
            ImageView im2 = rootView.findViewById(R.id.imUP);
            Glide.with(this).load(R.mipmap.greenarrow).into(im2);
            adapter = new holdingRecyclerView(tr,getContext());
            madapter = adapter;
            count = 0;
            rv.setAdapter(adapter);
            dob =rootView.findViewById(R.id.from);
            dob.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    DialogFragment newFragment = new SelectDateFragment();
                    newFragment.show(mFragementManager, "DatePicker");


                }
            });
            dob1 = rootView.findViewById(R.id.to);
            dob1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment newFragment = new SelectDateFragment1();
                    newFragment.show(mFragementManager, "DatePicker");

                }
            });
            db();
            return rootView;
        }
        public void db(){
             final String TAG = "Firebase";
            ChildEventListener childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                    transactionData trdata = dataSnapshot.getValue(transactionData.class);
                    count += trdata.price;
                    HoldingPage_final.setText("$ "+count+"");
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
            if(day == 2){
                tr.clear();
                madapter.notifyDataSetChanged();
            }
        }

    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0 ){

                Log.d("JAAAAAA","HSS");
                return  new holdings_fragment();
            }
            else if (position == 1) {
                Log.d("JAAAAAA","HSSa");
                return  new watchlist();
            }
            else{
                return new NewsView();
            }



        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

    }
}
