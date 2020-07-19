package com.example.hackerthon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*로그인 액티비티입니다*/
public class LoginActivity extends BaseActivity {

    @BindView(R.id.textview_loginActivity_AppName)
    TextView textviewLoginActivityAppName; // 상위 앱 이름
    @BindView(R.id.edittext_login_InputEmail)
    TextInputEditText edittextLoginInputEmail; // 이메일 입력칸
    @BindView(R.id.edittext_loginActivity_InputPassword)
    TextInputEditText edittextLoginActivityInputPassword; //비밀번호 입력칸
    @BindView(R.id.button_loginActivity_login)
    Button buttonLoginActivityLogin; // 로그인 버트
    @BindView(R.id.button_loginActivity_signUp)
    Button buttonLoginActivitySignUp; // 회원가입 버트


    //---------------------내가 추가한 변수----------------------
    String inputEmail;
    String inputPassword;
    String userKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);





    }

    //로그인 버튼 클릭시
    @OnClick(R.id.button_loginActivity_login)
    public void onButtonLoginActivityLoginClicked() {
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(intent);
        inputEmail = edittextLoginInputEmail.getText().toString(); //입력 이메일값
        inputPassword = edittextLoginActivityInputPassword.getText().toString(); //입력 비밀번호
        userKey = applicationClass.EncodeString(inputEmail); // 키값으로 바꾸기 위해 이메일 . 을 , 으로 바꾼값

        //파이어베이스 로그인
        applicationClass.firebaseAuth.signInWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //로그인 성공시
//                            FirebaseUser user = mAuth.getCurrentUser();
                            makeLog(new Object() {
                            }.getClass().getEnclosingMethod().getName() + "()", "로그인 성공 : " );
                            makeToast("로그인 성공",SHORT_TOAST);

                            //유저키를 Sharedpreference에 저장
                            applicationClass.mySharedPref.saveStringPref(userKey,userKey);

                            //로그 & 토스트
                            makeLog(new Object() {
                            }.getClass().getEnclosingMethod().getName() + "()", "로그인 성공 : " );
                            makeToast("로그인 성공",SHORT_TOAST);

                            //메인액티비티로 이동
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            
                        } else {
                            //로그인 실패시
                            makeLog(new Object() {
                            }.getClass().getEnclosingMethod().getName() + "()", "로그인 실패 : " );
                            makeToast("로그인 실패",SHORT_TOAST);
                        }

                        // ...
                    }
                });





    }


    //회원가입버튼 클릭시
    @OnClick(R.id.button_loginActivity_signUp)
    public void onButtonLoginActivitySignUpClicked() {
        makeToast("회원가입버튼 클릭",LONG_TOAST);

        //회원가입페이지로 이동
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }
}
