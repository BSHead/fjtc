package com.example.panda.myapplication.clean_activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.panda.myapplication.R;
import com.example.panda.myapplication.bean.BaseBean;
import com.example.panda.myapplication.utils.DataCall;
import com.example.panda.myapplication.utils.EncryptUtils;
import com.example.panda.myapplication.utils.GsonUtility;
import com.example.panda.myapplication.utils.HttpUtils;
import com.example.panda.myapplication.utils.RequestLoginBeanToJson;
import com.example.panda.myapplication.utils.SharedUtils;
import com.example.panda.myapplication.utils.SwitchButton;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import static com.example.panda.myapplication.port.InterfaceName.CLEAR;
import static com.example.panda.myapplication.utils.HttpTools.HTTP_BASE;

public class CleanActivity extends AppCompatActivity implements View.OnClickListener {
    public static CleanActivity cleanActivity;
    private ImageView clean_back_img_one;
    private TextView title_tv;
    private Button a_btn_one,b_btn_one,next_btn_one;
    private String currentShelfId ;
    private SwitchButton sButtona,sButtonb,sButtonac;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                String result = (String) msg.obj;
                  BaseBean bean = GsonUtility.json2Bean(result, BaseBean.class);
                if (bean.getType().equals("2000")){
                    if (bean.getState().equals("111")){
                        Toast.makeText(CleanActivity.this, "A口开始清洗", Toast.LENGTH_SHORT).show();
                    }
                    if (bean.getState().equals("222")){
                        Toast.makeText(CleanActivity.this, "B口开始清洗", Toast.LENGTH_SHORT).show();
                    }
                    if (bean.getState().equals("333")){
                        Toast.makeText(CleanActivity.this, "C口开始清洗", Toast.LENGTH_SHORT).show();
                    }
                    if (bean.getState().equals("444")){
                        Toast.makeText(CleanActivity.this, "A口清洗完成", Toast.LENGTH_SHORT).show();
                    }
                    if (bean.getState().equals("555")){
                        Toast.makeText(CleanActivity.this, "B口清洗完成", Toast.LENGTH_SHORT).show();
                    }
                    if (bean.getState().equals("666")){
                        Toast.makeText(CleanActivity.this, "C口清洗完成", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(CleanActivity.this, "清洗失败请重试！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean);
        cleanActivity=this;
        initview();
        clean_back_img_one.setOnClickListener(this);
        a_btn_one.setOnClickListener(this);
        b_btn_one.setOnClickListener(this);
        next_btn_one.setOnClickListener(this);
        title_tv.setText("清洁");
        sButtona.setOnCheckedChangeListener(new SwitBa());
        sButtonb.setOnCheckedChangeListener(new SwitBb());
        sButtonac.setOnCheckedChangeListener(new SwitBc());
    }

    private void initview() {
        currentShelfId = getIntent().getStringExtra("shelfId");
        Log.e("TAG","机器id:"+currentShelfId);
        clean_back_img_one=findViewById(R.id.clean_back_img_one);
        a_btn_one=findViewById(R.id.a_btn_one);
        b_btn_one=findViewById(R.id.b_btn_one);
        next_btn_one=findViewById(R.id.next_btn_one);
        title_tv=findViewById(R.id.title_tv);
        sButtona = findViewById(R.id.sButton_a);
        sButtonb = findViewById(R.id.sButton_b);
        sButtonac = findViewById(R.id.sButton_c);
        name = SharedUtils.getName(CleanActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clean_back_img_one:
                finish();
                break;
            case R.id.a_btn_one:
                break;
            case R.id.b_btn_one:
                break;
            case R.id.next_btn_one:
                finish();
                break;
        }
    }
    //a口开关
    private class SwitBa implements SwitchButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(SwitchButton view, boolean isChecked) {
            if (isChecked){
                String params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requestClearBeanToJson(name,currentShelfId,"111"));
                RequestBody requestBody = new FormBody.Builder().add("request", params).build();
                Log.e("111",params.toString());
                HttpUtils httpUtils = new HttpUtils(new Clear());
                httpUtils.request(HTTP_BASE,requestBody,CLEAR );
            }else{
                String params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requestClearBeanToJson(name,currentShelfId,"444"));
                RequestBody requestBody = new FormBody.Builder().add("request", params).build();
                Log.e("111",params.toString());
                HttpUtils httpUtils = new HttpUtils(new Clear());
                httpUtils.request(HTTP_BASE,requestBody,CLEAR );
            }
        }
    }
    //b口关闭
    private class SwitBb implements SwitchButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(SwitchButton view, boolean isChecked) {
            if (isChecked){
                String params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requestClearBeanToJson(name,currentShelfId,"222"));
                RequestBody requestBody = new FormBody.Builder().add("request", params).build();
                HttpUtils httpUtils = new HttpUtils(new Clear());
                httpUtils.request(HTTP_BASE,requestBody,CLEAR );
            }else{
                String params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requestClearBeanToJson(name,currentShelfId,"555"));
                RequestBody requestBody = new FormBody.Builder().add("request", params).build();
                HttpUtils httpUtils = new HttpUtils(new Clear());
                httpUtils.request(HTTP_BASE,requestBody,CLEAR );
            }
        }
    }
    //c口关闭
    private class SwitBc implements SwitchButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(SwitchButton view, boolean isChecked) {
            if (isChecked){
                String params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requestClearBeanToJson(name,currentShelfId,"333"));
                RequestBody requestBody = new FormBody.Builder().add("request", params).build();
                HttpUtils httpUtils = new HttpUtils(new Clear());
                httpUtils.request(HTTP_BASE,requestBody,CLEAR );
            }else{
                String params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requestClearBeanToJson(name,currentShelfId,"666"));
                RequestBody requestBody = new FormBody.Builder().add("request", params).build();
                HttpUtils httpUtils = new HttpUtils(new Clear());
                httpUtils.request(HTTP_BASE,requestBody,CLEAR );
            }
        }
    }
    private RequestBody  clear(String state){
        String name = SharedUtils.getName(CleanActivity.this);
        String params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requestClearBeanToJson(name,currentShelfId,state));
        RequestBody requestBody = new FormBody.Builder().add("request", params).build();
        Log.e("111",params.toString());
        return  requestBody;
    }

    private class Clear implements DataCall {
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
            Log.e("------", "fail: "+e);
        }
    }
}
