package com.example.panda.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.panda.myapplication.R;
import com.example.panda.myapplication.bean.NoticeBean;
import com.example.panda.myapplication.bean.NoticeListBean;
import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends BaseAdapter {
    private List<NoticeListBean.PushDataBean> mlist = new ArrayList<>();
    private Context context;

    public NoticeAdapter(Context context) {
        this.context = context;
    }

    public  void setList(List<NoticeListBean.PushDataBean> beanList){
        mlist.addAll(beanList);
    }

    @Override
    public int getCount() {
        Log.e("ooo",mlist.size()+"");
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context,R.layout.item_notice,null);
            holder.content = convertView.findViewById(R.id.notice_content);
            holder.shelid = convertView.findViewById(R.id.notice_shelid);
            holder.createTime = convertView.findViewById(R.id.notice_createTime);
            holder.state = convertView.findViewById(R.id.notice_state);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();


        }
        holder.state.setText("未读");
        holder.state.setTextColor(Color.BLACK);
        holder.content.setTextColor(Color.BLACK);
        holder.shelid.setTextColor(Color.BLACK);
        holder.createTime.setTextColor(Color.BLACK);
        if (mlist.get(position).getState()!=null){
            if (mlist.get(position).getState().equals("0")){
                holder.state.setText("未读");
                holder.state.setTextColor(Color.BLACK);
                holder.content.setTextColor(Color.BLACK);
                holder.shelid.setTextColor(Color.BLACK);
                holder.createTime.setTextColor(Color.BLACK);
            }else {
                holder.state.setText("已读");
                holder.state.setTextColor(Color.GRAY);
                holder.content.setTextColor(Color.GRAY);
                holder.shelid.setTextColor(Color.GRAY);
                holder.createTime.setTextColor(Color.GRAY);
            }
        }

        holder.content.setText(mlist.get(position).getContent());
        holder.shelid.setText(mlist.get(position).getShelfid()+"");
        String s = mlist.get(position).getCreatetime();
        holder.createTime.setText(s.substring(0,4)+"年"+s.substring(4,6)+"月"+s.substring(6,8)+"日"+s.substring(8,10)+"点"+s.substring(10,12)+"分");

        return convertView;
    }
    class ViewHolder {
        TextView  shelid;//货柜图片
        TextView content;//
        TextView createTime;//
        TextView state;
    }

}
