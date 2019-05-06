package com.example.panda.myapplication.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.panda.myapplication.R;
import com.example.panda.myapplication.fragment.DetailFragment;
import com.example.panda.myapplication.fragment.HomeFragment;
import com.example.panda.myapplication.fragment.MyFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private ArrayList<Fragment> arrayList;
    private RadioGroup rg;
    private RadioButton[] radioButtons;
    private FragmentManager manager;
    private int count=0;
    private TextView title_tv;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // fullScreen(this);
        initView();
        arrayList = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        DetailFragment detailFragment = new DetailFragment();
        MyFragment myFragment=new MyFragment();
        arrayList.add(homeFragment);
        arrayList.add(detailFragment);
        arrayList.add(myFragment);
        radioButtons=new RadioButton[rg.getChildCount()];
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i]= (RadioButton) rg.getChildAt(i);
        }
        manager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=manager.beginTransaction();
        fragmentTransaction.add(R.id.main_layout,arrayList.get(0));
        fragmentTransaction.commit();
        radioButtons[0].setChecked(true);
        rg.setOnCheckedChangeListener(this);
    }

    private void initView() {
        rg =findViewById(R.id.rg);
        title_tv=findViewById(R.id.title_tv);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction=manager.beginTransaction();
        for (int i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].getId()==checkedId){
                if (arrayList.get(i).isAdded()){
                    transaction.show(arrayList.get(i)).hide(arrayList.get(count)).commit();
                }else {
                    transaction.add(R.id.main_layout,arrayList.get(i)).hide(arrayList.get(count)).commit();
                }
                count=i;
                if (count==0){
                    title_tv.setText("首页");
                }else if (count==1){
                    title_tv.setText("明细");
                }else if (count==2){
                    title_tv.setText("我的");
                }
            }
        }
    }
    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity
     */
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//              window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//              attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

}
