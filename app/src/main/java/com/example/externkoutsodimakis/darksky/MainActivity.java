package com.example.externkoutsodimakis.darksky;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.example.externkoutsodimakis.darksky.Model.Currently;
import com.example.externkoutsodimakis.darksky.Services.WeatherServiceProvider;
import com.example.externkoutsodimakis.darksky.Utils.Constants;
import com.example.externkoutsodimakis.darksky.Utils.TempConversion;
import com.example.externkoutsodimakis.darksky.View.AppbarFragment;
import com.example.externkoutsodimakis.darksky.View.SearchFragment;
import com.example.externkoutsodimakis.darksky.events.ErrorEvent;
import com.example.externkoutsodimakis.darksky.events.WeatherEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.externkoutsodimakis.darksky.Utils.IconUtil.weatherIconMap;

public class MainActivity extends AppCompatActivity {

    TextView tempTextView;
    ImageView weatherIcon;
    TextView summaryTv;
    TextView timeStamp;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestCurrentWeather(41.0522, 23.2227);

        tempTextView = findViewById(R.id.temp_tv);
        weatherIcon = findViewById(R.id.weatherIcon);
        summaryTv = findViewById(R.id.summary_tv);
        timeStamp = findViewById(R.id.timeStamp);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.current));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.hourly));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.daily));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.map));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        PagerAdapter pagerAdapter = new com.example.externkoutsodimakis.darksky.Adapter.PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        loadAppBar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItem = item.getItemId();

        switch (menuItem) {
            case R.id.action_search:
                loadSearchBar();
                break;
            case R.id.action_share:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void requestCurrentWeather(double lat, double log) {
        WeatherServiceProvider weatherServiceProvider = new WeatherServiceProvider();
        weatherServiceProvider.getWeather(lat, log);
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(WeatherEvent weatherEvent) {
        Currently currentWeather = weatherEvent.getWeather().getCurrently();
        Log.d(Constants.MAIN_ACTIVITY_TAG, "TIME" + currentWeather.getTime());
        timeStamp.setText(String.valueOf(currentWeather.getTime()));
        tempTextView.setText(String.valueOf(Math.round(TempConversion.tempConversion(currentWeather.getTemperature()))) + "/" + Math.round(TempConversion.tempConversion(currentWeather.getApparentTemperature())));
        summaryTv.setText(currentWeather.getSummary());
        weatherIcon.setImageResource(weatherIconMap.get(currentWeather.getIcon()));
    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent) {
        Toast.makeText(this, errorEvent.getErrorMsg(), Toast.LENGTH_SHORT).show();

    }

    private void loadAppBar() {
        AppbarFragment appbarFragment = new AppbarFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.toolbar, appbarFragment, appbarFragment.toString());
        fragmentTransaction.addToBackStack(appbarFragment.toString());
        fragmentTransaction.commit();
    }

    private void loadSearchBar() {
        SearchFragment searchFragment = new SearchFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.toolbar, searchFragment, searchFragment.toString());
        fragmentTransaction.addToBackStack(searchFragment.toString());
        fragmentTransaction.commit();
    }
}
