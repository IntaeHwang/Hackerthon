package com.example.hackerthon;

import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    String adminId = "김청아";
    int adminAge = 27;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        makeLog(new Object() {
        }.getClass().getEnclosingMethod().getName() + "()", "관리자 이름 : " + adminId);
        makeLog(new Object() {
        }.getClass().getEnclosingMethod().getName() + "()", "관리자 나이 : " + adminAge);



        makeToast("로그인되었습니다.", SHORT_TOAST);
        makeToast("로그인되었습니다.", LONG_TOAST);

    }

}
