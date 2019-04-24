package com.my.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.my.myapplication.R;

public class ProfileActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etEmail;
    private TextView tvName;
    private TextView tvEmail;
    private Button btUpdate;
    private String str_name, str_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etName =findViewById(R.id.etName);
        etEmail =findViewById(R.id.etEmail);
        tvName  =findViewById(R.id.tvName);
        tvEmail =findViewById(R.id.tvEmail);
        btUpdate = findViewById(R.id.btUpdate);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewId = v.getId();
                if(viewId == R.id.btUpdate){
                    str_name = etName.getText().toString().trim();
                    str_email = etEmail.getText().toString().trim();
                    tvName.setText(str_name);
                    tvEmail.setText(str_email);
                }
            }
        });
    }
}
