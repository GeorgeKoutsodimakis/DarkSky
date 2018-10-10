package com.example.externkoutsodimakis.darksky.Utils;

public class TempConversion {

    public static final Double tempConversion(double fahreneightTemp) {

        double celcius;

        celcius = (fahreneightTemp - 32) * 5 / 9;

        return celcius;
    }
}
