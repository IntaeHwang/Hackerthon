package com.example.hackerthon;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

public class ApplicationClass extends Application {

    MySharedPref mySharedPref = MySharedPref.getInstance(this);


    //토스트메세지
    public void makeToast(String strData){
        Toast.makeText(this, strData, Toast.LENGTH_SHORT).show();
    }

    //파이어베이스 users 데이터 key 값 저장할때 . 안되서 , 로 바꿔서 저장함
    public String EncodeString(String string) {
        return string.replace(".", ",");
    }

    //key값 불러올때는 , -> . 로 변환해야함
    public String DecodeString(String string) {
        return string.replace(",", ".");
    }
}
