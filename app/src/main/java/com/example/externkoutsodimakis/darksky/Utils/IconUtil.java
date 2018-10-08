package com.example.externkoutsodimakis.darksky.Utils;

import com.example.externkoutsodimakis.darksky.R;

import java.util.HashMap;
import java.util.Map;

public class IconUtil {

 public static final Map<String, Integer> weatherIconMap = new HashMap<>();

 static {
     weatherIconMap.put("clear-day", R.drawable.ic_clear_day);
     weatherIconMap.put("clear-night", R.drawable.ic_clear_night);
     weatherIconMap.put("rain", R.drawable.ic_rain);
     weatherIconMap.put("snow", R.drawable.ic_snow);
     weatherIconMap.put("sleet", R.drawable.ic_sleet);
     weatherIconMap.put("wind", R.drawable.ic_wind);
     weatherIconMap.put("fog", R.drawable.ic_fog);
     weatherIconMap.put("cloudy", R.drawable.ic_cloudy);
     weatherIconMap.put("partly-cloudy-day", R.drawable.ic_partly_cloudy_day);
     weatherIconMap.put("partly-cloudy-night", R.drawable.ic_partly_cloudy_night);
     weatherIconMap.put("thunderstorm", R.drawable.ic_thunderstorm);


 }

}
