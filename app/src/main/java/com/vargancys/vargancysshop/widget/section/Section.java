package com.vargancys.vargancysshop.widget.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/27
 * version:1.0
 */
public abstract class Section {

    public enum State{
        LOADED
    }

    private State state = State.LOADED;
    boolean visible = true;
    boolean hasHeader = false;
    boolean hasFooter = false;
    Integer headerResourceId;
    Integer footerResourceId;
    int itemResourceId;
    private Integer loadingResourceId;
    private Integer failedResourceId;

    Section(){};

    public Section(int itemResourceId,int loadingResourceId,int failedResourceId){
        this.itemResourceId = itemResourceId;
        this.loadingResourceId = loadingResourceId;
        this.failedResourceId = failedResourceId;
    }

    public Section(int headerResourceId,int itemResourceId,int loadingResourceId,int failedResourceId){
        this(itemResourceId,loadingResourceId,failedResourceId);
        this.headerResourceId = headerResourceId;
        hasHeader = true;
    }

    public Section(int headerResourceId,int itemResourceId,int footerResourceId,int loadingResourceId,int failedResourceId){
        this(headerResourceId,itemResourceId,loadingResourceId,failedResourceId);
        this.footerResourceId = footerResourceId;
        hasFooter = true;
    }


    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isHasHeader() {
        return hasHeader;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public boolean isHasFooter() {
        return hasFooter;
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    public Integer getHeaderResourceId() {
        return headerResourceId;
    }

    public Integer getFooterResourceId() {
        return footerResourceId;
    }

    public int getItemResourceId() {
        return itemResourceId;
    }

    public Integer getFailedResourceId() {
        return failedResourceId;
    }

    public Integer getLoadingResourceId() {
        return loadingResourceId;
    }

    public final void onBindContentViewHolder(RecyclerView.ViewHolder holder,int position){
        switch (state){
            case LOADED:
                onBindItemViewHolder(holder,position);
                break;
            default:
                throw new IllegalStateException("Invalid state");
        }
    }

    public final  int getSectionItemTotal(){
        int contentItemTotal;
        switch (state){
            case LOADED:
                contentItemTotal = getContentItemsTotal();
                break;
            default:
                throw new IllegalStateException("Invalid state");
        }
        return contentItemTotal+(hasHeader?1:0)+(hasFooter?1:0);
    }

    public abstract int getContentItemsTotal();

    public RecyclerView.ViewHolder getHeaderViewHolder(View view){
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder){

    }

    public RecyclerView.ViewHolder getFooterViewHolder(View view){
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder){

    }

    public abstract RecyclerView.ViewHolder getItemViewHolder(View view);

    public void onBindItemViewHolder(RecyclerView.ViewHolder holder,int position){}

}

