package com.example.hackerthon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TapTapActivity extends BaseActivity {

    @BindView(R.id.progressBar_activity_taptap_timeBar)
    ProgressBar progressBarActivityTaptapTimeBar; //프로그래스바
    @BindView(R.id.textView_activity_taptap_time)
    TextView textViewActivityTaptapTime; // 시간초
    @BindView(R.id.textView_activity_taptap_userScore)
    TextView textViewActivityTaptapUserScore; // 실제 게임값 들어가는 텍스트뷰
    @BindView(R.id.button_activity_order_game_roundTouchButton)
    Button buttonActivityOrderGameRoundTouchButton; //사용하지않는 버튼
    @BindView(R.id.button_activity_taptap_realRoundTouchButton)
    Button buttonActivityTaptapRealRoundTouchButton; //클릭계속하는 버튼

    int count = 0; //게임 초기값
    String roomNumberKey;
    /**
     * 시간 쓰레드
     */
    private Thread timeThread;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_tap);
        ButterKnife.bind(this);

        //인텐트로 받아온 roomNumberKey >> DB 경로에 사용
        Intent intent = getIntent();
        roomNumberKey = intent.getStringExtra("roomNumberKey");
        makeLog(new Object() {
        }.getClass().getEnclosingMethod().getName() + "()", "roomNumberKey : " + roomNumberKey);
// 스레드 시작
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        makeToast("실행", SHORT_TOAST);
                        Log.d("ㅂㅂ", "GameShakeActivity - handleMessage() | 메시지 수신 :" );
                        Log.d("ㅂㅂ", "GameShakeActivity - handleMessage() | 스코어 점수: :"+count ); // 값 확인

                        //DB에 USER데이터 추가하기
                        Player player = new Player(applicationClass.currentUserEmailKey, applicationClass.currentUserName, count, 0);
                        applicationClass.databaseReference.child("PLAYERLIST").child(roomNumberKey).child(applicationClass.currentUserEmailKey).setValue(player);

                        Intent intent = new Intent(getApplicationContext(),ScoreExampleActivity.class);
                        intent.putExtra("qq",count);
                        startActivity(intent);

                        break;
                    default:
                        break;
                }
            }
        };
        timeThread = new GameThread(handler, textViewActivityTaptapTime, progressBarActivityTaptapTimeBar);
        timeThread.start();
    }//OnCreate


    @OnClick(R.id.button_activity_taptap_realRoundTouchButton)
    public void onButtonActivityTaptapRealRoundTouchButtonClicked() {
        if (count >= 0) {
            count++;
            textViewActivityTaptapUserScore.setText("" + count);

        }

//프로그래스바 10초 종료됬을때 유저 데이터의 gamescore에 추가하기

    }

//    public void addGameScore() {
//        String userKey = applicationClass.currentUserEmailKey; //유저의 키값
////DB에 USER데이터 추가하기
//        DatabaseReference myRef = applicationClass.databaseReference.child("USER").child(userKey);
//        User user = new User();
//        user.setGameScore(44); // 44에 변수값 넣기
//        makeLog(new Object() {
//        }.getClass().getEnclosingMethod().getName() + "()", " :user " + user);
//        myRef.setValue(user.toUserMap(user)); // 데이터베이스에 추가
//
//    }


}//class
