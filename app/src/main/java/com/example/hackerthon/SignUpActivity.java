package com.example.hackerthon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*회원가입 액티비티 입니다*/
public class SignUpActivity extends BaseActivity {

    @BindView(R.id.textview_signUpActivity_intro1)
    TextView textviewSignUpActivityIntro1; //수정불필요 - 환영합니다.
    @BindView(R.id.textview_signUpActivity_intro2)
    TextView textviewSignUpActivityIntro2; //수정불필요 - 회원정보를 입력해주세요.
    @BindView(R.id.edittext_signUpActivity_name)
    EditText edittextSignUpActivityName; //회원가입시 이름 입력
    @BindView(R.id.edittext_signUpActivity_email)
    EditText edittextSignUpActivityEmail; //회원가입시 이메일 입력
    @BindView(R.id.edittext_signUpActivity_password)
    EditText edittextSignUpActivityPassword; // 회원가입시 비밀번호 입력
    @BindView(R.id.edittext_signUpActivity_passwordCheck)
    EditText edittextSignUpActivityPasswordCheck; // 회원가입시 비밀번호 재입력
    @BindView(R.id.iv_PwCheckImage)
    ImageView ivPwCheckImage; // 비밀번호 재입력값 비교 이미지
    @BindView(R.id.button_signUpActivity_back)
    Button buttonSignUpActivityBack; // 뒤로가기 버튼
    @BindView(R.id.button_signUpActivity_success)
    Button buttonSignUpActivitySuccess; // 회원가입 완료 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_signUpActivity_back)
    public void onButtonSignUpActivityBackClicked() {

        //회원가입페이지로 이동
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_signUpActivity_success)
    public void onButtonSignUpActivitySuccessClicked() {
        //회원가입페이지로 이동
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);

    }
}
