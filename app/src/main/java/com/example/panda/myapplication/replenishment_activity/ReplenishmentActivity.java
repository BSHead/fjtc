package com.example.panda.myapplication.replenishment_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.panda.myapplication.R;

public class ReplenishmentActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView  bu_next_btn_one,bh_back_one;
    private String shelfid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replenishment);
        initview();
        bu_next_btn_one.setOnClickListener(this);
        bh_back_one.setOnClickListener(this);
        shelfid = getIntent().getStringExtra("shelfid");

    }

    private void initview() {
        bu_next_btn_one=findViewById(R.id.bu_next_btn_one);
        bh_back_one=findViewById(R.id.bh_back_one);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bh_back_one:
                finish();
                break;
            case R.id.bu_next_btn_one:
                Intent intent = new Intent(this,ReplenishmentActivityTwo.class);
                intent.putExtra("1111",shelfid);
                startActivity(intent);
                finish();
                break;
        }
    }
}