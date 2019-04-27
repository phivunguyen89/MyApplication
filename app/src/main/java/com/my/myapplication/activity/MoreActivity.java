package com.my.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.my.myapplication.R;
import com.my.myapplication.YoutubeModel.YoutubeActivity;

public class MoreActivity extends AppCompatActivity {
    LinearLayout ll_Youtube, ll_Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        //Find layout by their ID
        ll_Youtube = findViewById(R.id.ll_Youtube);
        ll_Info = findViewById(R.id.ll_MoreInfo);
        //Use intent to open the activity when click the layout
        ll_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
        ll_Youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, YoutubeActivity.class);
                startActivity(intent);
            }
        });
    }
}
