package com.vargancys.vargancysshop.module.home.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.module.common.GoodsInfoActivity;
import com.vargancys.vargancysshop.module.home.home.data.RecommendInfo;
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
public class HomeRecommendAdapter extends StatelessSection {
    private Context mContext;
    private List<RecommendInfo> recommendInfos;
    private RecommendItemAdapter mAdapter;
    private Activity activity;

    public HomeRecommendAdapter(Context context,Activity activity, List<RecommendInfo> recommendInfos){
        super(R.layout.home_recommend_head,R.layout.common_recycler);
        this.mContext = context;
        this.recommendInfos = recommendInfos;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new RecommendHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
        RecommendHeaderViewHolder mHolder = (RecommendHeaderViewHolder) holder;
        mHolder.RecommendMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"更多了!",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new RecommendItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindItemViewHolder(holder, position);
        RecommendItemViewHolder mHolder = (RecommendItemViewHolder) holder;
        mAdapter = new RecommendItemAdapter(mContext,recommendInfos);
        mHolder.CommonRecycler.setLayoutManager(new GridLayoutManager(mContext,3));
        mHolder.CommonRecycler.setAdapter(mAdapter);
        mAdapter.setOnRecommendRecyclerView(new RecommendItemAdapter.OnRecommendRecyclerView() {
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

    class RecommendHeaderViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.recommend_more)
        TextView RecommendMore;
        RecommendHeaderViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

    class RecommendItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.common_recycler)
        RecyclerView CommonRecycler;
        RecommendItemViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
