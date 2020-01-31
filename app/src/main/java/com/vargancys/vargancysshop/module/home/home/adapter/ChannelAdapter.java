package com.vargancys.vargancysshop.module.home.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.module.home.home.data.ChannelInfo;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/28
 * version:1.0
 */
public class ChannelAdapter extends BaseAdapter {
    private Context mContext;
    private List<ChannelInfo> channelInfos;
    public ChannelAdapter(Context context, List<ChannelInfo> channelInfo) {
        this.mContext = context;
        this.channelInfos = channelInfo;
    }

    @Override
    public int getCount() {
        return channelInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_channel,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = convertView.findViewById(R.id.item_image);
            viewHolder.iv_title = convertView.findViewById(R.id.item_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ChannelInfo channelInfo = channelInfos.get(position);
        Glide.with(mContext).load(channelInfo.getUrl()).into(viewHolder.iv_icon);
        viewHolder.iv_title.setText(channelInfo.getTitle());
        return convertView;
    }

    static class ViewHolder{
        ImageView iv_icon;
        TextView iv_title;
    }
}
