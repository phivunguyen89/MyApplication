package com.my.myapplication.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.my.myapplication.R;

public class ShiJuanActivity3 extends Activity{

    private RadioButton rb_a1;
    private RadioButton rb_b1;
    private RadioButton rb_c1;
    private RadioButton rb_d1;
    private RadioButton rb_a2;
    private RadioButton rb_b2;
    private RadioButton rb_c2;
    private RadioButton rb_d2;
    private RadioButton rb_a3;
    private RadioButton rb_b3;
    private RadioButton rb_c3;
    private RadioButton rb_d3;
    private RadioButton rb_a4;
    private RadioButton rb_b4;
    private RadioButton rb_c4;
    private RadioButton rb_d4;
    private RadioButton rb_a5;
    private RadioButton rb_b5;
    private RadioButton rb_c5;
    private RadioButton rb_d5;
    private RadioGroup rg_1;
    private RadioGroup rg_2;
    private RadioGroup rg_3;
    private RadioGroup rg_4;
    private RadioGroup rg_5;
    private TextView tv_send;

    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int number5;
    private int score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shijuan3);

        initView();
        initEvent();
    }

    private void initEvent() {
        rg_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==rb_b1.getId()){
                    number1=20;
                }else {
                    number1=0;
                }
            }
        });

        rg_2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==rb_a2.getId()){
                    number2=20;
                }else {
                    number2=0;
                }
            }
        });

        rg_3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==rb_a3.getId()){
                    number3=20;
                }else {
                    number3=0;
                }
            }
        });

        rg_4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==rb_c4.getId()){
                    number4=20;
                }else {
                    number4=0;
                }
            }
        });

        rg_5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==rb_a5.getId()){
                    number5=20;
                }else {
                    number5=0;
                }
            }
        });

        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score=number1+number2+number3+number4+number5;
//                Toast.makeText(ShiJuanActivity1.this,"您的得分是："+score+"分！",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.putExtra("score",score);
                intent.setClass(ShiJuanActivity3.this,ResultActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initView() {
        rb_a1= (RadioButton) findViewById(R.id.rb_a1);
        rb_b1= (RadioButton) findViewById(R.id.rb_b1);
        rb_c1= (RadioButton) findViewById(R.id.rb_c1);
        rb_d1= (RadioButton) findViewById(R.id.rb_d1);
        rb_a2= (RadioButton) findViewById(R.id.rb_a2);
        rb_b2= (RadioButton) findViewById(R.id.rb_b2);
        rb_c2= (RadioButton) findViewById(R.id.rb_c2);
        rb_d2= (RadioButton) findViewById(R.id.rb_d2);
        rb_a3= (RadioButton) findViewById(R.id.rb_a3);
        rb_b3= (RadioButton) findViewById(R.id.rb_b3);
        rb_c3= (RadioButton) findViewById(R.id.rb_c3);
        rb_d3= (RadioButton) findViewById(R.id.rb_d3);
        rb_a4= (RadioButton) findViewById(R.id.rb_a4);
        rb_b4= (RadioButton) findViewById(R.id.rb_b4);
        rb_c4= (RadioButton) findViewById(R.id.rb_c4);
        rb_d4= (RadioButton) findViewById(R.id.rb_d4);
        rb_a5= (RadioButton) findViewById(R.id.rb_a5);
        rb_b5= (RadioButton) findViewById(R.id.rb_b5);
        rb_c5= (RadioButton) findViewById(R.id.rb_c5);
        rb_d5= (RadioButton) findViewById(R.id.rb_d5);
        rg_1= (RadioGroup) findViewById(R.id.rg_1);
        rg_2= (RadioGroup) findViewById(R.id.rg_2);
        rg_3= (RadioGroup) findViewById(R.id.rg_3);
        rg_4= (RadioGroup) findViewById(R.id.rg_4);
        rg_5= (RadioGroup) findViewById(R.id.rg_5);

        tv_send= (TextView) findViewById(R.id.tv_send);


    }
}
