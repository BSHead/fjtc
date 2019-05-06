package com.example.panda.myapplication.set_activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.panda.myapplication.R;
import com.example.panda.myapplication.bean.BaseBean;
import com.example.panda.myapplication.bean.GoodsBean;
import com.example.panda.myapplication.bean.RequestBaseBeanToJson;
import com.example.panda.myapplication.bean.RequestGoodsSettingInfoBeanToJson;
import com.example.panda.myapplication.bean.ResponseGoodsListBean;
import com.example.panda.myapplication.bean.ResponseMachineDetailListBean;
import com.example.panda.myapplication.clean_activity.CleanActivity;
import com.example.panda.myapplication.clean_activity.CleanActivityTwo;
import com.example.panda.myapplication.operation_activity.OperationPageActivity;
import com.example.panda.myapplication.port.InterfaceName;
import com.example.panda.myapplication.utils.EncryptUtils;
import com.example.panda.myapplication.utils.GsonUtility;
import com.zhy.http.okhttp.utils.L;
import org.angmarch.views.NiceSpinner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

public class SetkjActivity extends AppCompatActivity implements View.OnClickListener {
    private NiceSpinner niceSpinner,niceSpinnerb;
    private LinearLayout taste_a_layout, taste_b_layout;//口味设置
    private TextView taste_b_layout_text_view;
    private EditText setting_price_a, setting_price_b, setting_price_c;
    private Button ok_setting_goods_info;//确认上传
    private List<GoodsBean> goodsList = new ArrayList<>();//商品list，含有口味和id
    private List<String> tasteStringList = new ArrayList<>();//遍历出口味的数组
    private String currentShelfId = "9527e3d546ce8294";

    private String goodsId3 = "";
    private String goodsId2="";
    private String goodsId1 = "";
    private ImageView sz_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_sell);
        initview();
        getMachineGoodsInfoByShelfId(currentShelfId);
        getGoodsList();
        List<String> spinnerData = new LinkedList<>(Arrays.asList( "草莓味", "混合味",
                "原味", "巧克力味", "咖啡味"));
        niceSpinner = findViewById(R.id.nice_spinner_a);
        niceSpinnerb = findViewById(R.id.nice_spinner_b);
        niceSpinnerb.attachDataSource(spinnerData);
        niceSpinnerb.setTextColor(Color.RED);
        niceSpinnerb.setTextSize(13);
        niceSpinner.attachDataSource(spinnerData);
        // niceSpinner.setBackgroundResource(R.drawable.textview_round_border);
        niceSpinner.setTextColor(Color.RED);
        niceSpinner.setTextSize(13);
    }
    private void initview() {
        //currentShelfId = getIntent().getStringExtra("shelfId");
        taste_a_layout=findViewById(R.id.taste_a_layout);
        taste_a_layout.setOnClickListener(this);
        taste_b_layout=findViewById(R.id.taste_b_layout);
        taste_b_layout.setOnClickListener(this);
        taste_b_layout_text_view=findViewById(R.id.taste_b_layout_text_view);
        setting_price_a=findViewById(R.id.setting_price_a);
        setting_price_b=findViewById(R.id.setting_price_b);
        setting_price_c=findViewById(R.id.setting_price_c);
        ok_setting_goods_info=findViewById(R.id.ok_setting_goods_info);
        ok_setting_goods_info.setOnClickListener(this);
        sz_back=findViewById(R.id.sz_back);
        sz_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 请求商品（冰淇淋）列表信息，主要就是口味和id
     */
    private void getGoodsList() {
        RequestBody requestBody = new FormBody.Builder().add("request", "").build();
        Request request = new Request.Builder()
                .url(HTTP_BASE + InterfaceName.GOODS_LIST)
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG","调用" + InterfaceName.GOODS_LIST + "接口，链接服务器失败!!!" + e.getCause());
               // L.e("调用" + InterfaceName.GOODS_LIST + "接口，链接服务器失败!!!" + e.getCause());
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
                }
                message.obj = result;
                handler.sendMessage(message);
            }
        });
    }

    /**
     * 设置商品（冰淇淋）列表信息，主要就是口味和id
     */
    private void settingGoodsInfo() {
        double price1 = Double.parseDouble(setting_price_a.getText().toString().trim());
        double price2 = Double.parseDouble(setting_price_c.getText().toString().trim());
        double price3 = Double.parseDouble(setting_price_b.getText().toString().trim());

        goodsId1 = niceSpinner.getSelectedIndex()+1+"";
        goodsId2=taste_b_layout_text_view.getText().toString();
        goodsId3 =  niceSpinnerb.getSelectedIndex()+1+"";

        String params = EncryptUtils.aesEncryptString(RequestGoodsSettingInfoBeanToJson.
                requestGoodsSettingInfoBeanToJson(currentShelfId, goodsId1,goodsId3, price1, price2, price3));

        Log.e("ads",RequestGoodsSettingInfoBeanToJson.
                requestGoodsSettingInfoBeanToJson(currentShelfId, goodsId1,goodsId3, price1, price2, price3));

        RequestBody requestBody = new FormBody.Builder().add("request", params).build();
        Request request = new Request.Builder()
                .url(HTTP_BASE + InterfaceName.SETTING_GOODS_INFO)
                .post(requestBody)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.e("调用" + InterfaceName.SETTING_GOODS_INFO + "接口，链接服务器失败!!!" + e.getCause());
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
                }
                message.obj = result;
                handler.sendMessage(message);
            }
        });

    }
    /**
     * 根据机器id请求已经设置的数据
     *
     * @param shelfId
     */
    private void getMachineGoodsInfoByShelfId(String shelfId) {
        String params = EncryptUtils.aesEncryptString(RequestBaseBeanToJson.requestBaseBeanShelfIdToJson(shelfId));
        //String params =RequestBaseBeanToJson.requestBaseBeanShelfIdToJson(shelfId);

        RequestBody requestBody = new FormBody.Builder().add("request", params).build();
        Request request = new Request.Builder()
                .url(HTTP_BASE + InterfaceName.GET_MACHINE_GOODS_INFO)
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.e("调用" + InterfaceName.GET_MACHINE_GOODS_INFO + "接口，链接服务器失败!!!" + e.getCause());
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
                }
                message.obj = result;
                handler.sendMessage(message);
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
                Log.e("TAG",msg.what+"");
                BaseBean bean = GsonUtility.json2Bean(result, BaseBean.class);
                //返回正确数据，根据不同类型处理逻辑
                if (InterfaceName.GOODS_LIST.equals(bean.getMt())) {
                    //请求成功，刷新列表
                    ResponseGoodsListBean responseGoodsListBean = GsonUtility.json2Bean(result, ResponseGoodsListBean.class);
                    goodsList = responseGoodsListBean.getGoodsList();
                    //对goodsList进行遍历，得到地址选择器的list
                    Log.e("TAG","请求商品列表成功........."+goodsList);
                    tasteStringList = formatGoodsListToStringList(goodsList);
                } else if (InterfaceName.SETTING_GOODS_INFO.equals(bean.getMt())) {
                    //L.e("上传商品信息成功");
                    Log.e("TAG","上传商品信息成功");
                    Toast.makeText(getApplicationContext(), bean.getMsg(), Toast.LENGTH_LONG).show();
                    finish();
                } else if (InterfaceName.GET_MACHINE_GOODS_INFO.equals(bean.getMt())) {
                   // L.e("获取当前机器商品信息成功");
                    Log.e("TAG","获取当前机器商品信息成功.........");
                    ResponseMachineDetailListBean responseMachineDetailListBean = GsonUtility.json2Bean(result, ResponseMachineDetailListBean.class);
                    List<GoodsBean> shelfTasteList = responseMachineDetailListBean.getShelfTasteList();
                        for (int i = 0; i <shelfTasteList.size(); i++) {
                            String tasteType = shelfTasteList.get(i).getTaste();
                            String goodName = shelfTasteList.get(i).getGoodsName();
                            double goodPrice = shelfTasteList.get(i).getGoodsPrice();
                            String goodsId = shelfTasteList.get(i).getGoodsId();
                        switch (i) {
                            case 0://A出口
                                niceSpinner.setSelectedIndex(Integer.parseInt(shelfTasteList.get(0).getGoodsId())-1);
                                setting_price_a.setText(String.valueOf(shelfTasteList.get(0).getGoodsPrice()));
                                break;
                            case 1://中间出口
                                setting_price_c.setText(String.valueOf(goodPrice));
                                break;
                            case 2://B出口
                                niceSpinnerb.setSelectedIndex(Integer.parseInt(shelfTasteList.get(2).getGoodsId())-1);
                                setting_price_b.setText(String.valueOf(shelfTasteList.get(2).getGoodsPrice()));
                                break;
                        }
                    }
                }
            } else {
                //异常数据或者未连接等情况
               // L.e("请求失败!!!:" + result);
                Log.e("TAG","请求失败......"+result);
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        }
    };
    private List<String> formatGoodsListToStringList(List<GoodsBean> list) {
        for (int i = 0; i < list.size(); i++) {
            String goodsName = list.get(i).getGoodsName();
            tasteStringList.add(goodsName);
        }
        return tasteStringList;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.taste_a_layout:
                // singlePicker("a", tasteStringList);
                break;
            case R.id.taste_b_layout:
                // singlePicker("b", tasteStringList);
                break;
            case R.id.ok_setting_goods_info:
                settingGoodsInfo();
                break;
        }
    }
}
