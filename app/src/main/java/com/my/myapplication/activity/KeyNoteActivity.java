package com.my.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.my.myapplication.R;
import com.my.myapplication.adapter.KeyNoteAdapter;
import com.my.myapplication.entity.KeyNoteEntity;

import java.util.ArrayList;
import java.util.List;

public class KeyNoteActivity extends Activity {
    private ImageView ivBack;
    private SwipeRefreshLayout refresh;
    private ListView lv;
    private List<KeyNoteEntity> list=new ArrayList<>();
    private KeyNoteAdapter adapter;

    private void assignViews() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        lv = (ListView) findViewById(R.id.lv);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_key_note);

        initView();
        initEvent();
        initData();
    }

    private void initData() {
        list.add(new KeyNoteEntity("1","Introduction to Programming","http://lc-tmlu8swt.cn-n1.lcfile.com/ba8235b67d4ee7e448ff/a.mp4"));
        list.add(new KeyNoteEntity("2","Elementary Programming 1 with Introduction to OOP","http://lc-tmlu8swt.cn-n1.lcfile.com/6819ee50fadb00e14296/b.mp4"));
        list.add(new KeyNoteEntity("3","Elementary Programming 2 with Introduction to OOP","http://lc-tmlu8swt.cn-n1.lcfile.com/0926495c215ca91f7936/c.mp4"));
        list.add(new KeyNoteEntity("4","Elementary Programming and fundamental OOP concepts wrap-up","https://www.youtube.com/watch?v=HBnB69yFf_4&list=PLS1QulWo1RIbfTjQvTdj8Y6yyq4R7g-Al&index=9"));
        list.add(new KeyNoteEntity("5","Selections","https://www.youtube.com/watch?v=L5_7XQR0r0w&list=PLS1QulWo1RIbfTjQvTdj8Y6yyq4R7g-Al&index=10"));
        list.add(new KeyNoteEntity("6","Loops","https://www.youtube.com/watch?v=xwvdENKCkLg&list=PLS1QulWo1RIbfTjQvTdj8Y6yyq4R7g-Al&index=12"));
        list.add(new KeyNoteEntity("7","Methods","https://www.youtube.com/watch?v=o5dcpcuyuIA&list=PLS1QulWo1RIbfTjQvTdj8Y6yyq4R7g-Al&index=16"));
        list.add(new KeyNoteEntity("8","Single and multi-dimensional Arrays","https://www.youtube.com/watch?v=_NfwcH5zKpA&list=PLS1QulWo1RIbfTjQvTdj8Y6yyq4R7g-Al&index=13"));
        list.add(new KeyNoteEntity("9","OOP In-depth Overview","https://www.youtube.com/watch?v=ZHLdVRXIuC8&list=PLS1QulWo1RIbfTjQvTdj8Y6yyq4R7g-Al&index=18"));
        list.add(new KeyNoteEntity("10","Abstraction, Polymorphism, Inheritance and Encapsulation","https://www.youtube.com/watch?v=lz1Cx6GzOuc&list=PLS1QulWo1RIbfTjQvTdj8Y6yyq4R7g-Al&index=25"));
        adapter.setData(list);
        adapter.notifyDataSetChanged();
        stopRefresh();
    }

    private void initEvent() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startRefresh();
                initData();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(KeyNoteActivity.this,VideoActivity.class);
                intent.putExtra("position",position+"");
                intent.putExtra("url",list.get(position).getUtl());
                startActivity(intent);
            }
        });

    }

    private void initView() {
        assignViews();
        adapter=new KeyNoteAdapter(this,list);
        lv.setAdapter(adapter);
    }

    public void startRefresh(){
        refresh.setRefreshing(false);
        refresh.setColorSchemeResources(R.color.colorPrimary);
        refresh.setProgressViewOffset(false, 0, 30);
        refresh.setRefreshing(true);
    }


    private void stopRefresh() {
        if (refresh != null && refresh.isRefreshing()) {
            refresh.setRefreshing(false);
        }
    }
}
