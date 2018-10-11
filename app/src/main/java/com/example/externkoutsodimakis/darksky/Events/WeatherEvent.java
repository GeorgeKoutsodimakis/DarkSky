package com.example.externkoutsodimakis.darksky.Events;

import com.example.externkoutsodimakis.darksky.Model.Weather;

public class WeatherEvent {


    private final Weather weather;

    public WeatherEvent(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }

}
