package com.my.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.my.myapplication.R;
import com.my.myapplication.entity.KeyNoteEntity;

import java.util.List;

public class KeyNoteAdapter extends BaseAdapter {
    private Context context;
    private List<KeyNoteEntity> list;

    public KeyNoteAdapter(Context context, List<KeyNoteEntity> list) {
        this.context = context;
        this.list = list;
    }

    public void setData( List<KeyNoteEntity> list){
        this.list=list;
    }

    @Override
    public int getCount() {
        return list == null ? 0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View view1=LayoutInflater.from(context).inflate(R.layout.list_item_key,null);
        TextView tvNum=view1.findViewById(R.id.tv_num);
        TextView tv_content=view1.findViewById(R.id.tv_content);

        tvNum.setText(list.get(position).getNum());
        tv_content.setText(list.get(position).getTitle());
        return view1;
    }
}
