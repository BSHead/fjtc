package com.example.panda.myapplication.utils;

import android.util.Log;
import com.zhy.http.okhttp.utils.L;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
    DataCall stringDataCall;

    public HttpUtils(DataCall stringDataCall) {
        this.stringDataCall = stringDataCall;
    }

    public void request(String url, RequestBody requestBody,String uri){

            final Request request = new Request.Builder()
                    .url(url + uri)
                    .post(requestBody)
                    .build();
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    L.e("调用app/login.do接口，链接服务器失败!!!" + e.getCause());
                    if (e.getCause().toString().equals(null)){
                        stringDataCall.fail(e.getCause().toString());
                    }

                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.e("TAG","好了"+response);
                    String result;
                    if (InfoCode.SERVER_OK == response.code()) {
                        result = EncryptUtils.aesDecryptString(response.body().string());
                        Log.e("TAG","la"+response.body());
                    } else {
                        result = response.message() + "--" + response.code();
                    }
                    stringDataCall.success(result);
                }
            });
      }
}
