package com.panatlanticdev.preschool;

/**
 * Created by acifuina on 24/11/15.
 */


import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;


public class UserInfo extends Application {


    private String seccionActual;


    public String getSeccionActual() {
        return seccionActual;
    }

    public void setSeccionActual(String seccion) {
        this.seccionActual = seccion;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "YwvDcEU3CPCHqxqs3uxfXbhiyvXX1aBf3zTOY730", "kzftvMchwaserexcLFZv3TPpUqbyLtWvZbZCNo3O");
        ParseInstallation.getCurrentInstallation().saveInBackground();


    }
}
