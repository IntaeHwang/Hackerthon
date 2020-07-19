package com.example.hackerthon;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TapTapActivity extends BaseActivity {

    @BindView(R.id.progressBar_activity_taptap_timeBar)
    ProgressBar progressBarActivityTaptapTimeBar; //프로그래스바
    @BindView(R.id.textView_activity_taptap_userScore)
    TextView textViewActivityTaptapUserScore; // 실제 게임값 들어가는 텍스트뷰
    @BindView(R.id.button_activity_order_game_roundTouchButton)
    Button buttonActivityOrderGameRoundTouchButton; //사용하지않는 버튼
    @BindView(R.id.button_activity_taptap_realRoundTouchButton)
    Button buttonActivityTaptapRealRoundTouchButton; //클릭계속하는 버튼

    int count = 0; //게임 초기값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_tap);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.button_activity_taptap_realRoundTouchButton)
    public void onButtonActivityTaptapRealRoundTouchButtonClicked() {
        if(count%2==0){
            textViewActivityTaptapUserScore.setText(""+count);
            count++;

        }else if(count%2==1){
            textViewActivityTaptapUserScore.setText(""+count);
            count++;
        }

    }
}