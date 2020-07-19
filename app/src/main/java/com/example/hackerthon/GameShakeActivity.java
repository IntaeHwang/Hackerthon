package com.example.hackerthon;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import butterknife.BindView;
import butterknife.ButterKnife;

public class GameShakeActivity extends AppCompatActivity implements SensorEventListener {

    @BindView(R.id.textView_GameShakeActivity_time)
    TextView textViewGameShakeActivityTime;
    @BindView(R.id.textView_GameShakeActivity_score)
    TextView textViewGameShakeActivityScore;

    /** 센서 이벤트*/
    public int countNum = 0;  // 카운트 되는 수


    /** 속도를 통해서 기준이 되는 속도(SHAKE_THRESHOLD) 보다 빠른 경우 숫자가 카우팅이 되기 위함*/
    private long lastTime;  //
    private float speed;

    // x-lastX(현재 휴대폰의 좌표 - 최근 저장된 휴대폰의 좌표)를 통해 거리를 구할 수 있다.

    // 현재 휴대폰의 좌표
    private float x, y, z;

    // 최근 저장된 휴대폰의 좌표
    private float lastX;
    private float lastY;
    private float lastZ;

    /** 숫자값이 클수록 센서가 둔감하게 반응함.
     *      - 그 이유는 (speed > SHAKE_THRESHOLD) 인 경우에만 카운팅 되기 때문
     * */
    private static final int SHAKE_THRESHOLD = 600;
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;

    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;

    /**
     * 시간 쓰레드
     */
    private Thread timeThread = null;
    private Boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_shake);
        ButterKnife.bind(this);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        timeThread = new Thread(new TimeThread());
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
             *      - 센서의 측정 시간이 0.2초 이상일 경우 측정
             *      - 센서 측정이 시간이 낮을 수록 센서에 측정되는 수가 높아짐
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

    @SuppressLint("HandlerLeak")
    Handler handlerTime = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int time = msg.arg1;

            /**시간계산*/
            int millisecond = time % 100;
            int sec = time / 100;
            String result = String.format("%02d:%02d",  sec, millisecond);
            textViewGameShakeActivityTime.setText(result);

            if(sec >= 10){
                isRunning = false;
                textViewGameShakeActivityTime.setText("10:00");
//                sensorManager.unregisterListener(this);
            }
        }
    };

    public class TimeThread implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                while (isRunning) {
                    Message msg = new Message();
                    msg.arg1 = i++;
                    handlerTime.sendMessage(msg);
                    try {
                        Thread.sleep(10); // 1000이 1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        }
    }

}