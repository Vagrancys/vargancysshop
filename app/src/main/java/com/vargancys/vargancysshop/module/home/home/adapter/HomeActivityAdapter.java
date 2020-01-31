package com.vargancys.vargancysshop.module.home.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.module.home.home.data.ActivityInfo;
import com.vargancys.vargancysshop.widget.section.StatelessSection;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/28
 * version:1.0
 */
public class HomeActivityAdapter extends StatelessSection {
    private Context mContext;
    private List<ActivityInfo> activityInfos;
    public HomeActivityAdapter(Context context, List<ActivityInfo> activityInfos){
        super(R.layout.home_activity_item);
        mContext = context;
        this.activityInfos = activityInfos;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindItemViewHolder(holder, position);
        ActivityViewHolder mHolder = (ActivityViewHolder) holder;
        mHolder.activityViewPager.setPageMargin(20);
        mHolder.activityViewPager.setAdapter(new PagerAdapter() {
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, final int position) {
                ImageView imageView = new ImageView(mContext);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(mContext)
                        .load(activityInfos.get(position).getUrl())
                        .into(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext,position,Toast.LENGTH_LONG).show();
                    }
                });
                container.addView(imageView);
                return imageView;
            }

            @Override
            public int getCount() {
                return activityInfos.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });

        mHolder.activityViewPager.setOffscreenPageLimit(3);
        mHolder.activityViewPager.setPageTransformer(true,new RotateDownPageTransformer());
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    class ActivityViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.activity_viewPager)
        ViewPager activityViewPager;
        ActivityViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
