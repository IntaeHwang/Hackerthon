package com.example.hackerthon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*로그인 액티비티입니다*/
public class LoginActivity extends BaseActivity {

    @BindView(R.id.textview_loginActivity_AppName)
    TextView textviewLoginActivityAppName;
    @BindView(R.id.edittext_login_InputEmail)
    TextInputEditText edittextLoginInputEmail;
    @BindView(R.id.edittext_loginActivity_InputPassword)
    TextInputEditText edittextLoginActivityInputPassword;
    @BindView(R.id.button_loginActivity_login)
    Button buttonLoginActivityLogin;
    @BindView(R.id.button_loginActivity_signUp)
    Button buttonLoginActivitySignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);





    }

    //로그인 버튼 클릭시
    @OnClick(R.id.button_loginActivity_login)
    public void onButtonLoginActivityLoginClicked() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
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
