package com.example.externkoutsodimakis.darksky.Requests;

import com.example.externkoutsodimakis.darksky.Services.WeatherServiceProvider;

public class RequestHourly {
    public static void requestHourlyWeather(double lat, double log) {
        WeatherServiceProvider weatherServiceProvider = new WeatherServiceProvider();
        weatherServiceProvider.getWeather(lat, log);
    }
}
