package com.example.hackerthon;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 가위 바위 보 게임
 */
public class GameSRPActivity extends BaseActivity {

    private final String tag = "로그";


    @BindView(R.id.imageView_gameRPSActivity_opponentFirst)
    ImageView imageViewGameRPSActivityOpponentFirst;
    @BindView(R.id.imageView_gameRPSActivity_opponentSecond)
    ImageView imageViewGameRPSActivityOpponentSecond;
    @BindView(R.id.imageView_gameRPSActivity_opponentThird)
    ImageView imageViewGameRPSActivityOpponentThird;

    @BindView(R.id.imageView_gameRPSActivity_scissors)
    ImageButton imageViewGameRPSActivityScissors;
    @BindView(R.id.imageView_gameRPSActivity_rock)
    ImageButton imageViewGameRPSActivityRock;
    @BindView(R.id.imageView_gameRPSActivity_paper)
    ImageButton imageViewGameRPSActivityPaper;
    @BindView(R.id.progressBar_ameRPSActivity_timeBar)
    ProgressBar progressBarAmeRPSActivityTimeBar;
    @BindView(R.id.textView_ameRPSActivity_userScore)
    TextView textViewAmeRPSActivityUserScore;
    @BindView(R.id.textView_ameRPSActivity_time)
    TextView textViewAmeRPSActivityTime;

    // 가위, 바위, 보를 상수를 정해놓고 사용해서 코드를 보다 쉽게 이해 할 수 있도록 함.
    private int SCISSORS = 1;
    private int ROCK = 2;
    private int PAPER = 3;

    // 컴퓨터가 가질 가위 바위 보 리스트 값
    ArrayList<Integer> arrayList;

    // 사용자의 점수
    private int myScore;

    /**
     * 시간 쓰레드
     */
    private Thread timeThread;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_s_r_p);
        ButterKnife.bind(this);

        // 변수 초기화
        myScore = 0;
        arrayList = new ArrayList<>();
        handler = new Handler();

        // 랜덤 난수를 사용하기 위한 Random() 객체 생성
        Random rnd = new Random();

        // 컴퓨터가 가질 가위,바위,보 난수값 리스트에 넣기
        for (int i = 0; i < 100; i++) {
            int randomValue = rnd.nextInt(3) + 1; // 1~3 난수 생성
            arrayList.add(randomValue);
        }

        // 이미지 상태 초기화 세팅
        showOppoenet(arrayList.get(0), arrayList.get(1), arrayList.get(2));

        // 쓰레드를 상속받아서 생성 및 실행.
        timeThread = new GameThread(handler, textViewAmeRPSActivityTime, progressBarAmeRPSActivityTimeBar);
        timeThread.start();
    }

    // 사용자가 가위바위보 선택지를 클릭했을 때 이벤트 발생
    // calculateScore(사용자 선택, 리스트의 값): 인자값을 통해 승패를 판단하고 점수 반영
    // showOppoenet(첫번째 가위바위보 Icon, 2번째, 3번째)를 인자값으로 넘겨 Icon을 갱신하기 위함.
    @OnClick({R.id.imageView_gameRPSActivity_scissors, R.id.imageView_gameRPSActivity_rock, R.id.imageView_gameRPSActivity_paper})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_gameRPSActivity_scissors:
                if (arrayList.size() > 1) {
                    calculateScore(SCISSORS, arrayList.get(0));
                    arrayList.remove(0);
                    showOppoenet(arrayList.get(0), arrayList.get(1), arrayList.get(2));
                }
                break;
            case R.id.imageView_gameRPSActivity_rock:
                if (arrayList.size() > 1) {
                    calculateScore(ROCK, arrayList.get(0));
                    arrayList.remove(0);
                    showOppoenet(arrayList.get(0), arrayList.get(1), arrayList.get(2));
                }
                break;
            case R.id.imageView_gameRPSActivity_paper:
                if (arrayList.size() > 1) {
                    calculateScore(PAPER, arrayList.get(0));
                    arrayList.remove(0);
                    showOppoenet(arrayList.get(0), arrayList.get(1), arrayList.get(2));
                }
                break;
        }
    }

    // 리스트의 값에 따라 이미지를 세팅해주기 위함.
    private void showOppoenet(int opponentPickFirst, int opponentPickSecond, int opponentPickThird) {
        switch (opponentPickFirst) {
            case 1:
                opponentPickFirst = SCISSORS;
                imageViewGameRPSActivityOpponentFirst.setImageResource(R.drawable.scissors_hand);
                break;
            case 2:
                opponentPickFirst = ROCK;
                imageViewGameRPSActivityOpponentFirst.setImageResource(R.drawable.rock_hand);
                break;
            case 3:
                opponentPickFirst = PAPER;
                imageViewGameRPSActivityOpponentFirst.setImageResource(R.drawable.paper_hand);
                break;
        }
        switch (opponentPickSecond) {
            case 1:
                opponentPickSecond = SCISSORS;
                imageViewGameRPSActivityOpponentSecond.setImageResource(R.drawable.scissors_hand);
                break;
            case 2:
                opponentPickSecond = ROCK;
                imageViewGameRPSActivityOpponentSecond.setImageResource(R.drawable.rock_hand);
                break;
            case 3:
                opponentPickSecond = PAPER;
                imageViewGameRPSActivityOpponentSecond.setImageResource(R.drawable.paper_hand);
                break;
        }
        switch (opponentPickThird) {
            case 1:
                opponentPickThird = SCISSORS;
                imageViewGameRPSActivityOpponentThird.setImageResource(R.drawable.scissors_hand);
                break;
            case 2:
                opponentPickThird = ROCK;
                imageViewGameRPSActivityOpponentThird.setImageResource(R.drawable.rock_hand);
                break;
            case 3:
                opponentPickThird = PAPER;
                imageViewGameRPSActivityOpponentThird.setImageResource(R.drawable.paper_hand);
                break;
        }
    }

    // 값을 계산해주기 위한 메서드
    private void calculateScore(int myChoice, int opponentPick) {
        Log.d(tag, "MainActivity - calculateScore() | myChoice :" + myChoice);
        Log.d(tag, "MainActivity - calculateScore() | opponentPick :" + opponentPick);
        if (myChoice != opponentPick) {
            if (myChoice == SCISSORS) {
                Log.d(tag, "MainActivity - calculateScore() | 가위 :");
                switch (opponentPick) {
                    case 1:
                        Log.d(tag, "MainActivity - calculateScore() | 상대가 가위 :");
                        break;
                    case 2:
                        Log.d(tag, "MainActivity - calculateScore() | 상대가 주먹 :");
                        myScore -= 2;
                        break;
                    case 3:
                        Log.d(tag, "MainActivity - calculateScore() | 상대가 보 :");
                        myScore += 1;
                        break;
                }
            } else if (myChoice == ROCK) {
                switch (opponentPick) {
                    case 1:
                        Log.d(tag, "MainActivity - calculateScore() | 상대가 가위 :");
                        myScore += 1;
                        break;
                    case 2:
                        Log.d(tag, "MainActivity - calculateScore() | 상대가 주먹 :");
                        break;
                    case 3:
                        Log.d(tag, "MainActivity - calculateScore() | 상대가 보 :");
                        myScore -= 2;
                        break;
                }
            } else if (myChoice == PAPER) {
                switch (opponentPick) {
                    case 1:
                        Log.d(tag, "MainActivity - calculateScore() | 상대가 가위 :");
                        myScore -= 2;
                        break;
                    case 2:
                        Log.d(tag, "MainActivity - calculateScore() | 상대가 주먹 :");
                        myScore += 1;
                        break;
                    case 3:
                        Log.d(tag, "MainActivity - calculateScore() | 상대가 보 :");
                        break;
                }
            }
            // 점수 TextView에 반영
            textViewAmeRPSActivityUserScore.setText("" + myScore);
        }
    }
}