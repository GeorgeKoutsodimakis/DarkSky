package com.example.externkoutsodimakis.darksky.Utils;

import com.example.externkoutsodimakis.darksky.R;

import java.util.HashMap;
import java.util.Map;

public class UvIndexConversion {

    public static final Map<Integer, String> uvIndexToText = new HashMap<>();

    static {
        uvIndexToText.put(0, "Low");
        uvIndexToText.put(1, "Low");
        uvIndexToText.put(2, "Low");
        uvIndexToText.put(3, "Moderate");
        uvIndexToText.put(4, "Moderate");
        uvIndexToText.put(5, "Moderate");
        uvIndexToText.put(6, "High");
        uvIndexToText.put(7, "High");
        uvIndexToText.put(8, "Very High");
        uvIndexToText.put(9, "Very High");
        uvIndexToText.put(10, "Very High");
        uvIndexToText.put(11, "Extreme");


    }
}
