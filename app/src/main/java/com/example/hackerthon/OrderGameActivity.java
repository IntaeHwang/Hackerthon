package com.example.hackerthon;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderGameActivity extends BaseActivity {

    @BindView(R.id.progressBar_activity_taptap_timeBar)
    ProgressBar progressBarActivityTaptapTimeBar;
    @BindView(R.id.textView_activity_taptap_userScore)
    TextView textViewActivityTaptapUserScore;
    @BindView(R.id.textView_activity_taptap_scoreText)
    TextView textViewActivityTaptapScoreText;
    @BindView(R.id.button_activity_order_game_roundTouchButton)
    Button buttonActivityOrderGameRoundTouchButton;
    @BindView(R.id.button_activity_order_game_roundTouchButton2)
    Button buttonActivityOrderGameRoundTouchButton2;
    @BindView(R.id.button_activity_order_game_roundTouchButton4)
    Button buttonActivityOrderGameRoundTouchButton4;
    @BindView(R.id.button_activity_order_game_roundTouchButton8)
    Button buttonActivityOrderGameRoundTouchButton8;
    @BindView(R.id.button_activity_order_game_roundTouchButton12)
    Button buttonActivityOrderGameRoundTouchButton12;
    @BindView(R.id.button_activity_order_game_roundTouchButton16)
    Button buttonActivityOrderGameRoundTouchButton16;
    @BindView(R.id.button_activity_order_game_roundTouchButton20)
    Button buttonActivityOrderGameRoundTouchButton20;
    @BindView(R.id.button_activity_order_game_roundTouchButton19)
    Button buttonActivityOrderGameRoundTouchButton19;
    @BindView(R.id.button_activity_order_game_roundTouchButton18)
    Button buttonActivityOrderGameRoundTouchButton18;
    @BindView(R.id.button_activity_order_game_roundTouchButton17)
    Button buttonActivityOrderGameRoundTouchButton17;
    @BindView(R.id.button_activity_order_game_roundTouchButton14)
    Button buttonActivityOrderGameRoundTouchButton14;
    @BindView(R.id.button_activity_order_game_roundTouchButton13)
    Button buttonActivityOrderGameRoundTouchButton13;
    @BindView(R.id.button_activity_order_game_roundTouchButton15)
    Button buttonActivityOrderGameRoundTouchButton15;
    @BindView(R.id.button_activity_order_game_roundTouchButton9)
    Button buttonActivityOrderGameRoundTouchButton9;
    @BindView(R.id.button_activity_order_game_roundTouchButton10)
    Button buttonActivityOrderGameRoundTouchButton10;
    @BindView(R.id.button_activity_order_game_roundTouchButton11)
    Button buttonActivityOrderGameRoundTouchButton11;
    @BindView(R.id.button_activity_order_game_roundTouchButton7)
    Button buttonActivityOrderGameRoundTouchButton7;
    @BindView(R.id.button_activity_order_game_roundTouchButton6)
    Button buttonActivityOrderGameRoundTouchButton6;
    @BindView(R.id.button_activity_order_game_roundTouchButton5)
    Button buttonActivityOrderGameRoundTouchButton5;
    @BindView(R.id.button_activity_order_game_roundTouchButton3)
    Button buttonActivityOrderGameRoundTouchButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_game);
        ButterKnife.bind(this);


    }//OnCreate

    @Override
    protected void onStart() {
        super.onStart();

        //재시작마다 랜덤함수 생성하기
        buttonValueSetting();
    }

    public void onClick(View view) {
        check(view.getId()); //클릭된 버튼의 int형 id를 받아오기
        if(view.getId() == R.id.button_activity_order_game_roundTouchButton){
            makeToast("버튼 1클릭"+view.getId(),SHORT_TOAST);
        }else if(view.getId() == R.id.button_activity_order_game_roundTouchButton2){
            makeToast("버튼 2클릭"+view.getId(),SHORT_TOAST);
        }
    }


    public void check(int id) {
        int count=0;
        Button tmp = (Button) findViewById(id); //임의의 버튼객체를 하나 만들어
        int buttonValue = Integer.parseInt((String) tmp.getText());

        if(count == buttonValue){
            tmp.setBackgroundColor(Color.rgb(178,34,34));
            count++;
        }
    }


    //버튼에 랜덤함수값생성
    public void buttonValueSetting() {
        int array[] = new int[20];

        for (int i = 0; i < 20; i++) {
            double randomvalue = Math.random(); //랜덤함수는 더블형이기떄문에 인트로 초기화 해줘야댐
            int intValue = (int) (randomvalue * 20) + 1; // 1~20으로 초기화
            array[i] = intValue; //i에 랜덤값 넣기
            for (int j = 0; j < i; j++) {
                if (array[i] == array[j]) {
                    i--;
                    break;
                }
            }
        }
        buttonActivityOrderGameRoundTouchButton.setText(""+array[0]);
        buttonActivityOrderGameRoundTouchButton2.setText(""+array[1]);
        buttonActivityOrderGameRoundTouchButton3.setText(""+array[2]);
        buttonActivityOrderGameRoundTouchButton4.setText(""+array[3]);
        buttonActivityOrderGameRoundTouchButton5.setText(""+array[4]);
        buttonActivityOrderGameRoundTouchButton6.setText(""+array[5]);
        buttonActivityOrderGameRoundTouchButton7.setText(""+array[6]);
        buttonActivityOrderGameRoundTouchButton8.setText(""+array[7]);
        buttonActivityOrderGameRoundTouchButton9.setText(""+array[8]);
        buttonActivityOrderGameRoundTouchButton10.setText(""+array[9]);
        buttonActivityOrderGameRoundTouchButton11.setText(""+array[10]);
        buttonActivityOrderGameRoundTouchButton12.setText(""+array[11]);
        buttonActivityOrderGameRoundTouchButton13.setText(""+array[12]);
        buttonActivityOrderGameRoundTouchButton14.setText(""+array[13]);
        buttonActivityOrderGameRoundTouchButton15.setText(""+array[14]);
        buttonActivityOrderGameRoundTouchButton16.setText(""+array[15]);
        buttonActivityOrderGameRoundTouchButton17.setText(""+array[16]);
        buttonActivityOrderGameRoundTouchButton18.setText(""+array[17]);
        buttonActivityOrderGameRoundTouchButton19.setText(""+array[18]);
        buttonActivityOrderGameRoundTouchButton20.setText(""+array[19]);


    }


}//class