package com.example.hackerthon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    ApplicationClass applicationClass;

    public static final int SHORT_TOAST = 0;
    public static final int LONG_TOAST = 1;

    private static final String TAG = "DeveloperLog";
    String className = getClass().getSimpleName().trim();
//    String methodName = new Object() {}.getClass().getEnclosingMethod().getName()+"()";

    //로그 : 액티비티명 + 함수명 + 원하는 데이터를 한번에 보기위한 로그
    public void makeLog(String methodData, String strData){
        Log.d(TAG, className+"_"+methodData+"_"+strData);

    }

    //토스트메세지 : 귀찮음을 없애기 위해 토스트를 띄우는 함수를 만듦
    public void makeToast(String str, int length){
        if(length == SHORT_TOAST) {
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Log.d(TAG, className+"_onCreate()");

        applicationClass = (ApplicationClass)getApplicationContext();

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, className+"_onStart()");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, className+"-onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, className+"_onPause()");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, className+"_onStop()");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, className+"_onRestart()");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, className+"_onDestroy()");
        super.onDestroy();
    }

}
