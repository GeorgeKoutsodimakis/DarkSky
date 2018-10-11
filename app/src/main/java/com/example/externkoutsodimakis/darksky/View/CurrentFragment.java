package com.example.externkoutsodimakis.darksky.View;

import android.content.Context;
import android.net.Uri;
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
import com.example.externkoutsodimakis.darksky.Utils.Constants;
import com.example.externkoutsodimakis.darksky.Utils.TempConversion;
import com.example.externkoutsodimakis.darksky.Utils.TimeConversion;
import com.example.externkoutsodimakis.darksky.events.ErrorEvent;
import com.example.externkoutsodimakis.darksky.events.WeatherEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.externkoutsodimakis.darksky.Utils.IconUtil.weatherIconMap;

public class CurrentFragment extends Fragment {
    TextView tempTextView;
    ImageView weatherIcon;
    TextView summaryTv;
    TextView timeStamp;

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
        tempTextView = view.findViewById(R.id.temp_tv);
        weatherIcon = view.findViewById(R.id.weatherIcon);
        summaryTv = view.findViewById(R.id.summary_tv);
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
        Log.d(Constants.MAIN_ACTIVITY_TAG, "TIME" + currentWeather.getTime());
        timeStamp.setText(TimeConversion.epochToTime(currentWeather.getTime()));
        tempTextView.setText(String.valueOf(Math.round(TempConversion.tempConversion(currentWeather.getTemperature()))) + "/" + Math.round(TempConversion.tempConversion(currentWeather.getApparentTemperature())));
        summaryTv.setText(currentWeather.getSummary());
        weatherIcon.setImageResource(weatherIconMap.get(currentWeather.getIcon()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent errorEvent) {
        Toast.makeText(getActivity(), errorEvent.getErrorMsg(), Toast.LENGTH_SHORT).show();

    }
}


