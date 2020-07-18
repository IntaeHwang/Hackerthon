package com.example.hackerthon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    ApplicationClass applicationClass;
    private static final String TAG = "DeveloperLog";
    String classname = getClass().getSimpleName().trim();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        applicationClass = (ApplicationClass)getApplicationContext();


    }

}
