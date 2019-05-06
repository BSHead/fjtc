package com.example.panda.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.panda.myapplication.R;
import com.example.panda.myapplication.bean.BaseBean;
import com.example.panda.myapplication.utils.DataCall;
import com.example.panda.myapplication.utils.EncryptUtils;
import com.example.panda.myapplication.utils.GsonUtility;
import com.example.panda.myapplication.utils.HttpUtils;
import com.example.panda.myapplication.utils.RequestLoginBeanToJson;
import com.example.panda.myapplication.utils.SharedUtils;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import static com.example.panda.myapplication.port.InterfaceName.CLEAR;
import static com.example.panda.myapplication.port.InterfaceName.LOGIN;
import static com.example.panda.myapplication.utils.HttpTools.HTTP_BASE;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_login;
    private EditText user_id_eidt,password_edit;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context=this;
        SharedPreferences sharedPreferences = getSharedPreferences("Code",Context.MODE_PRIVATE);
        sharedPreferences.getInt("code",0);
        if ( sharedPreferences.getInt("code",0)==1){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        initview();
        btn_login.setOnClickListener(this);
    }

    private void initview() {
        btn_login=findViewById(R.id.btn_login);
        user_id_eidt=findViewById(R.id.user_id_eidt);
        password_edit=findViewById(R.id.password_edit);
    }
    //点击空白处，隐藏软键盘
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            String result = (String) msg.obj;
            if (1 == msg.what) {
                BaseBean bean = GsonUtility.json2Bean(result, BaseBean.class);
                //返回正确数据，根据不同类型处理逻辑
                if (bean.getState().equals("1001")) {
                    //登录成功，保存登录信息;跳转到主界面
                    String userId = user_id_eidt.getText().toString().trim();
                    SharedUtils.setName(getApplication(),userId);
                    SharedPreferences sharedPreferences = getSharedPreferences("Code",Context.MODE_PRIVATE);
                    SharedPreferences.Editor code = sharedPreferences.edit().putInt("code", 1);
                    code.commit();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if (bean.getState().equals("1002")){
                    Toast.makeText(context, "用户不存在或用户编号不正确", Toast.LENGTH_SHORT).show();
                }else if (bean.getState().equals("1003")){
                    Toast.makeText(context, "密码不正确", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    public void onClick(View v) {
        String zh=user_id_eidt.getText().toString();
        String mm=password_edit.getText().toString();
        if (zh.equals("")&&mm.equals("")||zh.equals("")||mm.equals("")){
            Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
        }else {
            dl();
        }
    }

    private void dl() {
        String userId = user_id_eidt.getText().toString().trim();
        String password = password_edit.getText().toString().trim();
        String params = EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requestLoginBeanToJson(userId, password));
        RequestBody requestBody = new FormBody.Builder().add("request", params).build();
        HttpUtils httpUtils = new HttpUtils(new Login());
        httpUtils.request(HTTP_BASE,requestBody,LOGIN );
    }

    private class Login implements DataCall {
        @Override
        public void success(String data) {
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
