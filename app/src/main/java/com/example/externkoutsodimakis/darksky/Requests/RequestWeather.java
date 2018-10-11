package com.example.externkoutsodimakis.darksky.Requests;

import com.example.externkoutsodimakis.darksky.Services.WeatherServiceProvider;

public class RequestWeather {

    public static void requestCurrentWeather(double lat, double log) {
        WeatherServiceProvider weatherServiceProvider = new WeatherServiceProvider();
        weatherServiceProvider.getWeather(lat, log);
    }
}
