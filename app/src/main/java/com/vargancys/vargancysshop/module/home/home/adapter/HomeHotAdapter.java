package com.vargancys.vargancysshop.module.home.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.module.common.GoodsInfoActivity;
import com.vargancys.vargancysshop.module.home.home.data.HotInfo;
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
public class HomeHotAdapter extends StatelessSection {
    private Context mContext;
    private List<HotInfo> hotInfos;
    private HotItemAdapter mAdapter;
    private Activity activity;

    public HomeHotAdapter(Context context, Activity activity, List<HotInfo> hotInfos){
        super(R.layout.home_hot_head,R.layout.common_recycler);
        this.mContext = context;
        this.hotInfos = hotInfos;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HotHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
        HotHeaderViewHolder mHolder = (HotHeaderViewHolder) holder;
        mHolder.HotMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"更多了!",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new HotItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindItemViewHolder(holder, position);
        HotItemViewHolder mHolder = (HotItemViewHolder) holder;
        mAdapter = new HotItemAdapter(mContext,hotInfos);
        mHolder.CommonRecycler.setLayoutManager(new GridLayoutManager(mContext,2));
        mHolder.CommonRecycler.setAdapter(mAdapter);
        mAdapter.setOnRecommendRecyclerView(new HotItemAdapter.OnHotRecyclerView() {
            @Override
            public void onItemClick(int position) {
                GoodsInfoActivity.launch(activity,position);
            }
        });
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    class HotHeaderViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.hot_more)
        TextView HotMore;
        HotHeaderViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

    class HotItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.common_recycler)
        RecyclerView CommonRecycler;
        HotItemViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}