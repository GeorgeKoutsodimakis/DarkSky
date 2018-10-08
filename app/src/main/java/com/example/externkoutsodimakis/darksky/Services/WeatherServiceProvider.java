package com.example.externkoutsodimakis.darksky.Services;

import android.util.Log;

import com.example.externkoutsodimakis.darksky.Utils.Constants;
import com.example.externkoutsodimakis.darksky.Model.Currently;
import com.example.externkoutsodimakis.darksky.Model.Weather;
import com.example.externkoutsodimakis.darksky.events.ErrorEvent;
import com.example.externkoutsodimakis.darksky.events.WeatherEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.externkoutsodimakis.darksky.Utils.Constants.WEATHER_PROVIDER_TAG;

public class WeatherServiceProvider {
    private Retrofit retrofit;

    private Retrofit getRetrofit() {
        if (this.retrofit == null) {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
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
                    Currently currently = weather.getCurrently();
                    Log.d(WEATHER_PROVIDER_TAG, "TEMPERATURE" + currently.getTemperature());
                    EventBus.getDefault().post(new WeatherEvent(weather));
                }else {
                    EventBus.getDefault().post(new ErrorEvent(Constants.ERROR_EVENT));
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d(WEATHER_PROVIDER_TAG, "UNABLE TO GET WEATHER DATA");
                EventBus.getDefault().post(new ErrorEvent(Constants.ERROR_EVENT));
            }
        });
    }
}
