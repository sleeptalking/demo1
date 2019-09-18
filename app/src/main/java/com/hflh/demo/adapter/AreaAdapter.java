package com.hflh.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hflh.demo.R;
import com.hflh.demo.bean.Area;

import java.util.List;

public class AreaAdapter extends BaseAdapter {

    private List<Area> shopCategoryList;
    private Context context;
    private LayoutInflater inflater;

    public AreaAdapter(List<Area> shopCategoryList, Context context) {
        this.shopCategoryList = shopCategoryList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return shopCategoryList.size();
    }

    @Override
    public Area getItem(int i) {
        return shopCategoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if(view==null){
            view = inflater.inflate(R.layout.item_simple_list, viewGroup,false);
            vh = new ViewHolder();
            vh.tvName = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }

        Area contact = getItem(i);
        vh.tvName.setText(contact.getAreaName());
        return view;

    }

    private class ViewHolder{
        TextView tvName;
    }
}
