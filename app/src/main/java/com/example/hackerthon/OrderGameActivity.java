package com.example.hackerthon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderGameActivity extends BaseActivity {

    @BindView(R.id.progressBar_ameRPSActivity_timeBar)
    ProgressBar progressBar_ameRPSActivity_timeBar; //프로그래스바
    @BindView(R.id.textView_ameRPSActivity_time)
    TextView textView_ameRPSActivity_time; //프로그래스바 시간

    @BindView(R.id.textView_activity_taptap_userScore)
    TextView textViewActivityTaptapUserScore; //실제 스코어
    @BindView(R.id.textView_activity_taptap_scoreText)
    TextView textViewActivityTaptapScoreText; // 데코용 텍스트 "score"

    //버튼 20개..
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


    Handler handler;
    int count = 1; // 버튼 클릭 시 값 증가하는 변수
    int score = 0; // 게임점수
    Thread ordergameThread;
    String roomNumberKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_game);
        ButterKnife.bind(this);

        //인텐트로 받아온 roomNumberKey >> DB 경로에 사용
        Intent intent = getIntent();
        roomNumberKey = intent.getStringExtra("roomNumberKey");
        makeLog(new Object() {
        }.getClass().getEnclosingMethod().getName() + "()", "roomNumberKey : " + roomNumberKey);


        //baseActivity에 선언된 쓰레드 사용을 위해 핸들러 선언 및 생성자에 핸들러,텍스트뷰, 프로그래스바 추가
        //수정 : 핸들러 자신에게 메세지를 보내서 10초뒤 score의 값을 가져온다
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        makeToast("실행", SHORT_TOAST);
                        Log.d("ㅂㅂ", "GameShakeActivity - handleMessage() | 메시지 수신 :" );
                        Log.d("ㅂㅂ", "GameShakeActivity - handleMessage() | 스코어 점수: :"+score ); // 값 확인

                        //DB에 USER데이터 추가하기
                                Player player = new Player(applicationClass.currentUserEmailKey, applicationClass.currentUserName, score, 0);
                                applicationClass.databaseReference.child("PLAYERLIST").child(roomNumberKey).child(applicationClass.currentUserEmailKey).setValue(player);

                        Intent intent = new Intent(getApplicationContext(),ScoreExampleActivity.class);
                        intent.putExtra("qq",score);
                        startActivity(intent);

                        break;
                    default:
                        break;
                }
            }
        };
        ordergameThread= new GameThread(handler,textView_ameRPSActivity_time,progressBar_ameRPSActivity_timeBar);
        ordergameThread.start();

    }//OnCreate

    @Override
    protected void onStart() {
        super.onStart();

        //재시작마다 랜덤함수 생성하기
        buttonValueSetting();

    }

    //숫자버튼 클릭시
    public void onClick(View view) {
        check(view.getId()); //클릭된 버튼의 int형 id를 받아오기
    }


    public void check(int id) {

        Button coverButton = (Button) findViewById(id); //임의의 버튼객체를 하나 만들어
        int buttonValue = Integer.parseInt((String) coverButton.getText()); // 클릭시 해당버튼의 숫자값 잘 나옴
        makeLog(new Object() {
        }.getClass().getEnclosingMethod().getName() + "()", "buttonValue : " + buttonValue);

            if (count == buttonValue) {
                //순서대로 맞게 눌렀으면 안보이고 텍스트만큼 점수에 추가
                coverButton.setVisibility(View.INVISIBLE);
//                coverButton.setBackgroundColor(Color.rgb(178, 34, 34));
                score = score+buttonValue;
                textViewActivityTaptapUserScore.setText(""+score);
                count ++;
            }else {
                //순서에 맞지 않게 누르면 -10점
                score = score-10;
                textViewActivityTaptapUserScore.setText(""+score);
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