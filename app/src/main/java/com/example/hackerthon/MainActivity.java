package com.example.hackerthon;

import android.os.Bundle;
import android.widget.Button;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    String adminId = "김청아";
    int adminAge = 27;
    String userName = "고은찬";
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button)
    Button button1;
    @BindView(R.id.button3)
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        makeLog(new Object() {
        }.getClass().getEnclosingMethod().getName() + "()", "관리자 이름 : " + adminId);
        makeLog(new Object() {
        }.getClass().getEnclosingMethod().getName() + "()", "관리자 나이 : " + adminAge);

        makeLog(new Object() {}.getClass().getEnclosingMethod().getName()+"()", "userName : "+userName);

        makeLog(new Object() {
        }.getClass().getEnclosingMethod().getName() + "()", "userName : " + userName);


        makeLog(new Object() {
        }.getClass().getEnclosingMethod().getName() + "()", "");

        makeToast("로그인되었습니다.", SHORT_TOAST);
        makeToast("로그인되었습니다.", LONG_TOAST);

        User user = new User("intae", "qwer1234");
        HashMap<String, Object> userMap = user.toUserMap();

        applicationClass.databaseReference.child("test").setValue(userMap);

    }

    @OnClick(R.id.button2)
    public void onButton2Clicked() {
    }

    @OnClick(R.id.button)
    public void onButtonClicked() {
    }

    @OnClick(R.id.button3)
    public void onButton3Clicked() {
    }
}
