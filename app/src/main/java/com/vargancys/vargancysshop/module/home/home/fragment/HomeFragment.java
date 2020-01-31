package com.vargancys.vargancysshop.module.home.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.base.BaseFragment;
import com.vargancys.vargancysshop.module.home.home.adapter.HomeActivityAdapter;
import com.vargancys.vargancysshop.module.home.home.adapter.HomeBannerAdapter;
import com.vargancys.vargancysshop.module.home.home.adapter.HomeChannelAdapter;
import com.vargancys.vargancysshop.module.home.home.adapter.HomeHotAdapter;
import com.vargancys.vargancysshop.module.home.home.adapter.HomeRecommendAdapter;
import com.vargancys.vargancysshop.module.home.home.adapter.HomeSeckillAdapter;
import com.vargancys.vargancysshop.module.home.home.data.ActivityInfo;
import com.vargancys.vargancysshop.module.home.home.data.BannerInfo;
import com.vargancys.vargancysshop.module.home.home.data.ChannelInfo;
import com.vargancys.vargancysshop.module.home.home.data.HomeBaseInfo;
import com.vargancys.vargancysshop.module.home.home.data.HotInfo;
import com.vargancys.vargancysshop.module.home.home.data.RecommendInfo;
import com.vargancys.vargancysshop.module.home.home.data.SeckillInfo;
import com.vargancys.vargancysshop.utils.Https;
import com.vargancys.vargancysshop.widget.section.SectionedRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/25
 * version:1.0
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.home_bar_search)
    EditText homeBarSearch;
    @BindView(R.id.home_bar_code)
    LinearLayout homeBarCode;
    @BindView(R.id.home_content)
    RecyclerView homeContent;
    @BindView(R.id.home_top)
    ImageButton homeTop;

    private List<BannerInfo> bannerLists = new ArrayList<>();
    private List<ChannelInfo> channelLists = new ArrayList<>();
    private List<ActivityInfo> activityInfos = new ArrayList<>();
    private SeckillInfo seckillInfo;
    private List<RecommendInfo> recommendInfos = new ArrayList<>();
    private List<HotInfo> hotInfos = new ArrayList<>();
    private SectionedRecyclerViewAdapter mSection;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void finishCreateView(Bundle save) {
        mSection = new SectionedRecyclerViewAdapter();

        homeTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeContent.scrollToPosition(0);
            }
        });

        Https.getHomeAPI().getHomeBaseInfo().
                enqueue(new Callback<HomeBaseInfo>() {
            @Override
            public void onResponse(Call<HomeBaseInfo> call, Response<HomeBaseInfo> response) {
                if(response.body().getState()==200){
                    bannerLists = response.body().getBannerLists();
                    channelLists = response.body().getChannelLists();
                    activityInfos = response.body().getActivityInfos();
                    seckillInfo = response.body().getSeckillInfo();
                    initData();
                }
            }

            @Override
            public void onFailure(Call<HomeBaseInfo> call, Throwable t) {
                Toast.makeText(getContext(),"失败!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void initData(){
        mSection.addSection(new HomeBannerAdapter(getContext(),getActivity(),bannerLists));
        mSection.addSection(new HomeChannelAdapter(getContext(),channelLists));
        mSection.addSection(new HomeActivityAdapter(getContext(),activityInfos));
        mSection.addSection(new HomeSeckillAdapter(getContext(),getActivity(),seckillInfo));
        mSection.addSection(new HomeRecommendAdapter(getContext(),getActivity(),recommendInfos));
        mSection.addSection(new HomeHotAdapter(getContext(),getActivity(),hotInfos));
        GridLayoutManager gridLayout = new GridLayoutManager(getContext(),1);
        gridLayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if(i <= 3){
                    homeTop.setVisibility(View.GONE);
                }else{
                    homeTop.setVisibility(View.VISIBLE);
                }
                return 1;
            }
        });
        homeContent.setLayoutManager(gridLayout);
        homeContent.setAdapter(mSection);
    }
}
