package com.example.panda.myapplication.operation_activity;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.myapplication.R;
import com.example.panda.myapplication.activity.NoticeActivity;
import com.example.panda.myapplication.bean.BaseBean;
import com.example.panda.myapplication.bean.NoticeListBean;
import com.example.panda.myapplication.clean_activity.CleanActivity;
import com.example.panda.myapplication.replenishment_activity.ReplenishmentActivity;
import com.example.panda.myapplication.set_activity.SetkjActivity;
import com.example.panda.myapplication.utils.DataCall;
import com.example.panda.myapplication.utils.EncryptUtils;
import com.example.panda.myapplication.utils.HttpUtils;
import com.example.panda.myapplication.utils.RequestLoginBeanToJson;
import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.RequestBody;

import static com.example.panda.myapplication.port.InterfaceName.JiQIZT;
import static com.example.panda.myapplication.port.InterfaceName.TSTATE;
import static com.example.panda.myapplication.port.InterfaceName.Tong;
import static com.example.panda.myapplication.port.InterfaceName.UPSTATE;
import static com.example.panda.myapplication.utils.HttpTools.HTTP_BASE;
import static com.example.panda.myapplication.utils.InfoCode.SERVER_OK;

public class OperationPageActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout clean_ll,replenishment_ll,set_ll,sell_ll;
    private ImageView back_img;
    private String currentShelfId = "";
    private boolean isOpen;
    private boolean isClick = true;
    private int  code;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                String result = (String) msg.obj;
                Gson gson = new Gson();
                BaseBean bean = gson.fromJson(result, BaseBean.class);
              //  Toast.makeText(OperationPageActivity.this, bean.getCode()+"", Toast.LENGTH_SHORT).show();
                if (bean.getCode().equals("1")){
                    image.setImageResource(R.drawable.oper_noo);
                    operText.setText("点击停止销售");
                    isOpen=true;
                }else {
                    image.setImageResource(R.drawable.oper_yess);
                    operText.setText("点击开始销售");
                    isOpen=false;
                }
            }else if (msg.what == 0){
                String result = (String) msg.obj;
                Gson gson = new Gson();
                BaseBean bean = gson.fromJson(result, BaseBean.class);
                if (bean.getCode().equals("1")){
                    image.setImageResource(R.drawable.oper_noo);
                    operText.setText("点击停止销售");
                    isOpen=true;
                }else {
                    image.setImageResource(R.drawable.oper_yess);
                    operText.setText("点击开始销售");
                    isOpen=false;
                }
            }
        }
    };
    private ImageView image;
    private TextView operText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_page);
        initview();
        clean_ll.setOnClickListener(this);
        replenishment_ll.setOnClickListener(this);
        set_ll.setOnClickListener(this);
        sell_ll.setOnClickListener(this);
        back_img.setOnClickListener(this);
        String shelfId = getIntent().getStringExtra("shelfId");
        //获取机器此时的状态
        String params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requestjqztToJson(shelfId));
        RequestBody requestBody = new FormBody.Builder().add("request", params).build();
        //  Log.e("111",RequestLoginBeanToJson.requesttongBeanToJson("999"));
        Log.e("111",params.toString());
        HttpUtils httpUtils = new HttpUtils(new SheCode());
        httpUtils.request(HTTP_BASE,requestBody,JiQIZT );
    }

    private void initview() {
        currentShelfId = getIntent().getStringExtra("shelfId");
        clean_ll=findViewById(R.id.clean_ll);
        replenishment_ll=findViewById(R.id.replenishment_ll);
        set_ll=findViewById(R.id.set_ll);
        sell_ll=findViewById(R.id.sell_ll);
        back_img=findViewById(R.id.back_img);
        image = findViewById(R.id.oper_image);
        operText = findViewById(R.id.oper_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clean_ll:
                Intent intent=new Intent(OperationPageActivity.this,CleanActivity.class);
                intent.putExtra("shelfId",currentShelfId);
                startActivity(intent);
                break;
            case R.id.replenishment_ll:
                Intent intent2=new Intent(OperationPageActivity.this,ReplenishmentActivity.class);
                intent2.putExtra("shelfid",currentShelfId);
                startActivity(intent2);
                break;
            case R.id.set_ll:
                Intent intent3=new Intent(OperationPageActivity.this,SetkjActivity.class);
                intent3.putExtra("shelfId", currentShelfId);
                startActivity(intent3);
                break;
            case R.id.sell_ll:
                    if (isOpen){
                        alertcloss();
                    }else {
                         alertopen();
                    }
                break;
            case R.id.back_img:
                finish();
                break;
        }
    }

    private class SheCode implements DataCall {
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

    private class Sstate implements DataCall {
        @Override
        public void success(String data) {
            Log.e("------", "success: "+data);
            Message message = new Message();
            message.what=0;
            message.obj = data;
            handler.sendMessage(message);
        }

        @Override
        public void fail(String e) {

        }
    }
    public void  alertopen(){
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.create();
        alertDialogBuilder.setTitle("提示");
        alertDialogBuilder.setMessage("请确认补货完成并清洗后开启机器！！！");
        alertDialogBuilder.setPositiveButton("确认开启", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(OperationPageActivity.this, "开启", Toast.LENGTH_SHORT).show();
                code = 1;
                String  params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requesttestBeanToJson(currentShelfId,code+""));
                RequestBody requestBody = new FormBody.Builder().add("request", params).build();
                HttpUtils httpUtils = new HttpUtils(new Sstate());
                httpUtils.request(HTTP_BASE,requestBody,UPSTATE );
            }
        });
        alertDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialogBuilder.show();
    }
    public void  alertcloss(){
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.create();
        alertDialogBuilder.setTitle("提示");
        alertDialogBuilder.setMessage("机器正在售卖请确定是否关闭！！！");
        alertDialogBuilder.setPositiveButton("确认关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                code = 4;
                String  params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requesttestBeanToJson(currentShelfId,code+""));
                RequestBody requestBody = new FormBody.Builder().add("request", params).build();
                HttpUtils httpUtils = new HttpUtils(new Sstate());
                httpUtils.request(HTTP_BASE,requestBody,UPSTATE );
            }
        });
        alertDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialogBuilder.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        String shelfId = getIntent().getStringExtra("shelfId");
        //获取机器此时的状态
        String params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requestjqztToJson(shelfId));
        RequestBody requestBody = new FormBody.Builder().add("request", params).build();
        //  Log.e("111",RequestLoginBeanToJson.requesttongBeanToJson("999"));
        Log.e("111",params.toString());
        HttpUtils httpUtils = new HttpUtils(new SheCode());
        httpUtils.request(HTTP_BASE,requestBody,JiQIZT );
    }
}
