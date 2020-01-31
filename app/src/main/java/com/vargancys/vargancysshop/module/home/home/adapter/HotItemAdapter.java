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
import com.vargancys.vargancysshop.module.home.home.data.HotInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/28
 * version:1.0
 */
public class HotItemAdapter  extends RecyclerView.Adapter {
    private Context mContext;
    private List<HotInfo> hotInfos;

    public HotItemAdapter(Context context, List<HotInfo> hotInfos){
        this.mContext = context;
        this.hotInfos = hotInfos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HotItemViewHolder(View.inflate(mContext, R.layout.home_hot_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        HotItemViewHolder mHolder = (HotItemViewHolder) holder;
        Glide.with(mContext).load(hotInfos.get(i).getUrl())
                .placeholder(R.drawable.welcome_background_1)
                .into(mHolder.ItemImage);
        mHolder.ItemTitle.setText(hotInfos.get(i).getTitle());
        mHolder.ItemMoney.setText(hotInfos.get(i).getMoney());
    }

    class HotItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_image)
        ImageView ItemImage;
        @BindView(R.id.item_title)
        TextView ItemTitle;
        @BindView(R.id.item_money)
        TextView ItemMoney;
        HotItemViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onHotRecyclerView !=null){
                        onHotRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return hotInfos.size();
    }

    public interface OnHotRecyclerView{
        void onItemClick(int position);
    }

    private OnHotRecyclerView onHotRecyclerView;

    public void setOnRecommendRecyclerView(OnHotRecyclerView onHotRecyclerView) {
        this.onHotRecyclerView = onHotRecyclerView;
    }
}
