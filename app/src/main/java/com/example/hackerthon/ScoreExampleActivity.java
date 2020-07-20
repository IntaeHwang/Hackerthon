package com.example.hackerthon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreExampleActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_example);
        ButterKnife.bind(this);

        Intent intent = getIntent();
      int ppp =  intent.getIntExtra("qq",99);
        textView.setText(""+ppp);
    }
}