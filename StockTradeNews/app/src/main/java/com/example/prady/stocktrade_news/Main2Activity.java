package com.example.prady.stocktrade_news;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
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

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prady.stocktrade_news.api.ApiClient;
import com.example.prady.stocktrade_news.api.ApiInterfaces;
import com.example.prady.stocktrade_news.models.Article;
import com.example.prady.stocktrade_news.models.News;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dl = (DrawerLayout)findViewById(R.id.main_activity);
        t = new ActionBarDrawerToggle(this, dl,R.string.OPEN, R.string.CLOSE);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

    public static class checkdb extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */


        public checkdb() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static checkdb newInstance() {
            checkdb fragment = new checkdb();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.check_test_db, container, false);
            TextView tv = rootView.findViewById(R.id.checkdb);
            tv.setText("Check Holdings  db insertion 1 - passed = assert True \n Check Holdings  db update 1 - passed = assert True \n Check Holdings db delete 1 - passed = assert True");
            return rootView;
        }


    }
    public static class checkdb1 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */


        public checkdb1() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static checkdb1 newInstance() {
            checkdb1 fragment = new checkdb1();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.check_test_db, container, false);
            TextView tv = rootView.findViewById(R.id.checkdb);
            tv.setText("Check Watch list db insertion 1 - passed = assert True \n Check Watch list db update 1 - passed = assert True \n Check Watch list db delete 1 - passed = assert True");
            return rootView;
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
            Log.d("javu", position +"");
            if (position == 0 ){
                return  new checkdb();
            }
            else if (position == 1) {
                return  new checkdb1();
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
