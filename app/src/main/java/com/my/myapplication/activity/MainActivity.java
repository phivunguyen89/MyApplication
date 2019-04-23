package com.my.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.my.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private LinearLayout llLessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initEvent() {
        llLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,KeyNoteActivity.class);
                startActivity(intent);

            }
        });

    }

    private void initView() {
        llLessons=findViewById(R.id.ll_lessons);

    }
}
