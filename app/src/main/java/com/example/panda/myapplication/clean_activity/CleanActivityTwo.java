package com.example.panda.myapplication.clean_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.panda.myapplication.R;

public class CleanActivityTwo extends AppCompatActivity implements View.OnClickListener {
    public static CleanActivityTwo cleanActivityTwo;
    private ImageView next_btn_two,clean_back_img_two;
    private TextView title_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_two);
        cleanActivityTwo=this;
        initview();
        next_btn_two.setOnClickListener(this);
        clean_back_img_two.setOnClickListener(this);
        title_tv.setText("清洁");
    }

    private void initview() {
        clean_back_img_two=findViewById(R.id.clean_back_img_two);
        next_btn_two=findViewById(R.id.next_btn_two);
        title_tv=findViewById(R.id.title_tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_btn_two:
                Intent intent = new Intent(this, CleanActivityThree.class);
                startActivity(intent);
                finish();
                break;
            case R.id.clean_back_img_two:
                finish();
                break;
        }
    }
}
