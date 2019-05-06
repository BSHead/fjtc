package com.example.panda.myapplication.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.myapplication.R;
import com.example.panda.myapplication.adapter.NoticeAdapter;
import com.example.panda.myapplication.bean.BaseBean;
import com.example.panda.myapplication.bean.NoticeListBean;
import com.example.panda.myapplication.utils.DataCall;
import com.example.panda.myapplication.utils.EncryptUtils;
import com.example.panda.myapplication.utils.GsonUtility;
import com.example.panda.myapplication.utils.HttpUtils;
import com.example.panda.myapplication.utils.RequestLoginBeanToJson;
import com.example.panda.myapplication.utils.SharedUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

import static com.example.panda.myapplication.port.InterfaceName.TSTATE;
import static com.example.panda.myapplication.port.InterfaceName.Tong;
import static com.example.panda.myapplication.utils.HttpTools.HTTP_BASE;
import static com.example.panda.myapplication.utils.InfoCode.SERVER_OK;

public class NoticeActivity extends AppCompatActivity {
    private List<NoticeListBean.PushDataBean> mPushDataBeanList = new ArrayList<>();
    private ListView listView;
    private NoticeAdapter adapter;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==SERVER_OK){
                String result = (String) msg.obj;
                Gson gson = new Gson();
                NoticeListBean bean = gson.fromJson(result, NoticeListBean.class);
                adapter.setList(bean.getPushData());
                adapter.notifyDataSetChanged();
                mPushDataBeanList.addAll(bean.getPushData());
            }else if(msg.what==1){
                String s = (String) msg.obj;
                Gson gson = new Gson();
                BaseBean beana = gson.fromJson(s, BaseBean.class);
                Toast.makeText(NoticeActivity.this, beana.getMsg()+"", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        listView = findViewById(R.id.notice_list);
        findViewById(R.id.notice_back_img_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter = new NoticeAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.notice_state);
                TextView content = view.findViewById(R.id.notice_content);
                TextView time = view.findViewById(R.id.notice_createTime);
                TextView shelid = view.findViewById(R.id.notice_shelid);
                showPopupWindow(position);
                if (textView.getText().equals("已读")){

                 }else {
                     String params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requeststateToJson(mPushDataBeanList.get(position).getId()+""));
                     RequestBody requestBody = new FormBody.Builder().add("request", params).build();
                       Log.e("111",RequestLoginBeanToJson.requeststateToJson(mPushDataBeanList.get(position+1).getId()+""));
                     HttpUtils httpUtils = new HttpUtils(new Tstate());
                     httpUtils.request(HTTP_BASE,requestBody,TSTATE );
                     textView.setText("已读");
                    textView.setTextColor(Color.GRAY);
                    content.setTextColor(Color.GRAY);
                    shelid.setTextColor(Color.GRAY);
                    time.setTextColor(Color.GRAY);
                 }
            }
        });
        String name = SharedUtils.getName(this);
        String params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requesttongBeanToJson(name,"1"));
        RequestBody requestBody = new FormBody.Builder().add("request", params).build();
      //  Log.e("111",RequestLoginBeanToJson.requesttongBeanToJson("999"));
        Log.e("111",params.toString());
        HttpUtils httpUtils = new HttpUtils(new Data());
        httpUtils.request(HTTP_BASE,requestBody,Tong );
    }

    private class Data implements DataCall {
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

        }
    }

    private class Tstate implements DataCall {
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


    private void showPopupWindow(int position) {
        View contentView = LayoutInflater.from(NoticeActivity.this).inflate(
                R.layout.pop_window, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        // 一个自定义的布局，作为显示的内容

        // 设置按钮的点击事件
        Button button =  contentView.findViewById(R.id.pop_but);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        TextView content = contentView.findViewById(R.id.pop_content);

     TextView shelid = contentView.findViewById(R.id.pop_shelid);
        TextView createTime = contentView.findViewById(R.id.pop_createTime);

        content.setText(mPushDataBeanList.get(position).getContent());
        shelid.setText("机器id："+mPushDataBeanList.get(position).getShelfid()+"");
        String s = mPushDataBeanList.get(position).getCreatetime();
        createTime.setText("日期："+s.substring(0,4)+"年"+s.substring(4,6)+"月"+s.substring(6,8)+"日"+s.substring(8,10)+"点"+s.substring(10,12)+"分");

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.my_pop));

        // 设置好参数之后再show
        popupWindow.showAtLocation(NoticeActivity.this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

}

