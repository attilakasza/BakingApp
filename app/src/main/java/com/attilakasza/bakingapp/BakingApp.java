package com.attilakasza.bakingapp;

import android.app.Application;

import com.attilakasza.bakingapp.helpers.ConnectivityReceiver;

public class BakingApp extends Application {

    private static BakingApp mInstance;

    public static synchronized BakingApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}