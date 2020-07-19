package com.example.hackerthon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    ApplicationClass applicationClass;

    public static final int SHORT_TOAST = 0;
    public static final int LONG_TOAST = 1;

    private static final String TAG = "DeveloperLog";
    String className = getClass().getSimpleName().trim();
//    String methodName = new Object() {}.getClass().getEnclosingMethod().getName()+"()";

    //로그 : 액티비티명 + 함수명 + 원하는 데이터를 한번에 보기위한 로그
    public void makeLog(String methodData, String strData){
        Log.d(TAG, className+"_"+methodData+"_"+strData);

    }

    //토스트메세지 : 귀찮음을 없애기 위해 토스트를 띄우는 함수를 만듦
    public void makeToast(String str, int length){
        if(length == SHORT_TOAST) {
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Log.d(TAG, className+"_onCreate()");

        applicationClass = (ApplicationClass)getApplicationContext();

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, className+"_onStart()");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, className+"-onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, className+"_onPause()");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, className+"_onStop()");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, className+"_onRestart()");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, className+"_onDestroy()");
        super.onDestroy();
    }

    /** 쓰레드의 용도
     *      1. 시간 제한(10초)
     *      2. 프로그레스 바
     * */
    class GameThread extends Thread{

        private Boolean isRunning = true;   // 스레드를 일시정지를 하기 위한 변수
        int countTimeValue = 0;     // 시간을 측정을 하기 위해 카운트 되는 변수
        Handler handler;            // Activity에서 선언될 핸들러
        TextView textView;      // 화면에 시간 경과를 보여주는 View 예: (00:00)
        ProgressBar progressBar;

        // 사용할 Activity에서 Thread를 선언해주고 아래 생성자를 통해서 Thread 생성해주면 됨.
        // Ex. Thread thread = new GameThread(핸들러, TextView, Progressbar)
        // thread.start() 메서드를 호출해 아래 run()함수를 실행
        public GameThread(Handler handler, TextView textView, ProgressBar progressBar){
            this.handler = handler;
            this.textView = textView;
            this.progressBar = progressBar;
        }

        @Override
        public void run() {
            while(true){
                while (isRunning) {
                    countTimeValue++;
                    // Activity에서 생성한 Handler가 아래 Runnable의 내용에 해당하는 코드를 실행
                    handler.post(new Runnable(){
                        @Override
                        public void run() {  // Runnable 의 Run() 메소드에서 UI 접근
                            int time = countTimeValue;

                            //시간 form에 맞추기
                            int millisecond = time % 100;
                            int sec = time / 100;
                            String result = String.format("%02d:%02d", sec, millisecond);


                            textView.setText(result);
                            progressBar.setProgress(time);

                            // 시간 제한이 다 된경우 쓰레드를 일시정지 시킴.
                            if (sec >= 10) {
                                isRunning = false;
                                textView.setText("10:00");
                            }
                        }
                    });
                    try {
                        Thread.sleep(10); // 1000이 1초, 밀리초를 구하기 위해 sleep을 10으로 둠.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        }
    }

}
