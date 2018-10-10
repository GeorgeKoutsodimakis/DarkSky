package com.example.externkoutsodimakis.darksky;

import android.app.FragmentTransaction;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.externkoutsodimakis.darksky.Utils.IconUtil.weatherIconMap;

public class MainActivity extends AppCompatActivity {

    TextView tempTextView;
    ImageView weatherIcon;
    TextView summaryTv;
    TextView timeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestCurrentWeather(41.0522, 23.2227);

        tempTextView = findViewById(R.id.temp_tv);
        weatherIcon = findViewById(R.id.weatherIcon);
        summaryTv = findViewById(R.id.summary_tv);
        timeStamp = findViewById(R.id.timeStamp);

        AppbarFragment appbarFragment = new AppbarFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();;
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.toolbar, appbarFragment, appbarFragment.toString());
        fragmentTransaction.addToBackStack(appbarFragment.toString());
        fragmentTransaction.commit();

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
                SearchFragment searchFragment = new SearchFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();;
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.toolbar, searchFragment, searchFragment.toString());
                fragmentTransaction.addToBackStack(searchFragment.toString());
                fragmentTransaction.commit();
                break;
            case R.id.action_share:
                Toast.makeText(this,"share",Toast.LENGTH_SHORT).show();
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
}
