package com.example.hackerthon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends BaseActivity {

    String autoLoginKey;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        autoLoginKey = applicationClass.mySharedPref.getStringPref("CurrentUserKey");

        //스플래쉬 액티비티 2초지속 후 로그인 액티비티로 이동하는 메소드 실행
        startLoading();

    }

    //3초 후 로그인액티비티로 넘어가는 메소드
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

//                /*자동로그인*/
//                if (shared_Email!=null) {
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
//                }else{
//                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//                    startActivity(intent);
//                }

                if(autoLoginKey.contentEquals("no key")) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }else{

                    applicationClass.firebaseDatabase.getReference("USER").child(autoLoginKey)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //로그인시 로그인한 데이터 불러오기 연습
//                                        ArrayList userArrayList = new ArrayList<>();

                            //로그인한 유저 이름값만 빼오기
                            User user = dataSnapshot.getValue(User.class);
                            name = user.getUserName();
//                                        applicationClass.mySharedPref.saveStringPref(name,name); //굳이 shared 쓸필요없음
//                                        userArrayList.add(user);

                            //키값이랑 로그인한유저의 이름값 applicationClass의 변수값으로 저장
                            applicationClass.currentUserEmailKey = autoLoginKey;
                            applicationClass.currentUserName = name;

                            //토스트로 '이름' 님 로그인하였습니다.
                            makeLog(new Object() {
                            }.getClass().getEnclosingMethod().getName() + "()", "name : " + name);
                            makeToast(name+"님 로그인 하였습니다.",SHORT_TOAST);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        },2300);

    }
}
