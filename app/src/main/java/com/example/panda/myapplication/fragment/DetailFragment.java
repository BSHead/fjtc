package com.example.panda.myapplication.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.panda.myapplication.R;
import com.example.panda.myapplication.adapter.MachineListAdapter;
import com.example.panda.myapplication.bean.BaseBean;
import com.example.panda.myapplication.bean.MachineBean;
import com.example.panda.myapplication.bean.RequestBaseBeanToJson;
import com.example.panda.myapplication.bean.ResponseMachineListBean;
import com.example.panda.myapplication.operation_activity.OperationPageActivity;
import com.example.panda.myapplication.utils.EncryptUtils;
import com.example.panda.myapplication.utils.GsonUtility;
import com.example.panda.myapplication.port.InterfaceName;
import com.example.panda.myapplication.utils.L;
import com.example.panda.myapplication.utils.SharedUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.panda.myapplication.utils.HttpTools.HTTP_BASE;
import static com.example.panda.myapplication.utils.InfoCode.SERVER_ERROR;
import static com.example.panda.myapplication.utils.InfoCode.SERVER_NOT_CONNECT;
import static com.example.panda.myapplication.utils.InfoCode.SERVER_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    // private static String url = HTTP_BASE +MACHINE_LIST;
    private ListView listView;
    private View view;
    private MachineListAdapter adapter;
    private List<MachineBean> list = new ArrayList<>();
    //private String l="http://47.93.88.6/Icecream_System/app/login.do?userId=admin&password=123456";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detail, container, false);
        initView();
        getMachineListData();//从服务器获取机器列表数据
        return view;
    }

    private void initView() {
        listView =view.findViewById(R.id.machine_list_view);
        adapter = new MachineListAdapter(list, getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), OperationPageActivity.class);
                intent.putExtra("shelfId", list.get(position).getShelfId());
                startActivity(intent);
            }
        });
    }

    private void getMachineListData() {
        String userId = SharedUtils.getName(getContext());
        String params = EncryptUtils.aesEncryptString(RequestBaseBeanToJson.requestBaseBeanToJson(userId));
        RequestBody requestBody = new FormBody.Builder().add("request", params).build();
        final Request request = new Request.Builder()
                .url(HTTP_BASE + InterfaceName.MACHINE_LIST)
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("sb","调用app/login.do接口，链接服务器失败!!!" + e.getCause());
                Message message = new Message();
                message.what = SERVER_NOT_CONNECT;//服务器未连接
                message.obj = e.getCause().toString();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = new Message();
                String result;
                if (SERVER_OK == response.code()) {
                    result = EncryptUtils.aesDecryptString(response.body().string());
                    message.what = SERVER_OK;
                } else {
                    result = response.message() + "--" + response.code();
                    message.what = SERVER_ERROR;
                    Log.e("1234","lalal"+result);
                }
                message.obj = result;
                handler.sendMessage(message);
                Log.e("1234",request.toString());
            }
        });
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = (String) msg.obj;
            if (SERVER_OK == msg.what) {
                BaseBean bean = GsonUtility.json2Bean(result, BaseBean.class);
                Log.e("1111",result);
                //返回正确数据，根据不同类型处理逻辑
                if (InterfaceName.MACHINE_LIST.equals(bean.getMt())) {
                    //请求成功，刷新列表
                    ResponseMachineListBean responseMachineListBean = GsonUtility.json2Bean(result, ResponseMachineListBean.class);
                    list = responseMachineListBean.getShelfInfoList();
                    adapter.onDataChanged(list);
                }
            } else {
                //异常数据或者未连接等情况
                L.e("请求失败!!!:" + result);
                Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
            }
        }
    };

}
