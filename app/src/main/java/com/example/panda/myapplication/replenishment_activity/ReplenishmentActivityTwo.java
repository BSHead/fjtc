package com.example.panda.myapplication.replenishment_activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.myapplication.R;
import com.example.panda.myapplication.bean.BaseBean;
import com.example.panda.myapplication.clean_activity.CleanActivity;
import com.example.panda.myapplication.operation_activity.OperationPageActivity;
import com.example.panda.myapplication.utils.DataCall;
import com.example.panda.myapplication.utils.EncryptUtils;
import com.example.panda.myapplication.utils.HttpUtils;
import com.example.panda.myapplication.utils.RequestLoginBeanToJson;
import com.example.panda.myapplication.utils.SharedUtils;
import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.RequestBody;

import static com.example.panda.myapplication.port.InterfaceName.ADDREPLEN;
import static com.example.panda.myapplication.port.InterfaceName.CLEAR;
import static com.example.panda.myapplication.utils.HttpTools.HTTP_BASE;

public class ReplenishmentActivityTwo extends AppCompatActivity implements View.OnClickListener {
    private ImageView bu_wc,bh_back_two;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                String result = (String) msg.obj;
                Gson gson = new Gson();
                BaseBean bean = gson.fromJson(result, BaseBean.class);
                Toast.makeText(ReplenishmentActivityTwo.this, bean.getMsg()+"", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private String shelfid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replenishment_two);
        initview();
        bu_wc.setOnClickListener(this);
        bh_back_two.setOnClickListener(this);
        shelfid = getIntent().getStringExtra("1111");
    }

    private void initview() {
        bu_wc=findViewById(R.id.bu_wc);
        bh_back_two=findViewById(R.id.bh_back_two);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bu_wc:
                showdhk();
                break;
            case R.id.bh_back_two:
                finish();
                break;
        }

    }

    private void showdhk() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示！");
        builder.setMessage("补货完成后请对相关区域进行清洁");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String name = SharedUtils.getName(ReplenishmentActivityTwo.this);
                Log.e("111", shelfid);
                String params = EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requestaddBeanToJson(name, shelfid));

                RequestBody requestBody = new FormBody.Builder().add("request", params).build();

                HttpUtils httpUtils = new HttpUtils(new AddReplen());
                httpUtils.request(HTTP_BASE, requestBody, ADDREPLEN);
                finish();
            }
        });
        AlertDialog dialog = builder.create();// .setIcon(R.mipmap.icon)//设置标题的图片
//设置对话框的标题
//设置对话框的内容
//设置对话框的按钮
        dialog.show();
    }

    private class AddReplen implements DataCall {
        @Override
        public void success(String data) {
            Log.e("------", "success: "+data);
            Message message = new Message();
            message.what=1;
            message.obj = data;
            handler.sendMessage(message);
        }

        @Override
        public void fail(String e) {

        }
    }
}
