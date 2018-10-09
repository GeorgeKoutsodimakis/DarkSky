package com.example.externkoutsodimakis.darksky;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.externkoutsodimakis.darksky.Model.Currently;
import com.example.externkoutsodimakis.darksky.Services.WeatherServiceProvider;
import com.example.externkoutsodimakis.darksky.Utils.Constants;
import com.example.externkoutsodimakis.darksky.events.ErrorEvent;
import com.example.externkoutsodimakis.darksky.events.WeatherEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.externkoutsodimakis.darksky.Utils.IconUtil.weatherIconMap;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.temp_tv)
    TextView tempTextView;

    @BindView(R.id.weatherIcon)
    ImageView weatherIcon;

    @BindView(R.id.summary_tv)
    TextView summaryTv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.timeStamp)
    TextView timeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestCurrentWeather(41.0522, 23.2227);
        ButterKnife.bind(this);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
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
        tempTextView.setText(String.valueOf(currentWeather.getTemperature()) + "/" + currentWeather.getApparentTemperature());
        summaryTv.setText(currentWeather.getSummary());
        weatherIcon.setImageResource(weatherIconMap.get(currentWeather.getIcon()));
    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent) {
        Toast.makeText(this, errorEvent.getErrorMsg(), Toast.LENGTH_SHORT).show();

    }
}
