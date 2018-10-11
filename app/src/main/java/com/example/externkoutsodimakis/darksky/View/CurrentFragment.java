package com.example.externkoutsodimakis.darksky.View;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.externkoutsodimakis.darksky.Model.Currently;
import com.example.externkoutsodimakis.darksky.R;
import com.example.externkoutsodimakis.darksky.Requests.RequestWeather;
import com.example.externkoutsodimakis.darksky.Utils.COMMON;
import com.example.externkoutsodimakis.darksky.Utils.TempConversion;
import com.example.externkoutsodimakis.darksky.Utils.TimeConversion;
import com.example.externkoutsodimakis.darksky.Events.ErrorEvent;
import com.example.externkoutsodimakis.darksky.Events.WeatherEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.externkoutsodimakis.darksky.Utils.IconUtil.weatherIconMap;
import static com.example.externkoutsodimakis.darksky.Utils.UvIndexConversion.uvIndexToText;


public class CurrentFragment extends Fragment {
    TextView currentSummary;
    ImageView weatherIcon;
    TextView tempCurrent;
    TextView realFeel;
    TextView humidity_tv;
    TextView uv_tv;
    TextView timeStamp;
    TextView wind_tv;

    public CurrentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RequestWeather.requestCurrentWeather(41.0522, 23.2227);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current, container, false);
        currentSummary = view.findViewById(R.id.currentSummary);
        weatherIcon = view.findViewById(R.id.tempIcon);
        tempCurrent = view.findViewById(R.id.tempCurrent);
        realFeel = view.findViewById(R.id.realFeel);
        humidity_tv = view.findViewById(R.id.humidity_tv);
        uv_tv = view.findViewById(R.id.uv_tv);
        wind_tv = view.findViewById(R.id.wind_tv);
        timeStamp = view.findViewById(R.id.timeStamp);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

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
        Currently currentWeather = weatherEvent.getWeather().getCurrently();
        Log.d(COMMON.MAIN_ACTIVITY_TAG, "TIME" + currentWeather.getTime());
        timeStamp.setText(TimeConversion.epochToTime(currentWeather.getTime()));
        timeStamp.setTypeface(timeStamp.getTypeface(), Typeface.BOLD);
        tempCurrent.setText(String.valueOf(Math.round(TempConversion.tempConversion(currentWeather.getTemperature()))) + COMMON.DEGREE);
        realFeel.setText("(Real Feel" + " " + String.valueOf(Math.round(TempConversion.tempConversion(currentWeather.getApparentTemperature()))) + COMMON.DEGREE + ")");
        currentSummary.setText(currentWeather.getSummary());
        weatherIcon.setImageResource(weatherIconMap.get(currentWeather.getIcon()));
        String humdity = String.valueOf(((currentWeather.getHumidity()) * 100) + COMMON.PERCENT_SIGN);
        humidity_tv.setText("HUMIDITY :" + COMMON.SPACE + humdity);
        uv_tv.setText("UV INDEX : " + COMMON.SPACE + uvIndexToText.get(currentWeather.getUvIndex()));
        wind_tv.setText("WIND SPEED : "+ COMMON.SPACE + currentWeather.getWindSpeed());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent errorEvent) {
        Toast.makeText(getActivity(), errorEvent.getErrorMsg(), Toast.LENGTH_SHORT).show();

    }
}


