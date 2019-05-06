package com.example.panda.myapplication.clean_activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.panda.myapplication.R;
import com.example.panda.myapplication.operation_activity.OperationPageActivity;
import com.example.panda.myapplication.utils.SharedUtils;

public class CleanActivityThree extends AppCompatActivity implements View.OnClickListener {
    private TextView title_tv,pop_text_two;
    private ImageView clean_back_img_three,three_wc,set_pop_next,yes_btn,no_btn,sl;
    private CheckBox clean_three_a,clean_three_b,clean_three_c;
    private PopupWindow popupWindow;
    private Context context;
    private TextView pop_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_three);
        context=this;
        initview();
        title_tv.setText("清洁");
        clean_back_img_three.setOnClickListener(this);
        clean_three_a.setOnClickListener(this);
        clean_three_b.setOnClickListener(this);
        clean_three_c.setOnClickListener(this);
        three_wc.setOnClickListener(this);
    }

    private void initview() {
        title_tv=findViewById(R.id.title_tv);
        clean_back_img_three=findViewById(R.id.clean_back_img_three);
        clean_three_a=findViewById(R.id.clean_three_a);
        clean_three_b=findViewById(R.id.clean_three_b);
        clean_three_c=findViewById(R.id.clean_three_c);
        three_wc=findViewById(R.id.three_wc);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clean_back_img_three:
                finish();
                break;
            case R.id.clean_three_a:
                if (clean_three_a.isChecked()){
                    Toast.makeText(this, "a口开", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "a口关", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.clean_three_b:
                if (clean_three_b.isChecked()){
                    Toast.makeText(this, "b口开", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "b口关", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.clean_three_c:
                if (clean_three_c.isChecked()){
                    Toast.makeText(this, "c口开", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "c口关", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.three_wc:
                showpopwindow();
                break;
        }
    }

    private void showpopwindow() {
        // TODO Auto-generated method stub
        View view=LayoutInflater.from(this).inflate(R.layout.set_pop, null);
        // popupWindow = new PopupWindow(view, 300, 200);
        popupWindow = new PopupWindow(view,  ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        // 需要设置一下此参数，点击外边可消失
        //popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popupWindow.setOutsideTouchable(false);
        // 设置此参数获得焦点，否则无法点击
        popupWindow.setFocusable(false);
        if(popupWindow.isShowing()) {
            // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
            popupWindow.dismiss();
        } else {
            // 显示窗口
            // popupWindow.isShowing();
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            darkenBackground(0.2f);
        }
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground(1f);
            }
        });
        pop_text=view.findViewById(R.id.pop_text);
        set_pop_next=view.findViewById(R.id.set_pop_next);
        if(SharedUtils.getclean(context).equals("")){
            pop_text.setText("为确保食品安全，请至少清洗三轮以上，点击“下一步”进行第二轮清洗");
        }
        if(SharedUtils.getclean(context).equals("2")){
           three();
        }
        if(SharedUtils.getclean(context).equals("3")){
            showpopwindow_two();
        }
        if(SharedUtils.getclean(context).equals("4")){
            showpopwindow_two();
        }

        set_pop_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedUtils.setclean(context,"1");
                if(SharedUtils.getclean(context).equals("1")){
                   // pop_text.setText("为确保食品安全，请至少清洗三轮以上，点击“下一步”进行第三轮清洗");
                    Intent intent=new Intent(getApplicationContext(),CleanActivity.class);
                    startActivity(intent);
                    SharedUtils.setclean(context,"");
                    SharedUtils.setclean(context,"2");
                    finish();
                }

//                if (CleanActivity.cleanActivity!=null){
//                    CleanActivity.cleanActivity.finish();
//                }
//                if (CleanActivityTwo.cleanActivityTwo!=null){
//                    CleanActivityTwo.cleanActivityTwo.finish();
//                }
            }
        });
    }

    private void three() {
        // TODO Auto-generated method stub
        View view=LayoutInflater.from(this).inflate(R.layout.ste_pop_three, null);
        // popupWindow = new PopupWindow(view, 300, 200);
        popupWindow = new PopupWindow(view,  ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        // 需要设置一下此参数，点击外边可消失
        // popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popupWindow.setOutsideTouchable(false);
        // 设置此参数获得焦点，否则无法点击
        popupWindow.setFocusable(false);
        if(popupWindow.isShowing()) {
            // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
            popupWindow.dismiss();
        } else {
            // 显示窗口
            // popupWindow.isShowing();
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            darkenBackground(0.2f);
        }
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground(1f);
            }
        });
        sl=view.findViewById(R.id.sl);
        sl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedUtils.setclean(context,"");
                SharedUtils.setclean(context,"3");
                Intent intent = new Intent(getApplicationContext(), CleanActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showpopwindow_two() {
        // TODO Auto-generated method stub
        View view=LayoutInflater.from(this).inflate(R.layout.set_pop_two, null);
        // popupWindow = new PopupWindow(view, 300, 200);
        popupWindow = new PopupWindow(view,  ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        // 需要设置一下此参数，点击外边可消失
       // popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popupWindow.setOutsideTouchable(false);
        // 设置此参数获得焦点，否则无法点击
        popupWindow.setFocusable(false);
        if(popupWindow.isShowing()) {
            // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
            popupWindow.dismiss();
        } else {
            // 显示窗口
            // popupWindow.isShowing();
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            darkenBackground(0.2f);
        }
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground(1f);
            }
        });
        no_btn=view.findViewById(R.id.no_btn);
        yes_btn=view.findViewById(R.id.yes_btn);
        pop_text_two=view.findViewById(R.id.pop_text_two);
        pop_text_two.setText("清洗完成，是否再次清洗");
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedUtils.setclean(context,"");
                if (CleanActivity.cleanActivity!=null){
                    CleanActivity.cleanActivity.finish();
                }
                if (CleanActivityTwo.cleanActivityTwo!=null){
                    CleanActivityTwo.cleanActivityTwo.finish();
                }
                finish();
            }
        });

        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedUtils.setclean(context,"");
                SharedUtils.setclean(context,"4");
                Intent intent = new Intent(getApplicationContext(), CleanActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 改变背景颜色
     */
    private void darkenBackground(Float bgcolor){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
