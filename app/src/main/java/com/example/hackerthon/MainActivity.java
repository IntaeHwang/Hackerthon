package com.example.hackerthon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    String adminId = "김청아";
    int adminAge = 27;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeLog(new Object() {}.getClass().getEnclosingMethod().getName()+"()", "관리자 이름 : "+adminId);
        makeLog(new Object() {}.getClass().getEnclosingMethod().getName()+"()", "관리자 나이 : "+adminAge);

        makeToast("로그인되었습니다.", SHORT_TOAST);
        makeToast("로그인되었습니다.", LONG_TOAST);

    }
}
