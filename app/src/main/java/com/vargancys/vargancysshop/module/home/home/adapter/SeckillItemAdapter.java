package com.vargancys.vargancysshop.module.home.home.adapter;

import android.content.Context;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.module.home.home.data.SeckillInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/28
 * version:1.0
 */
public class SeckillItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<SeckillInfo.SeckillItem> seckillInfos;

    public SeckillItemAdapter(Context context, List<SeckillInfo.SeckillItem> seckillInfos){
        this.mContext = context;
        this.seckillInfos = seckillInfos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SeckillItemViewHolder(View.inflate(mContext, R.layout.home_seckill_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        SeckillItemViewHolder mHolder = (SeckillItemViewHolder) holder;
        Glide.with(mContext).load(seckillInfos.get(i).getUrl())
                .placeholder(R.drawable.welcome_background_1)
                .into(mHolder.ItemImage);
        mHolder.ItemTitle.setText(seckillInfos.get(i).getTitle());
        mHolder.ItemTitleOld.setText(seckillInfos.get(i).getTitle_old());
    }

    class SeckillItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_image)
        ImageView ItemImage;
        @BindView(R.id.item_title)
        TextView ItemTitle;
        @BindView(R.id.item_title_old)
        TextView ItemTitleOld;
        SeckillItemViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onSeckillRecyclerView !=null){
                        onSeckillRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return seckillInfos.size();
    }

    public interface OnSeckillRecyclerView{
        void onItemClick(int position);
    }

    private OnSeckillRecyclerView onSeckillRecyclerView;

    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView) {
        this.onSeckillRecyclerView = onSeckillRecyclerView;
    }
}
