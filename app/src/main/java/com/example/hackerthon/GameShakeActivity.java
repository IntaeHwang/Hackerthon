package com.example.hackerthon;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/** GameShakeActivity
 * 1. 센서 이벤트
 * 2. 시간 측정 및 프로그레스바
 *
 * */
public class GameShakeActivity extends BaseActivity implements SensorEventListener {

    @BindView(R.id.textView_GameShakeActivity_time)
    TextView textViewGameShakeActivityTime;
    @BindView(R.id.textView_GameShakeActivity_score)
    TextView textViewGameShakeActivityScore;
    @BindView(R.id.progressbar_GameShakeActivity_progressbar)
    ProgressBar progressbarGameShakeActivityProgressbar;

    /** 센서 이벤트
     * - 센서이벤트의 센서 감도는 휴대폰의 속도와 측정 시간에 따라 결정된다.
     * - 그래서 변수가 거리, 시간, 속도를 담는 변수가 나오게 됨.
     */

    public int countNum = 0; //센서를 통해 카운팅 된 수


    // 시간
    private long lastTime;

// 거리
// (현재 휴대폰의 좌표 - 최근 저장된 휴대폰의 좌표)를 통해 거리차를 구할 수 있다.

    // 현재 휴대폰의 좌표
    private float x, y, z;
    // 최근 저장된 휴대폰의 좌표
    private float lastX, lastY, lastZ;

    // 휴대폰의 위치에 대한 좌표 상수 DATA_X = 1, DATA_Y = 2 이런 식
    private int DATA_X = SensorManager.DATA_X;
    private int DATA_Y = SensorManager.DATA_Y;
    private int DATA_Z = SensorManager.DATA_Z;

// 속도

    /* SHAKE_THRESHOLD: 휴대폰이 움직이는 속도와 비교하기 위한 기준값
     * - 숫자값이 클수록 센서가 둔감하게 반응함.
     * - 그 이유는 (speed > SHAKE_THRESHOLD) 인 경우에만 카운팅 되기 때문
     */
    private float speed;
    private int SHAKE_THRESHOLD = 600;


    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;

    /**
     * 시간 쓰레드
     */
    private Thread timeThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_shake);
        ButterKnife.bind(this);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // 쓰레드를 상속받아서 생성 및 실행.
        timeThread = new GameThread(handler, textViewGameShakeActivityTime, progressbarGameShakeActivityProgressbar);
        timeThread.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (accelerormeterSensor != null)
            sensorManager.registerListener(this, accelerormeterSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();

            long gabOfTime = (currentTime - lastTime);

/**조건절의 목적
 * - 센서의 측정 시간이 0.2초 이상일 경우 측정
 * - 센서 측정이 시간이 낮을 수록 센서에 측정되는 수가 높아짐
 * */
            if (gabOfTime > 200) {
                lastTime = currentTime;

//
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000; // Math.abs : 절대값 구하기

                if (speed > SHAKE_THRESHOLD) {
                    textViewGameShakeActivityScore.setText("" + (++countNum));
                }

                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    /**주석 처리한 이유
     * - 핸들러 사용을 메세지 객체로 이용했는데, Runnable 객체*/
// @SuppressLint("HandlerLeak")
// Handler handlerTime = new Handler() {
// @Override
// public void handleMessage(Message msg) {
// int time = msg.arg1;
//
// /**시간계산*/
// int millisecond = time % 100;
// int sec = time / 100;
// String result = String.format("%02d:%02d", sec, millisecond);
// textViewGameShakeActivityTime.setText(result);
// progressbarGameShakeActivityProgressbar.setProgress(time);
//
// if (sec >= 10) {
// isRunning = false;
// textViewGameShakeActivityTime.setText("10:00");
//// sensorManager.unregisterListener(this);
// }
// }
// };
//
// public class TimeThread implements Runnable {
// @Override
// public void run() {
// int i = 0;
// while (true) {
// while (isRunning) {
// Message msg = new Message();
// msg.arg1 = i++;
// handlerTime.sendMessage(msg);
// try {
// Thread.sleep(10); // 1000이 1초
// } catch (InterruptedException e) {
// e.printStackTrace();
// return;
// }
// }
// }
// }
// }

// class BackThread extends Thread{
// @Override
// public void run() {
// while(true){
// while (isRunning) {
// countTimeValue++;
// // 메인스레드에 있던 handler겍체를 사용하여
// // Runnable 객체를 보내고 (post)
// handler.post(new Runnable(){
// @Override
// public void run() { // Runnable 의 Run() 메소드에서 UI 접근
// int time = countTimeValue;
//
// /**시간계산*/
// int millisecond = time % 100;
// int sec = time / 100;
// String result = String.format("%02d:%02d", sec, millisecond);
// textViewGameShakeActivityTime.setText(result);
// progressbarGameShakeActivityProgressbar.setProgress(time);
//
// if (sec >= 10) {
// isRunning = false;
// textViewGameShakeActivityTime.setText("10:00");
// }
// }
// });
// try {
// Thread.sleep(10); // 1000이 1초
// } catch (InterruptedException e) {
// e.printStackTrace();
// return;
// }
// }
// }
// }
// }


    Handler handler = new Handler();

}