package com.example.panda.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.panda.myapplication.R;
import com.example.panda.myapplication.bean.MachineBean;
import java.util.List;

/**
 * Created by zzl_w on 2018/7/16.
 */


public class MachineListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<MachineBean> list;
    private Context context;

    public MachineListAdapter(List<MachineBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void onDataChanged(List<MachineBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (null != list) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_machine_list, null);
            holder.machine_image_view = view.findViewById(R.id.machine_image_view);
            holder.shelf_id_view = view.findViewById(R.id.shelf_id_view);
            holder.address_view = view.findViewById(R.id.address_view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Glide.with(context).load(list.get(i).getShelfImage()).into(holder.machine_image_view);
        //ImageLoader.getInstance().displayImage(list.get(i).getShelfImage(), holder.machine_image_view);
        holder.shelf_id_view.setText("ID  " + list.get(i).getShelfId());
        holder.address_view.setText("地址  " + list.get(i).getAddress());

        return view;
    }

    class ViewHolder {
        ImageView machine_image_view;//货柜图片
        TextView shelf_id_view;//
        TextView address_view;//
    }

}
