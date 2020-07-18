package com.example.hackerthon;

import android.app.Application;

public class ApplicationClass extends Application {

    MySharedPref mySharedPref = MySharedPref.getInstance(this);
}
