package com.example.externkoutsodimakis.darksky.View;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.externkoutsodimakis.darksky.Events.ErrorEvent;
import com.example.externkoutsodimakis.darksky.Events.WeatherEvent;
import com.example.externkoutsodimakis.darksky.Model.Currently;
import com.example.externkoutsodimakis.darksky.Model.Hourly;
import com.example.externkoutsodimakis.darksky.R;
import com.example.externkoutsodimakis.darksky.Requests.RequestWeather;
import com.example.externkoutsodimakis.darksky.Utils.COMMON;
import com.example.externkoutsodimakis.darksky.Utils.TempConversion;
import com.example.externkoutsodimakis.darksky.Utils.TimeConversion;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.externkoutsodimakis.darksky.Utils.IconUtil.weatherIconMap;
import static com.example.externkoutsodimakis.darksky.Utils.UvIndexConversion.uvIndexToText;

public class HourlyFragment extends Fragment {


    public HourlyFragment() {
        // Required empty public constructor
    }


    public static HourlyFragment newInstance(String param1, String param2) {
        HourlyFragment fragment = new HourlyFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RequestWeather.requestCurrentWeather(41.0522, 23.2227);
        View view = inflater.inflate(R.layout.fragment_hourly, container, false);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(WeatherEvent weatherEvent) {
        Hourly hourlyWeather = weatherEvent.getWeather().getHourly();
        for (int i=0; i<= hourlyWeather.getData().size(); i++){
            Log.d(COMMON.MAIN_ACTIVITY_TAG,"HOURLY" + hourlyWeather.getData().get(i).getSummary());
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent errorEvent) {
        Toast.makeText(getActivity(), errorEvent.getErrorMsg(), Toast.LENGTH_SHORT).show();

    }
}
