package com.vargancys.vargancysshop.module.home.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.module.common.GoodsInfoActivity;
import com.vargancys.vargancysshop.module.home.home.data.BannerInfo;
import com.vargancys.vargancysshop.widget.section.StatelessSection;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/28
 * version:1.0
 */
public class HomeBannerAdapter extends StatelessSection {
    private List<BannerInfo> bannerLists;
    private Context mContext;
    private Activity activity;

    public HomeBannerAdapter(Context context, Activity activity, List<BannerInfo> bannerLists){
        super(R.layout.home_banner_item);
        this.bannerLists = bannerLists;
        this.mContext = context;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new HomeBannerItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindItemViewHolder(holder, position);
        final HomeBannerItemViewHolder mHolder = (HomeBannerItemViewHolder) holder;
        mHolder.homeBanner.setImageLoader(new GlideImageLoader());
        mHolder.homeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        mHolder.homeBanner.setIndicatorGravity(BannerConfig.CENTER);
        mHolder.homeBanner.setImages(bannerLists);
        mHolder.homeBanner.start();
        mHolder.homeBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                GoodsInfoActivity.launch(activity,position);
            }
        });
    }

    class HomeBannerItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.home_banner)
        Banner homeBanner;
        HomeBannerItemViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }
}
