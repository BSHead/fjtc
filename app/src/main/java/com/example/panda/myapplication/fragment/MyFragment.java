package com.example.panda.myapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.panda.myapplication.R;
import com.example.panda.myapplication.activity.NoticeActivity;
import com.example.panda.myapplication.activity.RegisterActivity;
import com.example.panda.myapplication.utils.SharedUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements View.OnClickListener {

    private View view;
    private TextView textName;
    private Button butOut;
    private LinearLayout linNotice;

    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container,false);
        //初始化控件
        initVIew();
        //设置用户名
        setName();
        return view;
    }

    private void setName() {
        String name = SharedUtils.getName(getActivity());
        textName.setText("用户名:"+name);
    }

    private void initVIew() {
        textName = view.findViewById(R.id.my_name);
        butOut = view.findViewById(R.id.my_outbut);
        butOut.setOnClickListener(this);
        linNotice = view.findViewById(R.id.my_notice);
        linNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_outbut:
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Code",Context.MODE_PRIVATE);
                SharedPreferences.Editor clear = sharedPreferences.edit().clear();
                clear.commit();
                startActivity(new Intent(getActivity(),RegisterActivity.class));
                getActivity().finish();
                break;
            case R.id.my_notice:
                startActivity(new Intent(getActivity(),NoticeActivity.class));
                break;
        }
    }
}
