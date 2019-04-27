package com.my.myapplication.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.myapplication.R;

public class SearchActivity extends AppCompatActivity {
    private Button btHome, btSearch;
    private EditText etSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //Assign Home button
        btHome = findViewById(R.id.btHome);
        //Assign Search EditText
        etSearch = findViewById(R.id.etSearch);
        //Assign Search button
        btSearch = findViewById(R.id.btSearch);
        //Assign Home button action to go back Home page
        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //Assign Search button Action
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "Search Query");
                startActivity(intent);
            }
        });
    }
}


