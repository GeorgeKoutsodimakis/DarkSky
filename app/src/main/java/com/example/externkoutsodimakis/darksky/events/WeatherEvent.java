package com.example.externkoutsodimakis.darksky.events;

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
