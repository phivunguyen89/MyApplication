package com.my.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.my.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout llLessons;
    private LinearLayout ll_Profile;
    private LinearLayout ll_Progress;
    private LinearLayout ll_Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        llLessons = findViewById(R.id.ll_lessons);
        ll_Profile = findViewById(R.id.ll_Profile);
        ll_Progress = findViewById(R.id.ll_Progress);
        ll_Search = findViewById(R.id.ll_Search);

        llLessons.setOnClickListener(this);
        ll_Profile.setOnClickListener(this);
        ll_Progress.setOnClickListener(this);
        ll_Search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_lessons:
                Toast.makeText(this, "Lessons Chosen", Toast.LENGTH_SHORT).show();
                openLessonsActivity();
                break;

            case R.id.ll_Profile:
                Toast.makeText(this, "Profile Chosen", Toast.LENGTH_SHORT).show();
                openProfileActivity();
                break;
            case R.id.ll_Progress:
                Toast.makeText(this, "Progress Chosen", Toast.LENGTH_SHORT).show();
                openProgressActivity();
                break;
            case R.id.ll_Search:
                Toast.makeText(this, "Search Chosen", Toast.LENGTH_SHORT).show();
                openSearchActivity();
                break;
        }
    }

    private void openSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    private void openProgressActivity() {
        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }

    private void openLessonsActivity() {
        Intent intent = new Intent(this, KeyNoteActivity.class);
        startActivity(intent);
    }

    private void openProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
