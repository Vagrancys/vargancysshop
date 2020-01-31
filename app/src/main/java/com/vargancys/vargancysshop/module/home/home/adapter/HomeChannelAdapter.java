package com.vargancys.vargancysshop.module.home.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.module.home.home.data.ChannelInfo;
import com.vargancys.vargancysshop.widget.section.StatelessSection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/28
 * version:1.0
 */
public class HomeChannelAdapter extends StatelessSection {
    private Context mContext;
    private List<ChannelInfo> channelLists;
    private ChannelAdapter channelAdapter;
    public HomeChannelAdapter(Context context, List<ChannelInfo> channelLists){
        super(R.layout.home_channel_item);
        this.channelLists = channelLists;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ChannelViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindItemViewHolder(holder, position);
        ChannelViewHolder mHolder = (ChannelViewHolder) holder;
        channelAdapter = new ChannelAdapter(mContext,channelLists);
        mHolder.channelGrid.setAdapter(channelAdapter);
        mHolder.channelGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,position,Toast.LENGTH_LONG).show();
            }
        });
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.channel_grid)
        GridView channelGrid;
        ChannelViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }


}
