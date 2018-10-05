package com.example.externkoutsodimakis.darksky;


import com.example.externkoutsodimakis.darksky.Services.WeatherServiceProvider;

public class CONSTANTS {
    private static String API_KEY = "7671f0f40e2a54a2b8379a2d35ceeaef/";
    public static String BASE_URL = "https://api.darksky.net/forecast/"+ API_KEY;
    public static final String MAIN_ACTIVITY_TAG = MainActivity.class.getSimpleName();
    public static final String WEATHER_PROVIDER_TAG = WeatherServiceProvider.class.getSimpleName();
}
