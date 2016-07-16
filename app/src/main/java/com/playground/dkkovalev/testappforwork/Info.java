package com.playground.dkkovalev.testappforwork;

/**
 * Created by DKKovalev on 16.07.2016.
 */
public class Info {

    private String imei;
    private String message;

    public String getUuid() {
        return imei;
    }

    public void setUuid(String uuid) {
        this.imei = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
