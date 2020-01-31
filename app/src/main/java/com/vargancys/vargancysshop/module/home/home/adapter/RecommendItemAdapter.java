package com.vargancys.vargancysshop.module.home.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.module.home.home.data.RecommendInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/28
 * version:1.0
 */
public class RecommendItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<RecommendInfo> recommendInfos;

    public RecommendItemAdapter(Context context, List<RecommendInfo> recommendInfos){
        this.mContext = context;
        this.recommendInfos = recommendInfos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecommendItemViewHolder(View.inflate(mContext, R.layout.home_recommend_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        RecommendItemViewHolder mHolder = (RecommendItemViewHolder) holder;
        Glide.with(mContext).load(recommendInfos.get(i).getUrl())
                .placeholder(R.drawable.welcome_background_1)
                .into(mHolder.ItemImage);
        mHolder.ItemTitle.setText(recommendInfos.get(i).getTitle());
        mHolder.ItemMoney.setText(recommendInfos.get(i).getMoney());
    }

    class RecommendItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_image)
        ImageView ItemImage;
        @BindView(R.id.item_title)
        TextView ItemTitle;
        @BindView(R.id.item_money)
        TextView ItemMoney;
        RecommendItemViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onRecommendRecyclerView !=null){
                        onRecommendRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return recommendInfos.size();
    }

    public interface OnRecommendRecyclerView{
        void onItemClick(int position);
    }

    private OnRecommendRecyclerView onRecommendRecyclerView;

    public void setOnRecommendRecyclerView(OnRecommendRecyclerView onRecommendRecyclerView) {
        this.onRecommendRecyclerView = onRecommendRecyclerView;
    }
}
