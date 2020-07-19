package com.example.hackerthon;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ApplicationClass extends Application {

    MySharedPref mySharedPref = MySharedPref.getInstance(this);

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseStorage firebaseStorage;
    StorageReference storageRef;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String currentUserEmailKey, currentUserName;

    @Override
    public void onCreate() {
        super.onCreate();

        //파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //파이어베이스 저장소 객체 선언
        firebaseStorage = FirebaseStorage.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    //토스트메세지
    public void makeToast(String strData){
        Toast.makeText(this, strData, Toast.LENGTH_SHORT).show();
    }

    //파이어베이스 users 데이터 key 값 저장할때 . 안되서 , 로 바꿔서 저장함
    public String EncodeString(String string) {
        return string.replace(".", ",");
    }

    //key값 불러올때는 , -> . 로 변환해야함
    public String DecodeString(String string) {
        return string.replace(",", ".");
    }
}
