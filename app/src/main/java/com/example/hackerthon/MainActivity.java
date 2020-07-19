package com.example.hackerthon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.linearLayout_mainAcitivity_createRoom)
    Button linearLayoutMainAcitivityCreateRoom; //버튼 - 방만들기
    @BindView(R.id.linearLayout_mainAcitivity_searchRoom)
    Button linearLayoutMainAcitivitySearchRoom; //버튼 - 방찾기
    @BindView(R.id.linearLayout_mainAcitivity_myProfile)
    Button linearLayoutMainAcitivityMyProfile;  //버튼 - 내정보

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.linearLayout_mainAcitivity_createRoom, R.id.linearLayout_mainAcitivity_searchRoom, R.id.linearLayout_mainAcitivity_myProfile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linearLayout_mainAcitivity_createRoom:
                Intent roomCreateIntent = new Intent(this, RoomCreateActivity.class);
                startActivity(roomCreateIntent);
                break;
            case R.id.linearLayout_mainAcitivity_searchRoom:
                Intent roomSearchIntent = new Intent(this, RoomSearchActivity.class);
                startActivity(roomSearchIntent);
                break;
            case R.id.linearLayout_mainAcitivity_myProfile:
                break;
        }
    }
}
