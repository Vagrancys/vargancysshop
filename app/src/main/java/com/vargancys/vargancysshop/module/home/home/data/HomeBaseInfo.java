package com.vargancys.vargancysshop.module.home.home.data;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/27
 * version:1.0
 */
public class HomeBaseInfo {
    private int state;
    private String error_message;
    private List<BannerInfo> bannerLists;
    private List<ChannelInfo> channelLists;
    private List<ActivityInfo> activityInfos;
    private SeckillInfo seckillInfo;
    private List<RecommendInfo> recommendInfos;

    public List<RecommendInfo> getRecommendInfos() {
        return recommendInfos;
    }

    public void setRecommendInfos(List<RecommendInfo> recommendInfos) {
        this.recommendInfos = recommendInfos;
    }

    public SeckillInfo getSeckillInfo() {
        return seckillInfo;
    }

    public void setSeckillInfo(SeckillInfo seckillInfo) {
        this.seckillInfo = seckillInfo;
    }

    public List<ActivityInfo> getActivityInfos() {
        return activityInfos;
    }

    public void setActivityInfos(List<ActivityInfo> activityInfos) {
        this.activityInfos = activityInfos;
    }

    public List<ChannelInfo> getChannelLists() {
        return channelLists;
    }

    public void setChannelLists(List<ChannelInfo> channelLists) {
        this.channelLists = channelLists;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<BannerInfo> getBannerLists() {
        return bannerLists;
    }

    public void setBannerLists(List<BannerInfo> bannerLists) {
        this.bannerLists = bannerLists;
    }
}
