package com.example.externkoutsodimakis.darksky.events;

import com.example.externkoutsodimakis.darksky.Model.Weather;

public class ErrorEvent {

    String errorMsg;

    public ErrorEvent(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
