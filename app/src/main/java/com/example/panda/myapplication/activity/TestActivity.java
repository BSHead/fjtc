package com.example.panda.myapplication.activity;



import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.panda.myapplication.R;
import com.example.panda.myapplication.utils.DataCall;
import com.example.panda.myapplication.utils.EncryptUtils;
import com.example.panda.myapplication.utils.HttpUtils;
import com.example.panda.myapplication.utils.RequestLoginBeanToJson;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import static com.example.panda.myapplication.port.InterfaceName.CLEAR;
import static com.example.panda.myapplication.port.InterfaceName.PUSH;
import static com.example.panda.myapplication.port.InterfaceName.Tong;
import static com.example.panda.myapplication.utils.HttpTools.HTTP_BASE;
import static com.example.panda.myapplication.utils.InfoCode.SERVER_OK;

public class TestActivity extends AppCompatActivity {
    Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if (msg.what==SERVER_OK){
                String result = (String) msg.obj;
           //     BaseBean bean = GsonUtility.json2Bean(result, BaseBean.class);
                Toast.makeText(TestActivity.this, result+"", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requesttestBeanToJson("9527e3d546ce8294","6"));
        RequestBody requestBody = new FormBody.Builder().add("request", params).build();
        Log.e("111",RequestLoginBeanToJson.requesttestBeanToJson("9527e3d546ce8294","6"));
        HttpUtils httpUtils = new HttpUtils(new Dat());
        httpUtils.request("http://quanminlangdu.cn/Icecream_System2.0/",requestBody,"uploadShelfInfoState.do");
    }
    private class Dat implements DataCall{
        @Override
        public void success(String data) {
            Log.e("------", "success: "+data);
            Message message = new Message();
            message.what=SERVER_OK;
            message.obj = data;
            handler.sendMessage(message);
        }
        @Override
        public void fail(String e) {
            Log.e("------", "请求失败: "+e);
        }
    }
}
