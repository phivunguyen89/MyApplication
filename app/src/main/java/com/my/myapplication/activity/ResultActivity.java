package com.my.myapplication.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.myapplication.R;

public class ResultActivity extends Activity{

    private ImageView iv_back;
    private TextView tv_result;
    private TextView tv_des;
    private int score;
    private Button btHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        score = getIntent().getIntExtra("score", 0);

        initView();

        btHome=findViewById(R.id.btHome);
        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        iv_back= (ImageView) findViewById(R.id.iv_back);
        tv_result= (TextView) findViewById(R.id.tv_result);
        tv_des= (TextView) findViewById(R.id.tv_des);


        tv_result.setText("Your score is："+score+".");
        if (score==0){
            tv_des.setText("Quit java lah, it doesn’t fit you.");
        }
        if (score == 20){
            tv_des.setText("You gotta work hard to catch up with others.");
        }
        if (score == 40){
            tv_des.setText("Almost pass, keep working.");
        }
        if (score==60){
            tv_des.setText("Congrats! You have passed.");
        }
        if (score==80){
            tv_des.setText("Nice job! Almost fully correct.");
        }
        if (score==100){
            tv_des.setText("Time to be a java tutor!");
        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
