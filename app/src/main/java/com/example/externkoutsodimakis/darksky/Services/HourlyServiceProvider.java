package com.example.externkoutsodimakis.darksky.Services;

import android.util.Log;

import com.example.externkoutsodimakis.darksky.Events.ErrorEvent;
import com.example.externkoutsodimakis.darksky.Events.WeatherEvent;
import com.example.externkoutsodimakis.darksky.Model.Currently;
import com.example.externkoutsodimakis.darksky.Model.Hourly;
import com.example.externkoutsodimakis.darksky.Model.Weather;
import com.example.externkoutsodimakis.darksky.Utils.COMMON;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.externkoutsodimakis.darksky.Utils.COMMON.WEATHER_PROVIDER_TAG;

public class HourlyServiceProvider {
    private Retrofit retrofit;

    private Retrofit getRetrofit() {
        if (this.retrofit == null) {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(COMMON.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return this.retrofit;
    }

    public void getWeather(double lat, double log) {

        WeatherService weatherService = getRetrofit().create(WeatherService.class);
        Call<Weather> weatherData = weatherService.getWeather(lat, log);
        weatherData.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather = response.body();
                if (response != null) {
                    Hourly hourly = weather.getHourly();
                    Log.d(WEATHER_PROVIDER_TAG, "HOURLY"+ hourly.getData().get(0).getSummary());
                    EventBus.getDefault().post(new WeatherEvent(weather));
                }else {
                    EventBus.getDefault().post(new ErrorEvent(COMMON.ERROR_EVENT));
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d(WEATHER_PROVIDER_TAG, "UNABLE TO GET WEATHER DATA");
                EventBus.getDefault().post(new ErrorEvent(COMMON.ERROR_EVENT));
            }
        });
    }
}

