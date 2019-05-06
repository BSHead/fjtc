package com.example.panda.myapplication.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.panda.myapplication.R;
import com.example.panda.myapplication.activity.NoticeActivity;
import com.example.panda.myapplication.bean.BaseBean;
import com.example.panda.myapplication.bean.NoticeListBean;
import com.example.panda.myapplication.bean.Shelf;
import com.example.panda.myapplication.location.GetKeyStor;
import com.example.panda.myapplication.utils.DataCall;
import com.example.panda.myapplication.utils.EncryptUtils;
import com.example.panda.myapplication.utils.HttpUtils;
import com.example.panda.myapplication.utils.RequestLoginBeanToJson;
import com.example.panda.myapplication.utils.SharedUtils;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

import static com.example.panda.myapplication.port.InterfaceName.LL;
import static com.example.panda.myapplication.port.InterfaceName.Tong;
import static com.example.panda.myapplication.utils.HttpTools.HTTP_BASE;
import static com.example.panda.myapplication.utils.InfoCode.SERVER_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private View view;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    LocationClient mLocationClient;
    LocationClientOption option;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what==1){
                String s = (String) msg.obj;
                Log.e("123",s);
                Gson gson = new Gson();
                Shelf shelf = gson.fromJson(s, Shelf.class);
                List<Shelf.ShelfListBean> list = shelf.getShelfList();
                for (int i = 0; i <list.size() ; i++) {
                    LatLng point1 = new LatLng(list.get(i).getLatitude(), list.get(i).getLongitude());
                    BitmapDescriptor bitmap1 = BitmapDescriptorFactory
                            .fromResource(R.mipmap.ls);
                    //构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions option1 = new MarkerOptions()
                            .position(point1)
                            .icon(bitmap1);
                    //在地图上添加Marker，并显示
                    mBaiduMap.addOverlay(option1);
                }
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SDKInitializer.initialize(getActivity().getApplicationContext());
        SDKInitializer.setCoordType(CoordType.BD09LL);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        GetKeyStor.newInsance().getSign(getActivity());
        //获取权限
        getPermission();
        //初始化
        initView();
        //定位
        getLocation();
        String name = SharedUtils.getName(getActivity());
        String params =EncryptUtils.aesEncryptString(RequestLoginBeanToJson.requestllBeanToJson(name));
        RequestBody requestBody = new FormBody.Builder().add("request", params).build();
        //  Log.e("111",RequestLoginBeanToJson.requesttongBeanToJson("999"));
        Log.e("111",params.toString());
        HttpUtils httpUtils = new HttpUtils(new ll());
        httpUtils.request(HTTP_BASE,requestBody,LL );
        return view;
    }

    private void getPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                        Manifest.permission.REQUEST_INSTALL_PACKAGES}, 0);
    }

    private void initView() {
        mMapView=view.findViewById(R.id.mMapView);
        mBaiduMap=mMapView.getMap();
    }
    public void getLocation() {
        //声明LocationClient类
        mLocationClient = new LocationClient(getActivity().getApplicationContext());
        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                //获取纬度信息
                double latitude = bdLocation.getLatitude();//获取经度信息
                double longitude = bdLocation.getLongitude();
                String addr = bdLocation.getAddrStr();    //获取详细地址信息
                String country = bdLocation.getCountry();    //获取国家
                String province = bdLocation.getProvince();    //获取省份
                String city = bdLocation.getCity();    //获取城市
                String district = bdLocation.getDistrict();    //获取区县
                String street = bdLocation.getStreet();    //获取街道信息
                Log.e("TAG",latitude+"");
                Log.e("百度地图纬度", latitude + "");
                Log.e("百度地图经度", longitude + "");
                Log.e("百度地图地址", "省：" + province + "\n市：" + city + "\n县：" + district + "\n街：" + street);
                Toast.makeText(getContext(), "当前位置"+addr, Toast.LENGTH_SHORT).show();
                //定义Maker坐标点
                LatLng point = new LatLng(latitude, longitude);
                //构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.current);
                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .position(point)
                        .icon(bitmap);
                //在地图上添加Marker，并显示
                mBaiduMap.addOverlay(option);
                //动画
                LatLng ll = new LatLng(bdLocation.getLatitude(),
                        bdLocation.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                //机器的坐标
                //定义Maker坐标点
            }
        });
        option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
        option.setCoorType("bd09ll");
        option.setOpenGps(true);
        option.setScanSpan(0);
        option.setLocationNotify(false);
        option.setIgnoreKillProcess(true);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        option.setEnableSimulateGps(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }
    //权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    //判断是否勾选禁止后不再询问
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissions[i]);
                    if (showRequestPermission) {

                    }
                }
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private class ll implements DataCall {
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

