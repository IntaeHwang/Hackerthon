package com.example.hackerthon;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameFinishActivity extends BaseActivity {

    //게임 결과 화면을 종료하고 GameReady Activity로 넘어가기 위한 확인 버튼
    @BindView(R.id.button_GameFinishActivity_dismiss)
    Button buttonGameFinishActivityDismiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finish);
        ButterKnife.bind(this);



    }


    @OnClick(R.id.button_GameFinishActivity_dismiss)
    public void onViewClicked() {

    }
}