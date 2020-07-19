package com.example.hackerthon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;

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
    @BindView(R.id.imageview_signUpActivity_pwCheckImage)
    ImageView imageviewsignUpActivitypwCheckImage; // 비밀번호 재입력값 비교 이미지
    @BindView(R.id.button_signUpActivity_back)
    Button buttonSignUpActivityBack; // 뒤로가기 버튼
    @BindView(R.id.button_signUpActivity_success)
    Button buttonSignUpActivitySuccess; // 회원가입 완료 버튼

    /*--------------------내가 선언하는 변수들 -----------------------------*/
    String myName; //입력이름
    String myEmail; //입력 이메일
    String myPassword; //입력 비밀번호
    String myCheckPassword; //재입력 비밀번호
    String userKey; // 이메일 . >> , replace 한것




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        //입력하는 변수값들
        myName = edittextSignUpActivityName.getText().toString(); // 이름 입력값
        myEmail = edittextSignUpActivityEmail.getText().toString(); // 이메일 입력값
        myPassword = edittextSignUpActivityPassword.getText().toString(); // 비밀번호 입력값
        myCheckPassword = edittextSignUpActivityPasswordCheck.getText().toString(); //비밀번호 재입력값
        userKey = applicationClass.EncodeString(myEmail); // 키값으로 바꾸기 위해 이메일 . 을 , 으로 바꾼값

//        //비밀번호, 비밀번호 재입력 시 텍스트 변화 감지하여 비밀번호 중복체크 이미지 생성
//        edittextSignUpActivityPasswordCheck.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (myPassword.equals(myCheckPassword)){ // 서로 같으면 체크 표시
//                    imageviewsignUpActivitypwCheckImage.setImageResource(R.drawable.check);
//                } else if(myCheckPassword.isEmpty()){ // 비밀번호재입력값이 없으면 null
//                    imageviewsignUpActivitypwCheckImage.setImageResource(0);
//                }else{ // 틀리면 X 표시
//                    imageviewsignUpActivitypwCheckImage.setImageResource(R.drawable.xxxxx);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });





    }//OnCreate









    //뒤로가기 버튼 클릭시
    @OnClick(R.id.button_signUpActivity_back)
    public void onButtonSignUpActivityBackClicked() {

        //회원가입페이지로 이동
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }


    //회원가입 완료 버튼 클릭시
    @OnClick(R.id.button_signUpActivity_success)
    public void onButtonSignUpActivitySuccessClicked() {
        myName = edittextSignUpActivityName.getText().toString(); // 이름 입력값
        myEmail = edittextSignUpActivityEmail.getText().toString(); // 이메일 입력값
        myPassword = edittextSignUpActivityPassword.getText().toString(); // 비밀번호 입력값
        myCheckPassword = edittextSignUpActivityPasswordCheck.getText().toString(); //비밀번호 재입력값
        userKey = applicationClass.EncodeString(myEmail); // 키값으로 바꾸기 위해 이메일 . 을 , 으로 바꾼값

        applicationClass.firebaseAuth.createUserWithEmailAndPassword(myEmail, myPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //회원가입 성공시
                            makeLog(new Object() {
                            }.getClass().getEnclosingMethod().getName() + "()", " :파이어베이스 회원가입 성공 " );
//                            FirebaseUser user = applicationClass.firebaseAuth.getCurrentUser();

                            //DB에 USER데이터 추가하기
                            DatabaseReference myRef = applicationClass.databaseReference.child("USER").child(userKey);
                            User user = new User(myEmail,myPassword,0,0, myName);

                            makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", " :user " +user);
//                            user.toUserMap(user);
                            makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", " user.toUserMap(user) : " +  user.toUserMap(user));

                            myRef.setValue(user.toUserMap(user)); // 데이터베이스에 회원정보 추가
                            makeToast("회원가입에 성공하였습니다.",SHORT_TOAST);

                            //로그인 액티비티로 이동
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);


                        } else {

                            makeLog(new Object() {}.getClass().getEnclosingMethod().getName() + "()", " :파이어베이스 회원가입 실패 "  );
                            makeToast("회원가입에 실패하였습니다.",SHORT_TOAST);

                        }

                    }
                });




    }
}