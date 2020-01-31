package com.vargancys.vargancysshop.module.home.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.module.common.GoodsInfoActivity;
import com.vargancys.vargancysshop.module.home.home.data.SeckillInfo;
import com.vargancys.vargancysshop.widget.section.StatelessSection;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/28
 * version:1.0
 */
public class HomeSeckillAdapter extends StatelessSection {
    private Context mContext;
    private SeckillInfo seckillInfo;
    private SeckillItemAdapter mAdapter;
    private int seckillTime;
    private Handler mHandler;
    private Activity activity;

    public HomeSeckillAdapter(Context context, Activity activity, SeckillInfo seckillInfo){
        super(R.layout.home_seckill_head,R.layout.common_recycler);
        this.mContext = context;
        this.seckillInfo = seckillInfo;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new SeckillHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
        SeckillHeaderViewHolder mHolder = (SeckillHeaderViewHolder) holder;
        mHolder.SeckillMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"更多了!",Toast.LENGTH_LONG).show();
            }
        });

        seckillTime = Integer.valueOf(seckillInfo.getEnd_time())
                - Integer.valueOf(seckillInfo.getStart_time());

        mHandler.sendEmptyMessageDelayed(0,1000);
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new SeckillItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindItemViewHolder(holder, position);
        SeckillItemViewHolder mHolder = (SeckillItemViewHolder) holder;
        mAdapter = new SeckillItemAdapter(mContext,seckillInfo.getSeckillItems());
        mHolder.CommonRecycler.setLayoutManager(new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL,false));
        mHolder.CommonRecycler.setAdapter(mAdapter);
        mAdapter.setOnSeckillRecyclerView(new SeckillItemAdapter.OnSeckillRecyclerView() {
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

    class SeckillHeaderViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_time_seckill)
        TextView TvTimeSeckill;
        @BindView(R.id.seckill_more)
        TextView SeckillMore;

        SeckillHeaderViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    seckillTime = seckillTime - 1000;
                    SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
                    String time = format.format(new Date(seckillTime));
                    TvTimeSeckill.setText(time);
                    mHandler.removeMessages(0);
                    mHandler.sendEmptyMessageDelayed(0,1000);
                    if(seckillTime <= 0){
                        mHandler.removeCallbacksAndMessages(null);
                    }
                }
            };
        }
    }

    class SeckillItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.common_recycler)
        RecyclerView CommonRecycler;
        SeckillItemViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
