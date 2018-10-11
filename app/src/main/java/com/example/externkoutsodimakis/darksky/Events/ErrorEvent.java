package com.example.externkoutsodimakis.darksky.Events;

public class ErrorEvent {

    String errorMsg;

    public ErrorEvent(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
