package com.example.externkoutsodimakis.darksky.Utils;


import com.example.externkoutsodimakis.darksky.MainActivity;
import com.example.externkoutsodimakis.darksky.Services.HourlyServiceProvider;
import com.example.externkoutsodimakis.darksky.Services.WeatherServiceProvider;

import java.security.PublicKey;

public class COMMON {
    private static String API_KEY = "7671f0f40e2a54a2b8379a2d35ceeaef/";
    public static String BASE_URL = "https://api.darksky.net/forecast/"+ API_KEY;
    public static final String MAIN_ACTIVITY_TAG = MainActivity.class.getSimpleName();
    public static final String WEATHER_PROVIDER_TAG = WeatherServiceProvider.class.getSimpleName();
    public static final String HOURLY_PROVIDER_TAG = HourlyServiceProvider.class.getSimpleName();
    public static final String ERROR_EVENT = "Unable to get weather information";
    public static final String CELCIUS =   "\u2103" ;
    public static final String FAHRENEIT =    "\u2109";
    public static final String DEGREE =  "\u00B0";
    public static final String PERCENT_SIGN= "\u0025";
    public static final String SPACE = " ";
}
