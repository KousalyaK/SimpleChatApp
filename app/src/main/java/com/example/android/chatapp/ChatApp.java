package com.example.android.chatapp;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by anjana on 10/9/15.
 */
public class ChatApp extends Application {

    @Override
    public void onCreate() {

        Parse.initialize(this, "urgYOlDnpq7A6dUaadzErbQuESM62H1tbKmphDhD", "hjB2RihMRp1LahDrXbqsnhvTNoHDrk3EyUeB60vd");
    }
}
